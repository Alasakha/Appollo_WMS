package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 维护工单质检业务子单身/CHT/維護工單質檢業務子單身
 * @TableName MFG_QC_RESULT_SD
 */
@TableName(value ="MFG_QC_RESULT_SD")
@Data
public class MfgQcResultSd implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "MFG_QC_RESULT_SD_ID")
    private String MFG_QC_RESULT_SD_ID;

    /**
     * 
     */
    @TableField(value = "SequenceNumber")
    private Integer sequenceNumber;

    /**
     * 允收业务数量
     */
    @TableField(value = "ACCEPTED_BUSINESS_QTY")
    private BigDecimal ACCEPTED_BUSINESS_QTY;

    /**
     * 拒收业务数量
     */
    @TableField(value = "RETURN_BUSINESS_QTY")
    private BigDecimal RETURN_BUSINESS_QTY;

    /**
     * 让步接收业务数量
     */
    @TableField(value = "SP_RECEIPT_BUSINESS_QTY")
    private BigDecimal SP_RECEIPT_BUSINESS_QTY;

    /**
     * 报废业务数量
     */
    @TableField(value = "SCRAP_BUSINESS_QTY")
    private BigDecimal SCRAP_BUSINESS_QTY;

    /**
     * 破坏业务数量
     */
    @TableField(value = "DESTROYED_BUSINESS_QTY")
    private BigDecimal DESTROYED_BUSINESS_QTY;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String REMARK;

    /**
     * 判定日期
     */
    @TableField(value = "DECIDE_DATE")
    private String DECIDE_DATE;

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
    private String UDF041;

    /**
     * 自定义字段13
     */
    @TableField(value = "UDF042")
    private String UDF042;

    /**
     * 自定义字段14
     */
    @TableField(value = "UDF051")
    private String UDF051;

    /**
     * 自定义字段15
     */
    @TableField(value = "UDF052")
    private String UDF052;

    /**
     * 自定义字段16
     */
    @TableField(value = "UDF053")
    private String UDF053;

    /**
     * 自定义字段17
     */
    @TableField(value = "UDF054")
    private String UDF054;

    /**
     * 版本号，不要随意更改
     */
    @TableField(value = "Version")
    private String version;

    /**
     * 单据状态属性
     */
    @TableField(value = "ApproveStatus")
    private String approveStatus;

    /**
     * 修改日期
     */
    @TableField(value = "ApproveDate")
    private String approveDate;

    /**
     * 修改人
     */
    @TableField(value = "ApproveBy")
    private String approveBy;

    /**
     * 创建日期
     */
    @TableField(value = "CreateDate")
    private String createDate;

    /**
     * 最后修改日期
     */
    @TableField(value = "LastModifiedDate")
    private String lastModifiedDate;

    /**
     * 修改日期
     */
    @TableField(value = "ModifiedDate")
    private String modifiedDate;

    /**
     * 创建者
     */
    @TableField(value = "CreateBy")
    private String createBy;

    /**
     * 最后修改者
     */
    @TableField(value = "LastModifiedBy")
    private String lastModifiedBy;

    /**
     * 修改者
     */
    @TableField(value = "ModifiedBy")
    private String modifiedBy;

    /**
     * 
     */
    @TableField(value = "MFG_QC_RESULT_D_ID")
    private String MFG_QC_RESULT_D_ID;

    /**
     * 
     */
    @TableField(value = "GENERATE_SOURCE")
    private String GENERATE_SOURCE;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}