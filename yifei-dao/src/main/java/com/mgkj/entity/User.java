package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 登录用户/CHT/登錄使用者/ENU/Login User
 * @TableName USER
 */
@TableName(value ="[USER]")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "USER_ID")
    private Object USER_ID;

    /**
     * 关联部门
     */
    @TableField(value = "Owner_Dept")
    private Object owner_Dept;

    /**
     * 关联员工
     */
    @TableField(value = "Owner_Emp")
    private Object owner_Emp;

    /**
     * 登录名
     */
    @TableField(value = "LOGONNAME")
    private String LOGONNAME;

    /**
     * 用户名
     */
    @TableField(value = "USER_NAME")
    private String USER_NAME;

    /**
     * 类型
     */
    @TableField(value = "USER_TYPE")
    private String USER_TYPE;

    /**
     * 用户标识
     */
    @TableField(value = "SECURITYIDENTITY")
    private String SECURITYIDENTITY;

    /**
     * 域用户名
     */
    @TableField(value = "DOMAINUSERNAME")
    private String DOMAINUSERNAME;

    /**
     * 域用户标识
     */
    @TableField(value = "SECURITYDOMAINUSERNAME")
    private String SECURITYDOMAINUSERNAME;

    /**
     * 登录时间
     */
    @TableField(value = "LOGONTIME")
    private LocalDateTime LOGONTIME;

    /**
     * 生效日期
     */
    @TableField(value = "AVAILABLE_DATE")
    private LocalDateTime AVAILABLE_DATE;

    /**
     * 失效日期
     */
    @TableField(value = "UNAVAILABLE_DATE")
    private LocalDateTime UNAVAILABLE_DATE;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String REMARK;

    /**
     * 默认语言别
     */
    @TableField(value = "DEF_LANGUAGE")
    private String DEF_LANGUAGE;

    /**
     * 品号快捷码
     */
    @TableField(value = "ITEM_SHORTCUT")
    private String ITEM_SHORTCUT;

    /**
     * 客户快捷码
     */
    @TableField(value = "CUSTOMER_SHORTCUT")
    private String CUSTOMER_SHORTCUT;

    /**
     * 供货商快捷码
     */
    @TableField(value = "SUPPLIER_SHORTCUT")
    private String SUPPLIER_SHORTCUT;

    /**
     * 日期格式
     */
    @TableField(value = "DATE_FORMAT")
    private String DATE_FORMAT;

    /**
     * 日期区域
     */
    @TableField(value = "DATE_AREA")
    private String DATE_AREA;

    /**
     * 一周的第一天
     */
    @TableField(value = "FIRST_DAY_OF_WEEK")
    private String FIRST_DAY_OF_WEEK;

    /**
     * 默认公司
     */
    @TableField(value = "DEF_COMPANY_ID")
    private Object DEF_COMPANY_ID;

    /**
     * 默认工厂
     */
    @TableField(value = "DEF_PLANT_ID")
    private Object DEF_PLANT_ID;

    /**
     * 默认销售域
     */
    @TableField(value = "DEF_SALES_CENTER_ID")
    private Object DEF_SALES_CENTER_ID;

    /**
     * 默认服务域
     */
    @TableField(value = "DEF_SERVICE_CENTER_ID")
    private Object DEF_SERVICE_CENTER_ID;

    /**
     * 默认采购域
     */
    @TableField(value = "DEF_SUPPLY_CENTER_ID")
    private Object DEF_SUPPLY_CENTER_ID;

    /**
     * 默认营运域
     */
    @TableField(value = "DEF_OPERATION_CENTER_ID")
    private Object DEF_OPERATION_CENTER_ID;

    /**
     * 个人默认选定的菜单MenuId
     */
    @TableField(value = "DEF_USER_MENU_ID")
    private String DEF_USER_MENU_ID;

    /**
     * 个人快捷菜单MenuId集合，以","分隔
     */
    @TableField(value = "DEF_SHOTCUT_MENU_ID")
    private String DEF_SHOTCUT_MENU_ID;

    /**
     * 个人快捷菜单作业ID，以"`"分隔
     */
    @TableField(value = "DEF_SHOTCUT_PROGRAMID")
    private String DEF_SHOTCUT_PROGRAMID;

    /**
     * 个人快捷菜单显示名称，以"`"分隔
     */
    @TableField(value = "DEF_SHOTCUT_DISPLAYNAME")
    private String DEF_SHOTCUT_DISPLAYNAME;

    /**
     * 个人快捷菜单帮助文件名，以"`"分隔
     */
    @TableField(value = "DEF_SHOTCUT_HELPID")
    private String DEF_SHOTCUT_HELPID;

    /**
     * 用户参数
     */
    @TableField(value = "User_Preferences")
    private String user_Preferences;

    /**
     * 关联员工
     */
    @TableField(value = "EMPLOYEE_ID")
    private Object EMPLOYEE_ID;

    /**
     * 版本号，不要随意更改
     */
    @TableField(value = "Version")
    private LocalDateTime version;

    /**
     * 创建日期
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createDate;

    /**
     * 最后修改日期
     */
    @TableField(value = "LastModifiedDate")
    private LocalDateTime lastModifiedDate;

    /**
     * 修改日期
     */
    @TableField(value = "ModifiedDate")
    private LocalDateTime modifiedDate;

    /**
     * 创建者
     */
    @TableField(value = "CreateBy")
    private Object createBy;

    /**
     * 最后修改者
     */
    @TableField(value = "LastModifiedBy")
    private Object lastModifiedBy;

    /**
     * 修改者
     */
    @TableField(value = "ModifiedBy")
    private Object modifiedBy;

    /**
     * 表单所在的流程实例的编号
     */
    @TableField(value = "ProcessInstanceId")
    private Object processInstanceId;

    /**
     * 附件
     */
    @TableField(value = "Attachments")
    private String attachments;

    /**
     * 自定义字段0
     */
    @TableField(value = "UDF001")
    private BigDecimal UDF001;

    /**
     * 自定义字段1
     */
    @TableField(value = "UDF002")
    private BigDecimal UDF002;

    /**
     * 自定义字段2
     */
    @TableField(value = "UDF003")
    private BigDecimal UDF003;

    /**
     * 自定义字段3
     */
    @TableField(value = "UDF011")
    private BigDecimal UDF011;

    /**
     * 自定义字段4
     */
    @TableField(value = "UDF012")
    private BigDecimal UDF012;

    /**
     * 自定义字段5
     */
    @TableField(value = "UDF013")
    private BigDecimal UDF013;

    /**
     * 自定义字段6
     */
    @TableField(value = "UDF021")
    private String UDF021;

    /**
     * 自定义字段7
     */
    @TableField(value = "UDF022")
    private String UDF022;

    /**
     * 自定义字段8
     */
    @TableField(value = "UDF023")
    private String UDF023;

    /**
     * 自定义字段9
     */
    @TableField(value = "UDF024")
    private String UDF024;

    /**
     * 自定义字段10
     */
    @TableField(value = "UDF025")
    private String UDF025;

    /**
     * 自定义字段11
     */
    @TableField(value = "UDF026")
    private String UDF026;

    /**
     * 自定义字段12
     */
    @TableField(value = "UDF041")
    private LocalDateTime UDF041;

    /**
     * 自定义字段13
     */
    @TableField(value = "UDF042")
    private LocalDateTime UDF042;

    /**
     * 自定义字段14
     */
    @TableField(value = "UDF051")
    private Object UDF051;

    /**
     * 自定义字段15
     */
    @TableField(value = "UDF052")
    private Object UDF052;

    /**
     * 自定义字段16
     */
    @TableField(value = "UDF053")
    private Object UDF053;

    /**
     * 自定义字段17
     */
    @TableField(value = "UDF054")
    private Object UDF054;

    /**
     * 单据状态属性
     */
    @TableField(value = "ApproveStatus")
    private String approveStatus;

    /**
     * 修改日期
     */
    @TableField(value = "ApproveDate")
    private LocalDateTime approveDate;

    /**
     * 修改人
     */
    @TableField(value = "ApproveBy")
    private Object approveBy;

    /**
     * 
     */
    @TableField(value = "Owner_Org_RTK")
    private String owner_Org_RTK;

    /**
     * 
     */
    @TableField(value = "Owner_Org_ROid")
    private Object owner_Org_ROid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}