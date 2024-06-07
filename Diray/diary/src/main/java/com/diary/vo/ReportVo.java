package com.diary.vo;

import lombok.Data;

@Data
public class ReportVo {

    /**
     * 举报id
     */
    private String reportId;

    /**
     * 操作类型：可选值包括 "delete"、"block"、"warn" 等
     */
    private String action;

    /**
     * 操作理由
     */
    private String reason;
}
