package com.morimoto.taichi.moruku.domain.repository;

import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParticipantionMapper {
    @Insert("""
        INSERT INTO 
            participations (practice_id, user_firebase_uid) 
        VALUES 
            (#{practiceId}, #{firebaseUid})
    """)
    void insert(UUID practiceId, String firebaseUid);
    
    @Delete("""
        DELETE FROM 
            participations 
        WHERE 
            practice_id = #{practiceId}
                AND
            user_firebase_uid = #{firebaseUid}

    """)
    int delete(UUID practiceId, String firebaseUid);
}
