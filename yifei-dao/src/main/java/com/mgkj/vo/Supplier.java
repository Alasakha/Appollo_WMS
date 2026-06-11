package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "供应商")
public class Supplier {

    @ApiModelProperty("编号")
    private String supplierCode;

    @ApiModelProperty("简称")
    private String supplierName;

    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "条数")
    private Integer size;

}
