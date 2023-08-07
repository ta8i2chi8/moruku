package com.morimoto.taichi.moruku.controller.v1;

import com.morimoto.taichi.moruku.domain.entity.Question;
import com.morimoto.taichi.moruku.domain.service.QuestionService;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;
import com.morimoto.taichi.moruku.controller.v1.request.QuestionRequest;
import com.morimoto.taichi.moruku.controller.v1.response.QuestionDetailResponse;
import com.morimoto.taichi.moruku.controller.v1.response.QuestionResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{id}")
    public QuestionDetailResponse findById(@PathVariable Integer id) throws NoSuchIdException {
        Question question = questionService.findById(id);
        return QuestionDetailResponse.builder()
                .id(question.getId())
                .content(question.getContent())
                .imageContent(question.getImageContent())
                .attentionCount(question.getAttentionCount())
                .createdAt(question.getCreatedAt())
                .build();
    }

    @GetMapping
    public List<QuestionResponse> findAll(@RequestParam(required = false) String query, @RequestParam(required = false) String sortBy) {
        // QuestionServiceのfindAllメソッドを呼び出してQuestionテーブルの全データを取得する
        List<Question> questions = questionService.findAll(query, sortBy);
        // エンティティからレスポンスクラスへ変換する
        List<QuestionResponse> responses = new ArrayList<>();
        for (Question question : questions) {
            responses.add(new QuestionResponse(
                question.getId(), 
                question.getContent(),
                question.getAttentionCount(),
                question.getCreatedAt()
            ));
        }
        // レスポンスクラスを戻り値とする
        return responses;
    }

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

    @PutMapping("/{id}/attention")
    public void attend(@PathVariable Integer id) {
        questionService.attend(id);
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        questionService.delete(id);
    }

}
