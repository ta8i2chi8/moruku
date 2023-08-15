package com.morimoto.taichi.moruku.service;

import com.morimoto.taichi.moruku.domain.entity.Question;
import com.morimoto.taichi.moruku.domain.repository.QuestionMapper;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    public Question findById(Integer questionId) throws NoSuchIdException {

        Question question = questionMapper.findById(questionId);

        if (Objects.isNull(question))
            throw new NoSuchIdException("questions tableにid" + questionId + "が存在していません");
        return question;

    }

    public List<Question> findAll(String query, String sortBy) {
        List<Question> questions;
        if (Objects.isNull(query)) {
            if ("attentionCount".equals(sortBy)){
                questions = questionMapper.findAllSortByAttention();
            } else if ("answerCount".equals(sortBy)) {
                questions = questionMapper.findAllSortByAnswerCount();
            } else {
                questions = questionMapper.findAll();
            }
        } else {
            String[] queries = query.split("\\s|　+");
            questions = questionMapper.search(queries);
        }
        return questions;
    }

    public void insert(Question question) {
        questionMapper.insert(question);
    }
    
    public void attend(Integer questionId) {
        questionMapper.attend(questionId);
    }

    public void delete(Integer questionId){
        questionMapper.delete(questionId);
    }
}