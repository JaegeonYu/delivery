package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class FoodNotFound extends ServiceException{
    private static final String MESSAGE = FOOD_NOT_FOUND.getMessage();

    public FoodNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return FOOD_NOT_FOUND.getStatusCode();
    }
}
