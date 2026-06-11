package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "结束派工")
public class EndPgDto {
    // 单号
    @ApiModelProperty(value = "单号")
    private String odd;

    //序号
    @ApiModelProperty(value = "序号")
    private String xh;
}
