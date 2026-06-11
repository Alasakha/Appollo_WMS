package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "生产入库检验单单头Vo")
public class MoCheckHeader {

    @ApiModelProperty("检验单uuid")
    private String inspectionUuid;

    @ApiModelProperty("部门编号")
    private String departmentCode;

    @ApiModelProperty("部门UUID")
    private String departmentUUID;

    @ApiModelProperty("工厂编码")
    private String plantCode;

    @ApiModelProperty("检验类型")
    private String checkName;

    @ApiModelProperty("检验单号")
    private String checkDocNo;

    @ApiModelProperty("生产入库申请单号")
    private String applyDocNo;

    @ApiModelProperty("生产入库申请单单身序号")
    private String applyDocNoXh;

    @ApiModelProperty("工单")
    private String MoDocNo;

    @ApiModelProperty("供应商编码")
    private String supplierCode;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("部门名称")
    private String departmentName;


    @ApiModelProperty("检验员")
    private String employeeName;

    @ApiModelProperty("检验员编号")
    private String employeeCode;

    @ApiModelProperty("检验员UUID")
    private String employeeUUID;

    @ApiModelProperty("客户单号")
    private String receiptNo;

    @ApiModelProperty("创建日期")
    private String createDate;

    @ApiModelProperty("检验日期")
    private String checkDate;

    @ApiModelProperty("检验期限")
    private String  checkTerm;

    @ApiModelProperty("物料编码")
    private String itemCode;

    @ApiModelProperty("物料名称")
    private String itemName;

    @ApiModelProperty("物料规格")
    private String itemSpec;

    @ApiModelProperty("特征码")
    private String itemFeatureId;

    @ApiModelProperty("批次号")
    private String lotCode;

    @ApiModelProperty("批次描述")
    private String lotDescription;

    @ApiModelProperty("生效日期")
    private String effectiveDate;

    @ApiModelProperty("失效日期")
    private String ineffectiveDate;

    @ApiModelProperty("送检数量")
    private BigDecimal inspectionQty;

    @ApiModelProperty("宽严程度")
    private String strictnessDegree;

    @ApiModelProperty("合格数量")
    private BigDecimal acceptableQty;

    @ApiModelProperty("不合格数量")
    private BigDecimal unqualifiedQty;

    @ApiModelProperty("破坏数量")
    private BigDecimal destroyedQty;

    @ApiModelProperty("判定结果")
    private String decision;

    @ApiModelProperty("需复检")
    private String reinspection;

    @ApiModelProperty("业务判定状态(1.未判定 2.未判定 3.已判定)")
    private String resultStatus;

    @ApiModelProperty("判定说明")
    private String decisionDescription;

    @ApiModelProperty("判定说明")
    private String remark;

}
