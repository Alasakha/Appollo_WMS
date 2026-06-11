package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-06 19:10
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "无源/杂收入库提交DTO")
public class PassiveToStorageDto {



    @ApiModelProperty(value = "条码", required = true)
    @JSONField(ordinal = 2)
    private String barcode;

    @ApiModelProperty(value = "仓库编码",required = true)
    @JSONField(ordinal = 3)
    private String warehouseCode = "";

    @ApiModelProperty(value = "库位编码",required = true)
    @JSONField(ordinal = 3)
    private String binCode;

    @ApiModelProperty(value = "匹配量（数量）",required = true)
    @JSONField(ordinal = 3)
    private BigDecimal matchQty;

    @ApiModelProperty(value = "入库方式-其他收发",required = true)
    private String inToType = "其他收发";

    @ApiModelProperty(value = "人员编码",required = true)
    private String employeeCode;

    @ApiModelProperty(value = "是否扣账，Y:扣账，N:不扣账",required = true)
    @JSONField(ordinal = 4)
    private String isDeduct;

    @ApiModelProperty(value = "部门编号",required = true)
    private String departmentCode;

    @ApiModelProperty(value = "部门名称",required = true)
    private String departmentName;

    @ApiModelProperty(value = "单位",required = true)
    @JSONField(ordinal = 3)
    private String unitCode;

    @ApiModelProperty(value = "料号",required = true)
    @JSONField(ordinal = 3)
    private String itemCode;

    @ApiModelProperty("拒收数量")
    @JSONField(ordinal = 3)
    private BigDecimal rejectQty = BigDecimal.ZERO;

    @ApiModelProperty("报废数量")
    @JSONField(ordinal = 3)
    private BigDecimal scrapQty = BigDecimal.ZERO;

    @ApiModelProperty("收货机构")
    private String shjg;




}
