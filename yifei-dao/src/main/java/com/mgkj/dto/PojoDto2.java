package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "分页bean2")
public class PojoDto2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页码")
    private Integer current;

    @ApiModelProperty("页数大小")
    private Integer size;

    @ApiModelProperty("单别-单号")
    private String dh;

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

    @ApiModelProperty("开工时间")
    private String kgtime;

    @ApiModelProperty("升降序字段")
    private String sjStr;

    @ApiModelProperty("升或降序(不排序不传，升序传'ASC',降序传’DESC‘)")
    private String sjstatus;
}
