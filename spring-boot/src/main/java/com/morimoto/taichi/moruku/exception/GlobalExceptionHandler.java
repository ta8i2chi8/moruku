package com.morimoto.taichi.moruku.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
