package com.morimoto.taichi.moruku.exception;

public class NoSuchIdException extends Exception{

    private String detail;

    public NoSuchIdException(String message) {
        super(message);
        this.detail = Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
    }
}
