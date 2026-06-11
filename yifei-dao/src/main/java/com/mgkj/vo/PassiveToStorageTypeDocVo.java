package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "条码查询返回对象")
public class PassiveToStorageTypeDocVo {
    @ApiModelProperty(value = "单别编号")
    private String typeCode;
    @ApiModelProperty(value = "单别全称")
    private String typeName;

}
