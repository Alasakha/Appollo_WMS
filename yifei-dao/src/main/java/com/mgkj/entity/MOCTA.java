package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/16:06
 * @Description:
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "报工单单身档 实体类")
public class MOCTA extends Base {

    @ApiModelProperty(value = "工单单号")
    @JsonProperty("TA001")
    // 工单单号 char 4
    String TA001;

    @ApiModelProperty(value = "工单单别")
    @JsonProperty("TA002")
    // 工单单别
    String TA002;

    @ApiModelProperty(value = "开单日期")
    @JsonProperty("TA003")
    // 开单日期
    String TA003;

    @ApiModelProperty(value = "BOM日期")
    @JsonProperty("TA004")
    // BOM日期
    String TA004;

    @ApiModelProperty(value = "BOM版本")
    @JsonProperty("TA005")
    // BOM版本
    String TA005;

    @ApiModelProperty(value = "产品品号")
    @JsonProperty("TA006")
    // 产品品号
    String TA006;

    @ApiModelProperty(value = "单位")
    @JsonProperty("TA007")
    // 单位
    String TA007;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA008")
    // 预留字段
    String TA008;

    @ApiModelProperty(value = "预计开工")
    @JsonProperty("TA009")
    // 预计开工
    String TA009;

    @ApiModelProperty(value = "预计完工")
    @JsonProperty("TA010")
    // 预计完工
    String TA010;

    @ApiModelProperty(value = "状态码")
    @JsonProperty("TA011")
    // 状态码  1.未生产、2.已发料、3.生产中、Y.已完工、y.指定完工
    String TA011;

    @ApiModelProperty(value = "实际开工")
    @JsonProperty("TA012")
    // 实际开工
    String TA012;

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TA013")
    // 审核码 Y、N、V
    String TA013;

    @ApiModelProperty(value = "实际完工")
    @JsonProperty("TA014")
    // 实际完工
    String TA014;

    @ApiModelProperty(value = "预计产量")
    @JsonProperty("TA015")
    // 预计产量
    BigDecimal TA015;

    @ApiModelProperty(value = "已领套数")
    @JsonProperty("TA016")
    // 已领套数
    BigDecimal TA016;

    @ApiModelProperty(value = "已生产量")
    @JsonProperty("TA017")
    // 已生产量
    BigDecimal TA017;

    @ApiModelProperty(value = "报废数量")
    @JsonProperty("TA018")
    // 报废数量
    BigDecimal TA018;

    @ApiModelProperty(value = "工厂编号")
    @JsonProperty("TA019")
    // 工厂编号
    String TA019;

    @ApiModelProperty(value = "入库仓库")
    @JsonProperty("TA020")
    // 入库仓库
    String TA020;

    @ApiModelProperty(value = "工作中心")
    @JsonProperty("TA021")
    // 工作中心
    String TA021;

    @ApiModelProperty(value = "委外单价")
    @JsonProperty("TA022")
    // 委外单价
    BigDecimal TA022;

    @ApiModelProperty(value = "加工单位")
    @JsonProperty("TA023")
    // 加工单位
    String TA023;

    @ApiModelProperty(value = "源工单单别")
    @JsonProperty("TA024")
    // 源工单单别
    String TA024;

    @ApiModelProperty(value = "源工单编号")
    @JsonProperty("TA025")
    // 源工单编号
    String TA025;

    @ApiModelProperty(value = "订单单别")
    @JsonProperty("TA026")
    // 订单单别
    String TA026;

    @ApiModelProperty(value = "订单单号")
    @JsonProperty("TA027")
    // 订单单号
    String TA027;

    @ApiModelProperty(value = "订单序号")
    @JsonProperty("TA028")
    // 订单序号
    String TA028;

    @ApiModelProperty(value = "备注")
    @JsonProperty("TA029")
    // 备注
    String TA029;

    @ApiModelProperty(value = "性质")
    @JsonProperty("TA030")
    // 性质  1.厂内工单、2.委外工单
    String TA030;

    @ApiModelProperty(value = "打印次数")
    @JsonProperty("TA031")
    // 打印次数
    BigDecimal TA031;

    @ApiModelProperty(value = "委外供应商")
    @JsonProperty("TA032")
    // 委外供应商
    String TA032;

    @ApiModelProperty(value = "计划批号")
    @JsonProperty("TA033")
    // 计划批号
    String TA033;

    @ApiModelProperty(value = "产品品名")
    @JsonProperty("TA034")
    // 产品品名
    String TA034;

    @ApiModelProperty(value = "产品规格")
    @JsonProperty("TA035")
    // 产品规格
    String TA035;

    @ApiModelProperty(value = "预计开工")
    @JsonProperty("TA036")
    // 预计开工 休假显示0.不休假、1.全天、2.半天
    String TA036;

    @ApiModelProperty(value = "预计完工")
    @JsonProperty("TA037")
    // 预计完工 休假显示0.不休假、1.全天、2.半天
    String TA037;

    @ApiModelProperty(value = "实际开工")
    @JsonProperty("TA038")
    // 实际开工 休假显示0.不休假、1.全天、2.半天
    String TA038;

    @ApiModelProperty(value = "实际完工")
    @JsonProperty("TA039")
    // 实际完工 休假显示0.不休假、1.全天、2.半天
    String TA039;

    @ApiModelProperty(value = "审核日")
    @JsonProperty("TA040")
    // 审核日
    String TA040;

    @ApiModelProperty(value = "审核者")
    @JsonProperty("TA041")
    // 审核者
    String TA041;

    @ApiModelProperty(value = "币种")
    @JsonProperty("TA042")
    // 币种
    String TA042;

