package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PRSDto extends PageDTO{
    @ApiModelProperty(value = "三天前")
    private String start;

    @ApiModelProperty(value = "当天日期")
    private String end;
}
