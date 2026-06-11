package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "条码查询返回对象")
public class DeliveryDetailVo {
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "预计发货数")
    private String planQty;
    @ApiModelProperty(value = "已发货数")
    private String deliveryQty;
    @ApiModelProperty(value = "未发货数")
    private String unDeliveryQty;
    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;
    @ApiModelProperty(value = "库位")
    private String location;
    @ApiModelProperty(value = "库存单位名称")
    private String unitName;
    @ApiModelProperty(value = "库存单位Code")
    private String unitCode;
    @ApiModelProperty(value = "单号列表")
    private List<String> docNoList;
    @ApiModelProperty(value = "单号列表")
    private String docNoListStr;
}
