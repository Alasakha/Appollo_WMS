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
@ApiModel(value = "客户订单单头 实体类")
public class COPTC extends Base {

    @ApiModelProperty(value = "单别")
    @JsonProperty("TC001")
    private String TC001;

    @ApiModelProperty(value = "单号")
    @JsonProperty("TC002")
    private String TC002;

    @ApiModelProperty(value = "订单日期")
    @JsonProperty("TC003")
    private String TC003;

    @ApiModelProperty(value = "客户编号")
    @JsonProperty("TC004")
    private String TC004;

    @ApiModelProperty(value = "部门编号")
    @JsonProperty("TC005")
    private String TC005;

    @ApiModelProperty(value = "业务人员")
    @JsonProperty("TC006")
    private String TC006;

    @ApiModelProperty(value = "出货工厂")
    @JsonProperty("TC007")
    private String TC007;

    @ApiModelProperty(value = "交易币种")
    @JsonProperty("TC008")
    private String TC008;

    @ApiModelProperty(value = "汇率")
    @JsonProperty("TC009")
    private BigDecimal TC009;

    @ApiModelProperty(value = "送货地址(一)")
    @JsonProperty("TC010")
    private String TC010;

    @ApiModelProperty(value = "送货地址(二)")
    @JsonProperty("TC011")
    private String TC011;

    @ApiModelProperty(value = "客户单号")
    @JsonProperty("TC012")
    private String TC012;

    @ApiModelProperty(value = "价格说明")
    @JsonProperty("TC013")
    private String TC013;

    @ApiModelProperty(value = "付款条件")
    @JsonProperty("TC014")
    private String TC014;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TC015")
    private String TC015;

    @ApiModelProperty(value = "税种")
    @JsonProperty("TC016")
    private String TC016;

    @ApiModelProperty(value = "L/CNO.")
    @JsonProperty("TC017")
    private String TC017;

    @ApiModelProperty(value = "联系人")
    @JsonProperty("TC018")
    private String TC018;

    @ApiModelProperty(value = "运输方式")
    @JsonProperty("TC019")
    private String TC019;

    @ApiModelProperty(value = "起始港口")
    @JsonProperty("TC020")
    private String TC020;

    @ApiModelProperty(value = "目的港口")
    @JsonProperty("TC021")
    private String TC021;

    @ApiModelProperty(value = "代理商")
    @JsonProperty("TC022")
    private String TC022;

    @ApiModelProperty(value = "报关行")
    @JsonProperty("TC023")
    private String TC023;

    @ApiModelProperty(value = "验货公司")
    @JsonProperty("TC024")
    private String TC024;

    @ApiModelProperty(value = "运输公司")
    @JsonProperty("TC025")
    private String TC025;

    @ApiModelProperty(value = "佣金比率")
    @JsonProperty("TC026")
    private BigDecimal TC026;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TC027")
    private String TC027;

    @ApiModelProperty(value = "打印次数")
    @JsonProperty("TC028")
    private BigDecimal TC028;

    @ApiModelProperty(value = "订单金额")
    @JsonProperty("TC029")
    private BigDecimal TC029;

    @ApiModelProperty(value = "订单税额")
    @JsonProperty("TC030")
    private BigDecimal TC030;

    @ApiModelProperty(value = "总数量")
    @JsonProperty("TC031")
    private BigDecimal TC031;

    @ApiModelProperty(value = "CONSIGNEE")
    @JsonProperty("TC032")
    private String TC032;

    @ApiModelProperty(value = "NOTIFY")
    @JsonProperty("TC033")
    private String TC033;

    @ApiModelProperty(value = "唛头编号")
    @JsonProperty("TC034")
    private String TC034;

    @ApiModelProperty(value = "目的地")
    @JsonProperty("TC035")
    private String TC035;

    @ApiModelProperty(value = "往来银行")
    @JsonProperty("TC036")
    private String TC036;

    @ApiModelProperty(value = "INVOICE备注")
    @JsonProperty("TC037")
    private String TC037;

    @ApiModelProperty(value = "PACKING-LIST备注")
    @JsonProperty("TC038")
    private String TC038;

    @ApiModelProperty(value = "单据日期")
    @JsonProperty("TC039")
    private String TC039;

