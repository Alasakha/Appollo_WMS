package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName messagePz
 */
@TableName(value ="messagePz")
@Data
@Api(tags = "消息配置实体类")
public class Messagepz implements Serializable {
    /**
     * 主键uuid
     */
    @TableId(value = "uuid", type = IdType.AUTO)
    @ApiModelProperty("主键uuid")
    private Integer uuid;

    /**
     * 请求路径
     */
    @TableField(value = "path")
    @ApiModelProperty("请求路径")
    private String path;

    /**
     * 配置名称
     */
    @TableField(value = "name")
    @ApiModelProperty("配置名称")
    private String name;

    /**
     * 消息模版的uid
     */
    @TableField(value = "uid")
    @ApiModelProperty("消息模版的uid")
    private Integer uid;

    /**
     * 创建人名称
     */
    @TableField(value = "createName")
    @ApiModelProperty("创建人名称")
    private String createName;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    @ApiModelProperty("创建时间")
    private String createTime;


    @TableField(value = "personnel")
    @ApiModelProperty("人员编号集合")
    private String personnel;

    @TableField(value = "department")
    @ApiModelProperty("部门编号集合")
    private String department;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}