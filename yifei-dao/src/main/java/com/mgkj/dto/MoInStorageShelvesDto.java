package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "入库上架提交Dto")
public class MoInStorageShelvesDto {
    @ApiModelProperty(value = "工单单号",required = true)
    private String docNo;

    @ApiModelProperty(value = "成品条码",required = true)
    private String barcode;

//    @ApiModelProperty("品名")
//    private String itemName;

    @ApiModelProperty(value = "品号",required = true)
    private String itemCode;

    @ApiModelProperty(value = "单位",required = true)
    private String unitNo;

    //    @ApiModelProperty("单据量")
//    private Double sourceDocQty;
//
//    @ApiModelProperty("申请量")
//    private Double applyQty;
//
//    @ApiModelProperty("库存量")
//    private Double stockQty;
    @ApiModelProperty(value = "库位编码",required = false)
    @JSONField(ordinal = 3)
    private String binCode;

    @ApiModelProperty(value = "仓库编码",required = true)
    @JSONField(ordinal = 3)
    private String warehouseCode = "";

    @ApiModelProperty(value = "匹配量",required = true)
    private BigDecimal matchQty;

    @ApiModelProperty("拒收数量")
    @JSONField(ordinal = 3)
    private BigDecimal rejectQty = BigDecimal.ZERO;

    @ApiModelProperty("报废数量")
    @JSONField(ordinal = 3)
    private BigDecimal scrapQty = BigDecimal.ZERO;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;

    @ApiModelProperty(value = "批号说明(炉号)")
    private String lotDesc;

//    @ApiModelProperty(value = "批次说明")
//    private String lotDesc;

}
