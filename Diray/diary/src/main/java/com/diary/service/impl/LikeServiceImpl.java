package com.diary.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diary.bace.handler.MyException;
import com.diary.entity.Diary;
import com.diary.entity.Like;
import com.diary.mapper.DiaryMapper;
import com.diary.mapper.LikeMapper;
import com.diary.service.LikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diary.util.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Autowired
    private CacheService cacheService;

    @Override
    @Transactional
    public Like add(String diaryId) {
        if (cacheService.getUser()!=null) {
            LambdaQueryWrapper<Like> likeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            likeLambdaQueryWrapper.eq(Like::getUseId,cacheService.getUser().getUserInfo().getUserId());
            likeLambdaQueryWrapper.eq(Like::getDiaryId,diaryId);
            long count = this.count(likeLambdaQueryWrapper);
            if (count != 0){
                return null;
            }
        }

        Like like = new Like();
        like.setDiaryId(diaryId);
        if (cacheService.getUser()!=null){
            like.setUseId(cacheService.getUser().getUserInfo().getUserId());
        }
        String uuid = getUUID();
        like.setLikeId(uuid);
        this.save(like);

        Diary diary = diaryMapper.selectById(diaryId);
        diary.setLikesCount((diary.getLikesCount()+1));
        diaryMapper.updateById(diary);
        return like;
    }

    @Override
    public void del(String diaryId) {
        if (cacheService.getUser()!=null){
            LambdaQueryWrapper<Like> likeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            likeLambdaQueryWrapper.eq(Like::getUseId,cacheService.getUser().getUserInfo().getUserId());
            likeLambdaQueryWrapper.eq(Like::getDiaryId,diaryId);
            Like like = this.getOne(likeLambdaQueryWrapper);
            this.removeById(like);

            Diary diary = diaryMapper.selectById(diaryId);
            diary.setLikesCount((diary.getLikesCount()-1));
            diaryMapper.updateById(diary);
        }
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
