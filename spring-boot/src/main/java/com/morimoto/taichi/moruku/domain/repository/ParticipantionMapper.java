package com.morimoto.taichi.moruku.domain.repository;

import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ParticipantionMapper {
    @Select("""
        SELECT 
            count(*)
        FROM
            participations
        WHERE
            practice_id = #{practiceId}
                AND
            user_firebase_uid = #{firebaseUid}
    """)
    int getParticipantionByIds(UUID practiceId, String firebaseUid);

    @Insert("""
        INSERT INTO 
            participations (practice_id, user_firebase_uid) 
        VALUES 
            (#{practiceId}, #{firebaseUid})
    """)
    void insertParticipantion(UUID practiceId, String firebaseUid);
    
    @Delete("""
        DELETE FROM 
            participations 
        WHERE 
            practice_id = #{practiceId}
                AND
            user_firebase_uid = #{firebaseUid}

    """)
    int deleteParticipantion(UUID practiceId, String firebaseUid);
}
