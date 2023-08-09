package com.morimoto.taichi.moruku.controller.v1.request;

import jakarta.validation.constraints.NotNull;

public class QuestionRequest {

    @NotNull
    private String content;

    private String imageContent;
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getImageContent() {
        return imageContent;
    }
    
    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }
} 