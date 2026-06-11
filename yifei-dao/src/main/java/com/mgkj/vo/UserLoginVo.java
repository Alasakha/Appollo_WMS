package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "员工登录Vo")
public class UserLoginVo {
    private String token;
    private String name;
    private String message;
}
