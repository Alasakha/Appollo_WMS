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
@ApiModel(value = "销退单单身 实体类")
public class COPTJ extends Base {
    @ApiModelProperty(value = "单别")
    @JsonProperty("TJ001")
    private String TJ001;

    @ApiModelProperty(value = "单号")
    @JsonProperty("TJ002")
    private String TJ002;

    @ApiModelProperty(value = "序号")
    @JsonProperty("TJ003")
    private String TJ003;

    @ApiModelProperty(value = "品号")
    @JsonProperty("TJ004")
    private String TJ004;

    @ApiModelProperty(value = "品名")
    @JsonProperty("TJ005")
    private String TJ005;

    @ApiModelProperty(value = "规格")
    @JsonProperty("TJ006")
    private String TJ006;

    @ApiModelProperty(value = "数量")
    @JsonProperty("TJ007")
    private BigDecimal TJ007;

    @ApiModelProperty(value = "单位")
    @JsonProperty("TJ008")
    private String TJ008;

    @ApiModelProperty(value = "库存数量")
    @JsonProperty("TJ009")
    private BigDecimal TJ009;

    @ApiModelProperty(value = "小单位")
    @JsonProperty("TJ010")
    private String TJ010;

    @ApiModelProperty(value = "单价")
    @JsonProperty("TJ011")
    private BigDecimal TJ011;

    @ApiModelProperty(value = "金额")
    @JsonProperty("TJ012")
    private BigDecimal TJ012;

    @ApiModelProperty(value = "退货仓库")
    @JsonProperty("TJ013")
    private String TJ013;

    @ApiModelProperty(value = "批号")
    @JsonProperty("TJ014")
    private String TJ014;

    @ApiModelProperty(value = "销货单别")
    @JsonProperty("TJ015")
    private String TJ015;

    @ApiModelProperty(value = "销货单号")
    @JsonProperty("TJ016")
    private String TJ016;

    @ApiModelProperty(value = "销货序号")
    @JsonProperty("TJ017")
    private String TJ017;

    @ApiModelProperty(value = "订单单别")
    @JsonProperty("TJ018")
    private String TJ018;

    @ApiModelProperty(value = "订单单号")
    @JsonProperty("TJ019")
    private String TJ019;

    @ApiModelProperty(value = "订单序号")
    @JsonProperty("TJ020")
    private String TJ020;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TJ021")
    private String TJ021;

    @ApiModelProperty(value = "更新码")
    @JsonProperty("TJ022")
    private String TJ022;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TJ023")
    private String TJ023;

    @ApiModelProperty(value = "开票码")
    @JsonProperty("TJ024")
    private String TJ024;

    @ApiModelProperty(value = "销售发票单别")
    @JsonProperty("TJ025")
    private String TJ025;

    @ApiModelProperty(value = "销售发票单号")
    @JsonProperty("TJ026")
    private String TJ026;

    @ApiModelProperty(value = "销售发票序号")
    @JsonProperty("TJ027")
    private String TJ027;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TJ028")
    private String TJ028;

    @ApiModelProperty(value = "客户品号")
    @JsonProperty("TJ029")
    private String TJ029;

    @ApiModelProperty(value = "类型")
    @JsonProperty("TJ030")
    private String TJ030;

    @ApiModelProperty(value = "原币税前金额")
    @JsonProperty("TJ031")
    private BigDecimal TJ031;

    @ApiModelProperty(value = "原币税额")
    @JsonProperty("TJ032")
    private BigDecimal TJ032;

    @ApiModelProperty(value = "本币税前金额")
    @JsonProperty("TJ033")
    private BigDecimal TJ033;

    @ApiModelProperty(value = "本币税额")
    @JsonProperty("TJ034")
    private BigDecimal TJ034;

    @ApiModelProperty(value = "包装数量")
    @JsonProperty("TJ035")
    private BigDecimal TJ035;

    @ApiModelProperty(value = "包装单位")
    @JsonProperty("TJ036")
    private String TJ036;

    @ApiModelProperty(value = "已开票数量")
    @JsonProperty("TJ037")
    private BigDecimal TJ037;

    @ApiModelProperty(value = "验收日期")
    @JsonProperty("TJ038")
    private String TJ038;

    @ApiModelProperty(value = "验收数量")
    @JsonProperty("TJ039")
    private BigDecimal TJ039;

