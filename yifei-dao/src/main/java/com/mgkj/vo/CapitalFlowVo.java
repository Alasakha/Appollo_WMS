package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yyyjcg
 * @date 2024/1/9
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "资金流 Vo")
public class CapitalFlowVo {

    @ApiModelProperty(value = "账户类型")
    @JsonProperty("MA003")
    private String MA003;

    @ApiModelProperty(value = "流动详情")
    @JsonProperty("capitalFlow")
    private String capitalFlow;

    private String MandY;
}
