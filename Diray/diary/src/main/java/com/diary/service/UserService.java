package com.diary.service;

import com.diary.Dto.UserDto;
import com.diary.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.diary.vo.LoginVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
public interface UserService extends IService<User> {

    UserDto login(LoginVo loginVo);

    UserDto reg(LoginVo loginVo);

    String disabled(String userId, String reason);
}
