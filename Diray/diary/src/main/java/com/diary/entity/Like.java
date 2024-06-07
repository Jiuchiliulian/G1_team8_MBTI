package com.diary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 点赞表
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("likes")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞id
     */
    @TableId(value = "like_id", type = IdType.ASSIGN_UUID)
    private String likeId;

    /**
     * 日记id
     */
    private String diaryId;

    /**
     * 用户id
     */
    private String useId;


}
