package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 工单/CHT/工單/ENU/MO
 * @TableName MO
 */
@TableName(value ="MO")
@Data
public class Mo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "MO_ID")
    private Object moId;

    /**
     * 关联部门
     */
    @TableField(value = "Owner_Dept")
    private Object ownerDept;

    /**
     * 关联员工
     */
    @TableField(value = "Owner_Emp")
    private Object ownerEmp;

    /**
     * 单号
     */
    @TableField(value = "DOC_NO")
    private String docNo;

    /**
     * 单据日期
     */
    @TableField(value = "DOC_DATE")
    private LocalDateTime docDate;

    /**
     * 单据类型
     */
    @TableField(value = "DOC_ID")
    private Object docId;

    /**
     * 产品品名
     */
    @TableField(value = "ITEM_DESCRIPTION")
    private String itemDescription;

    /**
     * 产品规格
     */
    @TableField(value = "ITEM_SPECIFICATION")
    private String itemSpecification;

    /**
     * BOM版次
     */
    @TableField(value = "BOM_VERSION_TIMES")
    private String bomVersionTimes;

    /**
     * BOM日期
     */
    @TableField(value = "BOM_DATE")
    private LocalDateTime bomDate;

    /**
     * 紧急
     */
    @TableField(value = "URGENT")
    private Boolean urgent;

    /**
     * 状态码
     */
    @TableField(value = "STATUS")
    private String status;

    /**
     * 批工单
     */
    @TableField(value = "LOT_MO_FLAG")
    private Boolean lotMoFlag;

    /**
     * 预计产量
     */
    @TableField(value = "PLAN_QTY")
    private BigDecimal planQty;

    /**
     * 申请数量
     */
    @TableField(value = "REQ_QTY")
    private BigDecimal reqQty;

    /**
     * 已生产量
     */
    @TableField(value = "COMPLETED_QTY")
    private BigDecimal completedQty;

    /**
     * 报废数量
     */
    @TableField(value = "SCRAP_QTY")
    private BigDecimal scrapQty;

    /**
     * 破坏数量
     */
    @TableField(value = "DESTROYED_QTY")
    private BigDecimal destroyedQty;

    /**
     * 预计开工日期
     */
    @TableField(value = "PLAN_START_DATE")
    private LocalDateTime planStartDate;

    /**
     * 预计完工日期
     */
    @TableField(value = "PLAN_COMPLETE_DATE")
    private LocalDateTime planCompleteDate;

    /**
     * 实际开工日期
     */
    @TableField(value = "ACTUAL_START_DATE")
    private LocalDateTime actualStartDate;

    /**
     * 实际完工日期
     */
    @TableField(value = "ACTUAL_COMPLETE_DATE")
    private LocalDateTime actualCompleteDate;

    /**
     * 计划批号
     */
    @TableField(value = "PLAN_LOT")
    private String planLot;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String remark;

    /**
     * 工艺管理
     */
    @TableField(value = "ROUTING_CONTROL")
    private Boolean routingControl;

    /**
     * 批工单数量
     */
    @TableField(value = "LOT_MO_QTY")
    private BigDecimal lotMoQty;

    /**
     * 入库申请
     */
    @TableField(value = "RECEIPT_REQ_CONTROL")
    private Boolean receiptReqControl;

    /**
     * 已采数量
     */
    @TableField(value = "SUPPLY_PURCHASED_QTY")
    private BigDecimal supplyPurchasedQty;

    /**
     * 分配数量
     */
    @TableField(value = "SUPPLY_APPLY_QTY")
    private BigDecimal supplyApplyQty;

    /**
     * 分配状态
     */
    @TableField(value = "SUPPLY_DISPATCH_STATUS")
    private String supplyDispatchStatus;

    /**
     * 产品品号
     */
    @TableField(value = "ITEM_ID")
    private Object itemId;

    /**
     * 业务单位
     */
    @TableField(value = "BUSINESS_UNIT_ID")
    private Object businessUnitId;

    /**
     * 特征码
     */
    @TableField(value = "ITEM_FEATURE_ID")
    private Object itemFeatureId;

    /**
     * 工艺路线号
     */
    @TableField(value = "ITEM_ROUTING_ID")
    private Object itemRoutingId;

    /**
     * 源工单单号
     */
    @TableField(value = "SOURCE_MO_ID")
    private Object sourceMoId;

    /**
     * 母工单单号
     */
    @TableField(value = "PARA_MO_ID")
    private Object paraMoId;

    /**
     * 部门
     */
    @TableField(value = "ADMIN_UNIT_ID")
    private Object adminUnitId;

    /**
     * 上阶工单单号
     */
    @TableField(value = "UP_MO_ID")
    private Object upMoId;

    /**
     * 生产批号
     */
    @TableField(value = "ITEM_LOT_ID")
    private Object itemLotId;

    /**
     * 主键
     */
    @TableField(value = "ASSIGN_FINISH_PERSON")
    private Object assignFinishPerson;

    /**
     * 最早开工时间
     */
    @TableField(value = "ACTUAL_START_DATETIME")
    private LocalDateTime actualStartDatetime;

    /**
     * 预计产量第二数量
     */
    @TableField(value = "PLAN_SECOND_QTY")
    private BigDecimal planSecondQty;

    /**
     * 批工单第二数量
     */
    @TableField(value = "LOT_MO_SECOND_QTY")
    private BigDecimal lotMoSecondQty;

    /**
     * 入库申请第二数量
     */
    @TableField(value = "REQ_SECOND_QTY")
    private BigDecimal reqSecondQty;

    /**
     * 已入库第二数量
     */
    @TableField(value = "COMPLETED_SECOND_QTY")
    private BigDecimal completedSecondQty;

    /**
     * 报废第二数量
     */
    @TableField(value = "SCRAP_SECOND_QTY")
    private BigDecimal scrapSecondQty;

    /**
     * 破坏第二数量
     */
    @TableField(value = "DESTROYED_SECOND_QTY")
    private BigDecimal destroyedSecondQty;

    /**
     * 需求单号
     */
    @TableField(value = "DEMAND_NO")
    private String demandNo;

    /**
     * 工单图片
     */
    @TableField(value = "MO_PIC")
    private String moPic;

    /**
     * 工艺管理
     */
    @TableField(value = "ITEM_ROUTING_CONTROL")
    private String itemRoutingControl;

    /**
     * 审核日期
     */
    @TableField(value = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    /**
     * 入库仓库
     */
    @TableField(value = "WAREHOUSE_ID")
    private Object warehouseId;

    /**
     * 项目
     */
    @TableField(value = "PROJECT_ID")
    private Object projectId;

    /**
     * RMA号
     */
    @TableField(value = "RMA")
    private String rma;

    /**
     * 协同关系
     */
    @TableField(value = "SYNERGY_ID")
    private Object synergyId;

    /**
     * 不计算成本
     */
    @TableField(value = "NOT_COST")
    private Boolean notCost;

    /**
     * 
     */
    @TableField(value = "ISSUE_BY_OP_SEQ")
    private Boolean issueByOpSeq;

    /**
     * 
     */
    @TableField(value = "MO_CAN_PRODUCT")
    private Boolean moCanProduct;

    /**
     * 打印次数
     */
    @TableField(value = "PrintCount")
    private Integer printcount;

    /**
     * EF签核码
     */
    @TableField(value = "EFNETStatus")
    private String efnetstatus;

    /**
     * 签核业务动作
     */
    @TableField(value = "EFNETAction")
    private String efnetaction;

    /**
     * EFNET单别
     */
    @TableField(value = "EFNETDOCCategory")
    private String efnetdoccategory;

    /**
     * EFNET单号
     */
    @TableField(value = "EFNETDOCNumber")
    private String efnetdocnumber;

    /**
     * 创建日期
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createdate;

    /**
     * 最后修改日期
     */
    @TableField(value = "LastModifiedDate")
    private LocalDateTime lastmodifieddate;

    /**
     * 修改日期
     */
    @TableField(value = "ModifiedDate")
    private LocalDateTime modifieddate;

    /**
     * 创建者
     */
    @TableField(value = "CreateBy")
    private Object createby;

    /**
     * 最后修改者
     */
    @TableField(value = "LastModifiedBy")
    private Object lastmodifiedby;

    /**
     * 修改者
     */
    @TableField(value = "ModifiedBy")
    private Object modifiedby;

    /**
     * 附件
     */
    @TableField(value = "Attachments")
    private String attachments;

    /**
     * 版本号，不要随意更改
     */
    @TableField(value = "Version")
    private LocalDateTime version;

    /**
     * 单据状态属性
     */
    @TableField(value = "ApproveStatus")
    private String approvestatus;

    /**
     * 修改日期
     */
    @TableField(value = "ApproveDate")
    private LocalDateTime approvedate;

    /**
     * 修改人
     */
    @TableField(value = "ApproveBy")
    private Object approveby;

    /**
     * 自定义字段0
     */
    @TableField(value = "UDF001")
    private BigDecimal udf001;

    /**
     * 自定义字段1
     */
    @TableField(value = "UDF002")
    private BigDecimal udf002;

    /**
     * 自定义字段2
     */
    @TableField(value = "UDF003")
    private BigDecimal udf003;

    /**
     * 自定义字段3
     */
    @TableField(value = "UDF011")
    private BigDecimal udf011;

    /**
     * 自定义字段4
     */
    @TableField(value = "UDF012")
    private BigDecimal udf012;

    /**
     * 自定义字段5
     */
    @TableField(value = "UDF013")
    private BigDecimal udf013;

    /**
     * 自定义字段6
     */
    @TableField(value = "UDF021")
    private String udf021;

    /**
     * 自定义字段7
     */
    @TableField(value = "UDF022")
    private String udf022;

    /**
     * 自定义字段8
     */
    @TableField(value = "UDF023")
    private String udf023;

    /**
     * 自定义字段9
     */
    @TableField(value = "UDF024")
    private String udf024;

    /**
     * 自定义字段10
     */
    @TableField(value = "UDF025")
    private String udf025;

    /**
     * 自定义字段11
     */
    @TableField(value = "UDF026")
    private String udf026;

    /**
     * 自定义字段12
     */
    @TableField(value = "UDF041")
    private LocalDateTime udf041;

    /**
     * 自定义字段13
     */
    @TableField(value = "UDF042")
    private LocalDateTime udf042;

    /**
     * 自定义字段14
     */
    @TableField(value = "UDF051")
    private Object udf051;

    /**
     * 自定义字段15
     */
    @TableField(value = "UDF052")
    private Object udf052;

    /**
     * 自定义字段16
     */
    @TableField(value = "UDF053")
    private Object udf053;

    /**
     * 自定义字段17
     */
    @TableField(value = "UDF054")
    private Object udf054;

    /**
     * 表单所在的流程实例的编号
     */
    @TableField(value = "ProcessInstanceId")
    private Object processinstanceid;

    /**
     * 
     */
    @TableField(value = "Owner_Org_RTK")
    private String ownerOrgRtk;

    /**
     * 
     */
    @TableField(value = "Owner_Org_ROid")
    private Object ownerOrgRoid;

    /**
     * 
     */
    @TableField(value = "SOURCE_ID_RTK")
    private String sourceIdRtk;

    /**
     * 
     */
    @TableField(value = "SOURCE_ID_ROid")
    private Object sourceIdRoid;

    /**
     * 
     */
    @TableField(value = "SOURCE_FROM_RTK")
    private String sourceFromRtk;

    /**
     * 
     */
    @TableField(value = "SOURCE_FROM_ROid")
    private Object sourceFromRoid;

    /**
     * 下达
     */
    @TableField(value = "RELEASE_FLAG")
    private Boolean releaseFlag;

    /**
     * 挂起
     */
    @TableField(value = "SUSPEND_FLAG")
    private Boolean suspendFlag;

    /**
     * 挂起时允许退料
     */
    @TableField(value = "SUSPENDED_MATL_RETURN_FLAG")
    private Boolean suspendedMatlReturnFlag;

    /**
     * 挂起时允许生产入库
     */
    @TableField(value = "SUSPENDED_PROD_RECEIPT_FLAG")
    private Boolean suspendedProdReceiptFlag;

    /**
     * 树状码
     */
    @TableField(value = "TREE_CODE")
    private String treeCode;

    /**
     * 下达日期
     */
    @TableField(value = "RELEASE_DATE")
    private LocalDateTime releaseDate;

    /**
     * 挂起日期
     */
    @TableField(value = "SUSPEND_DATE")
    private LocalDateTime suspendDate;

    /**
     * 任务组号
     */
    @TableField(value = "TASK_GROUP_NO")
    private String taskGroupNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Mo other = (Mo) that;
        return (this.getMoId() == null ? other.getMoId() == null : this.getMoId().equals(other.getMoId()))
            && (this.getOwnerDept() == null ? other.getOwnerDept() == null : this.getOwnerDept().equals(other.getOwnerDept()))
            && (this.getOwnerEmp() == null ? other.getOwnerEmp() == null : this.getOwnerEmp().equals(other.getOwnerEmp()))
            && (this.getDocNo() == null ? other.getDocNo() == null : this.getDocNo().equals(other.getDocNo()))
            && (this.getDocDate() == null ? other.getDocDate() == null : this.getDocDate().equals(other.getDocDate()))
            && (this.getDocId() == null ? other.getDocId() == null : this.getDocId().equals(other.getDocId()))
            && (this.getItemDescription() == null ? other.getItemDescription() == null : this.getItemDescription().equals(other.getItemDescription()))
            && (this.getItemSpecification() == null ? other.getItemSpecification() == null : this.getItemSpecification().equals(other.getItemSpecification()))
            && (this.getBomVersionTimes() == null ? other.getBomVersionTimes() == null : this.getBomVersionTimes().equals(other.getBomVersionTimes()))
            && (this.getBomDate() == null ? other.getBomDate() == null : this.getBomDate().equals(other.getBomDate()))
            && (this.getUrgent() == null ? other.getUrgent() == null : this.getUrgent().equals(other.getUrgent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLotMoFlag() == null ? other.getLotMoFlag() == null : this.getLotMoFlag().equals(other.getLotMoFlag()))
            && (this.getPlanQty() == null ? other.getPlanQty() == null : this.getPlanQty().equals(other.getPlanQty()))
            && (this.getReqQty() == null ? other.getReqQty() == null : this.getReqQty().equals(other.getReqQty()))
            && (this.getCompletedQty() == null ? other.getCompletedQty() == null : this.getCompletedQty().equals(other.getCompletedQty()))
            && (this.getScrapQty() == null ? other.getScrapQty() == null : this.getScrapQty().equals(other.getScrapQty()))
            && (this.getDestroyedQty() == null ? other.getDestroyedQty() == null : this.getDestroyedQty().equals(other.getDestroyedQty()))
            && (this.getPlanStartDate() == null ? other.getPlanStartDate() == null : this.getPlanStartDate().equals(other.getPlanStartDate()))
            && (this.getPlanCompleteDate() == null ? other.getPlanCompleteDate() == null : this.getPlanCompleteDate().equals(other.getPlanCompleteDate()))
            && (this.getActualStartDate() == null ? other.getActualStartDate() == null : this.getActualStartDate().equals(other.getActualStartDate()))
            && (this.getActualCompleteDate() == null ? other.getActualCompleteDate() == null : this.getActualCompleteDate().equals(other.getActualCompleteDate()))
            && (this.getPlanLot() == null ? other.getPlanLot() == null : this.getPlanLot().equals(other.getPlanLot()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getRoutingControl() == null ? other.getRoutingControl() == null : this.getRoutingControl().equals(other.getRoutingControl()))
            && (this.getLotMoQty() == null ? other.getLotMoQty() == null : this.getLotMoQty().equals(other.getLotMoQty()))
            && (this.getReceiptReqControl() == null ? other.getReceiptReqControl() == null : this.getReceiptReqControl().equals(other.getReceiptReqControl()))
            && (this.getSupplyPurchasedQty() == null ? other.getSupplyPurchasedQty() == null : this.getSupplyPurchasedQty().equals(other.getSupplyPurchasedQty()))
            && (this.getSupplyApplyQty() == null ? other.getSupplyApplyQty() == null : this.getSupplyApplyQty().equals(other.getSupplyApplyQty()))
            && (this.getSupplyDispatchStatus() == null ? other.getSupplyDispatchStatus() == null : this.getSupplyDispatchStatus().equals(other.getSupplyDispatchStatus()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getBusinessUnitId() == null ? other.getBusinessUnitId() == null : this.getBusinessUnitId().equals(other.getBusinessUnitId()))
            && (this.getItemFeatureId() == null ? other.getItemFeatureId() == null : this.getItemFeatureId().equals(other.getItemFeatureId()))
            && (this.getItemRoutingId() == null ? other.getItemRoutingId() == null : this.getItemRoutingId().equals(other.getItemRoutingId()))
            && (this.getSourceMoId() == null ? other.getSourceMoId() == null : this.getSourceMoId().equals(other.getSourceMoId()))
            && (this.getParaMoId() == null ? other.getParaMoId() == null : this.getParaMoId().equals(other.getParaMoId()))
            && (this.getAdminUnitId() == null ? other.getAdminUnitId() == null : this.getAdminUnitId().equals(other.getAdminUnitId()))
            && (this.getUpMoId() == null ? other.getUpMoId() == null : this.getUpMoId().equals(other.getUpMoId()))
            && (this.getItemLotId() == null ? other.getItemLotId() == null : this.getItemLotId().equals(other.getItemLotId()))
            && (this.getAssignFinishPerson() == null ? other.getAssignFinishPerson() == null : this.getAssignFinishPerson().equals(other.getAssignFinishPerson()))
            && (this.getActualStartDatetime() == null ? other.getActualStartDatetime() == null : this.getActualStartDatetime().equals(other.getActualStartDatetime()))
            && (this.getPlanSecondQty() == null ? other.getPlanSecondQty() == null : this.getPlanSecondQty().equals(other.getPlanSecondQty()))
            && (this.getLotMoSecondQty() == null ? other.getLotMoSecondQty() == null : this.getLotMoSecondQty().equals(other.getLotMoSecondQty()))
            && (this.getReqSecondQty() == null ? other.getReqSecondQty() == null : this.getReqSecondQty().equals(other.getReqSecondQty()))
            && (this.getCompletedSecondQty() == null ? other.getCompletedSecondQty() == null : this.getCompletedSecondQty().equals(other.getCompletedSecondQty()))
            && (this.getScrapSecondQty() == null ? other.getScrapSecondQty() == null : this.getScrapSecondQty().equals(other.getScrapSecondQty()))
            && (this.getDestroyedSecondQty() == null ? other.getDestroyedSecondQty() == null : this.getDestroyedSecondQty().equals(other.getDestroyedSecondQty()))
            && (this.getDemandNo() == null ? other.getDemandNo() == null : this.getDemandNo().equals(other.getDemandNo()))
            && (this.getMoPic() == null ? other.getMoPic() == null : this.getMoPic().equals(other.getMoPic()))
            && (this.getItemRoutingControl() == null ? other.getItemRoutingControl() == null : this.getItemRoutingControl().equals(other.getItemRoutingControl()))
            && (this.getTransactionDate() == null ? other.getTransactionDate() == null : this.getTransactionDate().equals(other.getTransactionDate()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getRma() == null ? other.getRma() == null : this.getRma().equals(other.getRma()))
            && (this.getSynergyId() == null ? other.getSynergyId() == null : this.getSynergyId().equals(other.getSynergyId()))
            && (this.getNotCost() == null ? other.getNotCost() == null : this.getNotCost().equals(other.getNotCost()))
            && (this.getIssueByOpSeq() == null ? other.getIssueByOpSeq() == null : this.getIssueByOpSeq().equals(other.getIssueByOpSeq()))
            && (this.getMoCanProduct() == null ? other.getMoCanProduct() == null : this.getMoCanProduct().equals(other.getMoCanProduct()))
            && (this.getPrintcount() == null ? other.getPrintcount() == null : this.getPrintcount().equals(other.getPrintcount()))
            && (this.getEfnetstatus() == null ? other.getEfnetstatus() == null : this.getEfnetstatus().equals(other.getEfnetstatus()))
            && (this.getEfnetaction() == null ? other.getEfnetaction() == null : this.getEfnetaction().equals(other.getEfnetaction()))
            && (this.getEfnetdoccategory() == null ? other.getEfnetdoccategory() == null : this.getEfnetdoccategory().equals(other.getEfnetdoccategory()))
            && (this.getEfnetdocnumber() == null ? other.getEfnetdocnumber() == null : this.getEfnetdocnumber().equals(other.getEfnetdocnumber()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getLastmodifieddate() == null ? other.getLastmodifieddate() == null : this.getLastmodifieddate().equals(other.getLastmodifieddate()))
            && (this.getModifieddate() == null ? other.getModifieddate() == null : this.getModifieddate().equals(other.getModifieddate()))
            && (this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby()))
            && (this.getLastmodifiedby() == null ? other.getLastmodifiedby() == null : this.getLastmodifiedby().equals(other.getLastmodifiedby()))
            && (this.getModifiedby() == null ? other.getModifiedby() == null : this.getModifiedby().equals(other.getModifiedby()))
            && (this.getAttachments() == null ? other.getAttachments() == null : this.getAttachments().equals(other.getAttachments()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getApprovestatus() == null ? other.getApprovestatus() == null : this.getApprovestatus().equals(other.getApprovestatus()))
            && (this.getApprovedate() == null ? other.getApprovedate() == null : this.getApprovedate().equals(other.getApprovedate()))
            && (this.getApproveby() == null ? other.getApproveby() == null : this.getApproveby().equals(other.getApproveby()))
            && (this.getUdf001() == null ? other.getUdf001() == null : this.getUdf001().equals(other.getUdf001()))
            && (this.getUdf002() == null ? other.getUdf002() == null : this.getUdf002().equals(other.getUdf002()))
            && (this.getUdf003() == null ? other.getUdf003() == null : this.getUdf003().equals(other.getUdf003()))
            && (this.getUdf011() == null ? other.getUdf011() == null : this.getUdf011().equals(other.getUdf011()))
            && (this.getUdf012() == null ? other.getUdf012() == null : this.getUdf012().equals(other.getUdf012()))
            && (this.getUdf013() == null ? other.getUdf013() == null : this.getUdf013().equals(other.getUdf013()))
            && (this.getUdf021() == null ? other.getUdf021() == null : this.getUdf021().equals(other.getUdf021()))
            && (this.getUdf022() == null ? other.getUdf022() == null : this.getUdf022().equals(other.getUdf022()))
            && (this.getUdf023() == null ? other.getUdf023() == null : this.getUdf023().equals(other.getUdf023()))
            && (this.getUdf024() == null ? other.getUdf024() == null : this.getUdf024().equals(other.getUdf024()))
            && (this.getUdf025() == null ? other.getUdf025() == null : this.getUdf025().equals(other.getUdf025()))
            && (this.getUdf026() == null ? other.getUdf026() == null : this.getUdf026().equals(other.getUdf026()))
            && (this.getUdf041() == null ? other.getUdf041() == null : this.getUdf041().equals(other.getUdf041()))
            && (this.getUdf042() == null ? other.getUdf042() == null : this.getUdf042().equals(other.getUdf042()))
            && (this.getUdf051() == null ? other.getUdf051() == null : this.getUdf051().equals(other.getUdf051()))
            && (this.getUdf052() == null ? other.getUdf052() == null : this.getUdf052().equals(other.getUdf052()))
            && (this.getUdf053() == null ? other.getUdf053() == null : this.getUdf053().equals(other.getUdf053()))
            && (this.getUdf054() == null ? other.getUdf054() == null : this.getUdf054().equals(other.getUdf054()))
            && (this.getProcessinstanceid() == null ? other.getProcessinstanceid() == null : this.getProcessinstanceid().equals(other.getProcessinstanceid()))
            && (this.getOwnerOrgRtk() == null ? other.getOwnerOrgRtk() == null : this.getOwnerOrgRtk().equals(other.getOwnerOrgRtk()))
            && (this.getOwnerOrgRoid() == null ? other.getOwnerOrgRoid() == null : this.getOwnerOrgRoid().equals(other.getOwnerOrgRoid()))
            && (this.getSourceIdRtk() == null ? other.getSourceIdRtk() == null : this.getSourceIdRtk().equals(other.getSourceIdRtk()))
            && (this.getSourceIdRoid() == null ? other.getSourceIdRoid() == null : this.getSourceIdRoid().equals(other.getSourceIdRoid()))
            && (this.getSourceFromRtk() == null ? other.getSourceFromRtk() == null : this.getSourceFromRtk().equals(other.getSourceFromRtk()))
            && (this.getSourceFromRoid() == null ? other.getSourceFromRoid() == null : this.getSourceFromRoid().equals(other.getSourceFromRoid()))
            && (this.getReleaseFlag() == null ? other.getReleaseFlag() == null : this.getReleaseFlag().equals(other.getReleaseFlag()))
            && (this.getSuspendFlag() == null ? other.getSuspendFlag() == null : this.getSuspendFlag().equals(other.getSuspendFlag()))
            && (this.getSuspendedMatlReturnFlag() == null ? other.getSuspendedMatlReturnFlag() == null : this.getSuspendedMatlReturnFlag().equals(other.getSuspendedMatlReturnFlag()))
            && (this.getSuspendedProdReceiptFlag() == null ? other.getSuspendedProdReceiptFlag() == null : this.getSuspendedProdReceiptFlag().equals(other.getSuspendedProdReceiptFlag()))
            && (this.getTreeCode() == null ? other.getTreeCode() == null : this.getTreeCode().equals(other.getTreeCode()))
            && (this.getReleaseDate() == null ? other.getReleaseDate() == null : this.getReleaseDate().equals(other.getReleaseDate()))
            && (this.getSuspendDate() == null ? other.getSuspendDate() == null : this.getSuspendDate().equals(other.getSuspendDate()))
            && (this.getTaskGroupNo() == null ? other.getTaskGroupNo() == null : this.getTaskGroupNo().equals(other.getTaskGroupNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMoId() == null) ? 0 : getMoId().hashCode());
        result = prime * result + ((getOwnerDept() == null) ? 0 : getOwnerDept().hashCode());
        result = prime * result + ((getOwnerEmp() == null) ? 0 : getOwnerEmp().hashCode());
        result = prime * result + ((getDocNo() == null) ? 0 : getDocNo().hashCode());
        result = prime * result + ((getDocDate() == null) ? 0 : getDocDate().hashCode());
        result = prime * result + ((getDocId() == null) ? 0 : getDocId().hashCode());
        result = prime * result + ((getItemDescription() == null) ? 0 : getItemDescription().hashCode());
        result = prime * result + ((getItemSpecification() == null) ? 0 : getItemSpecification().hashCode());
        result = prime * result + ((getBomVersionTimes() == null) ? 0 : getBomVersionTimes().hashCode());
        result = prime * result + ((getBomDate() == null) ? 0 : getBomDate().hashCode());
        result = prime * result + ((getUrgent() == null) ? 0 : getUrgent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLotMoFlag() == null) ? 0 : getLotMoFlag().hashCode());
        result = prime * result + ((getPlanQty() == null) ? 0 : getPlanQty().hashCode());
        result = prime * result + ((getReqQty() == null) ? 0 : getReqQty().hashCode());
        result = prime * result + ((getCompletedQty() == null) ? 0 : getCompletedQty().hashCode());
        result = prime * result + ((getScrapQty() == null) ? 0 : getScrapQty().hashCode());
        result = prime * result + ((getDestroyedQty() == null) ? 0 : getDestroyedQty().hashCode());
        result = prime * result + ((getPlanStartDate() == null) ? 0 : getPlanStartDate().hashCode());
        result = prime * result + ((getPlanCompleteDate() == null) ? 0 : getPlanCompleteDate().hashCode());
        result = prime * result + ((getActualStartDate() == null) ? 0 : getActualStartDate().hashCode());
        result = prime * result + ((getActualCompleteDate() == null) ? 0 : getActualCompleteDate().hashCode());
        result = prime * result + ((getPlanLot() == null) ? 0 : getPlanLot().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getRoutingControl() == null) ? 0 : getRoutingControl().hashCode());
        result = prime * result + ((getLotMoQty() == null) ? 0 : getLotMoQty().hashCode());
        result = prime * result + ((getReceiptReqControl() == null) ? 0 : getReceiptReqControl().hashCode());
        result = prime * result + ((getSupplyPurchasedQty() == null) ? 0 : getSupplyPurchasedQty().hashCode());
        result = prime * result + ((getSupplyApplyQty() == null) ? 0 : getSupplyApplyQty().hashCode());
        result = prime * result + ((getSupplyDispatchStatus() == null) ? 0 : getSupplyDispatchStatus().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getBusinessUnitId() == null) ? 0 : getBusinessUnitId().hashCode());
        result = prime * result + ((getItemFeatureId() == null) ? 0 : getItemFeatureId().hashCode());
        result = prime * result + ((getItemRoutingId() == null) ? 0 : getItemRoutingId().hashCode());
        result = prime * result + ((getSourceMoId() == null) ? 0 : getSourceMoId().hashCode());
        result = prime * result + ((getParaMoId() == null) ? 0 : getParaMoId().hashCode());
        result = prime * result + ((getAdminUnitId() == null) ? 0 : getAdminUnitId().hashCode());
        result = prime * result + ((getUpMoId() == null) ? 0 : getUpMoId().hashCode());
        result = prime * result + ((getItemLotId() == null) ? 0 : getItemLotId().hashCode());
        result = prime * result + ((getAssignFinishPerson() == null) ? 0 : getAssignFinishPerson().hashCode());
        result = prime * result + ((getActualStartDatetime() == null) ? 0 : getActualStartDatetime().hashCode());
        result = prime * result + ((getPlanSecondQty() == null) ? 0 : getPlanSecondQty().hashCode());
        result = prime * result + ((getLotMoSecondQty() == null) ? 0 : getLotMoSecondQty().hashCode());
        result = prime * result + ((getReqSecondQty() == null) ? 0 : getReqSecondQty().hashCode());
        result = prime * result + ((getCompletedSecondQty() == null) ? 0 : getCompletedSecondQty().hashCode());
        result = prime * result + ((getScrapSecondQty() == null) ? 0 : getScrapSecondQty().hashCode());
        result = prime * result + ((getDestroyedSecondQty() == null) ? 0 : getDestroyedSecondQty().hashCode());
        result = prime * result + ((getDemandNo() == null) ? 0 : getDemandNo().hashCode());
        result = prime * result + ((getMoPic() == null) ? 0 : getMoPic().hashCode());
        result = prime * result + ((getItemRoutingControl() == null) ? 0 : getItemRoutingControl().hashCode());
        result = prime * result + ((getTransactionDate() == null) ? 0 : getTransactionDate().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getRma() == null) ? 0 : getRma().hashCode());
        result = prime * result + ((getSynergyId() == null) ? 0 : getSynergyId().hashCode());
        result = prime * result + ((getNotCost() == null) ? 0 : getNotCost().hashCode());
        result = prime * result + ((getIssueByOpSeq() == null) ? 0 : getIssueByOpSeq().hashCode());
        result = prime * result + ((getMoCanProduct() == null) ? 0 : getMoCanProduct().hashCode());
        result = prime * result + ((getPrintcount() == null) ? 0 : getPrintcount().hashCode());
        result = prime * result + ((getEfnetstatus() == null) ? 0 : getEfnetstatus().hashCode());
        result = prime * result + ((getEfnetaction() == null) ? 0 : getEfnetaction().hashCode());
        result = prime * result + ((getEfnetdoccategory() == null) ? 0 : getEfnetdoccategory().hashCode());
        result = prime * result + ((getEfnetdocnumber() == null) ? 0 : getEfnetdocnumber().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getLastmodifieddate() == null) ? 0 : getLastmodifieddate().hashCode());
        result = prime * result + ((getModifieddate() == null) ? 0 : getModifieddate().hashCode());
        result = prime * result + ((getCreateby() == null) ? 0 : getCreateby().hashCode());
        result = prime * result + ((getLastmodifiedby() == null) ? 0 : getLastmodifiedby().hashCode());
        result = prime * result + ((getModifiedby() == null) ? 0 : getModifiedby().hashCode());
        result = prime * result + ((getAttachments() == null) ? 0 : getAttachments().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getApprovestatus() == null) ? 0 : getApprovestatus().hashCode());
        result = prime * result + ((getApprovedate() == null) ? 0 : getApprovedate().hashCode());
        result = prime * result + ((getApproveby() == null) ? 0 : getApproveby().hashCode());
        result = prime * result + ((getUdf001() == null) ? 0 : getUdf001().hashCode());
        result = prime * result + ((getUdf002() == null) ? 0 : getUdf002().hashCode());
        result = prime * result + ((getUdf003() == null) ? 0 : getUdf003().hashCode());
        result = prime * result + ((getUdf011() == null) ? 0 : getUdf011().hashCode());
        result = prime * result + ((getUdf012() == null) ? 0 : getUdf012().hashCode());
        result = prime * result + ((getUdf013() == null) ? 0 : getUdf013().hashCode());
        result = prime * result + ((getUdf021() == null) ? 0 : getUdf021().hashCode());
        result = prime * result + ((getUdf022() == null) ? 0 : getUdf022().hashCode());
        result = prime * result + ((getUdf023() == null) ? 0 : getUdf023().hashCode());
        result = prime * result + ((getUdf024() == null) ? 0 : getUdf024().hashCode());
        result = prime * result + ((getUdf025() == null) ? 0 : getUdf025().hashCode());
        result = prime * result + ((getUdf026() == null) ? 0 : getUdf026().hashCode());
        result = prime * result + ((getUdf041() == null) ? 0 : getUdf041().hashCode());
        result = prime * result + ((getUdf042() == null) ? 0 : getUdf042().hashCode());
        result = prime * result + ((getUdf051() == null) ? 0 : getUdf051().hashCode());
        result = prime * result + ((getUdf052() == null) ? 0 : getUdf052().hashCode());
        result = prime * result + ((getUdf053() == null) ? 0 : getUdf053().hashCode());
        result = prime * result + ((getUdf054() == null) ? 0 : getUdf054().hashCode());
        result = prime * result + ((getProcessinstanceid() == null) ? 0 : getProcessinstanceid().hashCode());
        result = prime * result + ((getOwnerOrgRtk() == null) ? 0 : getOwnerOrgRtk().hashCode());
        result = prime * result + ((getOwnerOrgRoid() == null) ? 0 : getOwnerOrgRoid().hashCode());
        result = prime * result + ((getSourceIdRtk() == null) ? 0 : getSourceIdRtk().hashCode());
        result = prime * result + ((getSourceIdRoid() == null) ? 0 : getSourceIdRoid().hashCode());
        result = prime * result + ((getSourceFromRtk() == null) ? 0 : getSourceFromRtk().hashCode());
        result = prime * result + ((getSourceFromRoid() == null) ? 0 : getSourceFromRoid().hashCode());
        result = prime * result + ((getReleaseFlag() == null) ? 0 : getReleaseFlag().hashCode());
        result = prime * result + ((getSuspendFlag() == null) ? 0 : getSuspendFlag().hashCode());
        result = prime * result + ((getSuspendedMatlReturnFlag() == null) ? 0 : getSuspendedMatlReturnFlag().hashCode());
        result = prime * result + ((getSuspendedProdReceiptFlag() == null) ? 0 : getSuspendedProdReceiptFlag().hashCode());
        result = prime * result + ((getTreeCode() == null) ? 0 : getTreeCode().hashCode());
        result = prime * result + ((getReleaseDate() == null) ? 0 : getReleaseDate().hashCode());
        result = prime * result + ((getSuspendDate() == null) ? 0 : getSuspendDate().hashCode());
        result = prime * result + ((getTaskGroupNo() == null) ? 0 : getTaskGroupNo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", moId=").append(moId);
        sb.append(", ownerDept=").append(ownerDept);
        sb.append(", ownerEmp=").append(ownerEmp);
        sb.append(", docNo=").append(docNo);
        sb.append(", docDate=").append(docDate);
        sb.append(", docId=").append(docId);
        sb.append(", itemDescription=").append(itemDescription);
        sb.append(", itemSpecification=").append(itemSpecification);
        sb.append(", bomVersionTimes=").append(bomVersionTimes);
        sb.append(", bomDate=").append(bomDate);
        sb.append(", urgent=").append(urgent);
        sb.append(", status=").append(status);
        sb.append(", lotMoFlag=").append(lotMoFlag);
        sb.append(", planQty=").append(planQty);
        sb.append(", reqQty=").append(reqQty);
        sb.append(", completedQty=").append(completedQty);
        sb.append(", scrapQty=").append(scrapQty);
        sb.append(", destroyedQty=").append(destroyedQty);
        sb.append(", planStartDate=").append(planStartDate);
        sb.append(", planCompleteDate=").append(planCompleteDate);
        sb.append(", actualStartDate=").append(actualStartDate);
        sb.append(", actualCompleteDate=").append(actualCompleteDate);
        sb.append(", planLot=").append(planLot);
        sb.append(", remark=").append(remark);
        sb.append(", routingControl=").append(routingControl);
        sb.append(", lotMoQty=").append(lotMoQty);
        sb.append(", receiptReqControl=").append(receiptReqControl);
        sb.append(", supplyPurchasedQty=").append(supplyPurchasedQty);
        sb.append(", supplyApplyQty=").append(supplyApplyQty);
        sb.append(", supplyDispatchStatus=").append(supplyDispatchStatus);
        sb.append(", itemId=").append(itemId);
        sb.append(", businessUnitId=").append(businessUnitId);
        sb.append(", itemFeatureId=").append(itemFeatureId);
        sb.append(", itemRoutingId=").append(itemRoutingId);
        sb.append(", sourceMoId=").append(sourceMoId);
        sb.append(", paraMoId=").append(paraMoId);
        sb.append(", adminUnitId=").append(adminUnitId);
        sb.append(", upMoId=").append(upMoId);
        sb.append(", itemLotId=").append(itemLotId);
        sb.append(", assignFinishPerson=").append(assignFinishPerson);
        sb.append(", actualStartDatetime=").append(actualStartDatetime);
        sb.append(", planSecondQty=").append(planSecondQty);
        sb.append(", lotMoSecondQty=").append(lotMoSecondQty);
        sb.append(", reqSecondQty=").append(reqSecondQty);
        sb.append(", completedSecondQty=").append(completedSecondQty);
        sb.append(", scrapSecondQty=").append(scrapSecondQty);
        sb.append(", destroyedSecondQty=").append(destroyedSecondQty);
        sb.append(", demandNo=").append(demandNo);
        sb.append(", moPic=").append(moPic);
        sb.append(", itemRoutingControl=").append(itemRoutingControl);
        sb.append(", transactionDate=").append(transactionDate);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", projectId=").append(projectId);
        sb.append(", rma=").append(rma);
        sb.append(", synergyId=").append(synergyId);
        sb.append(", notCost=").append(notCost);
        sb.append(", issueByOpSeq=").append(issueByOpSeq);
        sb.append(", moCanProduct=").append(moCanProduct);
        sb.append(", printcount=").append(printcount);
        sb.append(", efnetstatus=").append(efnetstatus);
        sb.append(", efnetaction=").append(efnetaction);
        sb.append(", efnetdoccategory=").append(efnetdoccategory);
        sb.append(", efnetdocnumber=").append(efnetdocnumber);
        sb.append(", createdate=").append(createdate);
        sb.append(", lastmodifieddate=").append(lastmodifieddate);
        sb.append(", modifieddate=").append(modifieddate);
        sb.append(", createby=").append(createby);
        sb.append(", lastmodifiedby=").append(lastmodifiedby);
        sb.append(", modifiedby=").append(modifiedby);
        sb.append(", attachments=").append(attachments);
        sb.append(", version=").append(version);
        sb.append(", approvestatus=").append(approvestatus);
        sb.append(", approvedate=").append(approvedate);
        sb.append(", approveby=").append(approveby);
        sb.append(", udf001=").append(udf001);
        sb.append(", udf002=").append(udf002);
        sb.append(", udf003=").append(udf003);
        sb.append(", udf011=").append(udf011);
        sb.append(", udf012=").append(udf012);
        sb.append(", udf013=").append(udf013);
        sb.append(", udf021=").append(udf021);
        sb.append(", udf022=").append(udf022);
        sb.append(", udf023=").append(udf023);
        sb.append(", udf024=").append(udf024);
        sb.append(", udf025=").append(udf025);
        sb.append(", udf026=").append(udf026);
        sb.append(", udf041=").append(udf041);
        sb.append(", udf042=").append(udf042);
        sb.append(", udf051=").append(udf051);
        sb.append(", udf052=").append(udf052);
        sb.append(", udf053=").append(udf053);
        sb.append(", udf054=").append(udf054);
        sb.append(", processinstanceid=").append(processinstanceid);
        sb.append(", ownerOrgRtk=").append(ownerOrgRtk);
        sb.append(", ownerOrgRoid=").append(ownerOrgRoid);
        sb.append(", sourceIdRtk=").append(sourceIdRtk);
        sb.append(", sourceIdRoid=").append(sourceIdRoid);
        sb.append(", sourceFromRtk=").append(sourceFromRtk);
        sb.append(", sourceFromRoid=").append(sourceFromRoid);
        sb.append(", releaseFlag=").append(releaseFlag);
        sb.append(", suspendFlag=").append(suspendFlag);
        sb.append(", suspendedMatlReturnFlag=").append(suspendedMatlReturnFlag);
        sb.append(", suspendedProdReceiptFlag=").append(suspendedProdReceiptFlag);
        sb.append(", treeCode=").append(treeCode);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", suspendDate=").append(suspendDate);
        sb.append(", taskGroupNo=").append(taskGroupNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}