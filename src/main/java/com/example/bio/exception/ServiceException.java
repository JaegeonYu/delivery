package com.example.bio.exception;

public abstract class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }

    public abstract int getStatusCode();
}
