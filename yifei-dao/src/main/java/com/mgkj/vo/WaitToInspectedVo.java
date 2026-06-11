package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@ApiModel(description = "采购到货待检验DTO")
public class WaitToInspectedVo {
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "到货单号")
    private String deliveryNo;

    @ApiModelProperty(value = "到货日期")
    private String deliveryDate;
}
