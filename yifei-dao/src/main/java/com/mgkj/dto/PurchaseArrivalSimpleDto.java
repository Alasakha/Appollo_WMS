package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/2/29
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableSwagger2
@ApiModel(value = "到货单Dto")
public class PurchaseArrivalSimpleDto {
    @ApiModelProperty("到货单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;

    @ApiModelProperty("条码")
    @JSONField(ordinal = 1)
    private String barCode;

    @ApiModelProperty("供应商编号")
    @TableField("SUPPLIER_CODE")
    @JSONField(ordinal = 2)
    private String supplierCode;

    @ApiModelProperty("日期")
    @TableField("DOC_DATE")
    @JSONField(ordinal = 3)
    private String docDate;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

}
