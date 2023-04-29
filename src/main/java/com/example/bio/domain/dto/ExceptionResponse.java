package com.example.bio.domain.dto;

import lombok.Builder;

public class ExceptionResponse {
    private String Message;

    @Builder
    public ExceptionResponse(String message) {
        Message = message;
    }
}
