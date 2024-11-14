package com.banking_app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse {
    private Integer statusCode;
    private String message;
    private Date timestamp;


}
