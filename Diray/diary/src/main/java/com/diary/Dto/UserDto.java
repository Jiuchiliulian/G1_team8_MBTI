package com.diary.Dto;

import com.diary.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value="UserDto", description="用户返回对象")
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty("user_info")
    private User userInfo;

    private String token;
}

