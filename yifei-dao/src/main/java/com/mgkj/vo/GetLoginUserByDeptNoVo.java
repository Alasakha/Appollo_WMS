package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "GetLoginUserByDeptNoVo")
public class GetLoginUserByDeptNoVo {

    @ApiModelProperty(value = "用户编号")
    private String userNo;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "部门")
    private String deptName;

//    @ApiModelProperty(value = "部门代号")
//    private String deptNo;

}
