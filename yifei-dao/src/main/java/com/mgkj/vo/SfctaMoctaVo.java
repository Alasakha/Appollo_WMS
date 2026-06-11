package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.entity.SFCTA;
import com.mgkj.entity.SfcteBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "工单Vo")
public class SfctaMoctaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("MO_ROUTING_D_ID")
    private String MO_ROUTING_D_ID;
    @ApiModelProperty("单别")
    private String ta001;
    @ApiModelProperty("单号")
    private String ta002;
    @ApiModelProperty("加工顺序(工序)")
    private String ta003;
    @ApiModelProperty("工艺")
    private String ta004;
    @ApiModelProperty("部门")
    private String ta064;
    @ApiModelProperty("产品品号")
    private String ta006;
    @ApiModelProperty("单位")
    private String ta007;
    @ApiModelProperty("工作中心(供应商名称)")
    private String gz;
    @ApiModelProperty("产品品名")
    private String ta034;
    @ApiModelProperty("产品规格")
    private String ta035;
    @ApiModelProperty("开工时间")
    private String kgtime;
    @ApiModelProperty("预计产量")
    private BigDecimal ta015;
    @ApiModelProperty("pdf")
    private String pdf;
    @ApiModelProperty("部门名称")
    private String dname;
    @ApiModelProperty("派/报工数量")
    private BigDecimal num;
    @ApiModelProperty("状态值")
    private String ta011;
    @ApiModelProperty("百分比")
    private String percent;
    @ApiModelProperty("订单号")
    private String ta027;
    @ApiModelProperty("工艺性质")
    private String ta005;
    @ApiModelProperty(value = "备注")
    @JsonProperty("TA029")
    private String TA029;

    @ApiModelProperty(value = "工艺名称")
    private String name;

    @ApiModelProperty(value = "子工序列表")
    private List<SfcteBo> sonList;
}
