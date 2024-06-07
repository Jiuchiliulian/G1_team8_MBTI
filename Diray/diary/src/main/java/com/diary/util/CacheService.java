package com.diary.util;

import com.diary.Dto.UserDto;
import com.github.benmanes.caffeine.cache.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {
    private Cache<String, UserDto> userCache;

    public CacheService() {
        userCache = Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)  // 设置项在写入60分钟后过期
                .maximumSize(1000)                       // 设置最大缓存项为1000
                .build();
    }

    public void cacheUser(UserDto user) {
        userCache.put("user", user);               // 将用户信息存入缓存
    }

    public UserDto getUser() {
        return userCache.getIfPresent("user");     // 从缓存中获取用户信息
    }

    public void removeUser() {
        userCache.invalidate("user");              // 从缓存中移除用户信息
    }
}