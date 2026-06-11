package com.mgkj.vo;

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
@ApiModel("订单下架Vo")
public class ListSaleOrderOutStockVo {

    @ApiModelProperty("订单单号")
    private String docNo;

    @ApiModelProperty("订单日期")
    private String docDate;

    @ApiModelProperty("客户")
    private String customerName;

    @ApiModelProperty("销售部")
    private String departmentName;

    @ApiModelProperty("申请人")
    private String employeeName;

}
