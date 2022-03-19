package com.gitir.reading.exception;

public class OutOfStockException extends BusinessException {
    private static final String errorCode = "000003";

    public OutOfStockException(String errorMessage) {
        super(errorCode, errorMessage);
    }
}