    @ApiModelProperty(value = "汇率")
    @JsonProperty("TA043")
    // 汇率
    BigDecimal TA043;

    @ApiModelProperty(value = "急料")
    @JsonProperty("TA044")
    // 急料 Y/N
    String TA044;

    @ApiModelProperty(value = "预计产包装量")
    @JsonProperty("TA045")
    // 预计产包装量
    BigDecimal TA045;

    @ApiModelProperty(value = "已生产包装量")
    @JsonProperty("TA046")
    // 已生产包装量
    BigDecimal TA046;

    @ApiModelProperty(value = "报废包装数量")
    @JsonProperty("TA047")
    // 报废包装数量
    BigDecimal TA047;

    @ApiModelProperty(value = "包装单位")
    @JsonProperty("TA048")
    // 包装单位
    String TA048;

    @ApiModelProperty(value = "签核状态码")
    @JsonProperty("TA049")
    // 签核状态码  0.待处理、S.传送中、1.签核中、2.退件、3.已核准、4.撤销审核中、
    //              5.作废中、6.取消作废中、N.不运行电子签核[DEF:"N"]
    String TA049;

    @ApiModelProperty(value = "其他备注一")
    @JsonProperty("TA050")
    // 其他备注一
    String TA050;

    @ApiModelProperty(value = "其他备注二")
    @JsonProperty("TA051")
    // 其他备注二
    String TA051;

    @ApiModelProperty(value = "其他备注三")
    @JsonProperty("TA052")
    // 其他备注三
    String TA052;

    @ApiModelProperty(value = "其他备注四")
    @JsonProperty("TA053")
    // 其他备注四
    String TA053;

    @ApiModelProperty(value = "传送次数")
    @JsonProperty("TA054")
    // 传送次数
    BigDecimal TA054;

    @ApiModelProperty(value = "税种")
    @JsonProperty("TA055")
    // 税种 1.应税内含、2.应税外加、3.零税率、4.免税、9.不计税
    String TA055;

    @ApiModelProperty(value = "加工数量")
    @JsonProperty("TA056")
    // 加工数量
    BigDecimal TA056;

    @ApiModelProperty(value = "生产批号")
    @JsonProperty("TA057")
    // 生产批号
    String TA057;

    @ApiModelProperty(value = "批号说明")
    @JsonProperty("TA058")
    // 批号说明
    String TA058;

    @ApiModelProperty(value = "准产证")
    @JsonProperty("TA059")
    // 准产证
    String TA059;

    @ApiModelProperty(value = "破坏数量")
    @JsonProperty("TA060")
    // 破坏数量
    BigDecimal TA060;

    @ApiModelProperty(value = "破坏包装数量")
    @JsonProperty("TA061")
    // 破坏包装数量
    BigDecimal TA061;

    @ApiModelProperty(value = "途程卡打印次数")
    @JsonProperty("TA062")
    // 途程卡打印次数
    BigDecimal TA062;

    @ApiModelProperty(value = "需求日期")
    @JsonProperty("TA063")
    // 需求日期
    String TA063;

    @ApiModelProperty(value = "部门")
    @JsonProperty("TA064")
    // 部门
    String TA064;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA065")
    // 预留字段
    String TA065;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA066")
    //  预留字段
    String TA066;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA067")
    // 预留字段
    String TA067;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA068")
    // 预留字段
    BigDecimal TA068;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA069")
    // 预留字段
    BigDecimal TA069;

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA070")
    // 预留字段
    BigDecimal TA070;

    @ApiModelProperty(value = "付款条件编号")
    @JsonProperty("TA071")
    // 付款条件编号
    private String TA071;

    @ApiModelProperty(value = "税率")
    @JsonProperty("TA072")
    // 税率 税率(%)[DEF:9.9999]
    private BigDecimal TA072;

    @ApiModelProperty(value = "配置方案")
    @JsonProperty("TA073")
    // 配置方案
    private String TA073;

    @ApiModelProperty(value = "配置序号")
    @JsonProperty("TA074")
    // 配置序号
    private String TA074;

    @ApiModelProperty(value = "已装配数量")
    @JsonProperty("TAD01")
    // 已装配数量
    private BigDecimal TAD01;


    @ApiModelProperty(value = "类型")
    @JsonProperty("TAC01")
    // 类型 1.工程品号 2.正式品号[DEF:"2"]
    private String TAC01;

    @ApiModelProperty(value = "到货数量")
    @JsonProperty("TAC02")
    // 到货数量
    private String TAC02;

    @ApiModelProperty(value = "到货包装数量")
    @JsonProperty("TAC03")
    // 到货包装数量
    private BigDecimal TAC03;

    @ApiModelProperty(value = "树状码")
    @JsonProperty("TA075")
    // 树状码
    private String TA075;

    @ApiModelProperty(value = "根来源单别")
    @JsonProperty("TA076")
    // 根来源单别
    private String TA076;

    @ApiModelProperty(value = "根来源单号")
    @JsonProperty("TA077")
    // 根来源单号
    private String TA077;

    @ApiModelProperty(value = "根来源序号")
    @JsonProperty("TA078")
    // 根来源序号
    private String TA078;

    @ApiModelProperty(value = "计划序号")
    @JsonProperty("TA079")
    // 计划序号
    private String TA079;

    @ApiModelProperty(value = "计划版本")
    @JsonProperty("TA080")
    // 计划版本
    private String TA080;

    @ApiModelProperty(value = "开工日占用天数")
    @JsonProperty("TA081")
    // 开工日占用天数
    private String TA081;

    @ApiModelProperty(value = "完工日占用天数")
    @JsonProperty("TA082")
    // 完工日占用天数
    private String TA082;

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TA083")
    // 项目编号
    private String TA083;
}
