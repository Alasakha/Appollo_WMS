package com.mgkj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/23:23
 * @Description:    工作中心信息单头档 实体类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "工作中心信息单头档 实体类")
public class CMSMD extends Base {
    @ApiModelProperty(value = "工作中心编号")
    @JsonProperty("MD001")
    private String MD001;    //工作中心编号	C	10.0	工作中心编号

    @ApiModelProperty(value = "工作中心名称")
    @JsonProperty("MD002")
    private String MD002;    //工作中心名称	C	20.0	工作中心名称

    @ApiModelProperty(value = "工厂编号")
    @JsonProperty("MD003")
    private String MD003;    //工厂编号	C	6.0	工厂编号

    @ApiModelProperty(value = "每日人工产能")
    @JsonProperty("MD004")
    private String MD004;    //每日人工产能	N	8.2	每日人工产能

    @ApiModelProperty(value = "每日机器产能")
    @JsonProperty("MD005")
    private String MD005;    //每日机器产能	N	8.2	每日机器产能

    @ApiModelProperty(value = "标准人工效率")
    @JsonProperty("MD006")
    private String MD006;    //标准人工效率	N	5.4	标准人工效率

    @ApiModelProperty(value = "标准机器负荷")
    @JsonProperty("MD007")
    private String MD007;    //标准机器负荷	N	5.4	标准机器负荷

    @ApiModelProperty(value = "制费分摊依据")
    @JsonProperty("MD008")
    private String MD008;    //制费分摊依据	C	1.0	预留字段

    @ApiModelProperty(value = "标准工资率")
    @JsonProperty("MD009")
    private String MD009;    //标准工资率	N	11.6	预留字段

    @ApiModelProperty(value = "标准制费分摊率")
    @JsonProperty("MD010")
    private String MD010;    //标准制费分摊率	N	11.6	预留字段

    @ApiModelProperty(value = "备注")
    @JsonProperty("MD011")
    private String MD011;    //备注	V	255.0	备注

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD012")
    private String MD012;    //预留字段	C	1.0	预留字段

    @ApiModelProperty(value = "标准固定制费分摊率")
    @JsonProperty("MD013")
    private String MD013;    //标准固定制费分摊率	N	11.6	预留字段

    @ApiModelProperty(value = "清场合格证有效天数")
    @JsonProperty("MD014")
    private String MD014;    //清场合格证有效天数	N	4.0	清场合格证有效天数

    @ApiModelProperty(value = "部门")
    @JsonProperty("MD015")
    private String MD015;    //部门	C	6.0	部门编号

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD016")
    private String MD016;    //预留字段	C	1.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD017")
    private String MD017;    //预留字段	C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD018")
    private String MD018;    //预留字段	V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD019")
    private String MD019;    //预留字段	N	4.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD020")
    private String MD020;    //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD021")
    private String MD021;    //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "自动领料仓库")
    @JsonProperty("MD022")
    private String MD022;    //自动领料仓库	C	10.0	自动领料仓库

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("MD023")
    private String MD023;    //预留字段	N	16.6	预留字段


}
