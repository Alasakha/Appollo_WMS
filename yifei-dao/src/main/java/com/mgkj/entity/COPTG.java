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
@ApiModel(value = "销货单单头 实体类")
public class COPTG extends Base {
    @ApiModelProperty(value = "单别")
    @JsonProperty("TG001")
    private String TG001;

    @ApiModelProperty(value = "单号")
    @JsonProperty("TG002")
    private String TG002;

    @ApiModelProperty(value = "销货日期")
    @JsonProperty("TG003")
    private String TG003;

    @ApiModelProperty(value = "客户编号")
    @JsonProperty("TG004")
    private String TG004;

    @ApiModelProperty(value = "部门")
    @JsonProperty("TG005")
    private String TG005;

    @ApiModelProperty(value = "业务员")
    @JsonProperty("TG006")
    private String TG006;

    @ApiModelProperty(value = "保留字段")
    @JsonProperty("TG007")
    private String TG007;

    @ApiModelProperty(value = "送货地址一")
    @JsonProperty("TG008")
    private String TG008;

    @ApiModelProperty(value = "送货地址二")
    @JsonProperty("TG009")
    private String TG009;

    @ApiModelProperty(value = "出货工厂")
    @JsonProperty("TG010")
    private String TG010;

    @ApiModelProperty(value = "币种")
    @JsonProperty("TG011")
    private String TG011;

    @ApiModelProperty(value = "汇率")
    @JsonProperty("TG012")
    private BigDecimal TG012;

    @ApiModelProperty(value = "原币销货金额")
    @JsonProperty("TG013")
    private BigDecimal TG013;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG014")
    private String TG014;  //C	10.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG015")
    private String TG015; //C	20.0	预留字段

    @ApiModelProperty(value = "发票种类")
    @JsonProperty("TG016")
    private String TG016;

    @ApiModelProperty(value = "税种")
    @JsonProperty("TG017")
    private String TG017;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG018")
    private String TG018;  //C	72.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG019")
    private String TG019;  //C	72.0	预留字段

    @ApiModelProperty(value = "备注")
    @JsonProperty("TG020")
    private String TG020;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG021")
    private String TG021;  //C	8.0	预留字段[FORMATE:YMD]

    @ApiModelProperty(value = "打印次数")
    @JsonProperty("TG022")
    private BigDecimal TG022;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TG023")
    private String TG023;

    @ApiModelProperty(value = "更新码")
    @JsonProperty("TG024")
    private String TG024;

    @ApiModelProperty(value = "原币销货税额")
    @JsonProperty("TG025")
    private BigDecimal TG025;

    @ApiModelProperty(value = "收款业务员")
    @JsonProperty("TG026")
    private String TG026;

    @ApiModelProperty(value = "备注一")
    @JsonProperty("TG027")
    private String TG027;

    @ApiModelProperty(value = "备注二")
    @JsonProperty("TG028")
    private String TG028;

    @ApiModelProperty(value = "备注三")
    @JsonProperty("TG029")
    private String TG029;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG030")
    private String TG030;  //C	1.0	预留字段[DEF:"N"]

    @ApiModelProperty(value = "烟酒注记")
    @JsonProperty("TG031")
    private String TG031;

    @ApiModelProperty(value = "件数")
    @JsonProperty("TG032")
    private BigDecimal TG032;

    @ApiModelProperty(value = "总数量")
    @JsonProperty("TG033")
    private BigDecimal TG033;

    @ApiModelProperty(value = "现销")
    @JsonProperty("TG034")
    private String TG034;

    @ApiModelProperty(value = "员工编号")
    @JsonProperty("TG035")
    private String TG035;

    @ApiModelProperty(value = "生成分录(收入)")
    @JsonProperty("TG036")
    private String TG036;

    @ApiModelProperty(value = "生成分录(成本)")
    @JsonProperty("TG037")
    private String TG037;

    @ApiModelProperty(value = "申报年月")
    @JsonProperty("TG038")
    private String TG038;

    @ApiModelProperty(value = "L/C_NO")
    @JsonProperty("TG039")
    private String TG039;

    @ApiModelProperty(value = "INVOICE_NO")
    @JsonProperty("TG040")
    private String TG040;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG041")
    private BigDecimal TG041;  //N	1.0	预留字段[DEF:0]

    @ApiModelProperty(value = "单据日期")
    @JsonProperty("TG042")
    private String TG042;

    @ApiModelProperty(value = "审核者")
    @JsonProperty("TG043")
    private String TG043;

    @ApiModelProperty(value = "税率")
    @JsonProperty("TG044")
    private BigDecimal TG044;

    @ApiModelProperty(value = "本币销货金额")
    @JsonProperty("TG045")
    private BigDecimal TG045;

    @ApiModelProperty(value = "本币销货税额")
    @JsonProperty("TG046")
    private BigDecimal TG046;

    @ApiModelProperty(value = "付款条件编号")
    @JsonProperty("TG047")
    private String TG047;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG048")
    private String TG048;  //C	4.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG049")
    private String TG049;  //C	11.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG050")
    private String TG050;  //C	4.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG051")
    private String TG051;  //C	11.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG052")
    private BigDecimal TG052;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG053")
    private BigDecimal TG053;  //N	16.6	预留字段

    @ApiModelProperty(value = "总包装数量")
    @JsonProperty("TG054")
    private BigDecimal TG054;

    @ApiModelProperty(value = "签核状态码")
    @JsonProperty("TG055")
    private String TG055;

    @ApiModelProperty(value = "更换发票码")
    @JsonProperty("TG056")
    private String TG056;

    @ApiModelProperty(value = "新销货单别")
    @JsonProperty("TG057")
    private String TG057;

    @ApiModelProperty(value = "新销货单号")
    @JsonProperty("TG058")
    private String TG058;

    @ApiModelProperty(value = "抛转状态")
    @JsonProperty("TG059")
    private String TG059;

    @ApiModelProperty(value = "流程编号")
    @JsonProperty("TG060")
    private String TG060;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG061")
    private String TG061;  //C	1.0	预留字段 Y/N[DEF:"N"]

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG064")
    private String TG064;  //C	10.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG065")
    private String TG065;  //C	12.0	签收人

    @ApiModelProperty(value = "海关手册")
    @JsonProperty("TG066")
    private String TG066;

    @ApiModelProperty(value = "传送次数")
    @JsonProperty("TG067")
    private String TG067;

    @ApiModelProperty(value = "EBC汇出码")
    @JsonProperty("TG068")
    private String TG068;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG069")
    private BigDecimal TG069;  //C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG070")
    private String TG070;  //V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG071")
    private BigDecimal TG071;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG072")
    private BigDecimal TG072;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TG073")
    private BigDecimal TG073;  //N	16.6	预留字段

    @ApiModelProperty(value = "超限放行")
    @JsonProperty("TG074")
    private String TG074;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TG075")
    private String TG075;

    @ApiModelProperty(value = "客户签收")
    @JsonProperty("TG076")
    private String TG076;

    @ApiModelProperty(value = "签收日期")
    @JsonProperty("TG077")
    private String TG077;

    @ApiModelProperty(value = "签收人")
    @JsonProperty("TG078")
    private String TG078;

    @ApiModelProperty(value = "生成分录(发出商品)")
    @JsonProperty("TG079")
    private String TG079;
}
