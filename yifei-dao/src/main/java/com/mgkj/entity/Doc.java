package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * 单据性质/CHT/單據性質/ENU/Doc. Property
 * </p>
 *
 * @author yyyjcg
 * @since 2024-01-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Doc对象", description="单据性质/CHT/單據性質/ENU/Doc. Property")
public class Doc extends Model<Doc> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联部门")
    @TableField("Owner_Dept")
    private String ownerDept;

    @ApiModelProperty(value = "关联员工")
    @TableField("Owner_Emp")
    private String ownerEmp;

    @ApiModelProperty(value = "跨公司委托生产")
    private Boolean crossCopProduce;

    @ApiModelProperty(value = "单据名称")
    private String docName;

//    @ApiModelProperty(value = "单据全称")
//    private String docFullName;
//
//    @ApiModelProperty(value = "单据性质")
//    private String category;
//
//    private Boolean prmcFlag;
//
//    private Boolean eamcFlag;
//
//    @ApiModelProperty(value = "交易类别")
//    private String transactionType;
//
//    @ApiModelProperty(value = "库存影响")
//    private Integer stockAction;
//
//    @ApiModelProperty(value = "保留控制")
//    private String reservedControl;
//
//    @ApiModelProperty(value = "编码方式")
//    private String codingBy;
//
//    @ApiModelProperty(value = "年位数")
//    private String yearDigit;
//
//    @ApiModelProperty(value = "流水号位数")
//    private Integer sequenceDigit;
//
//    private String accountsSysPayDocId;
//
//    private Boolean prdLimitFlag;
//
//    private String prdLimit1;
//
//    private String prdLimit2;
//
//    @ApiModelProperty(value = "使用未生效已失效品号")
//    private Boolean useNotInForceLapsed;
//
//    @ApiModelProperty(value = "更新入库日")
//    private Boolean updateReceiptDate;
//
//    @ApiModelProperty(value = "更新出库日")
//    private Boolean updateIssueDate;
//
//    @ApiModelProperty(value = "更新盘点日")
//    private Boolean updateStocktakingDate;
//
//    @ApiModelProperty(value = "自动审核")
//    private Boolean autoApprove;
//
//    @ApiModelProperty(value = "自动过账")
//    private Boolean autoPost;
//
//    @ApiModelProperty(value = "自动打印")
//    private Boolean autoPrint;
//
//    @ApiModelProperty(value = "每页打印页脚")
//    private Boolean printFooterPerPage;
//
//    @ApiModelProperty(value = "打印时更改页脚")
//    private Boolean modifyFooterWhenPrinting;
//
//    @ApiModelProperty(value = "每页打印签核")
//    private Boolean printSignPerPage;
//
//    @ApiModelProperty(value = "打印时更改签核")
//    private Boolean modifySignWhenPrinting;
//
//    @ApiModelProperty(value = "每页打印合计")
//    private Boolean printSummaryPerPage;
//
//    @ApiModelProperty(value = "单据格式")
//    private String defaultFormat;
//
//    @ApiModelProperty(value = "打印时选择单据格式")
//    private Boolean selectFormatWhenPrinting;
//
//    @ApiModelProperty(value = "页脚")
//    private String defaultFooterId;
//
//    @ApiModelProperty(value = "签核")
//    private String defaultSingId;
//
//    @ApiModelProperty(value = "凭证指定打印机")
//    private String docSpecifiedPrinter;
//
//    @ApiModelProperty(value = "凭证名称")
//    private String voucherName;
//
//    @ApiModelProperty(value = "制单与审核可为同一人")
//    private Boolean inputorApproverSame;
//
//    @ApiModelProperty(value = "修改/作废他人凭证")
//    private Boolean allowEditOther;
//
//    @ApiModelProperty(value = "汇总打印")
//    private Boolean summaryPrint;
//
//    @ApiModelProperty(value = "页脚编号（此字段废除//建议单号：T001-151209002）")
//    private String defaultFooter;
//
//    @ApiModelProperty(value = "签核编号（此字段废除//建议单号：T001-151209002）")
//    private String defaultSign;
//
//    @ApiModelProperty(value = "备注")
//    private String remark;
//
//    @ApiModelProperty(value = "应用期间类型")
//    private String accountPeriodType;
//
//    @ApiModelProperty(value = "凭证回转方式")
//    private Integer reverseMode;
//
//    @ApiModelProperty(value = "启用凭证类型标识")
//    private Boolean oaccbookUseIndicator;
//
//    @ApiModelProperty(value = "运营账簿-应用已定义的科目约束-借方必有")
//    private Boolean oaccbookDrExist;
//
//    @ApiModelProperty(value = "运营账簿-应用已定义的科目约束-贷方必有")
//    private Boolean oaccbookCrExist;
//
//    @ApiModelProperty(value = "运营账簿-应用已定义的科目约束-凭证必有")
//    private Boolean oaccbookDocExist;
//
//    @ApiModelProperty(value = "运营账簿-应用已定义的科目约束-借方必无")
//    private Boolean oaccbookDrNoexist;
//
//    @ApiModelProperty(value = "运营账簿-应用已定义的科目约束-贷方必无")
//    private Boolean oaccbookCrNoexist;
//
//    @ApiModelProperty(value = "运营账簿-应用已定义的科目约束-凭证必无")
//    private Boolean oaccbookDocNoexist;
//
//    @ApiModelProperty(value = "管理账簿-启用凭证类型标识")
//    private Boolean maccbookUseIndicator;
//
//    @ApiModelProperty(value = "管理账簿-应用已定义的科目约束-借方必有")
//    private Boolean maccbookDrExist;
//
//    @ApiModelProperty(value = "管理账簿-应用已定义的科目约束-贷方必有")
//    private Boolean maccbookCrExist;
//
//    @ApiModelProperty(value = "管理账簿-应用已定义的科目约束-凭证必有")
//    private Boolean maccbookDocExist;
//
//    @ApiModelProperty(value = "管理账簿-应用已定义的科目约束-借方必无")
//    private Boolean maccbookDrNoexist;
//
//    @ApiModelProperty(value = "管理账簿-应用已定义的科目约束-贷方必无")
//    private Boolean maccbookCrNoexist;
//
//    @ApiModelProperty(value = "管理账簿-应用已定义的科目约束-凭证必无")
//    private Boolean maccbookDocNoexist;
//
//    @ApiModelProperty(value = "禁止手动编制凭证")
//    private Boolean manualNewIndicatory;
//
//    @ApiModelProperty(value = "允许凭证被他人回转")
//    private Boolean allowReverseOther;
//
//    @ApiModelProperty(value = "允许凭证被他人冲销")
//    private Boolean allowOffsetOther;
//
//    @ApiModelProperty(value = "期末处理事务")
//    private Integer endingTrans;
//
//    @ApiModelProperty(value = "允许凭证被他人删除")
//    private Boolean allowDeleteOther;
//
//    @ApiModelProperty(value = "允许凭证被他人作废")
//    private Boolean allowVoidOther;
//
//    @ApiModelProperty(value = "核对报价单")
//    private Boolean checkQuotation;
//
//    @ApiModelProperty(value = "核对合同")
//    private Boolean checkContract;
//
//    @ApiModelProperty(value = "核对订单")
//    private Boolean checkSalesOrder;
//
//    @ApiModelProperty(value = "可低于红线价格销售")
//    private Boolean underRedPrice;
//
//    @ApiModelProperty(value = "流水号位数")
//    private Integer lotSequenceDigit;
//
//    @ApiModelProperty(value = "ISSUE_OVERRUN")
//    private String issueOverrun;
//
//    @ApiModelProperty(value = "控制超入")
//    private String receiptOverrun;
//
//    @ApiModelProperty(value = "控制缺领")
//    private String issueShortage;
//
//    @ApiModelProperty(value = "类别")
//    private String processType;
//
//    @ApiModelProperty(value = "启用入库申请")
//    private Boolean checkReceiptRequistion;
//
//    @ApiModelProperty(value = "启用自动领料")
//    private Boolean autoIssue;
//
//    @ApiModelProperty(value = "控制超转")
//    private String transferOverrun;
//
//    @ApiModelProperty(value = "内部交易")
//    private Boolean internal;
//
//    @ApiModelProperty(value = "核对采购订单")
//    private Boolean checkPurchaseOrder;
//
//    @ApiModelProperty(value = "更新核价")
//    private Boolean allowApprovedPrice;
//
//    @ApiModelProperty(value = "VMI到/退货")
//    private Boolean vmi;
//
//    @ApiModelProperty(value = "自动生成生产入库单")
//    private Boolean autoMoReceipt;
//
//    @ApiModelProperty(value = "依工单领料")
//    private Boolean issueByMo;
//
//    @ApiModelProperty(value = "核销方式")
//    private Integer verificationMode;
//
//    @ApiModelProperty(value = "税务发票控制")
//    private Boolean taxControlIndicator;
//
//    @ApiModelProperty(value = "CASH_SETTLEMENT_INDICATOR")
//    private Boolean cashSettlementIndicator;
//
//    @ApiModelProperty(value = "分期收付款标识(作废)")
//    private Boolean installmenIndicator;
//
//    @ApiModelProperty(value = "启用限定用户策略")
//    private Boolean authorizedUsers;
//
//    @ApiModelProperty(value = "内部协同(作废)")
//    private Boolean innerSynergyIndicator;
//
//    @ApiModelProperty(value = "税务发票种类")
//    private String taxInvoiceCategoryId;
//
//    @ApiModelProperty(value = "自动领料单据类型")
//    private String autoIssueDocId;
//
//    @ApiModelProperty(value = "出库单据类型")
//    private String issueDocId;
//
//    @ApiModelProperty(value = "换货销货单据类型")
//    private String salesChangeDocId;
//
//    @ApiModelProperty(value = "退料入库单据类型")
//    private String returnDocId;
//
//    @ApiModelProperty(value = "批工单单据类型")
//    private String lotMoDocId;
//
//    @ApiModelProperty(value = "生产入库单据类型")
//    private String autoMoReceiptDocId;
//
//    @ApiModelProperty(value = "现结单据类型")
//    private String cashSettlementDocId;
//
//    @ApiModelProperty(value = "采购单据类型")
//    private String purchaseDocId;
//
//    @ApiModelProperty(value = "试产工单")
//    private Boolean eMo;
//
//    @ApiModelProperty(value = "批工单共用源工单单据类型")
//    private Boolean lotInheritSourceDoc;
//
//    @ApiModelProperty(value = "成本码")
//    private String costCode;
//
//    @ApiModelProperty(value = "非产出信息入库")
//    private Boolean receiptByMo;
//
//    @ApiModelProperty(value = "转移方式")
//    private String transferDirection;
//
//    @ApiModelProperty(value = "配送在途损失单据类型")
//    private String loss1Doc;
//
//    @ApiModelProperty(value = "退配在途损失单据类型")
//    private String loss2Doc;
//
//    @ApiModelProperty(value = "促销方法")
//    private String salePromotionMethods;
//
//    @ApiModelProperty(value = "折扣券互斥")
//    private Boolean ticketExclusive;
//
//    @ApiModelProperty(value = "按序转移")
//    private Boolean transferBySeq;
//
//    @ApiModelProperty(value = "零售转采")
//    private Boolean retailPo;
//
//    @ApiModelProperty(value = "促销适用单据")
//    private String promotionApplyDoc;
//
//    @ApiModelProperty(value = "自动投产")
//    private Boolean autoGio;
//
//    @ApiModelProperty(value = "受控出纳管理系统标识")
//    private Boolean nmcIndicator;
//
//    @ApiModelProperty(value = "审核后方可打印")
//    private Boolean printOnlyConfirmed;
//
//    @ApiModelProperty(value = "控制依据")
//    private String controlby;
//
//    @ApiModelProperty(value = "自动审核质检业务记录")
//    private Boolean autoApproveQc;
//
//    @ApiModelProperty(value = "默认单别")
//    private Boolean docDf;
//
//    @ApiModelProperty(value = "会员折扣互斥")
//    private Boolean memberExclusive;
//
//    @ApiModelProperty(value = "不含过期品")
//    private Boolean notMyBeOutOfDate;
//
//    @ApiModelProperty(value = "分期预收付冲减单据类型")
//    private String offsetInstalAdvDocId;
//
//    @ApiModelProperty(value = "委外时产生采购订单")
//    private Boolean autoPo;
//
//    @ApiModelProperty(value = "拣货核对")
//    private String bcCheck;
//
//    @ApiModelProperty(value = "自动生成工艺转移/入库单")
//    private Boolean autoWipTransfer;
//
//    @ApiModelProperty(value = "工艺转移单据类型")
//    private String wipTransferDocId;
//
//    @ApiModelProperty(value = "工艺入库单据类型")
//    private String wipReceiptDocId;
//
//    @ApiModelProperty(value = "补入质检结果方式")
//    private String qcResultInputType;
//
//    private String inspectMode;
//
//    @ApiModelProperty(value = "PLM集成默认单别")
//    private Boolean plmDocTag;
//
//    @ApiModelProperty(value = "出口贸易")
//    private Boolean exportControl;
//
//    @ApiModelProperty(value = "IFRS事务")
//    private Integer ifrsTrans;
//
//    @ApiModelProperty(value = "转换日差异调整标识")
//    private Boolean ifrsAdcFlag;
//
//    @ApiModelProperty(value = "报告账簿1-启用凭证类型标识")
//    private Boolean raccbook1UseIndicator;
//
//    @ApiModelProperty(value = "报告账簿1-启用科目约束-借方必有")
//    private Boolean raccbook1DrExist;
//
//    @ApiModelProperty(value = "报告账簿1-启用科目约束-贷方必有")
//    private Boolean raccbook1CrExist;
//
//    @ApiModelProperty(value = "报告账簿1-启用科目约束-凭证必有")
//    private Boolean raccbook1DocExist;
//
//    @ApiModelProperty(value = "报告账簿1-启用科目约束-借方必无")
//    private Boolean raccbook1DrNoexist;
//
//    @ApiModelProperty(value = "报告账簿1-启用科目约束-贷方必无")
//    private Boolean raccbook1CrNoexist;
//
//    @ApiModelProperty(value = "报告账簿1-启用科目约束-凭证必无")
//    private Boolean raccbook1DocNoexist;
//
//    @ApiModelProperty(value = "报告账簿2-启用凭证类型标识")
//    private Boolean raccbook2UseIndicator;
//
//    @ApiModelProperty(value = "报告账簿2-启用科目约束-借方必有")
//    private Boolean raccbook2DrExist;
//
//    @ApiModelProperty(value = "报告账簿2-启用科目约束-贷方必有")
//    private Boolean raccbook2CrExist;
//
//    @ApiModelProperty(value = "报告账簿2-启用科目约束-凭证必有")
//    private Boolean raccbook2DocExist;
//
//    @ApiModelProperty(value = "报告账簿2-启用科目约束-借方必无")
//    private Boolean raccbook2DrNoexist;
//
//    @ApiModelProperty(value = "报告账簿2-启用科目约束-贷方必无")
//    private Boolean raccbook2CrNoexist;
//
//    @ApiModelProperty(value = "报告账簿2-启用科目约束-凭证必无")
//    private Boolean raccbook2DocNoexist;
//
//    @ApiModelProperty(value = "超申请数量出库")
//    private Boolean overReqQty;
//
//    @ApiModelProperty(value = "核对询价单")
//    private Boolean checkInquiries;
//
//    @ApiModelProperty(value = "订单结束才可结算")
//    private Boolean orderCloseSettlement;
//
//    @ApiModelProperty(value = "赠/备品不核对")
//    private Boolean largessUncheck;
//
//    @ApiModelProperty(value = "抛转WIP/MES")
//    private Boolean transWip;
//
//    @ApiModelProperty(value = "损益结转标识")
//    private Boolean plCarryOverFlag;
//
//    @ApiModelProperty(value = "借贷平衡")
//    private Boolean entryBalanceFlag;
//
//    @ApiModelProperty(value = "补录报表数据")
//    private Boolean addedEntryStmtDataFlag;
//
//    @ApiModelProperty(value = "分录行允许负数")
//    private Boolean allowNegativeNumberFlag;
//
//    @ApiModelProperty(value = "工单变更预计产量可小于WIP已发放量")
//    private Boolean planQtyOverwip;
//
//    @ApiModelProperty(value = "允许分录行金额为负数")
//    private Boolean entryNegFlag;
//
//    @ApiModelProperty(value = "自动直接结账")
//    private Boolean autoSettlement;
//
//    @ApiModelProperty(value = "服务类型")
//    private String serviceTypeId;
//
//    @ApiModelProperty(value = "展料方式")
//    private String extractType;
//
//    @ApiModelProperty(value = "库存量不足仍产生领料单")
//    private Boolean isissue;
//
//    @ApiModelProperty(value = "议价状态")
//    private String negotiateType;
//
//    @ApiModelProperty(value = "需往来系统结算标识")
//    private Boolean accountsSysFlag;
//
    @ApiModelProperty(value = "单据类别")
    private String docCode;
