package com.mgkj.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@ApiModel("易飞权限")
@Data
public class YFPermission implements GrantedAuthority {

    /**
     * 超级用户
     * 组ID 组名称
     * 部门ID 部门名称
     *
     *
     * 程序号  PURI06， PURI07， C0PI06
     *  1自己的权限
     *  2组内他人的权限
     *  3其他组权限
     */

    @ApiModelProperty("admin超级用户  common普通用户 illegal空权限")
    private String admin;
    @ApiModelProperty("组编号")
    private String groupId;
    @ApiModelProperty("组名称")
    private String groupName;
    @ApiModelProperty("维护请购单")
    private PermissionDetail PURI06;
    @ApiModelProperty("录入采购单")
    private PermissionDetail PURI07;
    @ApiModelProperty("录入客户订单")
    private PermissionDetail COPI06;
    @ApiModelProperty("维护请购单 组权限")
    private PermissionDetail PURI06group;
    @ApiModelProperty("录入采购单 组权限")
    private PermissionDetail PURI07group;
    @ApiModelProperty("录入客户订单 组权限")
    private PermissionDetail COPI06group;
    @ApiModelProperty("维护请购单 他组权限")
    private PermissionDetail PURI06stranger;
    @ApiModelProperty("录入采购单 他组权限")
    private PermissionDetail PURI07stranger;
    @ApiModelProperty("录入客户订单 他组权限")
    private PermissionDetail COPI06stranger;

    @Override
    public String getAuthority() {
        return null;
    }
}
