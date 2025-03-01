package com.deepseek.model.dto;

import com.deepseek.model.enums.ResponseCode;
import lombok.Data;

@Data
public class ChatResponse {
    private Integer code;
    private String message;
    private String data;

    public static ChatResponse success(String data) {
        ChatResponse response = new ChatResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static ChatResponse error(String msg) {
        ChatResponse response = new ChatResponse();
        response.setCode(ResponseCode.ERROR.getCode());
        response.setMessage(msg);
        return response;
    }
}