//
//    @ApiModelProperty(value = "进口贸易")
//    private Boolean importControl;
//
//    @ApiModelProperty(value = "默认利息项目")
//    private String defInterestExpenseId;
//
//    @ApiModelProperty(value = "不足可用量产生新领料记录")
//    private Boolean isNewIssue;
//
//    @ApiModelProperty(value = "INVOICE编号原则")
//    private String invoiceNoRuleId;
//
//    @ApiModelProperty(value = "整单协同")
//    private Boolean allSynergy;
//
//    @ApiModelProperty(value = "借贷不平允许保存标识")
//    private Boolean unbalSaveFlag;
//
//    @ApiModelProperty(value = "跨工厂委托生产")
//    private Boolean crossPlantProduce;
//
//    @ApiModelProperty(value = "自动生成内部订单")
//    private Boolean autoGengerCopDoc;
//
//    @ApiModelProperty(value = "自动生成调拨申请单")
//    private Boolean autoGengerPlantDoc;
//
//    @ApiModelProperty(value = "跨公司协同关系")
//    private String crossCopSynergyId;
//
//    @ApiModelProperty(value = "跨工厂协同关系")
//    private String crossPlantSynergyId;
//
    @ApiModelProperty(value = "主键")
      @TableId(value = "DOC_ID", type = IdType.AUTO)
    private String docId;
