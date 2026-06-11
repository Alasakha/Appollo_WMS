package com.mgkj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "客户订单单身 实体类")
public class COPTD extends Base {

    @ApiModelProperty(value = "单别")
    @JsonProperty("TD001")
    private String TD001;

    @ApiModelProperty(value = "单号")
    @JsonProperty("TD002")
    private String TD002;

    @ApiModelProperty(value = "序号")
    @JsonProperty("TD003")
    private String TD003;

    @ApiModelProperty(value = "品号")
    @JsonProperty("TD004")
    private String TD004;  //C	20.0	品号

    @ApiModelProperty(value = "品名")
    @JsonProperty("TD005")
    private String TD005;  //V	255.0	品名

    @ApiModelProperty(value = "规格")
    @JsonProperty("TD006")
    private String TD006;

    @ApiModelProperty(value = "仓库")
    @JsonProperty("TD007")
    private String TD007;

    @ApiModelProperty(value = "订单数量")
    @JsonProperty("TD008")
    private BigDecimal TD008;

    @ApiModelProperty(value = "已交数量")
    @JsonProperty("TD009")
    private BigDecimal TD009;

    @ApiModelProperty(value = "单位")
    @JsonProperty("TD010")
    private String TD010;

    @ApiModelProperty(value = "单价")
    @JsonProperty("TD011")
    private String TD011;

    @ApiModelProperty(value = "金额")
    @JsonProperty("TD012")
    private BigDecimal TD012;

    @ApiModelProperty(value = "预交货日")
    @JsonProperty("TD013")
    private String TD013;

    @ApiModelProperty(value = "客户品号")
    @JsonProperty("TD014")
    private String TD014;

    @ApiModelProperty(value = "预测编号")
    @JsonProperty("TD015")
    private String TD015;

    @ApiModelProperty(value = "结束")
    @JsonProperty("TD016")
    private String TD016;

    @ApiModelProperty(value = "前置单据-单别")
    @JsonProperty("TD017")
    private String TD017;

    @ApiModelProperty(value = "前置单据-单号")
    @JsonProperty("TD018")
    private String TD018;

    @ApiModelProperty(value = "前置单据-序号")
    @JsonProperty("TD019")
    private String TD019;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TD020")
    private String TD020;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TD021")
    private String TD021;

    @ApiModelProperty(value = "库存数量")
    @JsonProperty("TD022")
    private BigDecimal TD022;

    @ApiModelProperty(value = "小单位")
    @JsonProperty("TD023")
    private String TD023;

    @ApiModelProperty(value = "赠品量")
    @JsonProperty("TD024")
    private BigDecimal TD024;

    @ApiModelProperty(value = "赠品已交量")
    @JsonProperty("TD025")
    private BigDecimal TD025;

    @ApiModelProperty(value = "折扣率")
    @JsonProperty("TD026")
    private BigDecimal TD026;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TD027")
    private String TD027;

    @ApiModelProperty(value = "预测序号")
    @JsonProperty("TD028")
    private String TD028;

    @ApiModelProperty(value = "包装方式")
    @JsonProperty("TD029")
    private String TD029;

    @ApiModelProperty(value = "毛重(Kg)")
    @JsonProperty("TD030")
    private BigDecimal TD030;

    @ApiModelProperty(value = "材积(CUFT)")
    @JsonProperty("TD031")
    private String TD031;

    @ApiModelProperty(value = "订单包装数量")
    @JsonProperty("TD032")
    private BigDecimal TD032;

    @ApiModelProperty(value = "已交包装数量")
    @JsonProperty("TD033")
    private BigDecimal TD033;

    @ApiModelProperty(value = "赠品包装量")
    @JsonProperty("TD034")
    private BigDecimal TD034;

    @ApiModelProperty(value = "赠品已交包装量")
    @JsonProperty("TD035")
    private BigDecimal TD035;

    @ApiModelProperty(value = "包装单位")
    @JsonProperty("TD036")
    private String TD036;

    @ApiModelProperty(value = "税率")
    @JsonProperty("TD037")
    private BigDecimal TD037;

    @ApiModelProperty(value = "税前金额")
    @JsonProperty("TD038")
    private BigDecimal TD038;

    @ApiModelProperty(value = "税额")
    @JsonProperty("TD039")
    private BigDecimal TD039;

    @ApiModelProperty(value = "件装")
    @JsonProperty("TD040")
    private BigDecimal TD040;

    @ApiModelProperty(value = "件数")
    @JsonProperty("TD041")
    private BigDecimal TD041;

    @ApiModelProperty(value = "批发价")
    @JsonProperty("TD042")
    private BigDecimal TD042;

    @ApiModelProperty(value = "零售价")
    @JsonProperty("TD043")
    private BigDecimal TD043;

    @ApiModelProperty(value = "出货待销量")
    @JsonProperty("TD044")
    private BigDecimal TD044;

    @ApiModelProperty(value = "出货待销包装量")
    @JsonProperty("TD045")
    private BigDecimal TD045;

    @ApiModelProperty(value = "原始客户")
    @JsonProperty("TD046")
    private String TD046;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD047")
    private String TD047;  //C	1.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD048")
    private String TD048;  //C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD049")
    private String TD049;  //V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD050")
    private BigDecimal TD050;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD051")
    private BigDecimal TD051;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TD052")
    private BigDecimal TD052;  //N	16.6	预留字段

    @ApiModelProperty(value = "配置方案")
    @JsonProperty("TD053")
    private String TD053;

    @ApiModelProperty(value = "模拟单位成本")
    @JsonProperty("TD054")
    private BigDecimal TD054;

    @ApiModelProperty(value = "模拟成本")
    @JsonProperty("TD055")
    private BigDecimal TD055;

    @ApiModelProperty(value = "预估毛利率")
    @JsonProperty("TD056")
    private BigDecimal TD056;

    @ApiModelProperty(value = "预估毛利")
    @JsonProperty("TD057")
    private BigDecimal TD057;

    @ApiModelProperty(value = "借出未销数量")
    @JsonProperty("TD058")
    private BigDecimal TD058;

    @ApiModelProperty(value = "借出未销包装量")
    @JsonProperty("TD059")
    private BigDecimal TD059;

    @ApiModelProperty(value = "合同类型")
    @JsonProperty("TD060")
    private String TD060;

    @ApiModelProperty(value = "零组件发货")
    @JsonProperty("TDD01")
    private String TDD01;

    @ApiModelProperty(value = "零组件接收仓库")
    @JsonProperty("TDD02")
    private String TDD02;

    @ApiModelProperty(value = "组装工单单别")
    @JsonProperty("TDD03")
    private String TDD03;

    @ApiModelProperty(value = "组装工单单号")
    @JsonProperty("TDD04")
    private String TDD04;

    @ApiModelProperty(value = "接收库位")
    @JsonProperty("TDD05")
    private String TDD05;

    @ApiModelProperty(value = "本币税前金额")
    @JsonProperty("TDI01")
    private BigDecimal TDI01;

    @ApiModelProperty(value = "本币税额")
    @JsonProperty("TDI02")
    private BigDecimal TDI02;

    @ApiModelProperty(value = "本币金额")
    @JsonProperty("TDI03")
    private BigDecimal TDI03;

    @ApiModelProperty(value = "分期期别")
    @JsonProperty("TD061")
    private String TD061;

    @ApiModelProperty(value = "分期合同")
    @JsonProperty("TD062")
    private String TD062;
}
