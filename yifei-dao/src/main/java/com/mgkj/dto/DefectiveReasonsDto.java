package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "采购检验不良原因dto")
public class DefectiveReasonsDto {
    @ApiModelProperty("当前页")
    private Integer current;

    @ApiModelProperty("每页显示条数")
    private Integer size;

    @ApiModelProperty("不良原因筛选(编号/名称/类型模糊查询)")
    private String reasons;
}
