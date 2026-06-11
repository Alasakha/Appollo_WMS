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
@ApiModel(value = "销退单单头 实体类")
public class COPTI extends Base {
    @ApiModelProperty(value = "单别")
    @JsonProperty("TI001")
    private String TI001;

    @ApiModelProperty(value = "单号")
    @JsonProperty("TI002")
    private String TI002;

    @ApiModelProperty(value = "销退日")
    @JsonProperty("TI003")
    private String TI003;

    @ApiModelProperty(value = "客户")
    @JsonProperty("TI004")
    private String TI004;

    @ApiModelProperty(value = "部门")
    @JsonProperty("TI005")
    private String TI005;

    @ApiModelProperty(value = "业务员")
    @JsonProperty("TI006")
    private String TI006;

    @ApiModelProperty(value = "工厂")
    @JsonProperty("TI007")
    private String TI007;

    @ApiModelProperty(value = "币种")
    @JsonProperty("TI008")
    private String TI008;

    @ApiModelProperty(value = "汇率")
    @JsonProperty("TI009")
    private BigDecimal TI009;

    @ApiModelProperty(value = "原币销退金额")
    @JsonProperty("TI010")
    private BigDecimal TI010;

    @ApiModelProperty(value = "原币销退税额")
    @JsonProperty("TI011")
    private BigDecimal TI011;

    @ApiModelProperty(value = "发票种类")
    @JsonProperty("TI012")
    private String TI012;

    @ApiModelProperty(value = "税种")
    @JsonProperty("TI013")
    private String TI013;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI014")
    private String TI014;  //C	10.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI015")
    private String TI015;  //C	20.0	预留字段

    @ApiModelProperty(value = "打印次数")
    @JsonProperty("TI016")
    private BigDecimal TI016;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI017")
    private String TI017;  //C	8.0	预留字段[FORMATE:YMD]

    @ApiModelProperty(value = "更新码")
    @JsonProperty("TI018")
    private String TI018;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TI019")
    private String TI019;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TI020")
    private String TI020;

    @ApiModelProperty(value = "客户全称")
    @JsonProperty("TI021")
    private String TI021;

    @ApiModelProperty(value = "收款业务员")
    @JsonProperty("TI022")
    private String TI022;

    @ApiModelProperty(value = "备注一")
    @JsonProperty("TI023")
    private String TI023;

    @ApiModelProperty(value = "备注二")
    @JsonProperty("TI024")
    private String TI024;

    @ApiModelProperty(value = "备注三")
    @JsonProperty("TI025")
    private String TI025;

    @ApiModelProperty(value = "扣抵区分")
    @JsonProperty("TI026")
    private String TI026;

    @ApiModelProperty(value = "烟酒注记")
    @JsonProperty("TI027")
    private String TI027;

    @ApiModelProperty(value = "件数")
    @JsonProperty("TI028")
    private BigDecimal TI028;

    @ApiModelProperty(value = "总销退数量")
    @JsonProperty("TI029")
    private BigDecimal TI029;

    @ApiModelProperty(value = "员工编号")
    @JsonProperty("TI030")
    private String TI030;

    @ApiModelProperty(value = "生成分录(收入)")
    @JsonProperty("TI031")
    private String TI031;

    @ApiModelProperty(value = "生成分录(成本)")
    @JsonProperty("TI032")
    private String TI032;

    @ApiModelProperty(value = "申报年月")
    @JsonProperty("TI033")
    private String TI033;

    @ApiModelProperty(value = "单据日期")
    @JsonProperty("TI034")
    private String TI034;

    @ApiModelProperty(value = "审核者")
    @JsonProperty("TI035")
    private String TI035;

    @ApiModelProperty(value = "税率")
    @JsonProperty("TI036")
    private BigDecimal TI036;

    @ApiModelProperty(value = "本币销退金额")
    @JsonProperty("TI037")
    private BigDecimal TI037;

    @ApiModelProperty(value = "本币销退税额")
    @JsonProperty("TI038")
    private BigDecimal TI038;

    @ApiModelProperty(value = "付款条件编号")
    @JsonProperty("TI039")
    private String TI039;

    @ApiModelProperty(value = "总销退包装量")
    @JsonProperty("TI040")
    private BigDecimal TI040;

    @ApiModelProperty(value = "签核状态码")
    @JsonProperty("TI041")
    private String TI041;

    @ApiModelProperty(value = "流程编号")
    @JsonProperty("TI042")
    private String TI042;

    @ApiModelProperty(value = "抛转状态")
    @JsonProperty("TI043")
    private String TI043;

    @ApiModelProperty(value = "海关手册")
    @JsonProperty("TI044")
    private String TI044;

    @ApiModelProperty(value = "检验码")
    @JsonProperty("TI045")
    private String TI045;

    @ApiModelProperty(value = "传送次数")
    @JsonProperty("TI046")
    private BigDecimal TI046;

    @ApiModelProperty(value = "报损单别")
    @JsonProperty("TI047")
    private String TI047;

    @ApiModelProperty(value = "报损单号")
    @JsonProperty("TI048")
    private String TI048;

    @ApiModelProperty(value = "EBC汇出码")
    @JsonProperty("TI049")
    private String TI049;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI050")
    private String TI050;  //C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI051")
    private String TI051;  //V	30.0	预留字段

    @ApiModelProperty(value = "总验收数量")
    @JsonProperty("TI052")
    private BigDecimal TI052;

    @ApiModelProperty(value = "总验收包装量")
    @JsonProperty("TI053")
    private BigDecimal TI053;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI054")
    private BigDecimal TI054;  //N	16.6	预留字段

    @ApiModelProperty(value = "EBC销退单号")
    @JsonProperty("TI055")
    private String TI055;

    @ApiModelProperty(value = "EBC销退版本")
    @JsonProperty("TI056")
    private String TI056;

    @ApiModelProperty(value = "来源码")
    @JsonProperty("TI057")
    private String TI057;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI058")
    private BigDecimal TI058;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI059")
    private BigDecimal TI059;  //N	16.6	预留字段

    @ApiModelProperty(value = "赠备品总销退量")
    @JsonProperty("TI060")
    private BigDecimal TI060;

    @ApiModelProperty(value = "赠备品总销退包装量")
    @JsonProperty("TI061")
    private BigDecimal TI061;

    @ApiModelProperty(value = "赠备品总验收量")
    @JsonProperty("TI062")
    private BigDecimal TI062;

    @ApiModelProperty(value = "赠备品总验收包装量")
    @JsonProperty("TI063")
    private BigDecimal TI063;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TI064")
    private String TI064;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TI065")
    private String TI065;  //C	1.0	预留字段
}
