package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName filePath
 */
@TableName(value ="filePath")
@Data
public class Filepath implements Serializable {
    /**
     * 单号
     */
    @TableField(value = "oddnumber")
    private String oddnumber;

    /**
     * 路径
     */
    @TableField(value = "path")
    private String path;

    @TableField(value = "uuid")
    private String uuid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "createDate")
    private String createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}