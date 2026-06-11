package com.mgkj.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@TableName("barcodeChangeRecord")
public class BarcodeChangeRecord {
    @ApiModelProperty(value = "UUID")
    private String uuid;
    @ApiModelProperty(value = "条码")
    private String barcode;
    @ApiModelProperty(value = "原有数量")
    private BigDecimal originalQuantity;
    @ApiModelProperty(value = "修改数量")
    private BigDecimal changedQuantity;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}