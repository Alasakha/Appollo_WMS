package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * @TableName WMS_PO_ARRIVAL_INSPECTION_D_MEASUREMENT_VALUE
 */
@TableName(value ="WMS_PO_ARRIVAL_INSPECTION_D_MEASUREMENT_VALUE")
@Data
public class WmsPoArrivalInspectionDMeasurementValue implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     *
     */
    @TableField(value = "wpid_id")
    private String wpidId;


    /**
     * 
     */
    @TableField(value = "udf01")
    private BigDecimal udf01;

    /**
     * 
     */
    @TableField(value = "udf02")
    private BigDecimal udf02;

    /**
     * 
     */
    @TableField(value = "udf03")
    private BigDecimal udf03;

    /**
     * 
     */
    @TableField(value = "udf04")
    private BigDecimal udf04;

    /**
     * 
     */
    @TableField(value = "udf05")
    private BigDecimal udf05;

    /**
     * 
     */
    @TableField(value = "udf06")
    private BigDecimal udf06;

    /**
     * 
     */
    @TableField(value = "udf07")
    private BigDecimal udf07;

    /**
     * 
     */
    @TableField(value = "udf08")
    private BigDecimal udf08;

    /**
     * 
     */
    @TableField(value = "udf09")
    private BigDecimal udf09;

    /**
     * 
     */
    @TableField(value = "udf010")
    private BigDecimal udf010;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}