package com.diary.service;

import com.diary.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.diary.vo.CommentVo;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
public interface CommentService extends IService<Comment> {

    Comment add(CommentVo commentVo);
}
