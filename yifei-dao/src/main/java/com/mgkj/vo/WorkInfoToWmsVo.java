package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("WorkInfoToWmsVo")
public class WorkInfoToWmsVo {

    @ApiModelProperty(value = "工单")
    private List<WorkInfoToWmsTempVo> workInfo;

}
