package com.mgkj.dto.agv;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryMoList {
    @ApiModelProperty(value = "工单号")
    private String docNo;
    @ApiModelProperty("排产日期 yyyyMMdd 格式")
    private String planDate;
    @ApiModelProperty("生产线")
    private String productionLine;
}
