package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryAgvTaskInfoDTO {

    @ApiModelProperty(value = "任务唯一编号")
    private String taskCode;

    @ApiModelProperty(value = "任务状态 1-已创建，2-正在执行，5-取消完成，9-已结束")
    private Integer taskStatus;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;
}
