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
 * @date 2024/5/17
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("库存调拨查询dto")
public class ListTransferDocDto {
    @ApiModelProperty("调拨单号")
    private String docNo;

    @ApiModelProperty("申请人")
    private String employee;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private String endDate;
}
