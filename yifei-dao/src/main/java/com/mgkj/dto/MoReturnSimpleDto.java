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
 * @date 2024/3/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "工单退料simpleDto")
public class MoReturnSimpleDto {
    @ApiModelProperty("工单单号")
    private String docNo;

//    @ApiModelProperty("成品条码")
//    private String barcode;

    @ApiModelProperty("成品")
    private String item;

    @ApiModelProperty("下阶料")
    private String lowItem;

    @ApiModelProperty("部门/厂商")
    private String departmentName;

    @ApiModelProperty("起始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;

    @ApiModelProperty("工厂编号")
    private String plantCode;

    @ApiModelProperty("工单单据类型(5106/5156)")
    private String docType;

    @ApiModelProperty("工作中心")
    private String workCenter;

    @ApiModelProperty("车架号")
    private String frameNo;
}
