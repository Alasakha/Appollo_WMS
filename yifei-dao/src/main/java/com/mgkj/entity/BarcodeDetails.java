package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BarcodeDetails {
    @ApiModelProperty(value = "采购订单")
    private String purchaseOrder;

    @ApiModelProperty(value = "客户编号")
    private String customerNo;

    @ApiModelProperty(value = "品号")
    private String itemCode;

    @ApiModelProperty(value = "品名")
    private String itemName;

    @ApiModelProperty(value = "计价数量")
    private BigDecimal priceQty;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "计划到货日期")
    private Date planArrivalDate;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "员工名称")
    private String employeeName;

    @ApiModelProperty(value = "员工编号")
    private String employeeNo;

    @ApiModelProperty(value = "状态")
    private BigDecimal lot_att30;
}
