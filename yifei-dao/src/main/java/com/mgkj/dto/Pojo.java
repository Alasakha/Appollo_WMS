package com.mgkj.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "派工检验分页视图")
public class Pojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页码 必传")
    private Integer current;

    @ApiModelProperty("页数大小 必传")
    private Integer size;

    @ApiModelProperty("开始时间 必传")
    private String startTime;
    @ApiModelProperty("结束时间 必传")
    private String EndTime;

    @ApiModelProperty("状态条件")
    private String status;

    @ApiModelProperty("员工编号 必传")
    private String codeid;

    @ApiModelProperty("场别")
    private String romm;

    @ApiModelProperty("检验项目 必传")
    private String checky;

    @ApiModelProperty("品号")
    private String article;

    @ApiModelProperty("工艺")
    private String gy;

    @ApiModelProperty("品号")
    private String te017;

    @ApiModelProperty("设备编码")
    private String mv002;

    @ApiModelProperty("TE001+'-'+TE002+'-'+TE003+'-'+TE009+'-'+TE019")
    private String code;
}
