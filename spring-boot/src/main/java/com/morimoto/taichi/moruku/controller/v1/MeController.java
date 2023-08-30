package com.morimoto.taichi.moruku.controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morimoto.taichi.moruku.controller.v1.response.PracticeResponse;
import com.morimoto.taichi.moruku.controller.v1.response.UserResponse;
import com.morimoto.taichi.moruku.domain.entity.Practice;
import com.morimoto.taichi.moruku.domain.entity.User;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;
import com.morimoto.taichi.moruku.service.PracticeService;
import com.morimoto.taichi.moruku.service.UserService;

@RestController
@RequestMapping("/api/v1/me")
public class MeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PracticeService practiceService;

    // 自身のアカウント情報を取得
    @GetMapping("/info")
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

    // 自分が投稿した投稿一覧を取得
    @GetMapping("/practices")
    public List<PracticeResponse> findAllMe(
        @RequestParam(value = "limit", required = false) Integer limit, 
        @RequestParam(value = "offset", required = false) Integer offset
    ) throws NoSuchIdException {
        if (limit == null) limit = 20;
        if (offset == null) offset = 0;

        List<Practice> practices = practiceService.findAllMe(limit, offset);

        List<PracticeResponse> responses = new ArrayList<>();
        for (Practice practice : practices) {
            responses.add(new PracticeResponse(
                practice.getUuid().toString(), 
                practice.getTitle(), 
                practice.getDescription(), 
                practice.getMaxParticipants(), 
                practice.getPrefectureId(), 
                practice.getOrganizerId().toString(), 
                practice.getHeldOn(), 
                practice.getCreatedAt()
            ));
        }
        return responses;
    }
}
