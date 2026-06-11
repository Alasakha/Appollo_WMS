package com.mgkj.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("MES权限")
@Data
public class MesPermission {

    @ApiModelProperty("可管控车间 (*: 代表全部,  空白:表示沒有車間權限,  ST1,ST2,ST4: 表示可以看這3個車間)")
    private String workCenterPermission;

}
