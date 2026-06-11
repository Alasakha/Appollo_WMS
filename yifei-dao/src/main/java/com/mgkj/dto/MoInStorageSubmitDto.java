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
@ApiModel(value = "工单入库提交Dto")
public class MoInStorageSubmitDto {

    @ApiModelProperty(value = "id",required = true)
    private String id;

    @ApiModelProperty(value = "工单单号",required = true)
    private String docNo;

    @ApiModelProperty(value = "生产入库单号",required = true)
    private String moReceiptNo;

    @ApiModelProperty(value = "成品条码",required = true)
    private String barcode;

    @ApiModelProperty(value = "品号",required = true)
    private String itemCode;

    @ApiModelProperty(value = "单位",required = true)
    private String unitNo;

    @ApiModelProperty("单位名称")
    private String unitName;

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

    @ApiModelProperty(value = "批号")
    private String lotNumber;

    @ApiModelProperty(value = "收货机构(工厂编号)",required = true)
    private String shjg;

}
