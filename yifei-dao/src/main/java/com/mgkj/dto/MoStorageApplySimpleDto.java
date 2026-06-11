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
@ApiModel(value = "入库申请simpleDto")
public class MoStorageApplySimpleDto {

    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("成品条码")
    private String barCode;

    @ApiModelProperty("成品料号")
    private String itemCode;


    @ApiModelProperty("部门/厂商")
    private String departmentCode;

    @ApiModelProperty("起始日期")
    private String dateBegin;

    @ApiModelProperty("结束日期")
    private String dateEnd;
}
