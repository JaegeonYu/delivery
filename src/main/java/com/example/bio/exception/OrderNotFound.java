package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class OrderNotFound extends ServiceException {
    private final static String MESSAGE = ORDER_NOT_FOUND.getMessage();

    public OrderNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return ORDER_NOT_FOUND.getStatusCode();
    }
}
