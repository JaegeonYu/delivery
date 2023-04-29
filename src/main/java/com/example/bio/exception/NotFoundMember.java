package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class NotFoundMember extends ServiceException {
    private static final String MESSAGE = MEMBER_NOT_FOUND.getMessage();

    public NotFoundMember() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return MEMBER_NOT_FOUND.getStatusCode();
    }
}
