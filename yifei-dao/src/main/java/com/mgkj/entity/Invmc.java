package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("INVMC")
@ApiModel("品号仓库档")
public class Invmc extends Base {

    @TableField("MC001")
    private String mc001;

    @TableField("MC002")
    private String mc002;

    @TableField("MC003")
    private String mc003;

    @TableField("MC004")
    private Double mc004;

    @TableField("MC005")
    private Double mc005;

    @TableField("MC006")
    private Double mc006;

    @TableField("MC007")
    private Double mc007;

    @TableField("MC008")
    private Double mc008;

    @TableField("MC009")
    private Double mc009;

    @TableField("MC010")
    private Double mc010;

    @TableField("MC011")
    private String mc011;

    @TableField("MC012")
    private String mc012;

    @TableField("MC013")
    private String mc013;

    @TableField("MC014")
    private Double mc014;

    @TableField("MC015")
    private String mc015;

    @TableField("MC016")
    private String mc016;

    @TableField("MC017")
    private String mc017;

    @TableField("MC018")
    private String mc018;

    @TableField("MC019")
    private Double mc019;

    @TableField("MC020")
    private Double mc020;

    @TableField("MC021")
    private Double mc021;

}
