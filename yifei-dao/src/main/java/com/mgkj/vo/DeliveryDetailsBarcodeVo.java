package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDetailsBarcodeVo {
    @ApiModelProperty("条码")
    private String barcode;
    @ApiModelProperty("数量")
    private BigDecimal qty;
    @ApiModelProperty("条码匹配状态(null未匹配，1已匹配，2已到货)")
    private String lotAtt31;
    @ApiModelProperty("扫码记录id(purchase_summary.id，已匹配时有值)")
    private String id;
}
