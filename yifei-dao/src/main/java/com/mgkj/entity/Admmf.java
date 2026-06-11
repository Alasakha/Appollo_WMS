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
 * @since 2024-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ADMMF")
public class Admmf implements Serializable {

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

    @TableId("MF001")
    private String mf001;

    @TableField("MF002")
    private String mf002;

    @TableField("MF003")
    private String mf003;

    @TableField("MF004")
    private String mf004;

    @TableField("MF005")
    private String mf005;

    @TableField("MF006")
    private String mf006;

    @TableField("MF007")
    private String mf007;

    @TableField("MF008")
    private String mf008;

    @TableField("MF009")
    private String mf009;

    @TableField("MF010")
    private String mf010;

    @TableField("MF011")
    private String mf011;

    @TableField("MF012")
    private Double mf012;

    @TableField("MF013")
    private Double mf013;

    @TableField("MF014")
    private Double mf014;

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
