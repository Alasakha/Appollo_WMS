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
 * 到货检验单测量值/CHT/到貨檢驗單測量值/ENU/Measurement Value for Goods Arrival Inspection Form
 * @TableName PO_ARRIVAL_INSPECTION_SD1
 */
@TableName(value ="PO_ARRIVAL_INSPECTION_SD1")
@Data
public class PoArrivalInspectionSd1 implements Serializable {
    /**
     * 
     */
    @TableId(value = "PO_ARRIVAL_INSPECTION_SD1_ID")
    private String PO_ARRIVAL_INSPECTION_SD1_ID;

    /**
     * 
     */
    @TableField(value = "SequenceNumber")
    private Integer sequenceNumber;

    /**
     * 
     */
    @TableField(value = "MEASURED_VALUE")
    private BigDecimal MEASURED_VALUE;

    /**
     * 
     */
    @TableField(value = "DECISION")
    private Boolean DECISION;

    /**
     * 
     */
    @TableField(value = "REMARK")
    private String REMARK;

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
     * 版本号，不要随意更改
     */
    @TableField(value = "Version")
    private LocalDateTime version;

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
    @TableField(value = "PO_ARRIVAL_INSPECTION_D_ID")
    private String PO_ARRIVAL_INSPECTION_D_ID;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}