    @ApiModelProperty(value = "审核者")
    @JsonProperty("TC040")
    private String TC040;

    @ApiModelProperty(value = "税率")
    @JsonProperty("TC041")
    private BigDecimal TC041;

    @ApiModelProperty(value = "付款条件编号")
    @JsonProperty("TC042")
    private String TC042;

    @ApiModelProperty(value = "总毛重(Kg)")
    @JsonProperty("TC043")
    private BigDecimal TC043;

    @ApiModelProperty(value = "总材积(CUFT)")
    @JsonProperty("TC044")
    private BigDecimal TC044;

    @ApiModelProperty(value = "订金比率")
    @JsonProperty("TC045")
    private BigDecimal TC045;

    @ApiModelProperty(value = "总包装数量")
    @JsonProperty("TC046")
    private BigDecimal TC046;

    @ApiModelProperty(value = "押汇银行")
    @JsonProperty("TC047")
    private String TC047;

    @ApiModelProperty(value = "签核状态码")
    @JsonProperty("TC048")
    private String TC048;

    @ApiModelProperty(value = "流程编号")
    @JsonProperty("TC049")
    private String TC049;

    @ApiModelProperty(value = "抛转状态")
    @JsonProperty("TC050")
    private String TC050;

    @ApiModelProperty(value = "下游供应商")
    @JsonProperty("TC051")
    private String TC051;

    @ApiModelProperty(value = "其他备注一")
    @JsonProperty("TC052")
    private String TC052;

    @ApiModelProperty(value = "其他备注二")
    @JsonProperty("TC053")
    private String TC053;

    @ApiModelProperty(value = "其他备注三")
    @JsonProperty("TC054")
    private String TC054;

    @ApiModelProperty(value = "其他备注四")
    @JsonProperty("TC055")
    private String TC055;

    @ApiModelProperty(value = "正唛")
    @JsonProperty("TC056")
    private String TC056;

    @ApiModelProperty(value = "侧唛")
    @JsonProperty("TC057")
    private String TC057;

    @ApiModelProperty(value = "传送次数")
    @JsonProperty("TC058")
    private BigDecimal TC058;

    @ApiModelProperty(value = "EBC汇出码")
    @JsonProperty("TC059")
    private String TC059;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC060")
    private String TC060;   //C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC061")
    private String TC061;  //V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC062")
    private BigDecimal TC062; //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC063")
    private BigDecimal TC063; //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC064")
    private BigDecimal TC064; //N	16.6	预留字段

    @ApiModelProperty(value = "EBC订单单号")
    @JsonProperty("TC065")
    private String TC065;

    @ApiModelProperty(value = "EBC订单版本")
    @JsonProperty("TC066")
    private String TC066;

    @ApiModelProperty(value = "来源码")
    @JsonProperty("TC067")
    private String TC067;

    @ApiModelProperty(value = "已开票金额")
    @JsonProperty("TC068")
    private BigDecimal TC068;

    @ApiModelProperty(value = "已开票税额")
    @JsonProperty("TC069")
    private BigDecimal TC069;

    @ApiModelProperty(value = "超限放行")
    @JsonProperty("TC070")
    private String TC070;

    @ApiModelProperty(value = "合同类型")
    @JsonProperty("TC071")
    private String TC071;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC072")
    private String TC072;   //C	1.0	预留字段

    @ApiModelProperty(value = "税号")
    @JsonProperty("TC073")
    private String TC073;

    @ApiModelProperty(value = "电话")
    @JsonProperty("TC074")
    private String TC074;

    @ApiModelProperty(value = "传真")
    @JsonProperty("TC075")
    private String TC075;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TC076")
    private String TC076;  //C	72.0	预留字段

    @ApiModelProperty(value = "交货日期")
    @JsonProperty("TCI01")
    private String TCI01;

    @ApiModelProperty(value = "安装调试完成日期")
    @JsonProperty("TCI02")
    private String TCI02;

    @ApiModelProperty(value = "订单本币税前金额")
    @JsonProperty("TCI03")
    private BigDecimal TCI03;

    @ApiModelProperty(value = "订单本币税额")
    @JsonProperty("TCI04")
    private BigDecimal TCI04;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TC077")
    private String TC077;
}
