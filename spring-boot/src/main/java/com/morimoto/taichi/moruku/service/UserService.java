package com.morimoto.taichi.moruku.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morimoto.taichi.moruku.common.config.SecurityConfig;
import com.morimoto.taichi.moruku.domain.entity.User;
import com.morimoto.taichi.moruku.domain.repository.UserMapper;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SecurityConfig securityConfig;

    public User getUserById(UUID uuid) throws NoSuchIdException {
        User user = userMapper.getUserByUuid(uuid);

        if (Objects.isNull(user)) {
            throw new NoSuchIdException("users tableにuuid " + uuid + "が存在していません");
        }
        return user;
    }

    public User getMe() throws NoSuchIdException {
        String firebaseUid = securityConfig.getFirebaseUid();
        User user = userMapper.getUserByFirebaseUid(firebaseUid);

        if (Objects.isNull(user)) {
            throw new NoSuchIdException("users tableにfirebase_uid " + firebaseUid + "が存在していません");
        }
        return user;
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}