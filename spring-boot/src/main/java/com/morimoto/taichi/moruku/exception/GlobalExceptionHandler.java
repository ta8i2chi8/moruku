package com.morimoto.taichi.moruku.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.morimoto.taichi.moruku.controller.v1.response.ErrorResponse;

import org.slf4j.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger((GlobalExceptionHandler.class));

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchIdException(NoSuchIdException e) {
        logger.info(e.getMessage(), e);
        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), 
            "", 
            "idがDBに存在していません", 
            e.getMessage(), 
            "DB上に対象データがありません。入力値を確認してください"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleNotMyEntityException(NotMyEntityException e) {
        logger.info(e.getMessage(), e);
        return new ErrorResponse(
            HttpStatus.FORBIDDEN.value(), 
            "", 
            "自分以外が開催した練習会は編集できません", 
            e.getMessage(), 
            "自分が開催した練習会ではありません。対象としている練習会を確認してください"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.info(e.getMessage(), e);
        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), 
            "", 
            "RequestBodyが適切ではありません", 
            e.getMessage(), 
            "RequestBodyのバリデーションに失敗しました。RequestBodyを確認してください"
        );
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.info(e.getMessage(), e);
        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), 
            "", 
            "パラメータが適切ではありません", 
            e.getMessage(), 
            "パラメータの型の不一致が発生しました。パラメータを確認してください"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.info(e.getMessage(), e);
        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), 
            "", 
            "RequestBodyが適切ではありません", 
            e.getMessage(), 
            "RequestBodyの形式が適切ではありません。RequestBodyを確認してください"
        );
    }
}
