package com.morimoto.taichi.moruku.domain.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morimoto.taichi.moruku.domain.entity.User;
import com.morimoto.taichi.moruku.domain.repository.UserMapper;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(UUID uuid) throws NoSuchIdException {
        User user = userMapper.findById(uuid);

        if (Objects.isNull(user)) {
            throw new NoSuchIdException("users tableにid" + uuid + "が存在していません");
        }
        return user;
    }

    public void insert(User user) {
        userMapper.insert(user);
    }
}