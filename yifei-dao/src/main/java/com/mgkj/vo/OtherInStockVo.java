package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/10
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel("其他入库Vo")
public class OtherInStockVo {

    @ApiModelProperty("单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("部门编号")
    private String departmentNo;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("员工编号")
    private String employeeNo;

    @ApiModelProperty("员工名称")
    private String employeeName;
}
