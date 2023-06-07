package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class OutOfStuck extends ServiceException {
    private static final String MESSAGE = OUT_OF_STOCK.getMessage();

    public OutOfStuck() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return OUT_OF_STOCK.getStatusCode();
    }
}
