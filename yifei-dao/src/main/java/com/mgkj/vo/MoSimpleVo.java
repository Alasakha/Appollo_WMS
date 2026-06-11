package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/11
 * @Description
 */
@Data
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单发料simpleVo")
public class MoSimpleVo {
    @ApiModelProperty("工单单号")
    private String docNo;
    @ApiModelProperty("日期")
    private String docDate;
    @ApiModelProperty("品号")
    private String itemNo;
    @ApiModelProperty("品名")
    private String itemName;
    @ApiModelProperty("部门/厂商")
    private String departmentName;

}
