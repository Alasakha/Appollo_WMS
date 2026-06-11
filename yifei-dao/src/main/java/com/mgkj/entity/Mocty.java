package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @TableName MOCTY
 */
@TableName(value ="MOCTY")
@Data
public class Mocty implements Serializable {
    /**
     * 
     */
//    @TableId("CA001")
    @TableField(value = "TY001")
    private String TY001;

    /**
     * 
     */
//    @TableId(value = "TY002")
    @TableField(value = "TY002")
    private String TY002;

    /**
     * 
     */
//    @TableId(value = "TY003")
    @TableField(value = "TY003")
    private String TY003;

    /**
     * 
     */
//    @TableId(value = "TY009")
    @TableField(value = "TY009")
    private String TY009;

    /**
     * 
     */
//    @TableId(value = "TY019")
    @TableField(value = "TY019")
    private String TY019;

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
    @TableField(value = "TY004")
    private BigDecimal TY004;

    /**
     * 
     */
    @TableField(value = "TY005")
    private BigDecimal TY005;

    /**
     * 
     */
    @TableField(value = "TY006")
    private BigDecimal TY006;

    /**
     * 
     */
    @TableField(value = "TY007")
    private BigDecimal TY007;

    /**
     * 
     */
    @TableField(value = "TY008")
    private String TY008;

    /**
     * 
     */
    @TableField(value = "TY010")
    private BigDecimal TY010;

    /**
     * 
     */
    @TableField(value = "TY011")
    private BigDecimal TY011;

    /**
     * 
     */
    @TableField(value = "TY012")
    private BigDecimal TY012;

    /**
     * 
     */
    @TableField(value = "TY013")
    private BigDecimal TY013;

    /**
     * 
     */
    @TableField(value = "TY014")
    private BigDecimal TY014;

    /**
     * 
     */
    @TableField(value = "TY015")
    private BigDecimal TY015;

    /**
     * 
     */
    @TableField(value = "TY016")
    private BigDecimal TY016;

    /**
     * 
     */
    @TableField(value = "TY017")
    private BigDecimal TY017;

    /**
     * 
     */
    @TableField(value = "TY018")
    private String TY018;

    /**
     * 
     */
    @TableField(value = "TY020")
    private String TY020;

    /**
     * 
     */
    @TableField(value = "TY021")
    private BigDecimal TY021;

    /**
     * 
     */
    @TableField(value = "TY022")
    private BigDecimal TY022;

    /**
     * 
     */
    @TableField(value = "TY023")
    private BigDecimal TY023;

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
    @ApiModelProperty("派工单绑定的工单list集合")
    private List<MOCTA> moctaList;

    @TableField(exist = false)
    @ApiModelProperty("派工单下绑定的检验项目")
    private List<Qmsmg> qmsmgList;

    @TableField(exist = false)
    @ApiModelProperty("品号")
    private String TA006;

    @TableField(exist = false)
    @ApiModelProperty("品名")
    private String TA034;
    @TableField(exist = false)
    @ApiModelProperty("规格")
    private String TA035;


    @TableField(exist = false)
    @ApiModelProperty("工艺")
    private String TA004;

    @TableField(exist = false)
    @ApiModelProperty("工作中心")
    private String TA021;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}