package com.mgkj.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(tags = "检验项目Dto")
public class JiayanDto {

    @ApiModelProperty("品号")
    private String mg002;

    @ApiModelProperty("检验编号")
    private String mg003;
}
