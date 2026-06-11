package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("WorkSignLogVo")
@Data
public class WorkSignLogVo {

    @ApiModelProperty("工单单别")
    private String workSingle;

    @ApiModelProperty("工单单号")
    private String workNo;

    @ApiModelProperty("前端不展示(工作日期)")
    private String workDate;

    @ApiModelProperty("首次扫码时间")
    private String startTime;

    @ApiModelProperty("最后扫码时间")
    private String endTime;

    @ApiModelProperty("人数")
    private Integer peopleNum;

    @ApiModelProperty("当日报工数量")
    private Integer bgNum;

    @ApiModelProperty("品号")
    private String TA006;

    @ApiModelProperty("品名")
    private String TA034;

    @ApiModelProperty("规格")
    private String TA035;

    @ApiModelProperty("客户单号")
    private String TA033;

    @ApiModelProperty("工作中心")
    private String TA021;

    @ApiModelProperty("工单数量")
    private BigDecimal TA015;

    @ApiModelProperty("工价")
    private BigDecimal price;

    @ApiModelProperty("总时长")
    private BigDecimal totalHours;

    @ApiModelProperty("生产线")
    private String productLine;

    @ApiModelProperty("数组")
    private List<WorkSignLogItemVo> itemList;

}
