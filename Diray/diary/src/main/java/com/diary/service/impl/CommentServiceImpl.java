package com.diary.service.impl;

import cn.hutool.core.lang.UUID;
import com.diary.entity.Comment;
import com.diary.entity.Diary;
import com.diary.mapper.CommentMapper;
import com.diary.mapper.DiaryMapper;
import com.diary.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diary.service.DiaryService;
import com.diary.util.CacheService;
import com.diary.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    @Transactional
    public Comment add(CommentVo commentVo) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVo,comment);
        if (cacheService.getUser()!=null) {
            comment.setUserId(cacheService.getUser().getUserInfo().getUserId());
        }
        comment.setCreatedAt(LocalDateTime.now());
        String uuid = getUUID();
        comment.setCommentId(uuid);
        this.save(comment);

        // 增加评论数量
        Diary diary = diaryMapper.selectById(commentVo.getDiaryId());
        diary.setCommentsCount((diary.getCommentsCount()+1));
        diaryMapper.updateById(diary);

        return comment;
    }

    /**
     * 生成UUID
     * @return
     */
    public String getUUID(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        //32位UUID
        return new UUID(random.nextLong(), random.nextLong()).toString().replace("-", "");
    }
}
