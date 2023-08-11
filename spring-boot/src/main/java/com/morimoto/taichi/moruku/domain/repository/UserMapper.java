package com.morimoto.taichi.moruku.domain.repository;

import java.util.UUID;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.morimoto.taichi.moruku.domain.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT uuid, nickname, icon_url, email, firebase_uid FROM users WHERE uuid = #{uuid}")
    User findByUuid(UUID uuid);

    @Select("SELECT uuid, nickname, icon_url, email, firebase_uid FROM users WHERE firebase_uid = #{firebaseUid}")
    User findByFirebaseUid(String firebaseUid);

    @Insert("INSERT INTO users (nickname, icon_url, email, firebase_uid) VALUES (#{nickname}, #{iconUrl}, #{email}, #{firebaseUid})")
    @Options(useGeneratedKeys = true, keyColumn = "uuid", keyProperty = "uuid")
    void insert(User user);
}
