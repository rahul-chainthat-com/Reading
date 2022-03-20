package com.getir.reading.exception;

public class CustomerNotFoundException extends BusinessException {
    private static final String errorCode = "000001";
    private static final String errorMessage = "Customer Not Found";

    public CustomerNotFoundException() {
        super(errorCode, errorMessage);
    }
}
