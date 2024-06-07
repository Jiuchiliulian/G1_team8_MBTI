package com.diary.service;

import com.diary.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 点赞表 服务类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
public interface LikeService extends IService<Like> {

    Like add(String diaryId);

    void del(String diaryId);
}
