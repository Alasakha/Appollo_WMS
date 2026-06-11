package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "分页bean")
public class PojoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页码")
    private Integer current;

    @ApiModelProperty("页数大小")
    private Integer size;

    @ApiModelProperty("(单别) 检验模块不用管")
    private String single;

    @ApiModelProperty("(单号) 检验模块不用管")
    private String odd;

    @ApiModelProperty("品号 检验模块")
    private String ph;

    @ApiModelProperty("序号")
    private String xh;

    @ApiModelProperty("品名 检验模块")
    private String pm;

    @ApiModelProperty("工艺 检验模块")
    private String gy;

    @ApiModelProperty("检验方式 检验模块")
    private String fs;

}
