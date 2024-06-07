package com.diary.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diary.Dto.Diary1Dto;
import com.diary.Dto.DiaryDto;
import com.diary.entity.Comment;
import com.diary.entity.Diary;
import com.diary.entity.User;
import com.diary.mapper.CommentMapper;
import com.diary.mapper.DiaryMapper;
import com.diary.service.DiaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diary.service.UserService;
import com.diary.util.CacheService;
import com.diary.Dto.CommentDto;
import com.diary.vo.DiaryVo;
import com.diary.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 日记表 服务实现类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public DiaryDto add(DiaryVo diaryVo) {

        Diary diary = new Diary(diaryVo.getTitle(),diaryVo.getContent());
        String uuid = getUUID();
        diary.setDiaryId(uuid);
        diary.setCreatedAt(LocalDateTime.now());
        if (cacheService.getUser()!=null) {
            diary.setUserId(cacheService.getUser().getUserInfo().getUserId());
        }

        this.save(diary);

        DiaryDto diaryDto = new DiaryDto();

        BeanUtils.copyProperties(diary,diaryDto);
        return diaryDto;
    }

    @Override
    @Transactional
    public Diary1Dto getDiaryById(String diaryId) {
        Diary1Dto diary1Dto = new Diary1Dto();
        Diary diary = this.getById(diaryId);
        if (diary == null){
            return null;
        }
        BeanUtils.copyProperties(diary,diary1Dto);
        if (diary.getUserId()!=null){
            User user = userService.getById(diary.getUserId());
            UserVo userVo = new UserVo(user.getUserId(),user.getUsername());
            diary1Dto.setAuthor(userVo);
        }

        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getDiaryId,diary.getDiaryId());
        List<Comment> comments = commentMapper.selectList(commentLambdaQueryWrapper);

        if (!comments.isEmpty()){
            List<CommentDto> commentVos = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDto commentVo = new CommentDto();
                BeanUtils.copyProperties(comment,commentVo);
                if (comment.getUserId()!=null){
                    User user = userService.getById(comment.getUserId());
                    UserVo userVo = new UserVo(comment.getUserId(),user.getUsername());
                    commentVo.setAuthor(userVo);
                }
                commentVos.add(commentVo);
            }
            diary1Dto.setComments(commentVos);
        }
        return diary1Dto;
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
