package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/10
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiOperation("其他入库查询Dto")
public class OtherInStockDto {
    @ApiModelProperty(value = "杂收单号",required = true)
    private String docNo;

    @ApiModelProperty(value = "员工",required = true)
    private String employee;

    @ApiModelProperty(value = "部门",required = true)
    private String department;

    @ApiModelProperty(value = "开始时间",required = true)
    private String startDate;

    @ApiModelProperty(value = "结束时间",required = true)
    private String endDate;
}
