package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "无源出入库员工部门信息Vo")
public class PassiveEmployeeInfoVo {


    @ApiModelProperty(value = "员工编号")
    private String employeeCode="";

    @ApiModelProperty(value = "员工姓名")
    private String employeeName="";

    @ApiModelProperty(value = "部门编号")
    private String departmentCode="";

    @ApiModelProperty(value = "部门名称")
    private String departmentName= "";

}
