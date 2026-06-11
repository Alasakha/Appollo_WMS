package com.mgkj.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/12
 * @Description
 */
@Data
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单发料详情Vo")
public class MoDetailVo {
    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("料名")
    private String itemName;

    @ApiModelProperty("品名")
    private String productName;

    @ApiModelProperty("时间")
    private String docDate;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("品号")
    private String productCode;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("单据量（总共要领的数量）")
    private Double sourceDocQty;

    @ApiModelProperty("已领用量")
    private Double issuedQty;

    @ApiModelProperty("申请量（本次最大可领取数量）")
    private Double applyQty;

    @ApiModelProperty("库存量")
    private Double stockQty;

    @JSONField(name = "warehouseCode")
    @ApiModelProperty("仓库编号")
    private String warehouseCode;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库位")
    private String binCode;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("备注")
    private String remark;
//    @ApiModelProperty("数量")
//    private Double matchQty;
}
