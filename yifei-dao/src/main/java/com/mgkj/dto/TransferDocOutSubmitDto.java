package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

/**
 * @author yyyjcg
 * @date 2024/5/17
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("库存调拨出库提交dto")
public class TransferDocOutSubmitDto {

    @ApiModelProperty(value="库存调拨号，1601库存调拨单")
    private String docNo;

    @ApiModelProperty(value="条码",required = true)
    private String barcode;

    @ApiModelProperty(value="料号",required = true)
    private String itemCode;

    @ApiModelProperty(value="单位",required = true)
    private String unitCode;

    @ApiModelProperty(value="调出仓库编码",required = true)
    private String fromWarehouseCode = "";

    @ApiModelProperty(value="调出库位编码",required = true)
    private String fromBinCode;

    @ApiModelProperty(value="调入仓库编码",required = true)
    private String toWarehouseCode = "";

    @ApiModelProperty(value="调入库位编码",required = true)
    private String toBinCode;

    @ApiModelProperty(value="匹配量",required = true)
    private BigDecimal matchQty;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "调出收货机构")
    private String fromShjg;

    @ApiModelProperty(value = "调入收货机构")
    private String toShjg;

    //    @ApiModelProperty(value="最后修改时间")
//    private String lastModifiedDate;
//
//    @ApiModelProperty(value="修改时间")
//    private String modifiedDate;
//
//    @ApiModelProperty(value="最后修改人")
//    private String lastModifiedBy = "CDCAB790-A674-8BD3-F817-C04C90B7CACD";
//    @ApiModelProperty(value="修改人")
//    private String modifiedBy="CDCAB790-A674-8BD3-F817-C04C90B7CACD";
//

}
