package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "DscmaListDto")
public class DscmaListDto {

    @ApiModelProperty("用户编码")
    private String ma001;

    @ApiModelProperty("用户名称")
    private String ma002;

    @ApiModelProperty("部门编号列表")
    private List<String> deptNoList;

}
