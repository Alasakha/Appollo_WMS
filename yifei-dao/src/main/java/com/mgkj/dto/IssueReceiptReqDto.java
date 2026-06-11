package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class IssueReceiptReqDto{

    @ApiModelProperty(value = "客户单号")
    private String customerNo;

    @ApiModelProperty(value = "开始日期")
    private String beginTime;

    @ApiModelProperty(value = "结束日期")
    private String endTime;

    @ApiModelProperty(value = "员工编号")
    private String employeeNo;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "仓库编号列表(后端使用)")
    private List<String> warehouseCodeList;

}
