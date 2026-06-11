package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryIssueReceiptD {
    @ApiModelProperty(value = "客户单号")
    private String docNo;
    @ApiModelProperty(value = "工作中心")
    private String workName;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
