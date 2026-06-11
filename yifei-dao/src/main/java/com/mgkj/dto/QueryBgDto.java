package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "员工报工查询")
public class QueryBgDto {

    @ApiModelProperty("第几页")
    private Integer pageNum;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    @ApiModelProperty("单别")
    private String single;
//    @ApiModelProperty("派工单号")
//    private String odd;
//    @ApiModelProperty("派工序号")
//    private String xh;
    @ApiModelProperty("员工id")
    private String userId;

    // 起始时间
    @ApiModelProperty(value = "起始时间")
    private String beginTime;

    // 结束时间
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
