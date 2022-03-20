package com.getir.reading.exception;

public class OrderNotFoundException extends BusinessException {
    private static final String errorCode = "000003";
    private static final String errorMessage = "OrderHeader Not Found";

    public OrderNotFoundException() {
        super(errorCode, errorMessage);
    }
}