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
@ApiModel(value = "PO_QC_RESULT实体类，用于映射PO_QC_RESULT表数据")
@TableName("PO_QC_RESULT")
public class POQCRESULT {

    /**
     * 主键
     */
    @TableId(value = "PO_QC_RESULT_ID")
    private String PO_QC_RESULT_ID;

    /**
     * 关联部门
     */
    @TableField(value = "Owner_Dept")
    private String owner_Dept;

    /**
     * 关联员工
     */
    @TableField(value = "Owner_Emp")
    private String owner_Emp;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String REMARK;

    /**
     * 扣已到货量
     */
    @TableField(value = "DEDUCT_ARRIVED_QTY")
    private Boolean DEDUCT_ARRIVED_QTY;

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
     * 附件
     */
    @TableField(value = "Attachments")
    private String attachments;

    /**
     * 表单所在的流程实例的编号
     */
    @TableField(value = "ProcessInstanceId")
    private String processInstanceId;

    /**
     *
     */
    @TableField(value = "Owner_Org_RTK")
    private String owner_Org_RTK;

    /**
     *
     */
    @TableField(value = "Owner_Org_ROid")
    private String owner_Org_ROid;

    /**
     *
     */
    @TableField(value = "SOURCE_ID_RTK")
    private String SOURCE_ID_RTK;

    /**
     *
     */
    @TableField(value = "SOURCE_ID_ROid")
    private String SOURCE_ID_ROid;

    /**
     *
     */
    @TableField(value = "GENERATE_SOURCE")
    private String GENERATE_SOURCE;
}