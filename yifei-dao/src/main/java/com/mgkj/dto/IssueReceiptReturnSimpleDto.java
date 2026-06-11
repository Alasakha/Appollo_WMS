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
 * @date 2024/3/20
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "退料上架simpleDto")
public class IssueReceiptReturnSimpleDto {
    @ApiModelProperty("退料单号")
    private String docNo;
    @ApiModelProperty("条码")
    private String barcode;
    @ApiModelProperty("部门")
    private String department;
    @ApiModelProperty("人员")
    private String employee;
    @ApiModelProperty("起始日期")
    private String startDate;
    @ApiModelProperty("结束日期")
    private String endDate;
}
