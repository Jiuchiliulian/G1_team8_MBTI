package com.diary.controller;


import cn.hutool.core.lang.UUID;
import com.diary.Dto.Diary1Dto;
import com.diary.Dto.DiaryDto;
import com.diary.bace.util.BeanUtil;
import com.diary.bace.vo.Result;
import com.diary.entity.Diary;
import com.diary.service.DiaryService;
import com.diary.util.CacheService;
import com.diary.vo.DiaryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 日记表 前端控制器
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@RestController
@RequestMapping("/diary")
@Api(tags = "日记配置")
@ApiModel(value = "日记配置接口")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;


    @PostMapping("/add")
    @ApiOperation(value = "创建日记")
    @ApiImplicitParam(name = "入参",value = "DiaryVo",dataType = "DiaryVo")
    public Result<DiaryDto> add(@Valid @RequestBody DiaryVo diaryVo){
       return Result.ok(diaryService.add(diaryVo));
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取日记列表")
    public Result<List<Diary>> list(){
        return Result.ok(diaryService.list());
    }


    @GetMapping("/{diaryId}")
    @ApiOperation(value = "获取单个日记详情")
    public Result<Diary1Dto> getDiaryById(@PathVariable("diaryId") String diaryId){
        Diary1Dto diary = diaryService.getDiaryById(diaryId);
        if (diary == null){
            return Result.error("参数错误");
        }
        return Result.ok(diary);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改日记")
    public Result<Diary> update(@RequestBody Diary diary){
        Diary diaryById = diaryService.getById(diary);
        BeanUtil.copyNonNullProperties(diary,diaryById);
        boolean update = diaryService.updateById(diaryById);
        if (!update){
            return Result.error("更新失败");
        }
        return Result.ok(diaryById);
    }
}

