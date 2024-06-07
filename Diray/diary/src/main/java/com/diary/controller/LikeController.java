package com.diary.controller;


import com.diary.bace.vo.Result;
import com.diary.entity.Comment;
import com.diary.entity.Like;
import com.diary.service.CommentService;
import com.diary.service.LikeService;
import com.diary.vo.CommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 点赞表 前端控制器
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@RestController
//@RequestMapping("/like")
@Api(tags = "点赞配置")
@ApiModel(value = "点赞配置接口")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/diary/{diaryId}/like")
    @ApiOperation(value = "点赞日记")
    public Result<Like> save(@PathVariable("diaryId") String diaryId){
        return Result.ok(likeService.add(diaryId));
    }


    @GetMapping("/like/del")
    @ApiOperation(value = "取消点赞日记")
    public Result<String> del(@Param("diaryId")String diaryId){
        likeService.del(diaryId);
        return Result.ok("取消点赞成功");
    }

}

