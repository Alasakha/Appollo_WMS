package com.mgkj.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(value = "根据员工编号查询DTO")
public class QuerySfCteByTe004DTO implements Serializable {
    // 员工编号
    @ApiModelProperty(value = "员工编号")
    private String employeeNumber;

    // 单号
    @ApiModelProperty(value = "单号")
    private String odd;

    //序号
    @ApiModelProperty(value = "序号")
    private String xh;

    //品号
    @ApiModelProperty(value = "品号/品名/规格")
    private String TE017;

    // 起始时间
    @ApiModelProperty(value = "起始时间")
    private String beginTime;
    // 结束时间
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
