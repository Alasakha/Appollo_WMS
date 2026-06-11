package com.mgkj.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-09
 * @Description:
 */
@Data
@Api(tags = "分页查询条件")
public class SelectDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页码")
    private Integer current;

    @ApiModelProperty("页数大小")
    private Integer size;

    @ApiModelProperty("工作中心")
    private String workCenter;

    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("工单号")
    private String number;

    @ApiModelProperty("生产日期开始时间")
    private String startTime;

    @ApiModelProperty("生产日期结束时间")
    private String stopTime;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("工艺")
    private String craft;

    @ApiModelProperty("订单号")
    private String orderNumber;

}
