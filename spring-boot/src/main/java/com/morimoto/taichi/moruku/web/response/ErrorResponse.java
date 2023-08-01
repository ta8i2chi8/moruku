package com.morimoto.taichi.moruku.web.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private ErrorContextResponse error;

    @JsonCreator
    public ErrorResponse(Integer status, String code, String summary, String detail, String messages){
        this.error = new ErrorContextResponse(status, code, summary, detail,messages);
    }

}
