package com.mgkj.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/1
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "到货检验单详情Vo")
public class PurchaseArrivalCheckVo {
    @ApiModelProperty("到货单号")
    @TableField("DOC_NO")
    @JSONField(ordinal = 1)
    private String docNo;

    @ApiModelProperty("条码")
    @JSONField(ordinal = 2)
    private String barcode;

    @ApiModelProperty("序号")
    @JSONField(ordinal = 2)
    private String xh;

    @ApiModelProperty("储位")
    @JSONField(ordinal = 3)
    private String storage;


    @ApiModelProperty("日期")
    @TableField("DOC_DATE")
    @JSONField(ordinal = 2)
    private String docDate;

    @ApiModelProperty("供应商编号")
    @JSONField(ordinal = 3)
    private String supplierCode;

    @ApiModelProperty("供应商名称")
    @JSONField(ordinal = 3)
    private String supplierName;

    @ApiModelProperty("料号")
    @JSONField(ordinal = 3)
    private String itemCode;

    @ApiModelProperty("品名")
    @JSONField(ordinal = 3)
    private String itemDescription;

    @ApiModelProperty("规格")
    @JSONField(ordinal = 3)
    private String itemSpecification;

    @ApiModelProperty("申请量")
    @JSONField(ordinal = 3)
    private Double applyQty;

    @ApiModelProperty("单位")
    @JSONField(ordinal = 3)
    private String unitCode;

    @ApiModelProperty("到货数量（匹配量）")
    @JSONField(ordinal = 3)
    private Double matchQty;

    @ApiModelProperty("拒收数量")
    @JSONField(ordinal = 3)
    private Double rejectQty ;

    @ApiModelProperty("报废数量")
    @JSONField(ordinal = 3)
    private Double scrapQty;

    @ApiModelProperty("仓库编码")
    @JSONField(ordinal = 3)
    private String warehouseCode = "";

    @ApiModelProperty("仓库名称")
    @JSONField(ordinal = 3)
    private String warehouseName = "";

    @ApiModelProperty("库位编码")
    @JSONField(ordinal = 3)
    private String binCode;

    @ApiModelProperty("库位名称")
    @JSONField(ordinal = 3)
    private String binName;

    @ApiModelProperty("检验数量")
    @JSONField(ordinal = 3)
    private String checkQty = "";

    @ApiModelProperty("备注")
    @JSONField(ordinal = 3)
    private String remark = "";

    @ApiModelProperty("待检验数量")
    @JSONField(ordinal = 3)
    private Double pendingCheckQty;

    @ApiModelProperty("检验状态")
    private String checkStatus;
}
