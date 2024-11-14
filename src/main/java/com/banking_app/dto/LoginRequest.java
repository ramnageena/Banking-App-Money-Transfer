package com.banking_app.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private Long accountNumber;
    private String name;
}