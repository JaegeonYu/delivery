package com.example.bio.controller;

import com.example.bio.domain.dto.ExceptionResponse;
import com.example.bio.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(ServiceException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }
}
