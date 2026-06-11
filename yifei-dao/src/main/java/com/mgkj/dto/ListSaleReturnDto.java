package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/17
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("采购仓退查询dto")
public class ListSaleReturnDto {
    @ApiModelProperty("退货单号")
    private String docNo;
    @ApiModelProperty("客户")
    private String costumer;
    @ApiModelProperty("业务员")
    private String employee;
    @ApiModelProperty("部门")
    private String department;
    @ApiModelProperty("起始日期")
    private String startDate;
    @ApiModelProperty("结束日期")
    private String endDate;
}
