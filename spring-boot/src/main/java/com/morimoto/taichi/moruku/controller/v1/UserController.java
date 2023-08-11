package com.morimoto.taichi.moruku.controller.v1;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.morimoto.taichi.moruku.controller.v1.request.UserRequest;
import com.morimoto.taichi.moruku.controller.v1.response.UserResponse;
import com.morimoto.taichi.moruku.domain.entity.User;
import com.morimoto.taichi.moruku.domain.service.UserService;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uuid}")
    public UserResponse findById(@PathVariable String uuid) throws NoSuchIdException {
        User user = userService.findById(UUID.fromString(uuid));
        return UserResponse.builder()
                .uuid(user.getUuid().toString())
                .nickname(user.getNickname())
                .iconUrl(user.getIconUrl())
                .email(user.getEmail())
                .firebaseUid(user.getFirebaseUid())
                .build();
    }

    @GetMapping("/me")
    public UserResponse findMe() throws NoSuchIdException {
        User user = userService.findMe();
        return UserResponse.builder()
                .uuid(user.getUuid().toString())
                .nickname(user.getNickname())
                .iconUrl(user.getIconUrl())
                .email(user.getEmail())
                .firebaseUid(user.getFirebaseUid())
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Validated UserRequest userRequest) {
        User newUser = new User();
        newUser.setNickname(userRequest.getNickname());
        newUser.setEmail(userRequest.getEmail());
        newUser.setFirebaseUid(userRequest.getFirebaseUid());
        userService.insert(newUser);
    }
}
