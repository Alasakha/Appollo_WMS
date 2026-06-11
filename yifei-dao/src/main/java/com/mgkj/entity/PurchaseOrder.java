package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yyyjcg
 * @since 2024-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PurchaseOrder对象", description="")
@TableName("PURCHASE_ORDER")
public class PurchaseOrder extends Model<PurchaseOrder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单号")
    private String docNo;

    @ApiModelProperty(value = "单据日期")
    private String docDate;

    @ApiModelProperty(value = "单据类型")
    private String docId;

    @ApiModelProperty(value = "关联部门")
    @TableField("Owner_Dept")
    private String ownerDept;

    @ApiModelProperty(value = "关联员工")
    @TableField("Owner_Emp")
    private String ownerEmp;

    @ApiModelProperty(value = "主键")
    @TableId(value = "PURCHASE_ORDER_ID")
    private String purchaseOrderId;

    @ApiModelProperty(value = "单据性质")
    private String category;

    @ApiModelProperty(value = "订单日期")
    private String purchaseDate;

    @ApiModelProperty(value = "供应商全称")
    private String supplierFullName;

    @ApiModelProperty(value = "供应商联系人")
    private String supplierContactName;

    @ApiModelProperty(value = "供应商地址")
    private String supplierAddrName;

    @ApiModelProperty(value = "匯率")
    private Double exchangeRate;

    @ApiModelProperty(value = "含税")
    private Boolean taxIncluded;

    @ApiModelProperty(value = "多收货工厂")
    private Boolean multiPlant;

    @ApiModelProperty(value = "发货联系人")
    private String deliveryContactName;

    @ApiModelProperty(value = "发货地址")
    private String deliveryAddrName;

    @ApiModelProperty(value = "允许分批交货")
    private Boolean partialDelivery;

    @ApiModelProperty(value = "结算联系人")
    private String invoiceContactName;

    @ApiModelProperty(value = "结算地址")
    private String invoiceAddrName;

    @ApiModelProperty(value = "供应商单号")
    private String supplierOrderNo;

    @ApiModelProperty(value = "原币未税金额")
    private Double amountUnincludeTaxOc;

    @ApiModelProperty(value = "原币税额")
    private Double taxOc;

    @ApiModelProperty(value = "本币未税金额")
    private Double amountUnincludeTaxBc;

    @ApiModelProperty(value = "本币税额")
    private Double taxBc;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "采购类型")
    private String purchaseType;

    @ApiModelProperty(value = "供应商直运")
    private Boolean directShipment;

    @ApiModelProperty(value = "分期付款")
    private Boolean installment;

    @ApiModelProperty(value = "分期类型")
    private String installmentType;

    @ApiModelProperty(value = "订金")
    private Double earnest;

    private Double earnestRate;

    @ApiModelProperty(value = "订金结算方式")
    private String earnestSettlementType;

    @ApiModelProperty(value = "增值稅率")
    private Double taxRate;

    @ApiModelProperty(value = "预结算无税金额(原币)")
    private Double preSettlementAmtUnTaxOc;

    @ApiModelProperty(value = "预结算税额（原币）")
    private Double preSettlementTaxOc;

    @ApiModelProperty(value = "发票种类")
    private String taxInvoiceCategoryId;

    @ApiModelProperty(value = "订单公司")
    private String companyId;

    @ApiModelProperty(value = "供应商")
    private String supplierId;

    @ApiModelProperty(value = "联系人编码")
    private String supplierContactId;

    @ApiModelProperty(value = "地址编码")
    private String supplierAddrId;

    @ApiModelProperty(value = "币种")
    private String currencyId;

    @ApiModelProperty(value = "付款条件")
    private String paymentTermId;

    @ApiModelProperty(value = "收货工厂")
    private String plantId;

    @ApiModelProperty(value = "运输方式")
    private String deliveryTermId;

    @ApiModelProperty(value = "发货联系人编码")
    private String deliveryContactId;

    @ApiModelProperty(value = "发货地址编码")
    private String deliveryAddrId;

    @ApiModelProperty(value = "结算公司")
    private String invoiceCompanyId;

    @ApiModelProperty(value = "结算供应商")
    private String invoiceSupplierId;

    @ApiModelProperty(value = "结算联系人编码")
    private String invoiceContactId;

    @ApiModelProperty(value = "结算地址编码")
    private String invoiceAddrId;

    @ApiModelProperty(value = "采购合同")
    private String purchaseContractId;

    @ApiModelProperty(value = "税种")
    private String taxId;

    @ApiModelProperty(value = "已冲减原币未税金额")
    private Double offsetedAmtUnTaxOc;

    @ApiModelProperty(value = "已冲减原币税额")
    private Double offsetedTaxOc;

    @ApiModelProperty(value = "已结算原币未税金额")
    private Double settlementAmtUnTaxOc;

    @ApiModelProperty(value = "已结算原币税额")
    private Double settlementTaxOc;

    @ApiModelProperty(value = "需结算已入库原币未税金额")
    private Double receiptedAmtUnTaxOc;

    @ApiModelProperty(value = "需结算已入库原币税额")
    private Double receiptedTaxOc;

    @ApiModelProperty(value = "收货地址")
    private String plantAddress;

    @ApiModelProperty(value = "件数")
    private Integer pieces;

    @ApiModelProperty(value = "未结算预冲减原币未税金额")
    private Double unsettlePoffsetAmtUnTaxOc;

    @ApiModelProperty(value = "未结算预冲减原币税额")
    private Double unsettlePoffsetTaxOc;

    @ApiModelProperty(value = "未结算已到货原币未税金额")
    private Double unsettleArrAmtUnTaxOc;

    @ApiModelProperty(value = "未结算已到货原币税额")
    private Double unsettleArrTaxOc;

    @ApiModelProperty(value = "供应协同关系")
    private String supplySynergyId;

    @ApiModelProperty(value = "结束码")
    private String close;

    @ApiModelProperty(value = "WIP集成抛转传入ID")
    private String wipNo;

    @ApiModelProperty(value = "限用项目")
    private String projectId;

    @ApiModelProperty(value = "整单协同")
    private Boolean allSynergy;

    @ApiModelProperty(value = "协同序号")
    private String groupSynergyDId;

    @ApiModelProperty(value = "抛转单号")
    private String generateNo;

    @ApiModelProperty(value = "抛转状态")
    private Boolean generateStatus;

    @ApiModelProperty(value = "单据顺序")
    @TableField("DOC_Sequence")
    private Integer docSequence;

    @ApiModelProperty(value = "最终供应商")
    private String sourceSupplierId;

    private String sp001;

    @ApiModelProperty(value = "版本号，不要随意更改")
    @TableField("Version")
    private String version;

    @ApiModelProperty(value = "打印次数")
    @TableField("PrintCount")
    private Integer printcount;

    @ApiModelProperty(value = "创建日期")
    @TableField("CreateDate")
    private String createdate;

    @ApiModelProperty(value = "最后修改日期")
    @TableField("LastModifiedDate")
    private String lastmodifieddate;

    @ApiModelProperty(value = "修改日期")
    @TableField("ModifiedDate")
    private String modifieddate;

    @ApiModelProperty(value = "创建者")
    @TableField("CreateBy")
    private String createby;

    @ApiModelProperty(value = "最后修改者")
    @TableField("LastModifiedBy")
    private String lastmodifiedby;

    @ApiModelProperty(value = "修改者")
    @TableField("ModifiedBy")
    private String modifiedby;

    @ApiModelProperty(value = "附件")
    @TableField("Attachments")
    private String attachments;

    @ApiModelProperty(value = "表单所在的流程实例的编号")
    @TableField("ProcessInstanceId")
    private String processinstanceid;

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
    private String udf041;

    @ApiModelProperty(value = "自定义字段13")
    private String udf042;

    @ApiModelProperty(value = "自定义字段14")
    private String udf051;

    @ApiModelProperty(value = "自定义字段15")
    private String udf052;

    @ApiModelProperty(value = "自定义字段16")
    private String udf053;

    @ApiModelProperty(value = "自定义字段17")
    private String udf054;

    @ApiModelProperty(value = "EF签核码")
    @TableField("EFNETStatus")
    private String efnetstatus;

    @ApiModelProperty(value = "签核业务动作")
    @TableField("EFNETAction")
    private String efnetaction;

    @ApiModelProperty(value = "EFNET单别")
    @TableField("EFNETDOCCategory")
    private String efnetdoccategory;

    @ApiModelProperty(value = "EFNET单号")
    @TableField("EFNETDOCNumber")
    private String efnetdocnumber;

    @ApiModelProperty(value = "单据状态属性")
    @TableField("ApproveStatus")
    private String approvestatus;

    @ApiModelProperty(value = "修改日期")
    @TableField("ApproveDate")
    private String approvedate;

    @ApiModelProperty(value = "修改人")
    @TableField("ApproveBy")
    private String approveby;

    @TableField("Owner_Org_RTK")
    private String ownerOrgRtk;

    @TableField("Owner_Org_ROid")
    private String ownerOrgRoid;

    private String revOrgSourceIdRtk;

    @TableField("REV_ORG_SOURCE_ID_ROid")
    private String revOrgSourceIdRoid;

    private String groupSynergyIdRtk;

    @TableField("GROUP_SYNERGY_ID_ROid")
    private String groupSynergyIdRoid;

    private String sourceOrderRtk;

    @TableField("SOURCE_ORDER_ROid")
    private String sourceOrderRoid;


//    @Override
//    protected Serializable pkVal() {
//        return this.purchaseOrderId;
//    }
    @Override
    public Serializable pkVal() {
        return this.purchaseOrderId;
    }

}
