package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.entity.SfctaBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "工单2Vo")
public class SfctaMoctaTwoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("单别-单号")
    private String dh;

    @ApiModelProperty("订单号")
    private String ta027;

    @ApiModelProperty("产品品号")
    private String ta006;

    @ApiModelProperty("产品品名")
    private String ta034;

    @ApiModelProperty("产品规格")
    private String ta035;

    @ApiModelProperty("单位")
    private String ta007;

    @ApiModelProperty("预计产量")
    private BigDecimal ta015;

    @ApiModelProperty("开工时间")
    private String kgtime;

    @ApiModelProperty("派/报工数量")
    private BigDecimal num;

    @ApiModelProperty("状态值")
    private String ta011;

    @ApiModelProperty("部门")
    private String ta064;

    @ApiModelProperty("部门名称")
    private String dname;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TA029")
    private String TA029;

    @ApiModelProperty(value = "SFCTA单身列表")
    private List<SfctaBo> sfctaList;
}
