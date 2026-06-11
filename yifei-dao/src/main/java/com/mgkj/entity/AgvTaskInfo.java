package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName(value ="agv_task_info")
@Data
public class AgvTaskInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @TableField(value = "taskCode")
    @ApiModelProperty(value = "任务唯一编号")
    private String taskCode;

    @TableField(value = "reqCode")
    @ApiModelProperty(value = "请求编号")
    private String reqCode;

    @TableField(value = "reqParam")
    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    @TableField(value = "respParam")
    @ApiModelProperty(value = "响应参数")
    private String respParam;

    @TableField(value = "createTime")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @TableField(value = "createBy")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @TableField(value = "taskType")
    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @TableField(value = "taskStatus")
    @ApiModelProperty(value = "任务状态 1-已创建，2-正在执行，5-取消完成，9-已结束")
    private Integer taskStatus;

    @TableField(value = "startLocationCode")
    @ApiModelProperty(value = "起始位置编号")
    private String startLocationCode;

    @TableField(value = "endLocationCode")
    @ApiModelProperty(value = "结束位置编号")
    private String endLocationCode;

    @TableField(value = "startLocationType")
    @ApiModelProperty(value = "起始位置类型")
    private String startLocationType;

    @TableField(value = "endLocationType")
    @ApiModelProperty(value = "结束位置类型")
    private String endLocationType;

    @TableField(value = "docNo")
    @ApiModelProperty(value = "工单号")
    private String docNo;

    @TableField(exist = false)
    @ApiModelProperty(value = "开始时间-接收前端参数")
    private String startTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "结束时间-接收前端参数")
    private String endTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "开始站点名称")
    private String startSiteName;

    @TableField(exist = false)
    @ApiModelProperty(value = "结束站点名称")
    private String endSiteName;

    @TableField(exist = false)
    @ApiModelProperty(value = "任务明细")
    private List<AgvTaskDetail> taskDetail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}