    @ApiModelProperty(value = "验退数量")
    @JsonProperty("TJ040")
    private BigDecimal TJ040;

    @ApiModelProperty(value = "检验状态")
    @JsonProperty("TJ041")
    private String TJ041;

    @ApiModelProperty(value = "件装")
    @JsonProperty("TJ042")
    private BigDecimal TJ042;

    @ApiModelProperty(value = "件数")
    @JsonProperty("TJ043")
    private BigDecimal TJ043;

    @ApiModelProperty(value = "出货通知单别")
    @JsonProperty("TJ044")
    private String TJ044;

    @ApiModelProperty(value = "出货通知单号")
    @JsonProperty("TJ045")
    private String TJ045;

    @ApiModelProperty(value = "出货通知序号")
    @JsonProperty("TJ046")
    private String TJ046;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TJ047")
    private BigDecimal TJ047;  //N	5.4	预留字段-税率(%)[DEF:9.9999]

    @ApiModelProperty(value = "原始客户")
    @JsonProperty("TJ048")
    private String TJ048;

    @ApiModelProperty(value = "批号说明")
    @JsonProperty("TJ049")
    private String TJ049;

    @ApiModelProperty(value = "取样数量")
    @JsonProperty("TJ050")
    private BigDecimal TJ050;

    @ApiModelProperty(value = "报废数量")
    @JsonProperty("TJ051")
    private BigDecimal TJ051;

    @ApiModelProperty(value = "取样包装数量")
    @JsonProperty("TJ052")
    private BigDecimal TJ052;

    @ApiModelProperty(value = "报废包装数量")
    @JsonProperty("TJ053")
    private BigDecimal TJ053;

    @ApiModelProperty(value = "验收包装数量")
    @JsonProperty("TJ054")
    private BigDecimal TJ054;

    @ApiModelProperty(value = "验退包装数量")
    @JsonProperty("TJ055")
    private BigDecimal TJ055;

    @ApiModelProperty(value = "报废码")
    @JsonProperty("TJ056")
    private String TJ056;

    @ApiModelProperty(value = "库位")
    @JsonProperty("TJ057")
    private String TJ057;

    @ApiModelProperty(value = "有效日期")
    @JsonProperty("TJ058")
    private String TJ058;

    @ApiModelProperty(value = "复检日期")
    @JsonProperty("TJ059")
    private String TJ059;

    @ApiModelProperty(value = "生产日期")
    @JsonProperty("TJ060")
    private String TJ060;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TJ061")
    private String TJ061;  //C	1.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TJ062")
    private String TJ062;  //C	8.0	预留字段

    @ApiModelProperty(value = "报废仓库")
    @JsonProperty("TJ063")
    private String TJ063;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TJ064")
    private BigDecimal TJ064;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TJ065")
    private BigDecimal TJ065;  //N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TJ066")
    private BigDecimal TJ066;  //N	16.6	预留字段

    @ApiModelProperty(value = "赠备品类型")
    @JsonProperty("TJ067")
    private String TJ067;

    @ApiModelProperty(value = "赠/备品量")
    @JsonProperty("TJ068")
    private BigDecimal TJ068;

    @ApiModelProperty(value = "赠/备品包装量")
    @JsonProperty("TJ069")
    private BigDecimal TJ069;

    @ApiModelProperty(value = "赠/备品验收量")
    @JsonProperty("TJ070")
    private BigDecimal TJ070;

    @ApiModelProperty(value = "赠/备品验退量")
    @JsonProperty("TJ071")
    private BigDecimal TJ071;

    @ApiModelProperty(value = "赠/备品破坏量")
    @JsonProperty("TJ072")
    private BigDecimal TJ072;

    @ApiModelProperty(value = "赠/备品报废量")
    @JsonProperty("TJ073")
    private BigDecimal TJ073;

    @ApiModelProperty(value = "赠/备品验收包装量")
    @JsonProperty("TJ074;")
    private BigDecimal TJ074;

    @ApiModelProperty(value = "赠/备品验退包装量")
    @JsonProperty("TJ075")
    private BigDecimal TJ075;

    @ApiModelProperty(value = "赠/备品破坏包装量")
    @JsonProperty("TJ076")
    private BigDecimal TJ076;

    @ApiModelProperty(value = "赠/备品报废包装量")
    @JsonProperty("TJ077")
    private BigDecimal TJ077;
}
