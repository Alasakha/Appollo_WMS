package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-09
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("商品信息实体类")
public class OrderVo implements Serializable {

    @ApiModelProperty(value = "品号")
    @JsonProperty("MB001")
    private String MB001;

    @ApiModelProperty(value = "品名")
    @JsonProperty("MB002")
    private String MB002;

    @ApiModelProperty(value = "规格")
    @JsonProperty("MB003")
    private String MB003;

    @ApiModelProperty(value = "标准售价")
    @JsonProperty("MB047")
    private String MB047;

    @ApiModelProperty(value = "审核状况")
    @JsonProperty("MB109")
    private String MB109;

    @ApiModelProperty(value = "产品图号")
    @JsonProperty("MB029")
    private String MB029;

    @ApiModelProperty(value = "审核员")
    @JsonProperty("MV002")
    private String MV002;
}
