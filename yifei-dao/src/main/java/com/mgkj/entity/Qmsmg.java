package com.mgkj.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName QMSMG
 */
@TableName(value ="QMSMG")
@Data
@Api(tags = "检验项目类")
public class Qmsmg implements Serializable {
    /**
     * 
     */
    @TableId(value = "MG001")
    @ApiModelProperty("品管类别")
    @JsonProperty("mg001")
    private String MG001;

    /**
     *
     */
//    @TableId(value = "MG002")
    @TableField(value = "MG002")
    @JsonProperty("mg002")
    @ApiModelProperty("品号")
    @ExcelProperty("品号")
    private String MG002;

    /**
     *
     */
//    @TableId(value = "MG003")
    @TableField(value = "MG003")
    @JsonProperty("mg003")
    @ApiModelProperty("检验编号")
    @ExcelProperty("检验编号")
    private String MG003;

    /**
     *
     */
//    @TableId(value = "MG008")
    @TableField(value = "MG008")
    @JsonProperty("mg008")
    @ApiModelProperty("工艺编号")
    @ExcelProperty("工艺编号")
    private String MG008;

    /**
     *
     */
//    @TableId(value = "MG020")
    @TableField(value = "MG020")
    @JsonProperty("mg020")
    @ApiModelProperty("检验类别")
    @ExcelProperty("检验类别")
    private String MG020;

    /**
     *
     */
    @TableField(value = "COMPANY")
    private String COMPANY;

    /**
     *
     */
    @TableField(value = "CREATOR")
    @ApiModelProperty("创建人")
    @JsonProperty("creator")
    private String CREATOR;

    /**
     *
     */
    @TableField(value = "USR_GROUP")
    private String USR_GROUP;

    /**
     *
     */
    @TableField(value = "CREATE_DATE")
    @ApiModelProperty("创建时间")
    @JsonProperty("create_DATE")
    private String CREATE_DATE;

    /**
     *
     */
    @TableField(value = "MODIFIER")
    private String MODIFIER;

    /**
     *
     */
    @TableField(value = "MODI_DATE")
    private String MODI_DATE;

    /**
     *
     */
    @TableField(value = "FLAG")
    private Integer FLAG;

    /**
     *
     */
    @TableField(value = "MG004")
    @ApiModelProperty("检验水平")
    @JsonProperty("mg004")
//    @ExcelProperty("检验水平")
    private String MG004;

    /**
     *
     */
    @TableField(value = "MG005")
    @ApiModelProperty("缺点等级")
    @JsonProperty("mg005")
//    @ExcelProperty("缺点等级")
    private String MG005;

    /**
     *
     */
    @TableField(value = "MG006")
    @ApiModelProperty("检验标准")
    @JsonProperty("mg006")
    @ExcelProperty("检验标准")
    private String MG006;

    /**
     *
     */
    @TableField(value = "MG007")
    @ApiModelProperty("备注")
    @JsonProperty("mg007")
    @ExcelProperty("备注")
    private String MG007;

    /**
     *
     */
    @TableField(value = "MG009")
    @ApiModelProperty("类型 (1.计数 2.计量")
    @JsonProperty("mg009")
    @ExcelProperty("类型(计数/计量)")
    private String MG009;

    /**
     *
     */
    @TableField(value = "MG010")
    @ApiModelProperty("合格判定项目 Y/N,[DEF:Y]")
    @JsonProperty("mg010")
    @ExcelProperty("合格判定项目")
    private String MG010;

    /**
     *
     */
    @TableField(value = "MG011")
    @ApiModelProperty("3.数字型 4.文字型,[DEF:3]")
    @JsonProperty("mg011")
    @ExcelProperty("文本型/数字型")
    private String MG011;

    /**
     *
     */
    @TableField(value = "MG012")
    @JsonProperty("mg012")
    @ApiModelProperty("标准合格上限[DEF:99999999.999] 正公差")
    @ExcelProperty("标准合格上限")
    private BigDecimal MG012;

    /**
     *
     */
    @TableField(value = "MG013")
    @JsonProperty("mg013")
    @ApiModelProperty("标准合格下限[DEF:-99999999.999] 负公差")
    @ExcelProperty("标准合格下限")
    private BigDecimal MG013;

