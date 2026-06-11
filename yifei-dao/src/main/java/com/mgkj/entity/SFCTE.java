package com.mgkj.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mgkj.base.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/15/14:37
 * @Description:    报工单单身档 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "报工单单身档 实体类")
@TableName("SFCTE")
public class SFCTE extends Base {

    @ApiModelProperty(value = "报工单单别")
    @JsonProperty("TE001")
    @ExcelProperty("单别")
    private String TE001;  //报工单单别	C	4.0	报工单单别

    @ApiModelProperty(value = "报工单单号")
    @JsonProperty("TE002")
    @ExcelProperty("单号")
    private String TE002;  //报工单单号	C	11.0	报工单单号

    @ApiModelProperty(value = "序号")
    @JsonProperty("TE003")
    @ExcelProperty("序号")
    private String TE003;  //序号	C	4.0	序号

    @ApiModelProperty(value = "员工编号 必传", required = true)
    @JsonProperty("TE004")
    @ExcelProperty("员工编号")
    private String TE004;  //员工编号	C	10.0	员工编号

    @ApiModelProperty(value = "机器编号 false")
    @JsonProperty("TE005")
    @ExcelProperty("机器编号")
    private String TE005;  //机器编号	C	15.0	机器编号

    @ApiModelProperty(value = "工单单别 必须true", required = true)
    @JsonProperty("TE006")
    @ExcelProperty("工单单别")
    private String TE006;  //工单单别	C	4.0	工单单别

    @ApiModelProperty(value = "工单单号 必须true", required = true)
    @JsonProperty("TE007")
    @ExcelProperty("工单单号")
    private String TE007;  //工单单号	C	11.0	工单单号

    @ApiModelProperty(value = "工序 必须true", required = true)
    @JsonProperty("TE008")
    @ExcelProperty("工序")
    private String TE008;  //工序	C	4.0	工单工艺中的加工顺序

    @ApiModelProperty(value = "工艺 必须true", required = true)
    @JsonProperty("TE009")
    @ExcelProperty("工艺")
    private String TE009;  //工艺	C	4.0	工单工艺中的工艺

    @ApiModelProperty(value = "类型 false")
    @JsonProperty("TE010")
    @ExcelProperty("类型")
    private String TE010;  //类型	C	1.0	转移类型(1:正常完成 2:返工完成 3:报废 )

    @ApiModelProperty(value = "数量 必须true（派工数量，可填）", required = true)
    @JsonProperty("TE011")
    @ExcelProperty("数量")
    private BigDecimal TE011;  //数量	N	16.6	转移数量

    @ApiModelProperty(value = "使用人时(秒)")
    @JsonProperty("TE012")
    @ExcelProperty("使用人时")
    private BigDecimal TE012;  //使用人时(秒)	N	8.0	对该工单工艺所投入的人时(时:分:秒)

    @ApiModelProperty(value = "使用机时")
    @JsonProperty("TE013")
    @ExcelProperty("使用机时")
    private BigDecimal TE013;  //使用机时(秒)	N	8.0	对该工单工艺所投入的机时(时:分:秒)

    @ApiModelProperty(value = "审核码")
    @JsonProperty("TE014")
    @ExcelProperty("审核码")
    private String TE014;  //审核码	C	1.0	本单已否审核的判断码(Y/N)

    @ApiModelProperty(value = "备注 true（可填）")
    @JsonProperty("TE015")
    @ExcelProperty("备注")
    private String TE015;  //备注	V	255.0	备注

    @ApiModelProperty(value = "包装数量")
    @JsonProperty("TE016")
    @ExcelProperty("包装数量")
    private BigDecimal TE016;  //包装数量	N	16.6	转移包装数量

    @ApiModelProperty(value = "产品品号 必须true")
    @JsonProperty("TE017")
    @ExcelProperty("产品品号")
    private String TE017;  //产品品号	C	20.0	产品品号

    @ApiModelProperty(value = "产品品名 必须true")
    @JsonProperty("TE018")
    @ExcelProperty("产品品名")
    private String TE018;  //产品品名	V	255.0	产品品名

    @ApiModelProperty(value = "产品规格 必须true")
    @JsonProperty("TE019")
    @ExcelProperty("产品规格")
    private String TE019;  //产品规格	V	255.0	产品规格

    @ApiModelProperty(value = "单位 工单有就true")
    @JsonProperty("TE020")
    @ExcelProperty("单位")
    private String TE020;  //单位	C	4.0	单位

    @ApiModelProperty(value = "包装单位")
    @JsonProperty("TE021")
    @ExcelProperty("包装单位")
    private String TE021;  //包装单位	C	4.0	包装单位

    @ApiModelProperty(value = "机器名称")
    @ExcelProperty("机器名称")
    @JsonProperty("TE022")
    private String TE022;  //预留字段	C	1.0	预留字段

    @ApiModelProperty(value = "派工日期(数据不对，换CREATE_DATE)")
    @JsonProperty("TE023")
    private String TE023;  //预留字段	C	8.0	预留字段

    @ApiModelProperty(value = "预留字段", required = true)
    @JsonProperty("TE024")
    private String TE024;  //预留字段	V	30.0	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TE025")
    private BigDecimal TE025;  //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TE026")
    private BigDecimal TE026;  //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "预留字段")
    @JsonProperty("TE027")
    private BigDecimal TE027;  //预留字段	N	16.6	预留字段

    @ApiModelProperty(value = "计件单价")
    @JsonProperty("TE028")
    private BigDecimal TE028;  //计件单价	N	17.8	计件单价

    @ApiModelProperty(value = "工时工资率")
    @JsonProperty("TE029")
    private BigDecimal TE029;  //工时工资率	N	16.6	工时工资率

    @ApiModelProperty(value = "计件类型")
    @JsonProperty("TE030")
    private String TE030;  //计件类型	C	1.0	1.计件 2.计时 [DEF:"1"]

    @ApiModelProperty(value = "金额")
    @JsonProperty("TE031")
    private BigDecimal TE031;  //金额	N	16.2	合计金额

    @ApiModelProperty(value = "项目编号")
    @JsonProperty("TE032")
    private String TE032;  //项目编号	C	20.0	项目编号

    @ApiModelProperty(value = "子工序")
    @JsonProperty("UDF09")
    private String UDF09;  //项目编号	C	20.0	项目编号

    @ApiModelProperty(value = "子工艺")
    @JsonProperty("UDF10")
    private String UDF10;  //项目编号	C	20.0	项目编号

    @ApiModelProperty(value = "订单号")
    @JsonProperty("UDF04")
    private String UDF04;

    @ApiModelProperty("百分比")
    @TableField(exist = false)
    private String percent;

    @ApiModelProperty("工艺名称")
    @TableField(exist = false)
    private String name;

    @ApiModelProperty("子工艺id 报工追回派工时候用")
    @TableField(exist = false)
    private String sonId;

    @ApiModelProperty("子工艺名称")
    @TableField(exist = false)
    private String sonName;

    @ApiModelProperty("已报工数量")
    @TableField(exist = false)
    private BigDecimal num;

    @TableField(value = "status")
    @ApiModelProperty("状态")
    private String status;

    @TableField(exist = false)
    @ApiModelProperty("检验列表")
    private List<Qmsmg> qmsmgList;

    @ApiModelProperty("子工序")
    @TableField(exist = false)
    private List<SfcteBo> sonList;

    @TableField(exist = false)
    private String MD002;

    @ApiModelProperty("进度")
    @TableField(exist = false)
    private Double jindu;

    @ApiModelProperty("可报数量")
    @TableField(exist = false)
    private Double yunxu;


}
