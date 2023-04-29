package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class NotFoundFood extends ServiceException {
    private static final String MESSAGE = FOOD_NOT_FOUND.getMessage();

    public NotFoundFood() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return FOOD_NOT_FOUND.getStatusCode();
    }
}
