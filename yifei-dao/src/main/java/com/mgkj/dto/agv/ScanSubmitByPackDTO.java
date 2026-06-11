package com.mgkj.dto.agv;

import com.mgkj.entity.AgvTaskDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScanSubmitByPackDTO {
    private List<AgvTaskDetail> agvTaskDetailList;
    @ApiModelProperty(value = "起始位置")
    private String startLocationCode;
    @ApiModelProperty(value = "起始位置类型")
    private String startLocationType;
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
