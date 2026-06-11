package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("delivery_summary")
public class DeliverySummary {
    @ApiModelProperty(value = "送货单身uuid")
    @TableId
    private String uuid;
    @ApiModelProperty(value = "送货单号")
    private String deliveryNumber;
    @ApiModelProperty(value = "采购订单")
    private String purchaseOrder;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value = "应收数")
    private BigDecimal deliveryNum = BigDecimal.ZERO;
    @ApiModelProperty(value = "实收数")
    private BigDecimal matchQty = BigDecimal.ZERO;
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private String creator;
}