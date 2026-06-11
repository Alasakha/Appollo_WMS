package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/2/29
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "到货单简易信息Vo")
public class PurchaseArrivalSimpleVo {
    //收货单号
    @ApiModelProperty("收货单号")
    private String docNo;
    //日期
    @ApiModelProperty("日期")
    private String docDate;
    //供应商
    @ApiModelProperty("供应商")
    private String supplierName;
    //供应商编号
    @ApiModelProperty("供应商编号")
    private String supplierCode;
}
