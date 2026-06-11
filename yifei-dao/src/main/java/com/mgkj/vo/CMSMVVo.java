package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "员工登录 Vo")
public class CMSMVVo {

    @ApiModelProperty(value = "员工uuid")
    private String userId;

    @ApiModelProperty(value = "员工编号")
    private String MV001;

    @ApiModelProperty(value = "员工密码")
    private String UDF01;

    @ApiModelProperty(value = "员工姓名")
    private String MV002;

    @ApiModelProperty(value = "部门uuid")
    private String departmentUuid;

    @ApiModelProperty(value = "部门Code")
    private String departmentCode;

    @ApiModelProperty(value = "部门Name")
    private String departmentName;

    @ApiModelProperty(value = "工号对应uuid")
    private String employeeId;

    @ApiModelProperty(value = "员工工号")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
}
