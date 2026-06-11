package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WarehouseAndBinDto {
    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    @ApiModelProperty("仓库编号")
    private String warehouseNo;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("库位编号")
    private String binCode;

    @ApiModelProperty("库位名称")
    private String binName;
}
