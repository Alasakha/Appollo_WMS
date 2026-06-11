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
 * @TableName CMSMW
 */
@TableName(value ="CMSMW")
@Data
public class Cmsmw implements Serializable {
    /**
     * 
     */
    @TableId(value = "MW001")
    private String MW001;

    /**
     * 
     */
    @TableField(value = "COMPANY")
    private String COMPANY;

    /**
     * 
     */
    @TableField(value = "CREATOR")
    private String CREATOR;

    /**
     * 
     */
    @TableField(value = "USR_GROUP")
    private String USR_GROUP;

    /**
     * 
     */
    @TableField(value = "CREATE_DATE")
    private String CREATE_DATE;

    /**
     * 
     */
    @TableField(value = "MODIFIER")
    private String MODIFIER;

    /**
     * 
     */
    @TableField(value = "MODI_DATE")
    private String MODI_DATE;

    /**
     * 
     */
    @TableField(value = "FLAG")
    private Integer FLAG;

    /**
     * 
     */
    @TableField(value = "MW002")
    private String MW002;

    /**
     * 
     */
    @TableField(value = "MW003")
    private String MW003;

    /**
     * 
     */
    @TableField(value = "MW004")
    private String MW004;

    /**
     * 
     */
    @TableField(value = "MW005")
    private String MW005;

    /**
     * 
     */
    @TableField(value = "MW006")
    private String MW006;

    /**
     * 
     */
    @TableField(value = "MW007")
    private String MW007;

    /**
     * 
     */
    @TableField(value = "MW008")
    private String MW008;

    /**
     * 
     */
    @TableField(value = "MW009")
    private Integer MW009;

    /**
     * 
     */
    @TableField(value = "MW010")
    private String MW010;

    /**
     * 
     */
    @TableField(value = "MW011")
    private String MW011;

    /**
     * 
     */
    @TableField(value = "MW012")
    private String MW012;

    /**
     * 
     */
    @TableField(value = "MW013")
    private BigDecimal MW013;

    /**
     * 
     */
    @TableField(value = "MW014")
    private BigDecimal MW014;

    /**
     * 
     */
    @TableField(value = "MW015")
    private BigDecimal MW015;

    /**
     * 
     */
    @TableField(value = "UDF01")
    private String UDF01;

    /**
     * 
     */
    @TableField(value = "UDF02")
    private String UDF02;

    /**
     * 
     */
    @TableField(value = "UDF03")
    private String UDF03;

    /**
     * 
     */
    @TableField(value = "UDF04")
    private String UDF04;

    /**
     * 
     */
    @TableField(value = "UDF05")
    private String UDF05;

    /**
     * 
     */
    @TableField(value = "UDF06")
    private String UDF06;

    /**
     * 
     */
    @TableField(value = "UDF51")
    private BigDecimal UDF51;

    /**
     * 
     */
    @TableField(value = "UDF52")
    private BigDecimal UDF52;

    /**
     * 
     */
    @TableField(value = "UDF53")
    private BigDecimal UDF53;

    /**
     * 
     */
    @TableField(value = "UDF54")
    private BigDecimal UDF54;

    /**
     * 
     */
    @TableField(value = "UDF55")
    private BigDecimal UDF55;

    /**
     * 
     */
    @TableField(value = "UDF56")
    private BigDecimal UDF56;

    /**
     * 
     */
    @TableField(value = "UDF07")
    private String UDF07;

    /**
     * 
     */
    @TableField(value = "UDF08")
    private String UDF08;

    /**
     * 
     */
    @TableField(value = "UDF09")
    private String UDF09;

    /**
     * 
     */
    @TableField(value = "UDF10")
    private String UDF10;

    /**
     * 
     */
    @TableField(value = "UDF11")
    private String UDF11;

    /**
     * 
     */
    @TableField(value = "UDF12")
    private String UDF12;

    /**
     * 
     */
    @TableField(value = "UDF57")
    private BigDecimal UDF57;

    /**
     * 
     */
    @TableField(value = "UDF58")
    private BigDecimal UDF58;

    /**
     * 
     */
    @TableField(value = "UDF59")
    private BigDecimal UDF59;

    /**
     * 
     */
    @TableField(value = "UDF60")
    private BigDecimal UDF60;

    /**
     * 
     */
    @TableField(value = "UDF61")
    private BigDecimal UDF61;

    /**
     * 
     */
    @TableField(value = "UDF62")
    private BigDecimal UDF62;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}