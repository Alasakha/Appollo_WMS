package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "无源出入库汇总信息")
public class PassiveToOrOutInvBarcodeOperationDto {

    private String createBy;
    @ApiModelProperty("箱码")
    private String barcode;
    @ApiModelProperty("箱码数量")
    private Double qty;
    @ApiModelProperty("需操作数量")
    private Double matchQty;
    @ApiModelProperty(value = "品号")
    private String itemCode;
    @ApiModelProperty(value = "品名")
    private String itemName;
    @ApiModelProperty(value = "规格")
    private String itemSpec;
    @ApiModelProperty(value="单位编号")
    private String unitCode;
    @ApiModelProperty(value="单位名称")
    private String unitName;
    @ApiModelProperty(value="仓库编码",required = true)
    private String warehouseCode = "";
    @ApiModelProperty(value="库位编码",required = true)
    private String binCode;
    @ApiModelProperty(value = "人员编号",required = true)
    private String employeeCode;
    @ApiModelProperty(value = "部门编号",required = true)
    private String departmentCode;
    @ApiModelProperty(value = "部门名称",required = true)
    private String departmentName;
    @ApiModelProperty(value="备注")
    private String remark;
    @ApiModelProperty(value = "操作业务(5无源调拨、6无源入库、7无源出库)")
    private Integer chagType;
    @ApiModelProperty(value = "提交状态(0 未提交)")
    private Integer statusCode;
    @ApiModelProperty(value = "收货机构")
    private String shjg;

    @ApiModelProperty(value = "项目uuid")
    private String projectId="";
    @ApiModelProperty(value = "车型")
    private String carType="";
    @ApiModelProperty(value = "出库类别")
    private String typeCode="";

    @ApiModelProperty(value = "流水号")
    private String serialNo="";





}
