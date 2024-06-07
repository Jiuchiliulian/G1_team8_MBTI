package com.diary.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diary.Dto.UserDto;
import com.diary.bace.util.BeanUtil;
import com.diary.bace.vo.Result;
import com.diary.entity.User;
import com.diary.service.UserService;
import com.diary.util.CacheService;
import com.diary.util.JwtUtils;
import com.diary.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户配置")
@ApiModel(value = "用户配置接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @PostMapping("/reg")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "入参",value = "loginVo",dataType = "LoginVo")
    public Result<UserDto> reg(@Valid @RequestBody LoginVo loginVo){

        UserDto userDto = userService.reg(loginVo);
        if (userDto != null) {
            return Result.ok(userDto);
        }
        return Result.error("当前用户名已存在，请重新注册");
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "入参",value = "loginVo",dataType = "LoginVo")
    public Result<UserDto> login(@Valid @RequestBody LoginVo loginVo) {

        UserDto userDto = userService.login(loginVo);

        if (userDto != null) {
            return Result.ok(userDto);
        }
        cacheService.cacheUser(userDto);

        return Result.error("用户名或密码不正确");
    }

    @GetMapping("/getByUserId")
    @ApiOperation(value = "用户信息获取")
    public Result<User> getByUserId(@Param("userId") String userId){
        User user = userService.getById(userId);
        if (user == null){
            return Result.error("参数错误");
        }
        return Result.ok(user);
    }

    @PutMapping("/update")
    @ApiOperation(value = "用户信息更新")
    @Transactional
    public Result<User> getByUserId(@RequestBody User user){
        User userById = userService.getById(user.getUserId());
        BeanUtil.copyNonNullProperties(user,userById);
        boolean update = userService.updateById(userById);
        if (!update){
            return Result.error("更新失败");
        }
        return Result.ok(userById);
    }


    @GetMapping("/list")
    @ApiOperation(value = "获取用户列表")
    public Result<List<User>> list(){
        return Result.ok(userService.list());
    }

    @GetMapping("/disabled")
    @ApiOperation(value = "禁用用户")
    public Result<String> disabled(@Param("userId") String userId,@Param("reason") String reason){
        return Result.ok(userService.disabled(userId,reason));
    }
}

