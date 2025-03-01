package com.deepseek.model.dto;

import com.deepseek.model.enums.ResponseCode;
import lombok.Data;

import java.util.List;

@Data
public class HistoryResponse {
    private Integer code;
    private String messages;
    private List<String> data;

    public static HistoryResponse success(List<String> data) {
        HistoryResponse response = new HistoryResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessages("success");
        response.setData(data);
        return response;
    }

    public static HistoryResponse error(List<String> data) {
        HistoryResponse response = new HistoryResponse();
        response.setCode(ResponseCode.ERROR.getCode());
        response.setMessages("error");
        response.setData(data);
        return response;
    }
}
