package com.yfwms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author mgkj
 * @since 2024-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PURCC")
@ApiModel(value = "Purcc对象", description = "")
public class Purcc implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "UDF01")
    @TableField("UDF01")
    private String udf01;

    @JsonProperty(value = "CC005")
    @TableField("CC005")
    private String cc005;

    @JsonProperty(value = "CC022")
    @TableField("CC022")
    private String cc022;

    @JsonProperty(value = "UDF12")
    @TableField("UDF12")
    private String udf12;

    @JsonProperty(value = "CC002")
    @TableId("CC002")
    private String cc002;

    @JsonProperty(value = "CC025")
    @TableField("CC025")
    private Double cc025;

    @JsonProperty(value = "UDF59")
    @TableField("UDF59")
    private Double udf59;

    @JsonProperty(value = "CC008")
    @TableField("CC008")
    private String cc008;

    @JsonProperty(value = "MODI_DATE")
    @TableField("MODI_DATE")
    private String modiDate;

    @JsonProperty(value = "UDF53")
    @TableField("UDF53")
    private Double udf53;

    @JsonProperty(value = "CC019")
    @TableField("CC019")
    private String cc019;

    @JsonProperty(value = "USR_GROUP")
    @TableField("USR_GROUP")
    private String usrGroup;

    @JsonProperty(value = "UDF09")
    @TableField("UDF09")
    private String udf09;

    @JsonProperty(value = "UDF56")
    @TableField("UDF56")
    private Double udf56;

    @JsonProperty(value = "CC016")
    @TableField("CC016")
    private String cc016;

    @JsonProperty(value = "CC003")
    @TableField("CC003")
    private String cc003;

    @JsonProperty(value = "CREATE_DATE")
    @TableField("CREATE_DATE")
    private String createDate;

    @JsonProperty(value = "UDF10")
    @TableField("UDF10")
    private String udf10;

    @JsonProperty(value = "UDF07")
    @TableField("UDF07")
    private String udf07;

    @JsonProperty(value = "CC017")
    @TableField("CC017")
    private String cc017;

    @JsonProperty(value = "FLAG")
    @TableField("FLAG")
    private Double flag;

    @JsonProperty(value = "CC023")
    @TableField("CC023")
    private String cc023;

    @JsonProperty(value = "CC020")
    @TableField("CC020")
    private Double cc020;

    @JsonProperty(value = "UDF62")
    @TableField("UDF62")
    private Double udf62;

    @JsonProperty(value = "UDF51")
    @TableField("UDF51")
    private Double udf51;

    @JsonProperty(value = "CC011")
    @TableField("CC011")
    private String cc011;

    @JsonProperty(value = "UDF04")
    @TableField("UDF04")
    private String udf04;

    @JsonProperty(value = "UDF54")
    @TableField("UDF54")
    private Double udf54;

    @JsonProperty(value = "CC014")
    @TableField("CC014")
    private Double cc014;

    @JsonProperty(value = "COMPANY")
    @TableField("COMPANY")
    private String company;

    @JsonProperty(value = "CC015")
    @TableField("CC015")
    private String cc015;

    @JsonProperty(value = "UDF55")
    @TableField("UDF55")
    private Double udf55;

    @JsonProperty(value = "CREATOR")
    @TableField("CREATOR")
    private String creator;

    @JsonProperty(value = "MODIFIER")
    @TableField("MODIFIER")
    private String modifier;

    @JsonProperty(value = "UDF52")
    @TableField("UDF52")
    private Double udf52;

    @JsonProperty(value = "CC018")
    @TableField("CC018")
    private String cc018;

    @JsonProperty(value = "CC012")
    @TableField("CC012")
    private String cc012;

    @JsonProperty(value = "CC026")
    @TableField("CC026")
    private String cc026;

    @JsonProperty(value = "UDF60")
    @TableField("UDF60")
    private Double udf60;

    @JsonProperty(value = "UDF05")
    @TableField("UDF05")
    private String udf05;

    @JsonProperty(value = "CC009")
    @TableField("CC009")
    private String cc009;

    @JsonProperty(value = "UDF02")
    @TableField("UDF02")
    private String udf02;

    @JsonProperty(value = "UDF57")
    @TableField("UDF57")
    private Double udf57;

    @JsonProperty(value = "CC006")
    @TableField("CC006")
    private String cc006;

    @JsonProperty(value = "UDF03")
    @TableField("UDF03")
    private String udf03;

    @JsonProperty(value = "CC007")
    @TableField("CC007")
    private Double cc007;

    @JsonProperty(value = "CC013")
    @TableField("CC013")
    private Double cc013;

    @JsonProperty(value = "UDF61")
    @TableField("UDF61")
    private Double udf61;

    @JsonProperty(value = "UDF06")
    @TableField("UDF06")
    private String udf06;

    @JsonProperty(value = "CC010")
    @TableField("CC010")
    private String cc010;

    @JsonProperty(value = "CC024")
    @TableField("CC024")
    private String cc024;

    @JsonProperty(value = "CC001")
    @TableField("CC001")
    private String cc001;

    @JsonProperty(value = "UDF58")
    @TableField("UDF58")
    private Double udf58;

    @JsonProperty(value = "CC021")
    @TableField("CC021")
    private Double cc021;

    @JsonProperty(value = "CC027")
    @TableField("CC027")
    private String cc027;

    @JsonProperty(value = "CC004")
    @TableField("CC004")
    private String cc004;

    @JsonProperty(value = "UDF11")
    @TableField("UDF11")
    private String udf11;

    @JsonProperty(value = "UDF08")
    @TableField("UDF08")
    private String udf08;


}