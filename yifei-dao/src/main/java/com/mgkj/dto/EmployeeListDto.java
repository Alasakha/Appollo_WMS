package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("EmployeeListDto")
@Data
public class EmployeeListDto {

    @ApiModelProperty("工作中心")
    private String ta021;

    @ApiModelProperty("姓名")
    private String ma002;

    @ApiModelProperty("左时间区间")
    private String createTimeLeft;

    @ApiModelProperty("右时间区间")
    private String createTimeRight;

}
