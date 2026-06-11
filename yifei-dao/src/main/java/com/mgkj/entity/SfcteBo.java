package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName SFCTE_BO
 */
@TableName(value ="SFCTE_BO")
@Data
public class SfcteBo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "MO_ROUTING_D_ID")
    private String MO_ROUTING_D_ID;

    /**
     * 子工序
     */
    @TableField(value = "ZGX")
    private String ZGX;

    /**
     * 子工艺
     */
    @TableField(value = "ZGY")
    private String ZGY;

    /**
     * 子工艺名称
     */
    @TableField(value = "ZGYMC")
    private String ZGYMC;

    /**
     * 员工编号
     */
    @TableField(value = "code")
    private String code;

    /**
     * 员工名称
     */
    @TableField(value = "name")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}