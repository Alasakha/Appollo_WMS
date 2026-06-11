package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 生成入库检验单测量值/CHT/產生入庫檢驗單測量值/ENU/Generate Stock-In Inspection Measurement Values
 * @TableName MO_INSPECTION_SD1
 */
@TableName(value ="MO_INSPECTION_SD1")
@Data
public class MoInspectionSd1 implements Serializable {
    /**
     * 
     */
    @TableId(value = "MO_INSPECTION_SD1_ID")
    private Object moInspectionSd1Id;

    /**
     * 
     */
    @TableField(value = "SequenceNumber")
    private Integer sequencenumber;

    /**
     * 
     */
    @TableField(value = "MEASURED_VALUE")
    private BigDecimal measuredValue;

    /**
     * 
     */
    @TableField(value = "DECISION")
    private Boolean decision;

    /**
     * 
     */
    @TableField(value = "REMARK")
    private String remark;

    /**
     * 自定义字段0
     */
    @TableField(value = "UDF001")
    private BigDecimal udf001;

    /**
     * 自定义字段1
     */
    @TableField(value = "UDF002")
    private BigDecimal udf002;

    /**
     * 自定义字段2
     */
    @TableField(value = "UDF003")
    private BigDecimal udf003;

    /**
     * 自定义字段3
     */
    @TableField(value = "UDF011")
    private BigDecimal udf011;

    /**
     * 自定义字段4
     */
    @TableField(value = "UDF012")
    private BigDecimal udf012;

    /**
     * 自定义字段5
     */
    @TableField(value = "UDF013")
    private BigDecimal udf013;

    /**
     * 自定义字段6
     */
    @TableField(value = "UDF021")
    private String udf021;

    /**
     * 自定义字段7
     */
    @TableField(value = "UDF022")
    private String udf022;

    /**
     * 自定义字段8
     */
    @TableField(value = "UDF023")
    private String udf023;

    /**
     * 自定义字段9
     */
    @TableField(value = "UDF024")
    private String udf024;

    /**
     * 自定义字段10
     */
    @TableField(value = "UDF025")
    private String udf025;

    /**
     * 自定义字段11
     */
    @TableField(value = "UDF026")
    private String udf026;

    /**
     * 自定义字段12
     */
    @TableField(value = "UDF041")
    private LocalDateTime udf041;

    /**
     * 自定义字段13
     */
    @TableField(value = "UDF042")
    private LocalDateTime udf042;

    /**
     * 自定义字段14
     */
    @TableField(value = "UDF051")
    private Object udf051;

    /**
     * 自定义字段15
     */
    @TableField(value = "UDF052")
    private Object udf052;

    /**
     * 自定义字段16
     */
    @TableField(value = "UDF053")
    private Object udf053;

    /**
     * 自定义字段17
     */
    @TableField(value = "UDF054")
    private Object udf054;

    /**
     * 版本号，不要随意更改
     */
    @TableField(value = "Version")
    private LocalDateTime version;

    /**
     * 创建日期
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createdate;

    /**
     * 最后修改日期
     */
    @TableField(value = "LastModifiedDate")
    private LocalDateTime lastmodifieddate;

    /**
     * 修改日期
     */
    @TableField(value = "ModifiedDate")
    private LocalDateTime modifieddate;

    /**
     * 创建者
     */
    @TableField(value = "CreateBy")
    private Object createby;

    /**
     * 最后修改者
     */
    @TableField(value = "LastModifiedBy")
    private Object lastmodifiedby;

    /**
     * 修改者
     */
    @TableField(value = "ModifiedBy")
    private Object modifiedby;

    /**
     * 单据状态属性
     */
    @TableField(value = "ApproveStatus")
    private String approvestatus;

    /**
     * 修改日期
     */
    @TableField(value = "ApproveDate")
    private LocalDateTime approvedate;

    /**
     * 修改人
     */
    @TableField(value = "ApproveBy")
    private Object approveby;

    /**
     * 
     */
    @TableField(value = "MO_INSPECTION_D_ID")
    private Object moInspectionDId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



}