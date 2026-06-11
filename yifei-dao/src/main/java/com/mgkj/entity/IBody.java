package com.mgkj.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("检验身")
public class IBody {
    Integer uid;
    Integer i_uid;
    private String result;

    // 检验编号
    @ApiModelProperty("检验编号")
    private String mg003;
    // 检验项目
    @ApiModelProperty("检验项目")
    private String udf01;
    // 检验工具
    @ApiModelProperty("检验工具")
    private String udf02;
    // 抽样数量
    @ApiModelProperty("抽样数量")
    private String mg016;
    // 检验频次
    @ApiModelProperty("检验频次")
    private String udf03;
    // 检验标准
    @ApiModelProperty("检验标准")
    private String mg006;
    // 检查类型  1 计数 文本型  2.计量 数值型
    @ApiModelProperty("检查类型  1 计数 文本型  2.计量 数值型")
    private String mg009;
    // 关键尺寸
    @ApiModelProperty("关键尺寸")
    private String udf05;
    // 正公差
    @ApiModelProperty("正公差")
    private String mg012;
    // 负公差
    @ApiModelProperty("负公差")
    private String mg013;
    // 检验时机
    @ApiModelProperty("检验时机")
    private String mg020;
    // 工艺
    @ApiModelProperty("工艺")
    private String mg008;

}
