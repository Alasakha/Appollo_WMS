package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mgkj.entity.DeliverySummary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DeliverySummaryVo {
    @ApiModelProperty(value = "数组里面的最大创建时间")
    @TableField("create_time")
    private Date createTime;
    List<DeliverySummary> deliverySummaryList;
}
