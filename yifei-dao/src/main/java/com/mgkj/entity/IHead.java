package com.mgkj.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Api("检验")
public class IHead  {
    private Integer uid;
    private String result;
    private String creatTime;
    private String endTime;
    private String startTime;
    private String updateTime;

    // 工单单别
    @ApiModelProperty("派工单单别")
    private String  ta001;
    // 工单单号
    @ApiModelProperty("派工单单号")
    private String  ta002;
    // 工艺
    @ApiModelProperty("工艺")
    private String  ta004;
    // 工艺名称
    @ApiModelProperty("工艺名称")
    private String  ta024;
    // 品号
    @ApiModelProperty("品号")
    private String  ta006;
    // 品名
    @ApiModelProperty("品名")
    private String  mb002;
    // 规格
    @ApiModelProperty("规格")
    private String  mb003;
    // 客户品号
    @ApiModelProperty("客户品号")
    private String  udf01;
    // 预计生产量
    @ApiModelProperty("预计生产量")
    private BigDecimal ta015;
    // 样本数
    @ApiModelProperty("样本数")
    private BigDecimal udf66;
    // 抽检数
    @ApiModelProperty("抽检数")
    private BigDecimal udf88;
    // 类型
    @ApiModelProperty("类型")
    private String type;

    // 检验人员
    @ApiModelProperty("检验人员")
    private String people;
    // 检验地点
    @ApiModelProperty("检验地点")
    private String address;
    // 到货单别
    @ApiModelProperty("到货单别")
    private String arriveSgetsu;
    // 到货单单号
    @ApiModelProperty("到货单单号")
    private String arriveOddNmber;
    // 检验地点
    @ApiModelProperty("检验地点")
    String udf06;

    @ApiModelProperty("检验类型")
    private String mg020;
}
