package com.diary.service;

import com.diary.Dto.Diary1Dto;
import com.diary.Dto.DiaryDto;
import com.diary.entity.Diary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.diary.vo.DiaryVo;

/**
 * <p>
 * 日记表 服务类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
public interface DiaryService extends IService<Diary> {

    DiaryDto add(DiaryVo diaryVo);

    Diary1Dto getDiaryById(String diaryId);
}
