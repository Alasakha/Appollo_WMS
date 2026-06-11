package com.mgkj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "采购到货待检验Dto")
public class PurchaseArrivalStorageWaitCheckDto {
    @ApiModelProperty(value = "箱码")
    private String barcode= "sexdrcftgvybhunjimk";

    @ApiModelProperty("业务判定状态(1.未判定 2.部分判定 3.已判定)")
    private String resultStatus;

    @ApiModelProperty(value = "三天前")
    private String start;

    @ApiModelProperty(value = "当天日期")
    private String end;

    @ApiModelProperty(value = "员工")
    private String employee;

    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "条数")
    private Integer size;

    @ApiModelProperty("供应商编号")
    private String supplierCode;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("收货机构")
    private String shjg;

    @ApiModelProperty("检验类型")
    private String checkName = "";

    @ApiModelProperty("检验单号")
    private String checkDocNo = "";

    @ApiModelProperty("到货单号")
    @JsonProperty("arrivalDocNo")
    private String arrivalDocNo = "";

}
