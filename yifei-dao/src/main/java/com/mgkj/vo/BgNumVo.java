package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "员工个人产量查询Vo")
public class BgNumVo {
    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("报工数(合格加不良)")
    private String num;
}
