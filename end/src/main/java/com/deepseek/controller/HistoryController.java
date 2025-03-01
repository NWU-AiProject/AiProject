package com.deepseek.controller;

import com.deepseek.model.dto.ChatResponse;
import com.deepseek.model.dto.HistoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);


    @GetMapping("/today")
    public HistoryResponse getTodayHistory(
            @RequestParam String userId
    ) {
        // 实际开发时应查询数据库，这里返回固定值
        logger.info("用户ID：{}", userId);
        return HistoryResponse.success(Collections.singletonList("收到了ddddddddddddddddddd"));
    }

    @GetMapping("/weekly")
    public HistoryResponse getWeeklyHistory(
            @RequestParam String userId
    ) {
        // 实际开发时应查询数据库，这里返回固定值
        logger.info("用户ID：{}", userId);
        return HistoryResponse.success(Collections.singletonList("收到了"));
    }
}