package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ADMMG")
public class Admmg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("COMPANY")
    private String company;

    @TableField("CREATOR")
    private String creator;

    @TableField("USR_GROUP")
    private String usrGroup;

    @TableField("CREATE_DATE")
    private String createDate;

    @TableField("MODIFIER")
    private String modifier;

    @TableField("MODI_DATE")
    private String modiDate;

    @TableField("FLAG")
    private Double flag;

    @TableId("MG001")
    private String mg001;

    @TableField("MG002")
    private String mg002;

    @TableField("MG003")
    private String mg003;

    @TableField("MG004")
    private String mg004;

    @TableField("MG005")
    private String mg005;

    @TableField("MG006")
    private String mg006;

    @TableField("MG007")
    private String mg007;

    @TableField("MG008")
    private String mg008;

    @TableField("MG009")
    private String mg009;

    @TableField("MG010")
    private String mg010;

    @TableField("MG011")
    private String mg011;

    @TableField("MG012")
    private String mg012;

    @TableField("MG013")
    private String mg013;

    @TableField("MG014")
    private String mg014;

    @TableField("MG015")
    private Double mg015;

    @TableField("MG016")
    private Double mg016;

    @TableField("MG017")
    private Double mg017;

    @TableField("UDF01")
    private String udf01;

    @TableField("UDF02")
    private String udf02;

    @TableField("UDF03")
    private String udf03;

    @TableField("UDF04")
    private String udf04;

    @TableField("UDF05")
    private String udf05;

    @TableField("UDF06")
    private String udf06;

    @TableField("UDF51")
    private Double udf51;

    @TableField("UDF52")
    private Double udf52;

    @TableField("UDF53")
    private Double udf53;

    @TableField("UDF54")
    private Double udf54;

    @TableField("UDF55")
    private Double udf55;

    @TableField("UDF56")
    private Double udf56;

    @TableField("UDF07")
    private String udf07;

    @TableField("UDF08")
    private String udf08;

    @TableField("UDF09")
    private String udf09;

    @TableField("UDF10")
    private String udf10;

    @TableField("UDF11")
    private String udf11;

    @TableField("UDF12")
    private String udf12;

    @TableField("UDF57")
    private Double udf57;

    @TableField("UDF58")
    private Double udf58;

    @TableField("UDF59")
    private Double udf59;

    @TableField("UDF60")
    private Double udf60;

    @TableField("UDF61")
    private Double udf61;

    @TableField("UDF62")
    private Double udf62;


}
