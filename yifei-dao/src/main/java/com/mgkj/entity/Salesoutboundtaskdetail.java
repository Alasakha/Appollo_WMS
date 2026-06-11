package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @TableName SalesOutboundTaskDetail
 */
@TableName(value = "SalesOutboundTaskDetail")
@Data
public class Salesoutboundtaskdetail implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     *
     */
    @TableField(value = "barcode")
    private String barcode;

    /**
     *
     */
    @TableField(value = "warehouseCode")
    private String warehouseCode;

    /**
     *
     */
    @TableField(value = "warehouseName")
    private String warehouseName;

    /**
     *
     */
    @TableField(value = "binCode")
    private String binCode;

    /**
     *
     */
    @TableField(value = "lotNo")
    private String lotNo;

    /**
     *
     */
    @TableField(value = "qty")
    private BigDecimal qty;

    /**
     *
     */
    @TableField(value = "docNo")
    private String docNo;

    /**
     *
     */
    @TableField(value = "customerDocNo")
    private String customerDocNo;

    /**
     *
     */
    @TableField(value = "unitCode")
    private String unitCode;

    /**
     *
     */
    @TableField(value = "itemCode")
    private String itemCode;

    /**
     *
     */
    @TableField(value = "itemName")
    private String itemName;

    /**
     *
     */
    @TableField(value = "itemSpec")
    private String itemSpec;

    /**
     *
     */
    @TableField(value = "orgNo")
    private String orgNo;

    /**
     *
     */
    @TableField(value = "status")
    private Integer status;

    /**
     *
     */
    @TableField(value = "createTime")
    private String createTime;

    /**
     *
     */
    @TableField(value = "createBy")
    private String createBy;

    /**
     *
     */
    @TableField(value = "remark")
    private String remark;

    /**
     *
     */
    @TableField(value = "createDocNo")
    private String createDocNo;

    @TableField(value = "salesNo")
    private String salesNo;

    /**
     * 计划量（扫码时从E10销货单单身 BUSINESS_QTY 写入，后续不变）
     */
    @TableField(value = "plan_qty")
    private BigDecimal planQty;

    /**
     * 是否已同步到E10：0=未同步，1=已同步。默认0
     */
    @TableField(value = "synced")
    private Integer synced;

    /**
     * 同步到E10的时间
     */
    @TableField(value = "sync_time")
    private String syncTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}