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
@ApiModel(value = "当天待检验的到货单身物料信息Dto")
public class PurchaseReceiptStorageWaitCheckItemDto {
//    @ApiModelProperty(value = "箱码")
//    private String barcode= "sexdrcftgvybhunjimk";

    @ApiModelProperty("到货单单号")
    private String arrivalNo;

    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "条数")
    private Integer size;
}
