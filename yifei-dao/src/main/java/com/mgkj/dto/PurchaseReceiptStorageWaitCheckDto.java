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
@ApiModel(value = "采购到货待检验Dto")
public class PurchaseReceiptStorageWaitCheckDto {
//    @ApiModelProperty(value = "箱码")
//    private String barcode= "sexdrcftgvybhunjimk";

    @ApiModelProperty("检验状态 1免检，2待检，3部分检验，4检验完成")
    private String inspectionStatus;

    @ApiModelProperty(value = "入库结束码  0未结束 1指定结束 2.已结束")
    private String receiptClose;

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
}
