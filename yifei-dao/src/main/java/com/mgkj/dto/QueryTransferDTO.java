package com.mgkj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryTransferDTO {
    private String createBy;
    private String id;
    private String itemCode;
    @ApiModelProperty(value = "操作业务(5无源调拨、6无源入库、7无源出库，8库存调拨)")
    private Integer chagType;

}
