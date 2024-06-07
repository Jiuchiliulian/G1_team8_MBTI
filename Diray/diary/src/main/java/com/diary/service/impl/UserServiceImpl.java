package com.diary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diary.Dto.UserDto;
import com.diary.bace.vo.Result;
import com.diary.entity.User;
import com.diary.mapper.UserMapper;
import com.diary.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diary.util.JwtUtils;
import com.diary.vo.LoginVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @Transactional
    public UserDto login(LoginVo loginVo) {
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginVo.getUsername()).eq(User::getPassword, loginVo.getPassword()));
        // 是否为空
        if (user != null) {
            // 生成token
            HashMap<String, Object> emps = new HashMap();
            emps.put("username",user.getUsername());
            emps.put("userId",user.getUserId());
            emps.put("str", RandomStringUtils.random(10, new char[]{'a','b','c','d','e','f', '1', '2', '3'}));
            String token = JwtUtils.generateJwt(emps);

            UserDto userDto = new UserDto(user,token);
            return userDto;
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto reg(LoginVo loginVo) {
        long count = this.count(new LambdaQueryWrapper<User>().eq(User::getUsername, loginVo.getUsername()));
        if (count != 0){
            return null;
        }

        User user = new User(loginVo.getUsername(),loginVo.getPassword());
        this.save(user);
        return this.login(loginVo);
    }

    @Override
    public String disabled(String userId, String reason) {
        User user = this.getById(userId);
        if (user.getStatus() == 0){
            return "当前已是禁用状态，无需再次禁用";
        }
        user.setStatus(0);
        user.setReason(reason);
        this.updateById(user);
        return "用户已成功被禁用";
    }
}
