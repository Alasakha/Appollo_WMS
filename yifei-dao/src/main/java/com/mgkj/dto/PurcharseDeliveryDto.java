package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "标签号查标签Dto")
public class PurcharseDeliveryDto {

    @ApiModelProperty("条码/送货单号")
    String number;

    @ApiModelProperty("送货单身uuid")
    String uuid;

    @ApiModelProperty("创建人")
    String createBy;

}
