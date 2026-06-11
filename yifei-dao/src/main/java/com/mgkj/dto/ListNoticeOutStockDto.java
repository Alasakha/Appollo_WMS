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
 * @date 2024/4/16
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("通知下架Dto")
public class ListNoticeOutStockDto {

    @ApiModelProperty("出通单号")
    private String docNo;

    @ApiModelProperty("客户")
    private String customer;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("业务员")
    private String employee;

    @ApiModelProperty("条码")
    private String barCode;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("起始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;

}
