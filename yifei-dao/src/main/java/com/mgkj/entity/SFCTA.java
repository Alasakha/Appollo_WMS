package com.mgkj.entity;

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
 * @Date: 2023/12/18/23:04
 * @Description:    工单工艺信息档 实体类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("SFCTA")
@ApiModel(value = "工单工艺信息档 实体类")
public class SFCTA extends Base {
    @ApiModelProperty(value = "工单单别")
    @JsonProperty("TA001")
    private String TA001;   //工单单别	C	4.0	工单单别

    @ApiModelProperty(value = "工单单号")
    @JsonProperty("TA002")
    private String TA002;   //工单单别	C	11.0	工单单号

    @ApiModelProperty(value = "加工顺序")
    @JsonProperty("TA003")
    private String TA003;   //加工顺序	C	4.0	加工顺序

    @ApiModelProperty(value = "工艺")
    @JsonProperty("TA004")
    private String TA004;   //工艺	C	4.0	工艺

    @ApiModelProperty(value = "工艺性质")
    @JsonProperty("TA005")
    private String TA005;   //工艺性质	C	1.0	1.厂内工艺、2.委外工艺

    @ApiModelProperty(value = "工作中心(供应商编号)")
    @JsonProperty("TA006")
    private String TA006;   //工作中心/供应商编号	C	10.0	此工艺生产的主要工作中心/主要委外供应商编号

    @ApiModelProperty(value = "工作中心(供应商名称)")
    @JsonProperty("TA007")
    private String TA007;   //工作中心/供应商名称	C	20.0	此工艺生产的主要工作中心/主要委外供应商名称

    @ApiModelProperty(value = "预计开工日")
    @JsonProperty("TA008")
    private String TA008;   //预计开工日	C	8.0	预计开工日[FORMATE:YMD]

    @ApiModelProperty(value = "预计完工日")
    @JsonProperty("TA009")
    private String TA009;   //预计完工日	C	8.0	预计完工日[FORMATE:YMD]

    @ApiModelProperty(value = "投入数量")
    @JsonProperty("TA010")
    private BigDecimal TA010;   //投入数量	N	16.6	对此工艺的投入数量

    @ApiModelProperty(value = "完成数量")
    @JsonProperty("TA011")
    private BigDecimal TA011;   //完成数量	N	16.6	对此工艺的完成数量

    @ApiModelProperty(value = "报废数量")
    @JsonProperty("TA012")
    private BigDecimal TA012;   //报废数量	N	16.6	对此工艺的报废数量

    @ApiModelProperty(value = "返工投入")
    @JsonProperty("TA013")
    private BigDecimal TA013;   //返工投入	N	16.6	对此工艺的返工投入数量

    @ApiModelProperty(value = "返工完成")
    @JsonProperty("TA014")
    private BigDecimal TA014;   //返工完成	N	16.6	对此工艺的返工完成数量

    @ApiModelProperty(value = "拨转数量")
    @JsonProperty("TA015")
    private BigDecimal TA015;   //拨转数量	N	16.6	对此工艺的拨转数量

    @ApiModelProperty(value = "盘盈亏量")
    @JsonProperty("TA016")
    private BigDecimal TA016;   //盘盈亏量	N	16.6	对此工艺的盘盈亏数量

    @ApiModelProperty(value = "待转数量")
    @JsonProperty("TA017")
    private BigDecimal TA017;   //待转数量	N	16.6	对此工艺的待转数量

    @ApiModelProperty(value = "币种")
    @JsonProperty("TA018")
    private String TA018;   //币种	C	4.0	币种


    //原字段类型BigDecimal
    @ApiModelProperty(value = "汇率")
    @JsonProperty("TA019")
    private String TA019;   //汇率	N	12.7	汇率

    @ApiModelProperty(value = "计价单位")
    @JsonProperty("TA020")
    private String TA020;   //计价单位	C	4.0	计价单位

    @ApiModelProperty(value = "委外单价")
    @JsonProperty("TA021")
    private BigDecimal TA021;   //委外单价	N	17.8	委外单价

    @ApiModelProperty(value = "标准人时")
    @JsonProperty("TA022")
    private BigDecimal TA022;   //标准人时(秒)	N	8.0	对此工艺的标准人时(时:分:秒)

    @ApiModelProperty(value = "实际人时")
    @JsonProperty("TA023")
    private BigDecimal TA023;   //实际人时(秒)	N	8.0	对此工艺的实际人时(时:分:秒)

    @ApiModelProperty(value = "工艺的详细说明")
    @JsonProperty("TA024")
    private String TA024;   //工艺的详细说明	T	0.0

    @ApiModelProperty(value = "转移批量")
    @JsonProperty("TA025")
    private BigDecimal TA025;   //转移批量	N	7.0	转移批量

    @ApiModelProperty(value = "固定制造天数")
    @JsonProperty("TA026")
    private BigDecimal TA026;   //固定制造天数	N	7.3	固定批量的制造天数

    @ApiModelProperty(value = "变动制造天数")
    @JsonProperty("TA027")
    private BigDecimal TA027;   //变动制造天数	N	7.3	随批量变动制造天数

    @ApiModelProperty(value = "工时批量")
    @JsonProperty("TA028")
    private BigDecimal TA028;   //工时批量	N	7.0	工时批量[DEF:1]

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA029")
    private BigDecimal TA029;   //预留字段	N	16.6	预留字段(损坏扣款)

    @ApiModelProperty(value = "实际开工日")
    @JsonProperty("TA030")
    private String TA030;   //实际开工日	C	8.0	对此工艺的实际开工日[FORMATE:YMD]

