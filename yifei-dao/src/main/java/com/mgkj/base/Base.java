package com.mgkj.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/13/11:31
 * @Description:  易飞9.x 数据库表的基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "易飞9.x 数据库表的基类")
public class Base implements Serializable {
    // 公司简称
    @JsonProperty("COMPANY")
    @ApiModelProperty(value = "公司简称")
    private  String COMPANY;

    // 创建人
    @JsonProperty("CREATOR")
    @ApiModelProperty(value = "创建人")
    private String CREATOR;

    // 用户所在组
    @JsonProperty("USR_GROUP")
    @ApiModelProperty(value = "用户所在组")
    private String USR_GROUP;

    // 创建时间
    @JsonProperty("CREATE_DATE")
    @ApiModelProperty(value = "创建时间")
    private String CREATE_DATE;

    @JsonProperty("MODIFIER")
    @ApiModelProperty(value = "修改人")
    private String MODIFIER;

    // 修改时间
    @JsonProperty("MODI_DATE")
    @ApiModelProperty(value = "修改时间")
    private String MODI_DATE;

    @JsonProperty("FLAG")
    @ApiModelProperty(value = "编辑次数")
    private BigDecimal FLAG;



    // 用户自定义字段 varchar 255
    @JsonProperty("UDF01")
    @ApiModelProperty(value = "自定义字段UDF01")
    @TableField("UDF01")
    private String UDF01;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF02")
    @ApiModelProperty(value = "自定义字段UDF02")
    private String UDF02;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF03")
    @ApiModelProperty(value = "自定义字段UDF03")
    private String UDF03;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF04")
    @ApiModelProperty(value = "自定义字段UDF04")
    private String UDF04;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF05")
    @ApiModelProperty(value = "自定义字段UDF05")
    private String UDF05;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF06")
    @ApiModelProperty(value = "自定义字段UDF06")
    private String UDF06;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF07")
    @ApiModelProperty(value = "自定义字段UDF07 派/报工单中是员工名称")
    private String UDF07;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF08")
    @ApiModelProperty(value = "自定义字段UDF08")
    private String UDF08;


    // 用户自定义字段 varchar 255
    @JsonProperty("UDF09")
    @ApiModelProperty(value = "自定义字段UDF09")
    private String UDF09;

    // 用户自定义字段 varchar 255
    @JsonProperty("UDF10")
    @ApiModelProperty(value = "自定义字段UDF10")
    private String UDF10;

    // 用户自定义字段 varchar 255
    @JsonProperty("UDF11")
    @ApiModelProperty(value = "自定义字段UDF11")
    private String UDF11;

    @JsonProperty("UDF12")
    @ApiModelProperty(value = "自定义字段UDF12")
    private String UDF12;

    @JsonProperty("UDF51")
    @ApiModelProperty(value = "自定义字段UDF51")
    private BigDecimal UDF51;

    @JsonProperty("UDF52")
    @ApiModelProperty(value = "自定义字段UDF52")
    private BigDecimal UDF52;

    // 用户自定义字段 N
    @JsonProperty("UDF53")
    @ApiModelProperty(value = "自定义字段UDF53")
    private BigDecimal UDF53;

    // 用户自定义字段 N
    @JsonProperty("UDF54")
    @ApiModelProperty(value = "自定义字段UDF54")
    private BigDecimal UDF54;

    // 用户自定义字段 N
    @JsonProperty("UDF55")
    @ApiModelProperty(value = "自定义字段UDF55")
    private BigDecimal UDF55;

    // 用户自定义字段 N
    @JsonProperty("UDF56")
    @ApiModelProperty(value = "自定义字段UDF56")
    private BigDecimal UDF56;


    // 用户自定义字段 N
    @JsonProperty("UDF57")
    @ApiModelProperty(value = "自定义字段UDF57")
    private BigDecimal UDF57;

    // 用户自定义字段 N
    @JsonProperty("UDF58")
    @ApiModelProperty(value = "自定义字段UDF58")
    private BigDecimal UDF58;

    // 用户自定义字段 N
    @JsonProperty("UDF59")
    @ApiModelProperty(value = "自定义字段UDF59")
    private BigDecimal UDF59;

    // 用户自定义字段 N
    @JsonProperty("UDF60")
    @ApiModelProperty(value = "自定义字段UDF60")
    private BigDecimal UDF60;

    // 用户自定义字段 N
    @JsonProperty("UDF61")
    @ApiModelProperty(value = "自定义字段UDF61")
    private BigDecimal UDF61;

    // 用户自定义字段 N
    @JsonProperty("UDF62")
    @ApiModelProperty(value = "自定义字段UDF62")
    private BigDecimal UDF62;



}
