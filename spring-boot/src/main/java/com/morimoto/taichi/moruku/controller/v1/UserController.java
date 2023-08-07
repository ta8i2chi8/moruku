package com.morimoto.taichi.moruku.controller.v1;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.morimoto.taichi.moruku.domain.entity.Question;
import com.morimoto.taichi.moruku.domain.service.QuestionService;
import com.morimoto.taichi.moruku.controller.v1.request.QuestionRequest;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Validated QuestionRequest questionRequest) {
        Question newQuestion = new Question();
        newQuestion.setContent(questionRequest.getContent());
        newQuestion.setImageContent(questionRequest.getImageContent());
        questionService.insert(newQuestion);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(newQuestion.getId().toString())
                .build().encode().toUri();
        return ResponseEntity.created(location).build();
    }
}
