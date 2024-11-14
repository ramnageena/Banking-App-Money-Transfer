package com.banking_app.exception;

public class AccountAlreadyExist extends RuntimeException{
    public AccountAlreadyExist(String message) {
        super(message);

        }
    }
