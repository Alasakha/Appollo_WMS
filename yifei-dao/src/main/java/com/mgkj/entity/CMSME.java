package com.mgkj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/16:42
 * @Description:    部门信息档 实体类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "部门信息档 实体类")
public class CMSME extends Base {

    @ApiModelProperty(value = "部门编号")
    @JsonProperty("ME001")
    private String ME001;   //部门编号	C	6.0	部门编号

    @ApiModelProperty(value = "部门名称")
    @JsonProperty("ME002")
    private String ME002;   //部门名称	C	20.0	部门名称

    @ApiModelProperty(value = "备注")
    @JsonProperty("ME003")
    private String ME003;   //备注	V	255.0	备注

    @ApiModelProperty(value = "折旧科目")
    @JsonProperty("ME004")
    private String ME004;   //折旧科目	C	20.0	折旧科目

    @ApiModelProperty(value = "质量管理部门")
    @JsonProperty("ME005")
    private String ME005;   //质量管理部门	C	1.0	Y/N[DEF:"N"]

    @ApiModelProperty(value = "传送码")
    @JsonProperty("ME006")
    private String ME006;   //传送码	C	1.0	Y/M/N[DEF:"N"]

    @ApiModelProperty(value = "传送日期")
    @JsonProperty("ME007")
    private String ME007;   //传送日期	C	12.0	日期时间yyyymmdd hh:mm

    @ApiModelProperty(value = "有效码")
    @JsonProperty("ME008")
    private String ME008;   //有效码	C	1.0	Y/N[DEF:"Y"]

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("ME009")
    private String ME009;   //预留字段	C	1.0	预留字段

    @ApiModelProperty(value = "下发功能解析")
    @JsonProperty("ME010")
    private String ME010;   //下发功能解析	C	8.0	下发功能解析

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("ME011")
    private String ME011;   //预留字段	V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("ME012")
    private BigDecimal ME012;   //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("ME013")
    private BigDecimal ME013;   //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("ME014")
    private BigDecimal ME014;   //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "生产部门")
    @JsonProperty("ME015")
    private String ME015;   //生产部门	C	1.0	Y/N[DEF:"N"]



}
