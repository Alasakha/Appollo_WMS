package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 到货单/CHT/到貨單/ENU/Arrival Doc.
 * </p>
 *
 * @author yyyjcg
 * @since 2024-01-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PurchaseArrival对象")
public class PurchaseArrival extends Model<PurchaseArrival> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单号")
    private String docNo;

    @ApiModelProperty(value = "单据日期")
    private LocalDateTime docDate;

    @ApiModelProperty(value = "单据类型")
    private String docId;

    @ApiModelProperty(value = "关联部门")
    @TableField("Owner_Dept")
    private String ownerDept;

    @ApiModelProperty(value = "关联员工")
    @TableField("Owner_Emp")
    private String ownerEmp;

    @ApiModelProperty(value = "主键")
    @TableId(value = "PURCHASE_ARRIVAL_ID", type = IdType.AUTO)
    private String purchaseArrivalId;

    @ApiModelProperty(value = "单据性质")
    private String category;

    @ApiModelProperty(value = "到货日期")
    private LocalDateTime arrivalDate;

    @ApiModelProperty(value = "供应商全称")
    private String supplierFullName;

    @ApiModelProperty(value = "供应商单号")
    private String supplierOrderNo;

    @ApiModelProperty(value = "匯率")
    private Double exchangeRate;

    @ApiModelProperty(value = "含税")
    private Boolean taxIncluded;

    private Integer pieces;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "供应商联系人")
    private String supplierContactName;

    @ApiModelProperty(value = "供应商地址")
    private String supplierAddrName;

    @ApiModelProperty(value = "结算联系人")
    private String invoiceContactName;

    @ApiModelProperty(value = "结算地址")
    private String invoiceAddrName;

    @ApiModelProperty(value = "原币未税金额")
    private Double amountUnincludeTaxOc;

    @ApiModelProperty(value = "原币税额")
    private Double taxOc;

    @ApiModelProperty(value = "本币未税金额")
    private Double amountUnincludeTaxBc;

    @ApiModelProperty(value = "本币税额")
    private Double taxBc;

    @ApiModelProperty(value = "来源别")
    private String sourceType;

    @ApiModelProperty(value = "税务发票号码")
    private String taxInvoiceNo;

    @ApiModelProperty(value = "可抵扣VAT标识")
    private Boolean deductibleIndicator;

    @ApiModelProperty(value = "发票种类")
    private String taxInvoiceCategoryId;

    @ApiModelProperty(value = "供应商地址编号")
    private String supplierAddrId;

    @ApiModelProperty(value = "结算联系人编号")
    private String invoiceContactId;

    @ApiModelProperty(value = "结算地址编号")
    private String invoiceAddrId;

    @ApiModelProperty(value = "收货人")
    private String receiptEmployeeId;

    @ApiModelProperty(value = "收货工厂/储运")
    private String plantId;

    @ApiModelProperty(value = "币种")
    private String currencyId;

    @ApiModelProperty(value = "付款条件")
    private String paymentTermId;

    @ApiModelProperty(value = "运输方式")
    private String deliveryTermId;

    @ApiModelProperty(value = "供应商")
    private String supplierId;

    @ApiModelProperty(value = "供应商联系人编号")
    private String supplierContactId;

    @ApiModelProperty(value = "结算供应商")
    private String invoiceSupplierId;

    private String invoiceCompanyId;

    @ApiModelProperty(value = "内部协同关系")
    private String synergyId;

    @ApiModelProperty(value = "协同序号")
    private String synergyDId;

    @ApiModelProperty(value = "直接结账")
    private Boolean directSettlementIndicator;

    @ApiModelProperty(value = "随货附票")
    private Boolean directInvoicingIndicator;

    @ApiModelProperty(value = "订金冲减")
    private Boolean offsetDepositIndicator;

    @ApiModelProperty(value = "入库状态")
    private String receiptedStatus;

    @ApiModelProperty(value = "当前可结算")
    private Boolean settlementIndicator;

    @ApiModelProperty(value = "签收日期")
    private LocalDateTime signDate;

    @ApiModelProperty(value = "税种")
    private String taxId;

    @ApiModelProperty(value = "WIP集成ID")
    private String wipNo;

    @ApiModelProperty(value = "WIP集成由E10质检")
    private Boolean wipE10Inspection;

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

    private String generateSource;

    private String deliveryDocNo;

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

    @ApiModelProperty(value = "附件")
    @TableField("Attachments")
    private String attachments;

    @ApiModelProperty(value = "表单所在的流程实例的编号")
    @TableField("ProcessInstanceId")
    private String processinstanceid;

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

    @ApiModelProperty(value = "版本号，不要随意更改")
    @TableField("Version")
    private LocalDateTime version;

    @ApiModelProperty(value = "打印次数")
    @TableField("PrintCount")
    private Integer printcount;

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

    @TableField("Owner_Org_RTK")
    private String ownerOrgRtk;

    @TableField("Owner_Org_ROid")
    private String ownerOrgRoid;

    private String sourceIdRtk;

    @TableField("SOURCE_ID_ROid")
    private String sourceIdRoid;

    @TableField("RECEIVE_Owner_Org_RTK")
    private String receiveOwnerOrgRtk;

    @TableField("RECEIVE_Owner_Org_ROid")
    private String receiveOwnerOrgRoid;

    private String groupSynergyIdRtk;

    @TableField("GROUP_SYNERGY_ID_ROid")
    private String groupSynergyIdRoid;


//    @Override
//    protected Serializable pkVal() {
//        return this.purchaseArrivalId;
//    }
    @Override
    public Serializable pkVal() {
        return this.purchaseArrivalId;
    }

}
