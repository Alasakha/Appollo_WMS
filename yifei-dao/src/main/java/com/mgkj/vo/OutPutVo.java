package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-15
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "产量 Vo")
public class OutPutVo implements Serializable {

    //入库单别
    @ApiModelProperty("入库单别")
    private String warehousingSingle;

    //入库单号
    @ApiModelProperty("入库单号")
    private String warehousingNumber;

    //入库日期
    @ApiModelProperty("入库日期")
    private String warehousingDate;

    //产品品号
    @ApiModelProperty("产品品号")
    private String productNumber;

    //产品品名
    @ApiModelProperty("产品品名")
    private String productName;

    //规格
    @ApiModelProperty("规格")
    private String specification;

    //单位
    @ApiModelProperty("单位")
    private String unit;

    //入库数量
    @ApiModelProperty("入库数量")
    private String warehousingQuantity;

    //工作中心
    private String workShop;
}
