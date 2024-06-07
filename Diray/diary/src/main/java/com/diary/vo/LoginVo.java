package com.diary.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel(value = "LoginVo", description = "获取登录入口入参")
public class LoginVo implements Serializable {


    @JsonProperty("id")
    private String id;

    @NotEmpty(message = "账户不能为空")
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @JsonProperty("password")
    private String password;



}
