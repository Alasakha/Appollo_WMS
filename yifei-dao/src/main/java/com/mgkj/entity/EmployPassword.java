package com.mgkj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployPassword {

    @ApiModelProperty(value = "员工id")
    private String ID;

    @ApiModelProperty(value = "员工账号")
    private String MV001;

    @ApiModelProperty(value = "员工姓名")
    private String MV002;

    @ApiModelProperty(value = "员工密码")
    private String UDF01;
}
