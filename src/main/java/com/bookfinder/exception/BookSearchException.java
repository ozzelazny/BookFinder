package com.bookfinder.exception;

public class BookSearchException extends RuntimeException {

    private int errorCode;

    public BookSearchException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
