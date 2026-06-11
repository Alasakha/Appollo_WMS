package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BindCtnrAndBinRequest {
    private String reqCode;
    private String reqTime;
    @ApiModelProperty(value = "容器类型", required = true)
    private String ctnrTyp;
    @ApiModelProperty(value = "仓位编号", required = true)
    private String stgBinCode;
    @ApiModelProperty(value = "是否绑定 1=绑定 0=解绑", required = true)
    private String indBind;
}
