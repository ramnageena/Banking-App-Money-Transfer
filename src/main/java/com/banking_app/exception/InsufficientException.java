package com.banking_app.exception;

public class InsufficientException extends RuntimeException{
    public InsufficientException(String message){
        super(message);
    }
}
