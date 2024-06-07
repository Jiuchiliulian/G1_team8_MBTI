package com.diary.mapper;

import com.diary.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
