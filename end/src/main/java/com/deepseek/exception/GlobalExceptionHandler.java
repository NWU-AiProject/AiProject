package com.deepseek.exception;

import com.deepseek.model.dto.ChatResponse;
import com.deepseek.model.dto.HistoryResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ChatResponse ChatHandleException(Exception e) {
        return ChatResponse.error(e.getMessage());
    }

}