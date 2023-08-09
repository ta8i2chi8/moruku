package com.morimoto.taichi.moruku.controller.v1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorContextResponse {
    private Integer status;
    private String code;
    private String summary;
    private String detail;
    private String messages;
}
