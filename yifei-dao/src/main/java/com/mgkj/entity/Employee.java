package com.mgkj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Employee {
    @ApiModelProperty(value = "员工账号")
    private String MV001;

    @ApiModelProperty(value = "员工姓名")
    private String MV002;
}
