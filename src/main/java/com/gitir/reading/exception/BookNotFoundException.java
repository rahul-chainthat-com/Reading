package com.gitir.reading.exception;

public class BookNotFoundException extends BusinessException {
    private static final String errorCode = "000002";
    private static final String errorMessage = "Book Not Found";

    public BookNotFoundException() {
        super(errorCode, errorMessage);
    }
}