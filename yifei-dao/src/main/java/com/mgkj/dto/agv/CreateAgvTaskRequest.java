package com.mgkj.dto.agv;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateAgvTaskRequest {
    private String reqCode;
    private String reqTime;
    @ApiModelProperty(value = "任务类型", required = true)
    private String taskTyp;
    @ApiModelProperty(value = "容器类型", required = true)
    private String ctnrTyp;
    @ApiModelProperty(value = "位置路径", required = true)
    private List<PositionCodePath> positionCodePath;
    @ApiModelProperty(value = "优先级")
    private String priority;

    @Data
    public static class PositionCodePath {
        @ApiModelProperty(value = "位置编号", required = true)
        private String positionCode;
        @ApiModelProperty(value = "位置类型 00:位置 05:仓位 06:巷道 07:容器", required = true)
        private String type;
    }
}