//
//    @ApiModelProperty(value = "借入业务存货认定方式")
//    private String borrowInvMethod;
//
//    @ApiModelProperty(value = "内部调拨")
//    private Boolean internalTransfer;
//
//    @ApiModelProperty(value = "工艺委外加工中主件调拨")
//    private Boolean subcontractingTransfer;
//
//    @ApiModelProperty(value = "材料调拨")
//    private Boolean materialTransfer;
//
//    @ApiModelProperty(value = "核对工单")
//    private Boolean checkMo;
//
//    @ApiModelProperty(value = "采购退货单据类型")
//    private String purchaseReturnDocId;
//
//    @ApiModelProperty(value = "存货冻结后允许结存调整")
//    private Boolean inventoryFreezeAdjust;
//
//    @ApiModelProperty(value = "核对调拨申请单")
//    private Boolean checkTransferRequisition;
//
//    private Boolean projectDefContractNo;
//
//    private Boolean projectDefSoNo;
//
//    private Boolean onlyRefundFlag;
//
//    private String depositDocId;
//
//    private String withdrawDocId;
//
//    private String transferDocId;
//
//    private Boolean arfFlag;
//
//    private Boolean autoAuthFlag;
//
//    private String opSeqControl;
//
//    private Boolean issueByOpSeq;
//
//    private Boolean reworkCompToIssue;
//
//    private Boolean isPackFlag;
//
//    private String packingReturnDocId;
//
//    private Boolean stopNextApprove;
//
//    private Boolean bpmFlag;
//
//    private Boolean purchasePriceControl;
//
//    private String expPayableDocTypeId;
//
//    private String paymentReqDocTypeId;
//
//    @ApiModelProperty(value = "版本号，不要随意更改")
//    @TableField("Version")
//    private LocalDateTime version;
//
//    @ApiModelProperty(value = "创建日期")
//    @TableField("CreateDate")
//    private LocalDateTime createdate;
//
//    @ApiModelProperty(value = "最后修改日期")
//    @TableField("LastModifiedDate")
//    private LocalDateTime lastmodifieddate;
//
//    @ApiModelProperty(value = "修改日期")
//    @TableField("ModifiedDate")
//    private LocalDateTime modifieddate;
//
//    @ApiModelProperty(value = "创建者")
//    @TableField("CreateBy")
//    private String createby;
//
//    @ApiModelProperty(value = "最后修改者")
//    @TableField("LastModifiedBy")
//    private String lastmodifiedby;
//
//    @ApiModelProperty(value = "修改者")
//    @TableField("ModifiedBy")
//    private String modifiedby;
//
//    @ApiModelProperty(value = "自定义字段0")
//    private Double udf001;
//
//    @ApiModelProperty(value = "自定义字段1")
//    private Double udf002;
//
//    @ApiModelProperty(value = "自定义字段2")
//    private Double udf003;
//
//    @ApiModelProperty(value = "自定义字段3")
//    private Double udf011;
//
//    @ApiModelProperty(value = "自定义字段4")
//    private Double udf012;
//
//    @ApiModelProperty(value = "自定义字段5")
//    private Double udf013;
//
//    @ApiModelProperty(value = "自定义字段6")
//    private String udf021;
//
//    @ApiModelProperty(value = "自定义字段7")
//    private String udf022;
//
//    @ApiModelProperty(value = "自定义字段8")
//    private String udf023;
//
//    @ApiModelProperty(value = "自定义字段9")
//    private String udf024;
//
//    @ApiModelProperty(value = "自定义字段10")
//    private String udf025;
//
//    @ApiModelProperty(value = "自定义字段11")
//    private String udf026;
//
//    @ApiModelProperty(value = "自定义字段12")
//    private LocalDateTime udf041;
//
//    @ApiModelProperty(value = "自定义字段13")
//    private LocalDateTime udf042;
//
//    @ApiModelProperty(value = "自定义字段14")
//    private String udf051;
//
//    @ApiModelProperty(value = "自定义字段15")
//    private String udf052;
//
//    @ApiModelProperty(value = "自定义字段16")
//    private String udf053;
//
//    @ApiModelProperty(value = "自定义字段17")
//    private String udf054;
//
//    @ApiModelProperty(value = "附件")
//    @TableField("Attachments")
//    private String attachments;
//
//    @ApiModelProperty(value = "表单所在的流程实例的编号")
//    @TableField("ProcessInstanceId")
//    private String processinstanceid;
//
//    @ApiModelProperty(value = "单据状态属性")
//    @TableField("ApproveStatus")
//    private String approvestatus;
//
//    @ApiModelProperty(value = "修改日期")
//    @TableField("ApproveDate")
//    private LocalDateTime approvedate;
//
//    @ApiModelProperty(value = "修改人")
//    @TableField("ApproveBy")
//    private String approveby;
//
//    @TableField("Owner_Org_RTK")
//    private String ownerOrgRtk;
//
//    @TableField("Owner_Org_ROid")
//    private String ownerOrgRoid;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout1ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout1ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout1Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout1Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout1ViewInfo;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout2ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout2ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout2Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout2Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout2ViewInfo;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout3ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout3ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout3Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout3Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout3ViewInfo;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout4ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout4ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout4Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout4Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout4ViewInfo;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout5ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout5ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout5Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout5Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout5ViewInfo;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout6ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout6ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout6Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout6Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout6ViewInfo;
//
//    @ApiModelProperty(value = "作业编号")
//    private String printLayout7ProgramInfo;
//
//    @ApiModelProperty(value = "报表编号")
//    private String printLayout7ReportTypekey;
//
//    @ApiModelProperty(value = "样式编号")
//    private String printLayout7Layout;
//
//    @ApiModelProperty(value = "账簿")
//    private Integer printLayout7Accountbook;
//
//    @ApiModelProperty(value = "视图名称")
//    private String printLayout7ViewInfo;
//
//    @ApiModelProperty(value = "发票底稿单据类型")
//    private String w055InvoiceDraftDocId;
//
//    @ApiModelProperty(value = "自动电子支付标识")
//    private Boolean w054AutoEpiFlag;

//
//    @Override
//    protected Serializable pkVal() {
//        return this.docId;
//    }

}
