package com.mgkj.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WarehouseAndBinVO {
    @ApiModelProperty(value = "仓库编号")
    private String warehouseNo;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "库位编号")
    private String binCode;
    @ApiModelProperty(value = "库位名称")
    private String binName;
}
