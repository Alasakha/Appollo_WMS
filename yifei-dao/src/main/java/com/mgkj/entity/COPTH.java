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
@ApiModel(value = "销货单单身 实体类")
public class COPTH extends Base {
    @ApiModelProperty(value = "单别")
    @JsonProperty("TH001")
    private String TH001;

    @ApiModelProperty(value = "单号")
    @JsonProperty("TH002")
    private String TH002;

    @ApiModelProperty(value = "序号")
    @JsonProperty("TH003")
    private String TH003;

    @ApiModelProperty(value = "品号")
    @JsonProperty("TH004")
    private String TH004;

    @ApiModelProperty(value = "品名")
    @JsonProperty("TH005")
    private String TH005;

    @ApiModelProperty(value = "规格")
    @JsonProperty("TH006")
    private String TH006;

    @ApiModelProperty(value = "仓库")
    @JsonProperty("TH007")
    private String TH007;

    @ApiModelProperty(value = "数量")
    @JsonProperty("TH008")
    private BigDecimal TH008;

    @ApiModelProperty(value = "单位")
    @JsonProperty("TH009")
    private String TH009;

    @ApiModelProperty(value = "库存数量")
    @JsonProperty("TH010")
    private BigDecimal TH010;

    @ApiModelProperty(value = "小单位")
    @JsonProperty("TH011")
    private String TH011;

    @ApiModelProperty(value = "单价")
    @JsonProperty("TH012")
    private BigDecimal TH012;

    @ApiModelProperty(value = "金额")
    @JsonProperty("TH013")
    private BigDecimal TH013;

    @ApiModelProperty(value = "订单单别")
    @JsonProperty("TH014")
    private String TH014;

    @ApiModelProperty(value = "订单单号")
    @JsonProperty("TH015")
    private String TH015;

    @ApiModelProperty(value = "订单序号")
    @JsonProperty("TH016")
    private String TH016;

    @ApiModelProperty(value = "批号")
    @JsonProperty("TH017")
    private String TH017;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TH018")
    private String TH018;

    @ApiModelProperty(value = "客户品号")
    @JsonProperty("TH019")
    private String TH019;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TH020")
    private String TH020;

    @ApiModelProperty(value = "更新码")
    @JsonProperty("TH021")
    private String TH021;

    @ApiModelProperty(value = "保留字段")
    @JsonProperty("TH022")
    private String TH022;  //C	8.0	保留字段

    @ApiModelProperty(value = "保留字段")
    @JsonProperty("TH023")
    private String TH023;  //C	10.0	保留字段

    @ApiModelProperty(value = "赠/备品量")
    @JsonProperty("TH024")
    private BigDecimal TH024;

    @ApiModelProperty(value = "折扣率")
    @JsonProperty("TH025")
    private BigDecimal TH025;

    @ApiModelProperty(value = "开票码")
    @JsonProperty("TH026")
    private String TH026;

    @ApiModelProperty(value = "销售发票单别")
    @JsonProperty("TH027")
    private String TH027;

    @ApiModelProperty(value = "销售发票单号")
    @JsonProperty("TH028")
    private String TH028;

    @ApiModelProperty(value = "销售发票序号")
    @JsonProperty("TH029")
    private String TH029;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TH030")
    private String TH030;

    @ApiModelProperty(value = "类型")
    @JsonProperty("TH031")
    private String TH031;

    @ApiModelProperty(value = "借出单别")
    @JsonProperty("TH032")
    private String TH032;

    @ApiModelProperty(value = "借出单号")
    @JsonProperty("TH033")
    private String TH033;

    @ApiModelProperty(value = "借出序号")
    @JsonProperty("TH034")
    private String TH034;

    @ApiModelProperty(value = "原币税前金额")
    @JsonProperty("TH035")
    private BigDecimal TH035;

    @ApiModelProperty(value = "原币税额")
    @JsonProperty("TH036")
    private BigDecimal TH036;

    @ApiModelProperty(value = "本币税前金额")
    @JsonProperty("TH037")
    private BigDecimal TH037;

    @ApiModelProperty(value = "本币税额")
    @JsonProperty("TH038")
    private BigDecimal TH038;

    @ApiModelProperty(value = "包装数量")
    @JsonProperty("TH039")
    private BigDecimal TH039;

    @ApiModelProperty(value = "赠/备品包装量")
    @JsonProperty("TH040")
    private BigDecimal TH040;

    @ApiModelProperty(value = "包装单位")
    @JsonProperty("TH041")
    private String TH041;

    @ApiModelProperty(value = "已开票数量")
    @JsonProperty("TH042")
    private BigDecimal TH042;

    @ApiModelProperty(value = "件装")
    @JsonProperty("TH043")
    private BigDecimal TH043;

    @ApiModelProperty(value = "件数")
    @JsonProperty("TH044")
    private BigDecimal TH044;

    @ApiModelProperty(value = "出货通知单别")
    @JsonProperty("TH045")
    private String TH045;

    @ApiModelProperty(value = "出货通知单号")
    @JsonProperty("TH046")
    private String TH046;

    @ApiModelProperty(value = "出货通知序号")
    @JsonProperty("TH047")
    private String TH047;

    @ApiModelProperty(value = "税率")
    @JsonProperty("TH048")
    private BigDecimal TH048;

    @ApiModelProperty(value = "批发价")
    @JsonProperty("TH049")
    private BigDecimal TH049;

    @ApiModelProperty(value = "零售价")
    @JsonProperty("TH050")
    private BigDecimal TH050;

    @ApiModelProperty(value = "生产日期")
    @JsonProperty("TH051")
    private String TH051;

    @ApiModelProperty(value = "有效日期")
    @JsonProperty("TH052")
    private String TH052;

    @ApiModelProperty(value = "复检日期")
    @JsonProperty("TH053")
    private String TH053;

    @ApiModelProperty(value = "原始客户")
    @JsonProperty("TH054")
    private String TH054;

    @ApiModelProperty(value = "批号说明")
    @JsonProperty("TH055")
    private String TH055;

    @ApiModelProperty(value = "库位")
    @JsonProperty("TH056")
    private String TH056;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TH057")
    private String TH057;  //C	1.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TH058")
    private String TH058;  //C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TH059")
    private String TH059;  //V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TH060")
    private BigDecimal TH060;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TH061")
    private BigDecimal TH061;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TH062")
    private BigDecimal TH062;  ////N	16.6	预留字段

    @ApiModelProperty(value = "安装调试数量")
    @JsonProperty("THD01")
    private BigDecimal THD01;

    @ApiModelProperty(value = "分期期别")
    @JsonProperty("TH063")
    private String TH063;

    @ApiModelProperty(value = "分期合同")
    @JsonProperty("TH064")
    private String TH064;

    @ApiModelProperty(value = "分期单别")
    @JsonProperty("TH065")
    private String TH065;

    @ApiModelProperty(value = "分期单号")
    @JsonProperty("TH066")
    private String TH066;

    @ApiModelProperty(value = "检核确认")
    @JsonProperty("THH01")
    private String THH01;

    @ApiModelProperty(value = "sQMS检验码")
    @JsonProperty("THS01")
    private String THS01;

    @ApiModelProperty(value = "sQMS检验单号")
    @JsonProperty("THS02")
    private String THS02;
}
