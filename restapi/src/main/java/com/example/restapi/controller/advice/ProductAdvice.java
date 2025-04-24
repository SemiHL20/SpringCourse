package com.example.restapi.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import com.example.restapi.dto.MessageDTO;

@RestControllerAdvice
public class ProductAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processNullPointerException(NullPointerException ex) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Error found. Please check your request and try again.");
        messageDTO.setType("Error");
        return messageDTO;
    }
}
