package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

/**
 * @author yyyjcg
 * @date 2024/1/24
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购到货单Dto")
public class PurchaseArrivalDto {

    @ApiModelProperty("单据性质")
    @JSONField(ordinal = 1)
    private String docCode;

    @ApiModelProperty("采购单号")
    @JSONField(ordinal = 2)
    private String docNo;

    @ApiModelProperty("品号")
    @JSONField(ordinal = 3)
    private String itemId;

    @ApiModelProperty("品名")
    @JSONField(ordinal = 4)
    private String itemDescription;

    @ApiModelProperty("规格")
    @JSONField(ordinal = 5)
    private String itemSpecification;

    @ApiModelProperty(value = "到货数量")
    @JSONField(ordinal = 6)
    private Double arrivalQty;

    @ApiModelProperty(value = "业务时间")
    @JSONField(ordinal = 7)
    private LocalDateTime businessTime;

    @ApiModelProperty(value = "入库仓库名称")
    @JSONField(ordinal = 8)
    private String warehouseCode;

    @ApiModelProperty(value = "库位号")
    @JSONField(ordinal = 9)
    private String binCode;

}
