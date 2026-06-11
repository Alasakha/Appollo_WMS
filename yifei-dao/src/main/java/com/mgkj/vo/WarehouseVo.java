package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2025-03-06 15:42
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)

public class WarehouseVo {


    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库库位")
    private String binCode;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
}
