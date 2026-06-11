package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "GetLoginUserByDeptNoDto")
public class GetLoginUserByDeptNoDto {

    @ApiModelProperty("部门编号")
    private String deptNo;

    @ApiModelProperty(value = "用户编号")
    private String userNo;

    @ApiModelProperty(value = "用户名称")
    private String userName;

}
