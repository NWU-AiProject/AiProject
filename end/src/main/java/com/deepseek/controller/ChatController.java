package com.deepseek.controller;

import com.deepseek.model.dto.ChatRequest;
import com.deepseek.model.dto.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);


    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        // 打印接收到的请求参数
        logger.debug("收到用户请求：{}", request.getUserId());
        logger.info("问题内容：{}", request.getMessage());

        // 直接返回固定响应
        return ChatResponse.success("收到了");
    }
}