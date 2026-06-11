package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/3/1
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "WMS测量值")
public class WmsMeasurementValue {
    @ApiModelProperty("检验单号")
    private String checkDocNo;
    @ApiModelProperty("检验单身uuid")
    private String wpidId;
    @ApiModelProperty("检验单身uuid")
    private BigDecimal udf01;
    @ApiModelProperty("检验单身uuid")
    private BigDecimal udf02;
    @ApiModelProperty("检验单身uuid")
    private BigDecimal udf03;



}
