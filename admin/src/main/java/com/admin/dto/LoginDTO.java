package com.admin.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class LoginDTO implements Serializable {
    @NotNull(message = "用户名不能为空")
    private String loginName;
    @NotNull(message = "密码不能为空")
    private  String password;
}
