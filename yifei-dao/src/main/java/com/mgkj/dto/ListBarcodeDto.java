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
 * @date 2024/4/9
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "标签补打Dto")
public class ListBarcodeDto {
    @ApiModelProperty(value = "料号",required = true)
    private String itemCode;

    @ApiModelProperty(value = "单号",required = true)
    private String docNo;

    @ApiModelProperty(value = "品名",required = true)
    private String itemName;

    @ApiModelProperty(value = "规格",required = true)
    private String itemSpec;

    @ApiModelProperty(value = "日期",required = true)
    private String docDate;

    @ApiModelProperty(value = "供应商",required = true)
    private String supplier;


}
