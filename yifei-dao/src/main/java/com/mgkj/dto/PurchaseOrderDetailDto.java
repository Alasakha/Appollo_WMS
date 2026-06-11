package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/2/27
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购单详情Dto")
public class PurchaseOrderDetailDto {
    @ApiModelProperty("采购单号")
    @JSONField(ordinal = 1)
    private String docNo;

    @ApiModelProperty("供应商名称,编号可以拿1009测试")
    @JSONField(ordinal = 2)
    private String supplierFullName;

    @ApiModelProperty("开始日期,格式yyyy-MM-dd")
    @JSONField(ordinal = 3)
    private String startDate;

    @ApiModelProperty("开始日期，格式yyyy-MM-dd")
    @JSONField(ordinal = 3)
    private String endDate;
}
