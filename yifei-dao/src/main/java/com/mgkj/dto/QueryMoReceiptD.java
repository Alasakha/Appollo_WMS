package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryMoReceiptD {
//    @ApiModelProperty(value = "车架号")
//    private String snNo;
    @ApiModelProperty(value = "生成入库单号")
    private String docNo;
//    @ApiModelProperty(value = "客户单号")
//    private String khdh;
//    @ApiModelProperty(value = "工单号")
//    private String moDocNo;
//    @ApiModelProperty(value = "开始时间")
//    private String startTime;
//    @ApiModelProperty(value = "结束时间")
//    private String endTime;
}
