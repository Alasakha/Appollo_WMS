package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yyyjcg
 * @since 2024-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PurchaseOrderD对象", description="")
public class PurchaseOrderD extends Model<PurchaseOrderD> {

    private static final long serialVersionUID = 1L;

    @TableField("SequenceNumber")
    private Integer sequencenumber;

    @ApiModelProperty(value = "主键")
      @TableId(value = "PURCHASE_ORDER_D_ID", type = IdType.AUTO)
    private String purchaseOrderDId;

    @ApiModelProperty(value = "品号")
    private String itemId;

    @ApiModelProperty(value = "品名")
    private String itemDescription;

    @ApiModelProperty(value = "规格")
    private String itemSpecification;

    @ApiModelProperty(value = "商品类型")
    private String itemType;

    @ApiModelProperty(value = "大包装数量")
    private Double packingQty1;

    @ApiModelProperty(value = "中包装数量")
    private Double packingQty2;

    @ApiModelProperty(value = "小包装数量")
    private Double packingQty3;

    @ApiModelProperty(value = "最小包装数量")
    private Double packingQty4;

    @ApiModelProperty(value = "包装数量描述")
    private String packingQty;

    @ApiModelProperty(value = "业务数量")
    private Double businessQty;

    @ApiModelProperty(value = "计价数量")
    private Double priceQty;

    @ApiModelProperty(value = "库存单位数量")
    private Double inventoryQty;

