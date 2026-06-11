package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/12
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单发料DetailDto")
public class MoDetailDto {
    @ApiModelProperty("工单单号")
    @JSONField(ordinal = 1)
    private String docNo;

//    @ApiModelProperty("条码")
//    @JSONField(ordinal = 2)
//    private String barcode;
    @ApiModelProperty("起始时间")
    @JSONField(ordinal = 1)
    private String startDate;

    @ApiModelProperty("结束时间")
    @JSONField(ordinal = 1)
    private String endDate;
}
