package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName agv_warehouse
 */
@TableName(value = "agv_warehouse")
@Data
public class AgvWarehouse implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "positionCode")
    @ApiModelProperty(value = "仓位编号")
    private String positionCode;

    @TableField(value = "shelfCode")
    @ApiModelProperty(value = "货架编号")
    private String shelfCode;

    @TableField(value = "stationCode")
    @ApiModelProperty(value = "站点编号")
    private String stationCode;

    @TableField(value = "siteName")
    @ApiModelProperty(value = "现场名称")
    private String siteName;

    @TableField(value = "status")
    @ApiModelProperty(value = "状态 1:有货 0:空闲")
    private Integer status;

    @TableField(value = "type")
    @ApiModelProperty(value = "类型: 普通缓冲区/巷道缓冲区/巷道")
    private String type;

    @TableField(value = "parentId")
    @ApiModelProperty(value = "父站点编号 stationCode")
    private String parentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}