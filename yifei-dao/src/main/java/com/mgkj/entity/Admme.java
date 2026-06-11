package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("ADMME")
public class Admme implements Serializable {

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

    @TableId("ME001")
    private String me001;

    @TableField("ME002")
    private String me002;

    @TableField("ME003")
    private String me003;

    @TableField("ME004")
    private String me004;

    @TableField("ME005")
    private String me005;

    @TableField("ME006")
    private String me006;

    @TableField("ME007")
    private String me007;

    @TableField("ME008")
    private Double me008;

    @TableField("ME009")
    private Double me009;

    @TableField("ME010")
    private Double me010;

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
