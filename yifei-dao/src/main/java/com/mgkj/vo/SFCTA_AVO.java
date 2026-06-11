package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "SFCTA_AVo")
public class SFCTA_AVO {
    @ApiModelProperty("MO_ROUTING_D_ID")
    private String MO_ROUTING_D_ID;
//    @ApiModelProperty("单别")
//    private String ta001;
//    @ApiModelProperty("单号")
//    private String ta002;
//    @ApiModelProperty("加工顺序(工序)")
//    private String ta003;
//    @ApiModelProperty("工艺")
//    private String ta004;
//
//    @ApiModelProperty("工序名称")
//    private String ta004c;

    @ApiModelProperty("子工序")
    private String ZGX;
    @ApiModelProperty("子工艺")
    private String ZGY;
    @ApiModelProperty(value = "子工艺名称")
    private String ZGYMC;
    @ApiModelProperty("员工编号")
    private String code;
    @ApiModelProperty("员工名称")
    private String name;
}
