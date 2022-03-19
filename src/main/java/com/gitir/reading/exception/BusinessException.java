package com.gitir.reading.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    private final String errorCode;
    private final String errorMessage;

    public BusinessException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
