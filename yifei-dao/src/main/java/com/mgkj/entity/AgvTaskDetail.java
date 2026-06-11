package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName agv_task_detail
 */
@TableName(value ="agv_task_detail")
@Data
public class AgvTaskDetail implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id主键")
    private Long id;

    @TableField(value = "barcode")
    @ApiModelProperty(value = "条码")
    private String barcode;

    @TableField(value = "itemCode")
    @ApiModelProperty(value = "品号")
    private String itemCode;

    @TableField(value = "itemName")
    @ApiModelProperty(value = "品名")
    private String itemName;

    @TableField(value = "itemSpec")
    @ApiModelProperty(value = "规格")
    private String itemSpec;

    @TableField(value = "qty")
    @ApiModelProperty(value = "数量")
    private String qty;

    @TableField(value = "docNo")
    @ApiModelProperty(value = "单据编号")
    private String docNo;

    @TableField(value = "agvTaskId")
    @ApiModelProperty(value = "agv任务id")
    private Integer agvTaskId;

    @TableField(value = "taskCode")
    @ApiModelProperty(value = "任务编号")
    private String taskCode;

    @TableField(value = "reqCode")
    @ApiModelProperty(value = "请求编号")
    private String reqCode;

    @TableField(value = "status")
    @ApiModelProperty(value = "状态：0-初始 1-已提交")
    private Integer status;

    @TableField(value = "type")
    @ApiModelProperty(value = "类型：0-配送 1-包装")
    private Integer type;

    @TableField(value = "createdAt")
    private String createdAt;

    @TableField(value = "createdBy")
    private String createdBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}