package com.mgkj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubmitAgvTaskDTO {
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
    @ApiModelProperty(value = "条码列表ID", required = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> barCodeList;

    @ApiModelProperty(value = "创建人", required = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createBy;

    @Data
    public static class PositionCodePath {
        @ApiModelProperty(value = "位置编号", required = true)
        private String positionCode;
        @ApiModelProperty(value = "位置类型 00:位置 05:仓位 06:巷道 07:容器", required = true)
        private String type;
    }
}
