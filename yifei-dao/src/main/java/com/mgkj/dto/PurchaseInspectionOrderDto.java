package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/26
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "产生采购质检单dto")
public class PurchaseInspectionOrderDto {
    @ApiModelProperty(value = "到货单号",required = true)
    private String docNo;

//    @ApiModelProperty("uuid")
//    private String uuid;

    @ApiModelProperty(value = "本次检验数量",required = true)
    private Double thisCheckQty;

    @ApiModelProperty(value = "上次检验状态",required = true)
    private String lastCheckStatus;

    @ApiModelProperty(value = "待检测数量(回传原本数据)",required = true)
    private Double PendingCheckQty;

    @ApiModelProperty(value = "料号",required = true)
    private String itemCode;

    @ApiModelProperty(value = "部门id",required = true)
    private String departmentId;

    @ApiModelProperty(value = "员工id",required = true)
    private String employeeId;

    @ApiModelProperty("时间")
    private String time ;

    @ApiModelProperty(value = "合格业务数量",required = true)
    private Double qualifiedBusinessQty;

    @ApiModelProperty(value = "不合格业务数量",required = true)
    private Double unQualifiedBusinessQty;

    @ApiModelProperty(value = "检验破坏业务数量",required = true)
    private Double inDestroyedBusinessQty;

    @ApiModelProperty(value = "待判定业务数量",required = true)
    private Double determineQty;

    @ApiModelProperty(value = "允收业务数量",required = true)
    private Double acceptedBusinessQty;

    @ApiModelProperty(value = "特采业务数量",required = true)
    private Double spReceiptBusinessQty;

    @ApiModelProperty(value = "报废业务数量",required = true)
    private Double scrapBusinessQty;

    @ApiModelProperty(value = "拒收业务数量",required = true)
    private Double returnBusinessQty;

    @ApiModelProperty(value = "备注",required = true)
    private String remark;

    @ApiModelProperty("状态码")
    private String resultStatus;

    @ApiModelProperty("到货单状态码")
    private String ArrivalResultStatus;
}