    /**
     *
     */
    @TableField(value = "MG014")
    @JsonProperty("mg014")
    @ApiModelProperty("计算公式")
//    @ExcelProperty("计算公式")
    private String MG014;

    /**
     *
     */
    @TableField(value = "MG015")
    @JsonProperty("mg015")
    @ApiModelProperty("1.依抽查基础2.定数抽样,[DEF:1] 抽样规则")
    @ExcelProperty("依抽查基础/定数抽样")
    private String MG015;

    /**
     *
     */
    @TableField(value = "MG016")
    @JsonProperty("mg016")
    @ApiModelProperty("抽样数量")
    @ExcelProperty("抽样数量")
    private BigDecimal MG016;

    /**
     *
     */
    @TableField(value = "MG017")
    @JsonProperty("mg017")
    @ApiModelProperty("累计破坏数量 Y/N,[DEF:N]")
    @ExcelProperty("累计破坏数量")
    private String MG017;

    /**
     *
     */
    @TableField(value = "MG018")
    private String MG018;

    /**
     *
     */
    @TableField(value = "MG019")
    private String MG019;

    /**
     *
     */
    @TableField(value = "MG021")
    private BigDecimal MG021;

    /**
     *
     */
    @TableField(value = "MG022")
    private BigDecimal MG022;

    /**
     *
     */
    @TableField(value = "MG023")
    private BigDecimal MG023;

    /**
     *
     */
    @TableField(value = "UDF01")
    @JsonProperty("udf01")
    @ApiModelProperty("检验项目")
    @ExcelProperty("检验项目")
    private String UDF01;

    /**
     *
     */
    @TableField(value = "UDF02")
    @JsonProperty("udf02")
    @ApiModelProperty("检验工具")
    @ExcelProperty("检验工具")
    private String UDF02;

    /**
     *
     */
    @TableField(value = "UDF03")
    @JsonProperty("udf03")
    @ApiModelProperty("检验频次")
    @ExcelProperty("检验频次")
    private String UDF03;

    /**
     *
     */
    @TableField(value = "UDF04")
    private String UDF04;

    /**
     *
     */
    @TableField(value = "UDF05")
    private String UDF05;

    /**
     *
     */
    @ApiModelProperty("检验场别")
    @JsonProperty("udf06")
    @TableField(value = "UDF06")
    @ExcelProperty("检验场别")
    private String UDF06;

    /**
     *
     */
    @TableField(value = "UDF51")
    private BigDecimal UDF51;

    /**
     *
     */
    @TableField(value = "UDF52")
    private BigDecimal UDF52;

    /**
     *
     */
    @TableField(value = "UDF53")
    private BigDecimal UDF53;

    /**
     *
     */
    @TableField(value = "UDF54")
    private BigDecimal UDF54;

    /**
     *
     */
    @TableField(value = "UDF55")
    private BigDecimal UDF55;

    /**
     *
     */
    @TableField(value = "UDF56")
    private BigDecimal UDF56;

    /**
     *
     */
    @TableField(value = "UDF07")
    private String UDF07;

    /**
     *
     */
    @ApiModelProperty("工艺说明")
    @JsonProperty("udf08")
    @TableField(value = "UDF08")
    private String UDF08;

    /**
     *
     */
    @ApiModelProperty("工艺名称")
    @JsonProperty("udf09")
    @TableField(value = "UDF09")
    private String UDF09;

    /**
     *
     */
    @ApiModelProperty("品名")
    @JsonProperty("udf10")
    @TableField(value = "UDF10")
    private String UDF10;

    /**
     *
     */
    @ApiModelProperty("规格")
    @JsonProperty("udf11")
    @TableField(value = "UDF11")
    private String UDF11;

    /**
     * 
     */
    @TableField(value = "UDF12")
    private String UDF12;

    /**
     * 
     */
    @TableField(value = "UDF57")
    private BigDecimal UDF57;

    /**
     * 
     */
    @TableField(value = "UDF58")
    private BigDecimal UDF58;

    /**
     * 
     */
    @TableField(value = "UDF59")
    private BigDecimal UDF59;

    /**
     * 
     */
    @TableField(value = "UDF60")
    private BigDecimal UDF60;

    /**
     * 
     */
    @TableField(value = "UDF61")
    private BigDecimal UDF61;

    /**
     * 
     */
    @TableField(value = "UDF62")
    private BigDecimal UDF62;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}