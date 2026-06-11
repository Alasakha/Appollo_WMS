package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "员工个人产量查询Dto")
public class QueryBgNumDto {
    @ApiModelProperty(value = "员工id", required = true)
    private String userId;

    // 起始时间
    @ApiModelProperty(value = "起始时间")
    private String beginTime;

    // 结束时间
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
