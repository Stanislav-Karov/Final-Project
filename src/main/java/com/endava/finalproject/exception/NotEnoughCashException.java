package com.endava.finalproject.exception;

public class NotEnoughCashException extends RuntimeException{
    public NotEnoughCashException(String message) {
        super(message);
    }
}
