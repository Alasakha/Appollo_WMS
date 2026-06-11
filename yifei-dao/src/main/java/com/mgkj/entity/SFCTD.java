package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDDA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/15/14:37
 * @Description:    报工单单头档 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("SFCTD")
@ApiModel(value = "报工单单头档 实体类")
public class SFCTD extends Base {

    @ApiModelProperty(value = "报工单单别")
    @JsonProperty("TD001")
    private String TD001;

    @ApiModelProperty(value = "报工单单号")
    @JsonProperty("TD002")
    private String TD002;

    @ApiModelProperty(value = "生产日期")
    @JsonProperty("TD003")
    private String TD003;

    @ApiModelProperty(value = "工作中心")
    @JsonProperty("TD004")
    private String TD004;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TD005")
    private String TD005;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TD006")
    private String TD006;

    @ApiModelProperty(value = "打印次数")
    @JsonProperty("TD007")
    private BigDecimal TD007;

    @ApiModelProperty(value = "单据日期")
    @JsonProperty("TD008")
    private String TD008;

    @ApiModelProperty(value = "审核者")
    @JsonProperty("TD009")
    private String TD009;

    @ApiModelProperty(value = "签核状态码")
    @JsonProperty("TD010")
    private String TD010;

    @ApiModelProperty(value = "传送次数")
    @JsonProperty("TD011")
    private BigDecimal TD011;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD012")
    private String TD012;  //C	1.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD013")
    private String TD013;  //C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD014")
    private String TD014;  //V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD015")
    private BigDecimal TD015;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD016")
    private BigDecimal TD016;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD017")
    private BigDecimal TD017;  //N	16.6	预留字段

    @ApiModelProperty(value = "班组编号")
    @JsonProperty("TD018")
    private String TD018;

    @ApiModelProperty(value = "合计金额")
    @JsonProperty("TD019")
    private BigDecimal TD019;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TD020")
    private String TD020;

    @ApiModelProperty(value = "sMES产生")
    @JsonProperty("TD021")
    private String TD021;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD022")
    private String TD022;

}
