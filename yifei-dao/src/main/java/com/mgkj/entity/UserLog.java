package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("USER_LOG")
@AllArgsConstructor
@NoArgsConstructor
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField(value = "[user]")
    private String user;

    @TableField(value = "[date]")
    private String date;

    private String path;

    private String ip;

    private String cost;

    private String result;

    private String params;

    @TableField(value = "[module]")
    private String module;

}
