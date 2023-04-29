package com.example.bio.exception;

import lombok.Getter;

@Getter
public enum ExceptionControl {


    FOOD_NOT_FOUND("존재하지 않는 음식입니다.", 404),
    MEMBER_NOT_FOUND("존재하지 않는 사용자입니다.", 404),
    ORDER_NOT_FOUND("존재하지 않는 주문입니다.", 404),
    ALREADY_MEMBER("이미 존재하는 이름입니다.", 409),
    ALREADY_DELIVERY("배송완료된 상품은 취소가 불가합니다.", 403);


    private final String Message;
    private final int StatusCode;

    private ExceptionControl(String message, int statusCode) {
        Message = message;
        StatusCode = statusCode;
    }
}
