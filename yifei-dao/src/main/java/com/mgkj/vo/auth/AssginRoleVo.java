package com.mgkj.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "分配菜单")
@Data
public class AssginRoleVo {

    @ApiModelProperty(value = "用户编号(Ma001)")
    private String userNo;

    @ApiModelProperty(value = "角色id列表")
    private List<Long> roleIdList;

}