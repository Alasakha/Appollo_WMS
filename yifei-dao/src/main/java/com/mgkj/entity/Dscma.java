package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mgkj.vo.SignInSignOutLogVo;
import com.mgkj.vo.WorkSignLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSCMA")
@Api(tags = "用户实体类")
public class Dscma implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableField("COMPANY")
//    private String company;
//
//    @TableField("CREATOR")
//    private String creator;
//
//    @TableField("USR_GROUP")
//    private String usrGroup;
//
//    @TableField("CREATE_DATE")
//    private String createDate;
//
//    @TableField("MODIFIER")
//    private String modifier;
//
//    @TableField("MODI_DATE")
//    private String modiDate;
//
//    @TableField("FLAG")
//    private Double flag;

    @TableId("MA001")
    @ApiModelProperty("编号")
    private String ma001;

    @TableField("MA002")
    @ApiModelProperty("名称")
    private String ma002;

//    @TableField("MA003")
//    private String ma003;
//
//    @TableField("MA004")
//    private String ma004;
//
//    @TableField("MA005")
//    private String ma005;
//
//    @TableField("MA006")
//    private String ma006;
//
//    @TableField("MA007")
//    private String ma007;
//
//    @TableField("MA008")
//    private String ma008;
//
//    @TableField("MA009")
//    private String ma009;

    @TableField(exist = false)
    @ApiModelProperty("部门编号")
    private String udf01;

//    @TableField("UDF02")
//    private String udf02;
//
//    @TableField("UDF03")
//    private String udf03;
//
//    @TableField("UDF04")
//    private String udf04;
//
//    @TableField("UDF05")
//    private String udf05;
//
//    @TableField("UDF06")
//    private String udf06;
//
//    @TableField("UDF51")
//    private Double udf51;
//
//    @TableField("UDF52")
//    private Double udf52;
//
//    @TableField("UDF53")
//    private Double udf53;
//
//    @TableField("UDF54")
//    private Double udf54;
//
//    @TableField("UDF55")
//    private Double udf55;
//
//    @TableField("UDF56")
//    private Double udf56;
//
//    @TableField("UDF07")
//    private String udf07;
//
//    @TableField("UDF08")
//    private String udf08;
//
//    @TableField("UDF09")
//    private String udf09;
//
//    @TableField("UDF10")
//    private String udf10;
//
//    @TableField("UDF11")
//    private String udf11;
//
//    @TableField("UDF12")
//    private String udf12;
//
//    @TableField("UDF57")
//    private Double udf57;
//
//    @TableField("UDF58")
//    private Double udf58;
//
//    @TableField("UDF59")
//    private Double udf59;
//
//    @TableField("UDF60")
//    private Double udf60;
//
//    @TableField("UDF61")
//    private Double udf61;
//
//    @TableField("UDF62")
//    private Double udf62;

    @TableField(exist = false)
    @ApiModelProperty("部门名称")
    private String deptName;

    @TableField(exist = false)
    @ApiModelProperty("工作中心")
    private String workCenter;

    @TableField(exist = false)
    @ApiModelProperty("系数")
    private BigDecimal xs;

    @TableField(exist = false)
    @ApiModelProperty("员工产时")
    private BigDecimal sc;

    @TableField(exist = false)
    @ApiModelProperty("金额")
    private BigDecimal money;

    @TableField(exist = false)
    @ApiModelProperty("工单数组")
    private List<WorkSignLogVo> docNoList;

    @TableField(exist = false)
    @ApiModelProperty("打卡签到数组")
    private List<SignInSignOutLogVo> daKaList;

}
