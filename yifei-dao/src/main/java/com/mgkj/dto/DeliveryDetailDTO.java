package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "发货单明细")
public class DeliveryDetailDTO {
    @ApiModelProperty(value = "发货单号")
    private List<String> docNoList;
    @ApiModelProperty(value = "仓库编号")
    private List<String> warehouseCodeList;
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
