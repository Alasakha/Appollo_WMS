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
 * @date 2024/4/15
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("出货下架-简洁查询dto")
public class ListSaleOrderOutStockDto {

    @ApiModelProperty("订单单号")
    private String docNo;
    @ApiModelProperty("条码")
    private String barCode;
    @ApiModelProperty("客户")
    private String customer;
    @ApiModelProperty("业务员")
    private String employee;
    @ApiModelProperty("业务部门")
    private String department;
    @ApiModelProperty("起始日期")
    private String startDate;
    @ApiModelProperty("结束日期")
    private String endDate;

}
