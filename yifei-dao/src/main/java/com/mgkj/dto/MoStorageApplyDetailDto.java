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
@ApiModel(value = "入库申请detailDto")
public class MoStorageApplyDetailDto {

    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("allowMultiOut,默认Y")
    private String allowMultiOut = "Y";
}
