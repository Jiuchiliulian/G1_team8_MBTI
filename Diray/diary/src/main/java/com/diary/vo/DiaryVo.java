package com.diary.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DiaryVo {

    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotEmpty(message = "内容不能为空")
    private String content;
}
