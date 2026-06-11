package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "PO_QC_RESULT_D实体类，用于映射PO_QC_RESULT_D表数据")
@TableName("PO_QC_RESULT_D")
public class POQCRESULTD {

    /**
     * 主键
     */
    @TableId(value = "PO_QC_RESULT_D_ID")
    private String PO_QC_RESULT_D_ID;

    /**
     * 检验日期
     */
    @TableField(value = "QC_DATE")
    private String QC_DATE;

    /**
     * 合格业务数量
     */
    @TableField(value = "QUALIFIED_BUSINESS_QTY")
    private BigDecimal QUALIFIED_BUSINESS_QTY;

    /**
     * 不合格业务数量
     */
    @TableField(value = "UNQUALIFIED_BUSINESS_QTY")
    private BigDecimal UNQUALIFIED_BUSINESS_QTY;

    /**
     * 检验破坏业务数量
     */
    @TableField(value = "IN_DESTROYED_BUSINESS_QTY")
    private BigDecimal IN_DESTROYED_BUSINESS_QTY;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String REMARK;

    /**
     * 检验单号
     */
    @TableField(value = "PO_INSPECTION_CODE")
    private String PO_INSPECTION_CODE;

    /**
     * 待判定业务数量
     */
    @TableField(value = "DETERMINE_QTY")
    private BigDecimal DETERMINE_QTY ;

    /**
     * 业务判定状态
     */
    @TableField(value = "RESULT_STATUS")
    private String RESULT_STATUS;

    /**
     * 检验单
     */
    @TableField(value = "PO_INSPECTION_ID")
    private Object PO_INSPECTION_ID;

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
    @TableField(value = "PO_QC_RESULT_ID")
    private String PO_QC_RESULT_ID;

    /**
     *
     */
    @TableField(value = "QC_TIMES")
    private Integer QC_TIMES;

    /**
     *
     */
    @TableField(value = "GENERATE_SOURCE")
    private String GENERATE_SOURCE;

}