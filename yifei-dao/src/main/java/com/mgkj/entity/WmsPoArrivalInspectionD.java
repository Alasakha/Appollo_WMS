package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * WMS保存检验单单身信息
 * @TableName WMS_PO_ARRIVAL_INSPECTION_D
 */
@TableName(value ="WMS_PO_ARRIVAL_INSPECTION_D")
@Data
public class WmsPoArrivalInspectionD implements Serializable {
    /**
     * 检验单身uuid
     */
    @TableId(value = "id")
    private String id;

    /**
     * 检验顺序
     */
    @TableField(value = "sequence")
    private Integer sequence;

    /**
     * 检验项目编号
     */
    @TableField(value = "inspectionItemCode")
    private String inspectionItemCode;

    /**
     * 检验项目名称
     */
    @TableField(value = "inspectionItemName")
    private String inspectionItemName;

    /**
     * 抽样数量
     */
    @TableField(value = "inspectionQty")
    private BigDecimal inspectionQty;

    /**
     * 允收数量
     */
    @TableField(value = "inspectionAc")
    private BigDecimal inspectionAc;

    /**
     * 拒收数量
     */
    @TableField(value = "inspectionRe")
    private BigDecimal inspectionRe;

    /**
     * 不良数量
     */
    @TableField(value = "defectiveQty")
    private BigDecimal defectiveQty;

    /**
     * 检验单头uuid
     */
    @TableField(value = "inspectionUuid")
    private String inspectionUuid;

    /**
     * 检验单号
     */
    @TableField(value = "checkDocNo")
    private String checkDocNo;

    /**
     * 检验单号
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 序列号
     */
    @TableField(value = "sequenceNumber")
    private Integer sequenceNumber;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}