    @ApiModelProperty(value = "第二数量")
    private Double secondQty;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "折扣率")
    private Double discountRate;

    @ApiModelProperty(value = "折扣后单价")
    private Double discountedPrice;

    @ApiModelProperty(value = "标准单价")
    private Double standardPrice;

    @ApiModelProperty(value = "折扣额")
    private Double discountAmt;

    @ApiModelProperty(value = "金额")
    private Double amount;

    @ApiModelProperty(value = "增值稅率")
    private Double taxRate;

    @ApiModelProperty(value = "原币未税金额")
    private Double amountUnincludeTaxOc;

    @ApiModelProperty(value = "原币税额")
    private Double taxOc;

    @ApiModelProperty(value = "本币未税金额")
    private Double amountUnincludeTaxBc;

    @ApiModelProperty(value = "本币税额")
    private Double taxBc;

    @ApiModelProperty(value = "预到货日")
    private LocalDateTime planArrivalDate;

    @ApiModelProperty(value = "多交期")
    private Boolean multiArrivalDate;

    @ApiModelProperty(value = "品牌信息")
    private String manufacturer;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "采购类型")
    private String purchaseType;

    @ApiModelProperty(value = "移转生成")
    private Boolean transferGenerate;

    @ApiModelProperty(value = "预结算计价数量")
    private Double preSettlementPriceQty;

    @ApiModelProperty(value = "已结算计价数量")
    private Double settlementPriceQty;

    @ApiModelProperty(value = "已结算原币税额")
    private Double settlementTaxOc;

    @ApiModelProperty(value = "预结算无税金额(原币)")
    private Double preSettlementAmtUnTaxOc;

    @ApiModelProperty(value = "预结算税额(原币)")
    private Double preSettlementTaxOc;

    @ApiModelProperty(value = "需结算已入库计价数量")
    private Double receiptedPriceQty;

    @ApiModelProperty(value = "已结算无税金额(原币)")
    private Double settlementAmtUnTaxOc;

    @ApiModelProperty(value = "结算状态")
    private String settlementClose;

    @ApiModelProperty(value = "特征码")
    private String itemFeatureId;

    @ApiModelProperty(value = "包装方式")
    private String packingModeId;

    @ApiModelProperty(value = "大包装单位")
    private String packing1UnitId;

    @ApiModelProperty(value = "中包装单位")
    private String packing2UnitId;

    @ApiModelProperty(value = "小包装单位")
    private String packing3UnitId;

    @ApiModelProperty(value = "最小包装单位")
    private String packing4UnitId;

    @ApiModelProperty(value = "业务单位")
    private String businessUnitId;

    @ApiModelProperty(value = "计价单位")
    private String priceUnitId;

    @ApiModelProperty(value = "税种")
    private String taxId;

    @ApiModelProperty(value = "认可文号")
    private String itemCertificationDId;

    @ApiModelProperty(value = "工艺")
    private String operationId;

    @ApiModelProperty(value = "已冲减计价数量")
    private Double offsetedPriceQty;

    @ApiModelProperty(value = "件数")
    private Integer pieces;

    @ApiModelProperty(value = "直供")
    private Boolean directSupply;

    @ApiModelProperty(value = "项目")
    private String projectId;

    @ApiModelProperty(value = "订单图片")
    private String orderPic;

    @ApiModelProperty(value = "WIP/MES集成移转单单号序号")
    private String wipDocno;

    @ApiModelProperty(value = "预计入库日")
    private LocalDateTime supplyDate;

    private String apsDocNo;

    private Boolean spApproveFlag;

    @ApiModelProperty(value = "版本号，不要随意更改")
    @TableField("Version")
    private LocalDateTime version;

    @ApiModelProperty(value = "创建日期")
    @TableField("CreateDate")
    private LocalDateTime createdate;

    @ApiModelProperty(value = "最后修改日期")
    @TableField("LastModifiedDate")
    private LocalDateTime lastmodifieddate;

    @ApiModelProperty(value = "修改日期")
    @TableField("ModifiedDate")
    private LocalDateTime modifieddate;

    @ApiModelProperty(value = "创建者")
    @TableField("CreateBy")
    private String createby;

    @ApiModelProperty(value = "最后修改者")
    @TableField("LastModifiedBy")
    private String lastmodifiedby;

    @ApiModelProperty(value = "修改者")
    @TableField("ModifiedBy")
    private String modifiedby;

    @ApiModelProperty(value = "自定义字段0")
    private Double udf001;

    @ApiModelProperty(value = "自定义字段1")
    private Double udf002;

    @ApiModelProperty(value = "自定义字段2")
    private Double udf003;

    @ApiModelProperty(value = "自定义字段3")
    private Double udf011;

    @ApiModelProperty(value = "自定义字段4")
    private Double udf012;

    @ApiModelProperty(value = "自定义字段5")
    private Double udf013;

    @ApiModelProperty(value = "自定义字段6")
    private String udf021;

    @ApiModelProperty(value = "自定义字段7")
    private String udf022;

    @ApiModelProperty(value = "自定义字段8")
    private String udf023;

    @ApiModelProperty(value = "自定义字段9")
    private String udf024;

    @ApiModelProperty(value = "自定义字段10")
    private String udf025;

    @ApiModelProperty(value = "自定义字段11")
    private String udf026;

    @ApiModelProperty(value = "自定义字段12")
    private LocalDateTime udf041;

    @ApiModelProperty(value = "自定义字段13")
    private LocalDateTime udf042;

    @ApiModelProperty(value = "自定义字段14")
    private String udf051;

    @ApiModelProperty(value = "自定义字段15")
    private String udf052;

    @ApiModelProperty(value = "自定义字段16")
    private String udf053;

    @ApiModelProperty(value = "自定义字段17")
    private String udf054;

    @ApiModelProperty(value = "单据状态属性")
    @TableField("ApproveStatus")
    private String approvestatus;

    @ApiModelProperty(value = "修改日期")
    @TableField("ApproveDate")
    private LocalDateTime approvedate;

    @ApiModelProperty(value = "修改人")
    @TableField("ApproveBy")
    private String approveby;

    private String sourceIdRtk;

    @TableField("SOURCE_ID_ROid")
    private String sourceIdRoid;

    private String referenceSourceIdRtk;

    @TableField("REFERENCE_SOURCE_ID_ROid")
    private String referenceSourceIdRoid;

    private String sourceOrderRtk;

    @TableField("SOURCE_ORDER_ROid")
    private String sourceOrderRoid;

    private String purchaseOrderId;


//    @Override
//    protected Serializable pkVal() {
//        return this.purchaseOrderDId;
//    }
    @Override
    public Serializable pkVal() {
        return this.purchaseOrderDId;
    }

}
