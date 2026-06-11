package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 工单单身档/CHT/工單單身檔/ENU/MO Detail
 * @TableName MO_D
 */
@TableName(value ="MO_D")
@Data
public class MoD implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "MO_D_ID")
    private Object moDId;

    /**
     * 
     */
    @TableField(value = "SequenceNumber")
    private Integer sequencenumber;

    /**
     * 材料品名
     */
    @TableField(value = "ITEM_DESCRIPTION")
    private String itemDescription;

    /**
     * 材料规格
     */
    @TableField(value = "ITEM_SPECIFICATION")
    private String itemSpecification;

    /**
     * 取替代料
     */
    @TableField(value = "REPLACE_ITEM")
    private String replaceItem;

    /**
     * 上阶主件品号
     */
    @TableField(value = "UP_LEVEL_ITEM_ID")
    private Object upLevelItemId;

    /**
     * 被取替代品号
     */
    @TableField(value = "REPLACED_ITEM_ID")
    private Object replacedItemId;

    /**
     * 被取替代特征码
     */
    @TableField(value = "REPLACED_ITEM_FEATURE_ID")
    private Object replacedItemFeatureId;

    /**
     * 预计用量
     */
    @TableField(value = "REQUIRED_QTY")
    private BigDecimal requiredQty;

    /**
     * 需领用量
     */
    @TableField(value = "REQU_INCLUDE_MULT_QTY")
    private BigDecimal requIncludeMultQty;

    /**
     * 被取替代数量
     */
    @TableField(value = "REPLACED_QTY")
    private BigDecimal replacedQty;

    /**
     * 已领用量
     */
    @TableField(value = "ISSUED_QTY")
    private BigDecimal issuedQty;

    /**
     * 供料方式
     */
    @TableField(value = "ITEM_TYPE")
    private String itemType;

    /**
     * 供料方式_02
     */
    @TableField(value = "ITEM_TYPE_02")
    private String itemType02;

    /**
     * 预计领料日期
     */
    @TableField(value = "PLAN_ISSUE_DATE")
    private LocalDateTime planIssueDate;

    /**
     * 实际领料日期
     */
    @TableField(value = "ACTUAL_ISSUE_DATE")
    private LocalDateTime actualIssueDate;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String remark;

    /**
     * 超领率
     */
    @TableField(value = "ISSUE_OVERRUN_RATE")
    private BigDecimal issueOverrunRate;

    /**
     * 缺领率
     */
    @TableField(value = "ISSUE_SHORTAGE_RATE")
    private BigDecimal issueShortageRate;

    /**
     * 上阶主件特征码
     */
    @TableField(value = "UP_LEVEL_ITEM_FEATURE_ID")
    private Object upLevelItemFeatureId;

    /**
     * 投料间距
     */
    @TableField(value = "ISSUE_MATERIAL_PERIOD")
    private Integer issueMaterialPeriod;

    /**
     * 元件群组
     */
    @TableField(value = "ITEM_GROUP")
    private Object itemGroup;

    /**
     * 替代群组
     */
    @TableField(value = "REPLACE_GROUP")
    private Object replaceGroup;

    /**
     * 材料品号
     */
    @TableField(value = "ITEM_ID")
    private Object itemId;

    /**
     * 单位
     */
    @TableField(value = "UNIT_ID")
    private Object unitId;

    /**
     * 特征码
     */
    @TableField(value = "ITEM_FEATURE_ID")
    private Object itemFeatureId;

    /**
     * 工艺
     */
    @TableField(value = "OPERATION_ID")
    private Object operationId;

    /**
     * 仓库
     */
    @TableField(value = "WAREHOUSE_ID")
    private Object warehouseId;

    /**
     * 库位
     */
    @TableField(value = "BIN_ID")
    private Object binId;

    /**
     * 批号
     */
    @TableField(value = "ITEM_LOT_ID")
    private Object itemLotId;

    /**
     * 需领用量第二数量
     */
    @TableField(value = "REQUIRED_SECOND_QTY")
    private BigDecimal requiredSecondQty;

    /**
     * 已领用量第二数量
     */
    @TableField(value = "ISSUED_SECOND_QTY")
    private BigDecimal issuedSecondQty;

    /**
     * 已申请量
     */
    @TableField(value = "ISSUED_REQ_QTY")
    private BigDecimal issuedReqQty;

    /**
     * 已申请第二数量
     */
    @TableField(value = "ISSUED_REQ_SECOND_QTY")
    private BigDecimal issuedReqSecondQty;

    /**
     * 工单材料图片
     */
    @TableField(value = "MO_D_PIC")
    private String moDPic;

    /**
     * 项目
     */
    @TableField(value = "PROJECT_ID")
    private Object projectId;

    /**
     * 最低用量
     */
    @TableField(value = "LEAST_REQUIRED_QTY")
    private BigDecimal leastRequiredQty;

    /**
     * 插件位置
     */
    @TableField(value = "COMPONENT_LOCATION")
    private String componentLocation;

    /**
     * 调拨数量
     */
    @TableField(value = "TRANSFER_QTY")
    private BigDecimal transferQty;

    /**
     * 
     */
    @TableField(value = "MO_ROUTING_D_ID")
    private Object moRoutingDId;

    /**
     * 
     */
    @TableField(value = "NOT_MO_MATERAIL")
    private Boolean notMoMaterail;

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
     * 
     */
    @TableField(value = "MO_ID")
    private Object moId;

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
        MoD other = (MoD) that;
        return (this.getMoDId() == null ? other.getMoDId() == null : this.getMoDId().equals(other.getMoDId()))
            && (this.getSequencenumber() == null ? other.getSequencenumber() == null : this.getSequencenumber().equals(other.getSequencenumber()))
            && (this.getItemDescription() == null ? other.getItemDescription() == null : this.getItemDescription().equals(other.getItemDescription()))
            && (this.getItemSpecification() == null ? other.getItemSpecification() == null : this.getItemSpecification().equals(other.getItemSpecification()))
            && (this.getReplaceItem() == null ? other.getReplaceItem() == null : this.getReplaceItem().equals(other.getReplaceItem()))
            && (this.getUpLevelItemId() == null ? other.getUpLevelItemId() == null : this.getUpLevelItemId().equals(other.getUpLevelItemId()))
            && (this.getReplacedItemId() == null ? other.getReplacedItemId() == null : this.getReplacedItemId().equals(other.getReplacedItemId()))
            && (this.getReplacedItemFeatureId() == null ? other.getReplacedItemFeatureId() == null : this.getReplacedItemFeatureId().equals(other.getReplacedItemFeatureId()))
            && (this.getRequiredQty() == null ? other.getRequiredQty() == null : this.getRequiredQty().equals(other.getRequiredQty()))
            && (this.getRequIncludeMultQty() == null ? other.getRequIncludeMultQty() == null : this.getRequIncludeMultQty().equals(other.getRequIncludeMultQty()))
            && (this.getReplacedQty() == null ? other.getReplacedQty() == null : this.getReplacedQty().equals(other.getReplacedQty()))
            && (this.getIssuedQty() == null ? other.getIssuedQty() == null : this.getIssuedQty().equals(other.getIssuedQty()))
            && (this.getItemType() == null ? other.getItemType() == null : this.getItemType().equals(other.getItemType()))
            && (this.getItemType02() == null ? other.getItemType02() == null : this.getItemType02().equals(other.getItemType02()))
            && (this.getPlanIssueDate() == null ? other.getPlanIssueDate() == null : this.getPlanIssueDate().equals(other.getPlanIssueDate()))
            && (this.getActualIssueDate() == null ? other.getActualIssueDate() == null : this.getActualIssueDate().equals(other.getActualIssueDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIssueOverrunRate() == null ? other.getIssueOverrunRate() == null : this.getIssueOverrunRate().equals(other.getIssueOverrunRate()))
            && (this.getIssueShortageRate() == null ? other.getIssueShortageRate() == null : this.getIssueShortageRate().equals(other.getIssueShortageRate()))
            && (this.getUpLevelItemFeatureId() == null ? other.getUpLevelItemFeatureId() == null : this.getUpLevelItemFeatureId().equals(other.getUpLevelItemFeatureId()))
            && (this.getIssueMaterialPeriod() == null ? other.getIssueMaterialPeriod() == null : this.getIssueMaterialPeriod().equals(other.getIssueMaterialPeriod()))
            && (this.getItemGroup() == null ? other.getItemGroup() == null : this.getItemGroup().equals(other.getItemGroup()))
            && (this.getReplaceGroup() == null ? other.getReplaceGroup() == null : this.getReplaceGroup().equals(other.getReplaceGroup()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId()))
            && (this.getItemFeatureId() == null ? other.getItemFeatureId() == null : this.getItemFeatureId().equals(other.getItemFeatureId()))
            && (this.getOperationId() == null ? other.getOperationId() == null : this.getOperationId().equals(other.getOperationId()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getBinId() == null ? other.getBinId() == null : this.getBinId().equals(other.getBinId()))
            && (this.getItemLotId() == null ? other.getItemLotId() == null : this.getItemLotId().equals(other.getItemLotId()))
            && (this.getRequiredSecondQty() == null ? other.getRequiredSecondQty() == null : this.getRequiredSecondQty().equals(other.getRequiredSecondQty()))
            && (this.getIssuedSecondQty() == null ? other.getIssuedSecondQty() == null : this.getIssuedSecondQty().equals(other.getIssuedSecondQty()))
            && (this.getIssuedReqQty() == null ? other.getIssuedReqQty() == null : this.getIssuedReqQty().equals(other.getIssuedReqQty()))
            && (this.getIssuedReqSecondQty() == null ? other.getIssuedReqSecondQty() == null : this.getIssuedReqSecondQty().equals(other.getIssuedReqSecondQty()))
            && (this.getMoDPic() == null ? other.getMoDPic() == null : this.getMoDPic().equals(other.getMoDPic()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getLeastRequiredQty() == null ? other.getLeastRequiredQty() == null : this.getLeastRequiredQty().equals(other.getLeastRequiredQty()))
            && (this.getComponentLocation() == null ? other.getComponentLocation() == null : this.getComponentLocation().equals(other.getComponentLocation()))
            && (this.getTransferQty() == null ? other.getTransferQty() == null : this.getTransferQty().equals(other.getTransferQty()))
            && (this.getMoRoutingDId() == null ? other.getMoRoutingDId() == null : this.getMoRoutingDId().equals(other.getMoRoutingDId()))
            && (this.getNotMoMaterail() == null ? other.getNotMoMaterail() == null : this.getNotMoMaterail().equals(other.getNotMoMaterail()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getLastmodifieddate() == null ? other.getLastmodifieddate() == null : this.getLastmodifieddate().equals(other.getLastmodifieddate()))
            && (this.getModifieddate() == null ? other.getModifieddate() == null : this.getModifieddate().equals(other.getModifieddate()))
            && (this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby()))
            && (this.getLastmodifiedby() == null ? other.getLastmodifiedby() == null : this.getLastmodifiedby().equals(other.getLastmodifiedby()))
            && (this.getModifiedby() == null ? other.getModifiedby() == null : this.getModifiedby().equals(other.getModifiedby()))
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
            && (this.getMoId() == null ? other.getMoId() == null : this.getMoId().equals(other.getMoId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMoDId() == null) ? 0 : getMoDId().hashCode());
        result = prime * result + ((getSequencenumber() == null) ? 0 : getSequencenumber().hashCode());
        result = prime * result + ((getItemDescription() == null) ? 0 : getItemDescription().hashCode());
        result = prime * result + ((getItemSpecification() == null) ? 0 : getItemSpecification().hashCode());
        result = prime * result + ((getReplaceItem() == null) ? 0 : getReplaceItem().hashCode());
        result = prime * result + ((getUpLevelItemId() == null) ? 0 : getUpLevelItemId().hashCode());
        result = prime * result + ((getReplacedItemId() == null) ? 0 : getReplacedItemId().hashCode());
        result = prime * result + ((getReplacedItemFeatureId() == null) ? 0 : getReplacedItemFeatureId().hashCode());
        result = prime * result + ((getRequiredQty() == null) ? 0 : getRequiredQty().hashCode());
        result = prime * result + ((getRequIncludeMultQty() == null) ? 0 : getRequIncludeMultQty().hashCode());
        result = prime * result + ((getReplacedQty() == null) ? 0 : getReplacedQty().hashCode());
        result = prime * result + ((getIssuedQty() == null) ? 0 : getIssuedQty().hashCode());
        result = prime * result + ((getItemType() == null) ? 0 : getItemType().hashCode());
        result = prime * result + ((getItemType02() == null) ? 0 : getItemType02().hashCode());
        result = prime * result + ((getPlanIssueDate() == null) ? 0 : getPlanIssueDate().hashCode());
        result = prime * result + ((getActualIssueDate() == null) ? 0 : getActualIssueDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIssueOverrunRate() == null) ? 0 : getIssueOverrunRate().hashCode());
        result = prime * result + ((getIssueShortageRate() == null) ? 0 : getIssueShortageRate().hashCode());
        result = prime * result + ((getUpLevelItemFeatureId() == null) ? 0 : getUpLevelItemFeatureId().hashCode());
        result = prime * result + ((getIssueMaterialPeriod() == null) ? 0 : getIssueMaterialPeriod().hashCode());
        result = prime * result + ((getItemGroup() == null) ? 0 : getItemGroup().hashCode());
        result = prime * result + ((getReplaceGroup() == null) ? 0 : getReplaceGroup().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getItemFeatureId() == null) ? 0 : getItemFeatureId().hashCode());
        result = prime * result + ((getOperationId() == null) ? 0 : getOperationId().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getBinId() == null) ? 0 : getBinId().hashCode());
        result = prime * result + ((getItemLotId() == null) ? 0 : getItemLotId().hashCode());
        result = prime * result + ((getRequiredSecondQty() == null) ? 0 : getRequiredSecondQty().hashCode());
        result = prime * result + ((getIssuedSecondQty() == null) ? 0 : getIssuedSecondQty().hashCode());
        result = prime * result + ((getIssuedReqQty() == null) ? 0 : getIssuedReqQty().hashCode());
        result = prime * result + ((getIssuedReqSecondQty() == null) ? 0 : getIssuedReqSecondQty().hashCode());
        result = prime * result + ((getMoDPic() == null) ? 0 : getMoDPic().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getLeastRequiredQty() == null) ? 0 : getLeastRequiredQty().hashCode());
        result = prime * result + ((getComponentLocation() == null) ? 0 : getComponentLocation().hashCode());
        result = prime * result + ((getTransferQty() == null) ? 0 : getTransferQty().hashCode());
        result = prime * result + ((getMoRoutingDId() == null) ? 0 : getMoRoutingDId().hashCode());
        result = prime * result + ((getNotMoMaterail() == null) ? 0 : getNotMoMaterail().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getLastmodifieddate() == null) ? 0 : getLastmodifieddate().hashCode());
        result = prime * result + ((getModifieddate() == null) ? 0 : getModifieddate().hashCode());
        result = prime * result + ((getCreateby() == null) ? 0 : getCreateby().hashCode());
        result = prime * result + ((getLastmodifiedby() == null) ? 0 : getLastmodifiedby().hashCode());
        result = prime * result + ((getModifiedby() == null) ? 0 : getModifiedby().hashCode());
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
        result = prime * result + ((getMoId() == null) ? 0 : getMoId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", moDId=").append(moDId);
        sb.append(", sequencenumber=").append(sequencenumber);
        sb.append(", itemDescription=").append(itemDescription);
        sb.append(", itemSpecification=").append(itemSpecification);
        sb.append(", replaceItem=").append(replaceItem);
        sb.append(", upLevelItemId=").append(upLevelItemId);
        sb.append(", replacedItemId=").append(replacedItemId);
        sb.append(", replacedItemFeatureId=").append(replacedItemFeatureId);
        sb.append(", requiredQty=").append(requiredQty);
        sb.append(", requIncludeMultQty=").append(requIncludeMultQty);
        sb.append(", replacedQty=").append(replacedQty);
        sb.append(", issuedQty=").append(issuedQty);
        sb.append(", itemType=").append(itemType);
        sb.append(", itemType02=").append(itemType02);
        sb.append(", planIssueDate=").append(planIssueDate);
        sb.append(", actualIssueDate=").append(actualIssueDate);
        sb.append(", remark=").append(remark);
        sb.append(", issueOverrunRate=").append(issueOverrunRate);
        sb.append(", issueShortageRate=").append(issueShortageRate);
        sb.append(", upLevelItemFeatureId=").append(upLevelItemFeatureId);
        sb.append(", issueMaterialPeriod=").append(issueMaterialPeriod);
        sb.append(", itemGroup=").append(itemGroup);
        sb.append(", replaceGroup=").append(replaceGroup);
        sb.append(", itemId=").append(itemId);
        sb.append(", unitId=").append(unitId);
        sb.append(", itemFeatureId=").append(itemFeatureId);
        sb.append(", operationId=").append(operationId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", binId=").append(binId);
        sb.append(", itemLotId=").append(itemLotId);
        sb.append(", requiredSecondQty=").append(requiredSecondQty);
        sb.append(", issuedSecondQty=").append(issuedSecondQty);
        sb.append(", issuedReqQty=").append(issuedReqQty);
        sb.append(", issuedReqSecondQty=").append(issuedReqSecondQty);
        sb.append(", moDPic=").append(moDPic);
        sb.append(", projectId=").append(projectId);
        sb.append(", leastRequiredQty=").append(leastRequiredQty);
        sb.append(", componentLocation=").append(componentLocation);
        sb.append(", transferQty=").append(transferQty);
        sb.append(", moRoutingDId=").append(moRoutingDId);
        sb.append(", notMoMaterail=").append(notMoMaterail);
        sb.append(", createdate=").append(createdate);
        sb.append(", lastmodifieddate=").append(lastmodifieddate);
        sb.append(", modifieddate=").append(modifieddate);
        sb.append(", createby=").append(createby);
        sb.append(", lastmodifiedby=").append(lastmodifiedby);
        sb.append(", modifiedby=").append(modifiedby);
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
        sb.append(", moId=").append(moId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}