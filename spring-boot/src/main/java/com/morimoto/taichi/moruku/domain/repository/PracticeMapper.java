package com.morimoto.taichi.moruku.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.morimoto.taichi.moruku.domain.entity.Practice;

@Mapper
public interface PracticeMapper {
    @Select("""
        SELECT 
            uuid, title, description, max_participants, prefecture_id, organizer_id, held_on, created_at 
        FROM 
            practices
        ORDER BY 
            created_at DESC
        LIMIT 
            #{limit}
        OFFSET 
            #{offset}
    """)
    List<Practice> findAll(Integer limit, Integer offset);

    @Select("""
        SELECT 
            uuid, title, description, max_participants, prefecture_id, organizer_id, held_on, created_at 
        FROM 
            practices
        WHERE
            organizer_id = #{organizerId}
        ORDER BY 
            created_at DESC
        LIMIT 
            #{limit}
        OFFSET 
            #{offset}
    """)
    List<Practice> getMyPractices(UUID organizerId, Integer limit, Integer offset);

    @Select("""
        SELECT 
            pr.uuid, pr.title, pr.description, pr.max_participants, pr.prefecture_id, pr.organizer_id, pr.held_on, pr.created_at 
        FROM
            participations as pa
            LEFT JOIN
                users as us
            ON  pa.user_firebase_uid = us.firebase_uid
            LEFT JOIN
                practices as pr
            ON  pa.practice_id = pr.uuid
        WHERE
            us.uuid = #{organizerId}
        ORDER BY 
            pr.created_at DESC
        LIMIT 
            #{limit}
        OFFSET 
            #{offset}
    """)
    List<Practice> getMyJoinedPractices(UUID organizerId, Integer limit, Integer offset);

    @Select("""
    <script>
        SELECT 
            uuid, title, description, max_participants, prefecture_id, organizer_id, held_on, created_at 
        FROM 
            practices
        <where>
            <if test="prefectureId != null">
                AND prefecture_id = #{prefectureId}
            </if>
            <if test="from != null">
                AND held_on <![CDATA[ >= ]]> #{from}
            </if>
            <if test="to != null">
                AND held_on <![CDATA[ <= ]]> #{to}
            </if>
            <if test="keyword != null">
                AND (title LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%'))
            </if>
        </where>
        ORDER BY 
            created_at DESC
        LIMIT 
            #{limit}
        OFFSET 
            #{offset}
    </script>
    """)
    List<Practice> search(
        Integer limit, 
        Integer offset, 
        Integer prefectureId, 
        LocalDate from,
        LocalDate to,
        String keyword
    );

    @Select("""
        SELECT 
            uuid, title, description, max_participants, prefecture_id, organizer_id, held_on, created_at 
        FROM 
            practices
        WHERE 
            uuid = #{uuid}
    """)    
    Practice findById(UUID uuid);

    @Insert("""
        INSERT INTO 
            practices (title, description, max_participants, prefecture_id, organizer_id, held_on) 
        VALUES 
            (#{title}, #{description}, #{maxParticipants}, #{prefectureId}, #{organizerId}, #{heldOn})
    """)
    @Options(useGeneratedKeys = true, keyColumn = "uuid", keyProperty = "uuid")
    void insert(Practice practice);

    @Update("""
        UPDATE 
            practices 
        SET 
            title = #{title},
            description = #{description},
            max_participants = #{maxParticipants},
            prefecture_id = #{prefectureId},
            held_on = #{heldOn}
        WHERE uuid = #{uuid}
    """)
    int update(Practice practice);

    @Delete("""
        DELETE FROM 
            practices 
        WHERE 
            uuid = #{uuid}
    """)
    int delete(UUID uuid);

    @Insert("""
        INSERT INTO 
            participations (practice_id, user_id) 
        VALUES 
            (#{practiceId}, #{userId})
    """)
    void applyParticipate(UUID practiceId, UUID userId);
    
    @Delete("""
        DELETE FROM 
            participations 
        WHERE 
            practice_id = #{practiceId}
                AND
            user_id = #{userId}

    """)
    int cancelParticipate(UUID practiceId, UUID userId);
}
