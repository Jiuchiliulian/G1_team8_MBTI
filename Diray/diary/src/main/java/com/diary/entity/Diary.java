package com.diary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 日记表
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日记ID
     */
    @TableId(value = "diary_id", type = IdType.ASSIGN_UUID)
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
     * 发布用户id
     */
    private String userId;

    /**
     * 发布时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * 评论数量
     */
    private Integer commentsCount;

    /**
     * 点赞数量
     */
    private Integer likesCount;


    public Diary(String title, String content) {
        this.title = title;
        this.content= content;
    }
}
