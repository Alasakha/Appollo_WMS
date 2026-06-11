package com.mgkj.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author yyyjcg
 * @date 2024/3/1
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "到货检验单身Vo")
public class PurchaseArrivalCheckLine {

    @ApiModelProperty("序列号")
    private Integer sequenceNumber;

    @ApiModelProperty("检验单身uuid")
    private String uuid;

    @ApiModelProperty("检验顺序")
    private Integer sequence;

    @ApiModelProperty("检验项目编号")
    private String inspectionItemCode;

    @ApiModelProperty("检验项目名称")
    private String inspectionItemName;

    @ApiModelProperty("检验项目说明")
    private String inspectionItemDescription;

    @ApiModelProperty("缺陷等级")
    private String defectClass;

    @ApiModelProperty("抽样数量")
    private BigDecimal inspectionQty;

    @ApiModelProperty("允收数量 Ac")
    private BigDecimal inspectionAc;

    @ApiModelProperty("拒收数量 Re")
    private BigDecimal inspectionRe;

    @ApiModelProperty("不良数量")
    private BigDecimal defectiveQty;

    @ApiModelProperty("不良原因说明")
    private String defectiveReasonDescription;

    @ApiModelProperty("不良原因uuid")
    private String defectReasonUuid = "00000000-0000-0000-0000-000000000000";

    @ApiModelProperty("合格否 1-false合格 0-true不合格；只要不良数量大于0，就不合格")
    private Boolean decision = false;

    @ApiModelProperty("影响判定")
    private Boolean impactResult;

    @ApiModelProperty("检验标准值")
    private BigDecimal standardValue;

    @ApiModelProperty("规范上限")
    private BigDecimal standardMax;

    @ApiModelProperty("规范下限")
    private BigDecimal standardMin;

    @ApiModelProperty("检验上限")
    private BigDecimal qcMax;

    @ApiModelProperty("检验下限")
    private BigDecimal qcMin;

    @ApiModelProperty("备注")
    private String remark;


    @ApiModelProperty("e10自定义字段，做测量值")
    private String UDF026;


    @ApiModelProperty("测量值")
    private List<BigDecimal> measurementValue;

}
