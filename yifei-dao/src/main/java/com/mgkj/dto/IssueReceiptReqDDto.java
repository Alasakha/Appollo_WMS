package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.List;

@Data
public class IssueReceiptReqDDto {
    @ApiModelProperty("领料单号列表")
    List<String> docNoList;
    @ApiModelProperty("仓库列表")
    List<String> warehouseList;
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
