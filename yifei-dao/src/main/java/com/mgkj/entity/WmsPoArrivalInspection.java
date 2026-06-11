package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName WMS_PO_ARRIVAL_INSPECTION
 */
@TableName(value ="WMS_PO_ARRIVAL_INSPECTION")
@Data
public class WmsPoArrivalInspection implements Serializable {
    /**
     * 检验单uuid
     */
    @TableId(value = "inspectionUuid")
    private String inspectionUuid;

    /**
     * 检验类型
     */
    @TableField(value = "checkName")
    private String checkName;

    /**
     * 检验单号
     */
    @TableField(value = "checkDocNo")
    private String checkDocNo;

    /**
     * 到货单号
     */
    @TableField(value = "arrivalDocNo")
    private String arrivalDocNo;

    /**
     * 到货单单身uuid
     */
    @TableField(value = "arrivalDUuid")
    private String arrivalDUuid;

    /**
     * 到货单单身序号
     */
    @TableField(value = "arrivalDSequenceNumber")
    private String arrivalDSequenceNumber;

    /**
     * 物料编码
     */
    @TableField(value = "itemCode")
    private String itemCode;

    /**
     * 物料名称
     */
    @TableField(value = "itemName")
    private String itemName;

    /**
     * 合格数量
     */
    @TableField(value = "acceptableQty")
    private BigDecimal acceptableQty;

    /**
     * 不合格数量
     */
    @TableField(value = "unqualifiedQty")
    private BigDecimal unqualifiedQty;

    /**
     * 破坏数量
     */
    @TableField(value = "destroyedQty")
    private BigDecimal destroyedQty;

    /**
     * 判定结果：1.合格 2.不合格
     */
    @TableField(value = "decision")
    private String decision;

    /**
     * 业务判定状态(1.未判定 2.部分判定 3.已判定)
     */
    @TableField(value = "resultStatus")
    private String resultStatus;

    /**
     * 判定说明
     */
    @TableField(value = "decisionDescription")
    private String decisionDescription;

    /**
     * 日期
     */
    @TableField(value = "CreateDate")
    private String createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}