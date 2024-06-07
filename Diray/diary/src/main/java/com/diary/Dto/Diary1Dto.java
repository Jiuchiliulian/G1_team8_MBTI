package com.diary.Dto;

import com.diary.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
public class Diary1Dto {

    /**
     * 日记ID
     */
    private String diaryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者用户信息
     */
    private UserVo author;

    /**
     * 评论列表
     */
    private List<CommentDto> comments;
}
