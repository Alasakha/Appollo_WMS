package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PRSVo {
    @ApiModelProperty(value = "到货单单号")
    private String arrivalNo;
    @ApiModelProperty(value = "供应商编号")
    private String supplierCode;
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    @ApiModelProperty(value = "单据日期")
    private String docDate;
}
