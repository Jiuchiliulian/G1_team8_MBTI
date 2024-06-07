package com.diary.controller;


import com.diary.bace.util.BeanUtil;
import com.diary.bace.vo.Result;
import com.diary.entity.Comment;
import com.diary.service.CommentService;
import com.diary.vo.CommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论配置")
@ApiModel(value = "评论配置接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperation(value = "发布评论")
    @ApiImplicitParam(name = "入参",value = "CommentVo",dataType = "CommentVo")
    public Result<Comment> save(@RequestBody @Valid CommentVo commentVo){
        return Result.ok(commentService.add(commentVo));
    }


}

