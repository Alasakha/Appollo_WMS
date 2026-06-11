package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "调拨单明细")
public class TransferDocDetailDTO {
    @ApiModelProperty(value = "调拨单单号")
    private String docNo;
    private String createBy;
}
