package com.diary.Dto;

import com.diary.vo.UserVo;
import lombok.Data;

@Data
public class CommentDto {

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论用户
     */
    private UserVo author;
}
