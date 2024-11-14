package com.banking_app.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    //it will work with the Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errorMessage = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        response.put("message", errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccountAlreadyExist.class)
    public ResponseEntity<APIResponse> myMethodArgumentNotValidException(AccountAlreadyExist e) {
      APIResponse apiResponse= new APIResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage(),new Date());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
