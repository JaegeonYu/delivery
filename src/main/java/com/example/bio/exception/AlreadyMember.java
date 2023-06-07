package com.example.bio.exception;

import static com.example.bio.exception.ExceptionControl.*;

public class AlreadyMember extends ServiceException {
    private static final String MESSAGE = ALREADY_MEMBER.getMessage();

    public AlreadyMember() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return ALREADY_DELIVERY.getStatusCode();
    }
}
