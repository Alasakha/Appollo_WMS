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
 * @date 2024/3/18
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "入库上架simpleDto")
public class MoShelvesSimpleDto {
    @ApiModelProperty("入库来源")
    private String receiptSource;

    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("条码")
    private String barCode;

    @ApiModelProperty("申请人(可以编号可以名字)")
    private String applyEmployee;

    @ApiModelProperty("部门/厂商")
    private String department;

    @ApiModelProperty("起始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;
}
