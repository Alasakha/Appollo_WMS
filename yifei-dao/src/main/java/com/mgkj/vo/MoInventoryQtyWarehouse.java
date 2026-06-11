package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2025-03-06 15:42
 * @Version 1.0
 */

@Data

public class MoInventoryQtyWarehouse {


    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @ApiModelProperty(value = "数量")
    private Double inventoryQty;
}
