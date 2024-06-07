package com.diary.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentVo {
    /**
     * 日记id
     */
    @NotEmpty(message = "日记ID不能为空")
    private String diaryId;

    /**
     * 评论内容
     */
    @NotEmpty(message = "评论内容不能为空")
    private String content;
}
