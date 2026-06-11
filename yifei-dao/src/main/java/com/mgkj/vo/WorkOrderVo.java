package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-09
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "工单Vo")
public class WorkOrderVo implements Serializable {

    //单别
    private String single;

    //单号
    private String singleNumber;

    // 工作中心
    @ApiModelProperty("工作中心")
    private String workCenter;

    // 开单日期
    @ApiModelProperty("开单日期")
    private String beginDate;

    // 工单单别
    @ApiModelProperty("工单单别")
    private String workSeparate;

    // 工单单号
    @ApiModelProperty("工单单号")
    private String workNumber;

    // 产品品号
    @ApiModelProperty("产品品号")
    private String productArticle;

    // 产品品名
    @ApiModelProperty("产品品名")
    private String productName;

    // 产品规格
    @ApiModelProperty("产品规格")
    private String productSpecification;

    // 预计产量
    @ApiModelProperty("预计产量")
    private String expectedOutput;

    // 已入库数
    @ApiModelProperty("已入库数")
    private String storedItems;

    // 预计开工
    @ApiModelProperty("预计开工")
    private String expectedStart;

    // 预计完工
    @ApiModelProperty("预计完工")
    private String expectedEnd;

    // 数量
    @ApiModelProperty("数量")
    private String number;
}
