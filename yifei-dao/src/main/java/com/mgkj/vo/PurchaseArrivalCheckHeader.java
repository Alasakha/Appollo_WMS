package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.entity.DefectNumReason;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "到货检验单单头")
public class PurchaseArrivalCheckHeader {

    @ApiModelProperty("附件名称")
    private List<String> fileName;

    @ApiModelProperty("箱码")
    private String barcode;

    @ApiModelProperty("附件内容")
    private List<String> content;

    @ApiModelProperty("检验单uuid")
    private String inspectionUuid;

    @ApiModelProperty("部门UUID")
    private String departmentUUID = "7D63316C-CE71-4A68-CB2F-193D37BC4158";
//    private String departmentUUID ;

    @ApiModelProperty("部门编号")
    private String departmentCode;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("检验员UUID")
    private String employeeUUID = "C41D5B9F-CF60-4979-FEC2-1B2854CFD562";
//    private String employeeUUID ;

    @ApiModelProperty("检验员")
    private String employeeName;

    @ApiModelProperty("检验员编号")
    private String employeeCode;



    @ApiModelProperty("工厂编码")
    private String plantCode;

    @ApiModelProperty("检验类型")
    private String checkName;

    @ApiModelProperty("检验单号")
    private String checkDocNo;

    @ApiModelProperty("到货单号")
    @JsonProperty("arrivalDocNo")
    private String arrivalDocNo;

    @ApiModelProperty("到货单单身uuid")
    private String arrivalDUuid;

    @ApiModelProperty("到货单单身序号")
    private String arrivalDSequenceNumber;


    @ApiModelProperty("供应商编码")
    private String supplierCode;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("客户单号")
    private String receiptNo;

    @ApiModelProperty("创建日期")
    private String createDate;

    @ApiModelProperty("到货日期")
    private String receiptDate;

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


    @ApiModelProperty("不良返修数")
    private BigDecimal defectRepairQty;

    @ApiModelProperty("不良数量及原因")
    private String defectNumReason;

    @ApiModelProperty("不良数量及原因")
    private List<DefectNumReason> defectNumReasonList;

    @ApiModelProperty("判定结果：1.合格 2.不合格")
    private String decision;

    @ApiModelProperty("需复检")
    private String reinspection;

    @ApiModelProperty("业务判定状态(1.未判定 2.部分判定 3.已判定)")
    private String resultStatus;

    @ApiModelProperty("判定说明")
    private String decisionDescription ;

    @ApiModelProperty("检验次数")
    private String qcTimes ;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("全部默认都是false，扫描之后将具体的改为true")
    private String UDF026;

    @ApiModelProperty("客户单号")
    private String customerNum;




}