    @ApiModelProperty(value = "实际完工日")
    @JsonProperty("TA031")
    private String TA031;   //实际完工日	C	8.0	对此工艺的实际完工日[FORMATE:YMD]

    @ApiModelProperty(value = "完工码")
    @JsonProperty("TA032")
    private String TA032;   //完工码	C	1.0	对此工艺的完工码

    @ApiModelProperty(value = "检验方式")
    @JsonProperty("TA033")
    private String TA033;   //检验方式	C	1.0	0:免检、1:抽检(减量)、2:抽检(正常)、3:抽检(加严)、4:全检

    @ApiModelProperty(value = "备注")
    @JsonProperty("TA034")
    private String TA034;   //备注	V	255.0	备注

    @ApiModelProperty(value = "标准机时")
    @JsonProperty("TA035")
    private BigDecimal TA035;   //标准机时(秒)	N	8.0	对此工艺的标准机时(时:分:秒)

    @ApiModelProperty(value = "实际机时")
    @JsonProperty("TA036")
    private BigDecimal TA036;   //实际机时(秒)	N	8.0	对此工艺的实际机时(时:分:秒)

    @ApiModelProperty(value = "检验天数")
    @JsonProperty("TA037")
    private BigDecimal TA037;   //检验天数	N	3.0	检验天数[DEF:0]

    @ApiModelProperty(value = "投入包装数量")
    @JsonProperty("TA038")
    private BigDecimal TA038;   //投入包装数量	N	16.6	对此工艺的投入包装数量

    @ApiModelProperty(value = "完成包装数量")
    @JsonProperty("TA039")
    private BigDecimal TA039;   //完成包装数量	N	16.6	对此工艺的完成包装数量

    @ApiModelProperty(value = "报废包装数量")
    @JsonProperty("TA040")
    private BigDecimal TA040;   //报废包装数量	N	16.6	对此工艺的报废包装数量

    @ApiModelProperty(value = "返工投入包装")
    @JsonProperty("TA041")
    private BigDecimal TA041;   //返工投入包装	N	16.6	对此工艺的返工投入包装数量

    @ApiModelProperty(value = "返工完成包装")
    @JsonProperty("TA042")
    private BigDecimal TA042;   //返工完成包装	N	16.6	对此工艺的返工完成包装数量

    @ApiModelProperty(value = "拨转包装数量")
    @JsonProperty("TA043")
    private BigDecimal TA043;   //拨转包装数量	N	16.6	对此工艺的拨转包装数量

    @ApiModelProperty(value = "盘盈亏包装量")
    @JsonProperty("TA044")
    private BigDecimal TA044;   //盘盈亏包装量	N	16.6	对此工艺的盘盈亏包装数量

    @ApiModelProperty(value = "待转包装数量")
    @JsonProperty("TA045")
    private BigDecimal TA045;   //待转包装数量	N	16.6	对此工艺的待转包装数量

    @ApiModelProperty(value = "完工判断依据")
    @JsonProperty("TA046")
    private String TA046;   //完工判断依据	C	1.0	1.数量 2.包装数量[DEF:"1"]

    @ApiModelProperty(value = "准产证")
    @JsonProperty("TA047")
    private String TA047;   //准产证	C	1.0	Y/N,[DEF:"N"]

    @ApiModelProperty(value = "破坏数量")
    @JsonProperty("TA048")
    private BigDecimal TA048;   //破坏数量	N	16.6	破坏数量

    @ApiModelProperty(value = "破坏包装数量")
    @JsonProperty("TA049")
    private BigDecimal TA049;   //破坏包装数量	N	16.6	破坏包装数量

    @ApiModelProperty(value = "含税")
    @JsonProperty("TA050")
    private String TA050;   //含税	C	1.0	含税,ONLY Y/N [DEF:"N"]

    @ApiModelProperty(value = "部门")
    @JsonProperty("TA051")
    private String TA051;   //部门	C	6.0	部门编号

    @ApiModelProperty(value = "工艺控管")
    @JsonProperty("TA052")
    private String TA052;   //工艺控管	C	1.0	Y/N,[DEF:"Y"]

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA053")
    private String TA053;   //预留字段	C	1.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA054")
    private String TA054;   //预留字段	C	8.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TA055")
    private String TA055;   //预留字段	V	30.0	预留字段

    @ApiModelProperty(value = "返工报废数量")
    @JsonProperty("TA056")
    private BigDecimal TA056;   //返工报废数量	N	16.6	返工报废数量

    @ApiModelProperty(value = "返工报废包装数量")
    @JsonProperty("TA057")
    private BigDecimal TA057;   //返工报废包装数量	N	16.6	返工报废包装数量

    @ApiModelProperty(value = "返工破坏数量")
    @JsonProperty("TA058")
    private BigDecimal TA058;   //返工破坏数量	N	16.6	返工破坏数量

    @ApiModelProperty(value = "计件单价")
    @JsonProperty("TA059")
    private BigDecimal TA059;   //计件单价	N	17.8	计件单价

    @ApiModelProperty(value = "工时工资率")
    @JsonProperty("TA060")
    private BigDecimal TA060;   //工时工资率	N	16.6	工时工资率

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TA061")
    private String TA061;   //项目编号	C	20.0	项目编号

    @ApiModelProperty(value = "返工破坏包装数量")
    @JsonProperty("TAD01")
    private BigDecimal TAD01;   //返工破坏包装数量	N	16.6	返工破坏包装数量

    @ApiModelProperty(value = "退回已投入数量")
    @JsonProperty("TAD02")
    private BigDecimal TAD02;   //退回已投入数量	N	16.6	退回已投入数量


}
