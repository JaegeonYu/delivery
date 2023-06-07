package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class NotFoundOrder extends ServiceException {
    private final static String MESSAGE = ORDER_NOT_FOUND.getMessage();

    public NotFoundOrder() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return ORDER_NOT_FOUND.getStatusCode();
    }
}
