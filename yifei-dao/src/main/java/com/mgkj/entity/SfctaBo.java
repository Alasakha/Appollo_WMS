package com.mgkj.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@Api("SFCTA单身")
public class SfctaBo {

    @ApiModelProperty("MO_ROUTING_D_ID")
    private String MO_ROUTING_D_ID;

    @ApiModelProperty("单别-单号")
    private String dh;

    @ApiModelProperty("加工顺序(工序)")
    private String ta003;

    @ApiModelProperty("工艺")
    private String ta004;

    @ApiModelProperty("工艺性质")
    private String ta005;

    @ApiModelProperty(value = "工艺名称")
    private String name;

    @ApiModelProperty("工作中心(供应商名称)")
    private String gz;

    @ApiModelProperty("pdf")
    private String pdf;

    @ApiModelProperty("百分比")
    private String percent;

    @ApiModelProperty(value = "子工序列表")
    private List<SfcteBo> sonList;
}
