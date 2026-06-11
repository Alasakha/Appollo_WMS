package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

/**
 * @author yyyjcg
 * @date 2024/3/6
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购收货提交DTO")
public class PurchaseReceiptStorageDto {

    @ApiModelProperty(value = "收货机构(工厂号)",required = true)
    private String shjg;

    @ApiModelProperty(value = "条码",required = true)
    @JSONField(ordinal = 2)
    private String barcode;

    @ApiModelProperty(value = "仓库编码",required = true)
    @JSONField(ordinal = 3)
    private String warehouseCode;

    @ApiModelProperty(value = "库位编码",required = true)
    @JSONField(ordinal = 3)
    private String binCode;

    @ApiModelProperty(value = "匹配量（数量）",required = true)
    @JSONField(ordinal = 3)
    private BigDecimal matchQty;

    @ApiModelProperty(value = "采购单号",required = true)
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;


//    @ApiModelProperty("是否扣账，Y:扣账，N:不扣账")
//    @JSONField(ordinal = 4)
//    private String isDeduct;

    @ApiModelProperty(value = "单位",required = true)
    @JSONField(ordinal = 3)
    private String unitCode;


    @ApiModelProperty(value = "品号",required = true)
    @JSONField(ordinal = 3)
    private String itemCode;

    @ApiModelProperty("拒收数量")
    @JSONField(ordinal = 3)
    private BigDecimal rejectQty = BigDecimal.ZERO;

    @ApiModelProperty("报废数量")
    @JSONField(ordinal = 3)
    private BigDecimal scrapQty = BigDecimal.ZERO;

    @ApiModelProperty(value = "备注",required = false)
    private String remark = "";


}