package com.deepseek.model.dto;

import lombok.Data;

@Data
public class FileInfoDTO {
    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String filePath;
}