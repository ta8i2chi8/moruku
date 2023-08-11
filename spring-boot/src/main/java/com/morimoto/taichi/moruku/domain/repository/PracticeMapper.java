package com.morimoto.taichi.moruku.domain.repository;

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
    """)
    List<Practice> findAll();

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
            organizer_id = #{organizerId},
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
}
