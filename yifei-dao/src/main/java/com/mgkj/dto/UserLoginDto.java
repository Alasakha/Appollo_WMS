package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "用户登录Dto")
public class UserLoginDto {

    @ApiModelProperty(value = "登录者编号", required = true)
    private String userId;

    @ApiModelProperty(value = "口令", required = true)
    private String password;

    @ApiModelProperty(value = "旧口令（这里登入的时候不用传）")
    private String oldpassword;

}
