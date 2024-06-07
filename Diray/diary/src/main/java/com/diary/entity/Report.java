package com.diary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 举报表
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Getter
@Setter
@Accessors(chain = true)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 举报id
     */
    @TableId(value = "report_id", type = IdType.ASSIGN_UUID)
    private String reportId;

    /**
     * 举报类型
     */
    private String reportType;

    /**
     * 目标id
     */
    private String targetId;

    /**
     * 举报内容
     */
    private String content;

    /**
     * 举报理由
     */
    private String reportReason;

    /**
     * 0:待审核 1:已审核
     */
    private Integer status;

    /**
     * 操作类型：可选值包括 "delete"、"block"、"warn" 等
     */
    private String action;

    /**
     * 操作理由
     */
    private String operationReason;


}
