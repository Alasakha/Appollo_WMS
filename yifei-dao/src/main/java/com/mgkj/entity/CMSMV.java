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
 * @Date: 2023/12/15/14:49
 * @Description:    员工基本信息档 实体类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("CMSMV")
@ApiModel(value = "报工单单身档 实体类")
public class CMSMV extends Base {

    @JsonProperty("MV001")
    @ApiModelProperty(value = "员工编号")
    @TableField("MV001")
    private String MV001;   //员工编号	C	10.0	员工编号

    @JsonProperty("MV002")
    @ApiModelProperty(value = "姓名")
    private String MV002;   //姓名	C	10.0	姓名

    @JsonProperty("MV003")
    @ApiModelProperty(value = "纳税公司")
    private String MV003;   //纳税公司	C	10.0	纳税公司

    @JsonProperty("MV004")
    @ApiModelProperty(value = "部门")
    private String MV004;   //部门	C	6.0	部门

    @JsonProperty("MV005")
    @ApiModelProperty(value = "职等")
    private String MV005;   //职等	C	8.0	职等

    @JsonProperty("MV006")
    @ApiModelProperty(value = "职务")
    private String MV006;   //职务	C	6.0	职务

    @JsonProperty("MV007")
    @ApiModelProperty(value = "性别")
    private String MV007;   //性别	C	1.0	1.男2.女

    @JsonProperty("MV008")
    @ApiModelProperty(value = "出生日期")
    private String MV008;   //出生日期	C	8.0	出生日期[FORMATE:YMD]

    @JsonProperty("MV009")
    @ApiModelProperty(value = "身分证号")
    private String MV009;   //身分证号	C	18.0	身分证号

    @JsonProperty("MV010")
    @ApiModelProperty(value = "科目种类")
    private String MV010;   //科目种类	C	1.0	1.直接费用2.间接费用3.管理费用4.销售费用5.研发费用

    @JsonProperty("MV011")
    @ApiModelProperty(value = "投保身份")
    private String MV011;   //投保身份	C	1.0	劳健保投保身份(一般或雇主或外籍人士..)

    @JsonProperty("MV012")
    @ApiModelProperty(value = "学历")
    private String MV012;   //学历	C	8.0	学历

    @JsonProperty("MV013")
    @ApiModelProperty(value = "专长")
    private String MV013;   //专长	C	72.0	专长

    @JsonProperty("MV014")
    @ApiModelProperty(value = "婚姻状况")
    private String MV014;   //婚姻状况	C	1.0	1.已婚2.未婚

    @JsonProperty("MV015")
    @ApiModelProperty(value = "联系电话1")
    private String MV015;   //联系电话1	C	20.0	联系电话1

    @JsonProperty("MV016")
    @ApiModelProperty(value = "联系电话2")
    private String MV016;   //联系电话2	C	20.0	联系电话2

    @JsonProperty("MV017")
    @ApiModelProperty(value = "户籍地址")
    private String MV017;   //户籍地址	V	255.0	户籍地址

    @JsonProperty("MV018")
    @ApiModelProperty(value = "邮编")
    private String MV018;   //邮编	C	10.0	邮编

    @JsonProperty("MV019")
    @ApiModelProperty(value = "通讯地址")
    private String MV019;   //通讯地址	V	255.0	通讯地址

    @JsonProperty("MV020")
    @ApiModelProperty(value = "E-MAIL")
    private String MV020;   //E-MAIL	V	255.0	E-MAIL

    @JsonProperty("MV021")
    @ApiModelProperty(value = "到职日")
    private String MV021;   //到职日	C	8.0	到职日[FORMATE:YMD]

    @JsonProperty("MV022")
    @ApiModelProperty(value = "退休日")
    private String MV022;   //离职日	C	8.0	离职日[FORMATE:YMD]

    @JsonProperty("MV023")
    @ApiModelProperty(value = "退休日")
    private String MV023;   //退休日	C	8.0	退休日[FORMATE:YMD]

    @JsonProperty("MV024")
    @ApiModelProperty(value = "补助等级")
    private String MV024;   //补助等级	C	1.0	补助等级(残障补助:轻度或中度或重度..)

    @JsonProperty("MV025")
    @ApiModelProperty(value = "劳保卡号")
    private String MV025;   //劳保卡号	C	10.0	劳保卡号

    @JsonProperty("MV026")
    @ApiModelProperty(value = "须刷卡")
    private String MV026;   //须刷卡	C	1.0	Y/N

    @JsonProperty("MV027")
    @ApiModelProperty(value = "主要班别")
    private String MV027;   //主要班别	C	3.0	主要班别

    @JsonProperty("MV028")
    @ApiModelProperty(value = "刷卡卡号")
    private String MV028;   //刷卡卡号	C	10.0	刷卡卡号

    @JsonProperty("MV029")
    @ApiModelProperty(value = "文档编号")
    private String MV029;   //文档编号	C	10.0	文档编号

    @JsonProperty("MV030")
    @ApiModelProperty(value = "健保投保号码")
    private String MV030;   //健保投保号码	C	10.0	健保投保号码

    @JsonProperty("MV031")
    @ApiModelProperty(value = "年资")
    private BigDecimal MV031;   //年资	N	4.2	当前累计年资

    @JsonProperty("MV032")
    @ApiModelProperty(value = "薪资类型")
    private String MV032;   //薪资类型	C	1.0	1.月薪2.日薪

    @JsonProperty("MV033")
    @ApiModelProperty(value = "基本工资")
    private BigDecimal MV033;   //基本工资	N	16.2	基本工资

    @JsonProperty("MV034")
    @ApiModelProperty(value = "转存方式")
    private String MV034;   //转存方式	C	1.0	B.银行P.邮局C.现金

    @JsonProperty("MV035")
    @ApiModelProperty(value = "行(局)号")
    private String MV035;   //行(局)号	C	10.0	行(局)号

    @JsonProperty("MV036")
    @ApiModelProperty(value = "账号")
    private String MV036;   //账号	C	30.0	账号

    @JsonProperty("MV037")
    @ApiModelProperty(value = "扶养人数")
    private BigDecimal MV037;   //扶养人数	N	2.0	扶养人数

    @JsonProperty("MV038")
    @ApiModelProperty(value = "课税方式")
    private String MV038;   //课税方式	C	1.0	0不代扣缴1按法扣缴2按固定金额扣缴3按固定税率扣缴4居住于国内的外籍人士5不居住于国内的外籍人士9不课税且不发扣缴凭单

    @JsonProperty("MV039")
    @ApiModelProperty(value = "固定税额")
    private BigDecimal MV039;   //固定税额	N	16.2	固定税额

    @JsonProperty("MV040")
    @ApiModelProperty(value = "固定税率")
    private BigDecimal MV040;   //固定税率	N	5.4	固定税率

    @JsonProperty("MV041")
    @ApiModelProperty(value = "发薪方式")
    private String MV041;   //发薪方式	C	1.0	1.每月发放一次2.每月发放两次

    @JsonProperty("MV042")
    @ApiModelProperty(value = "计全勤")
    private String MV042;   //计全勤	C	1.0	是否计全勤(Y/N)

    @JsonProperty("MV043")
    @ApiModelProperty(value = "全勤奖金")
    private BigDecimal MV043;   //全勤奖金	N	16.2	全勤奖金

    @JsonProperty("MV044")
    @ApiModelProperty(value = "计加班")
    private String MV044;   //计加班	C	1.0	Y/N

    @JsonProperty("MV045")
    @ApiModelProperty(value = "加班费")
    private BigDecimal MV045;   //加班费	N	16.2	每小时加班费

    @JsonProperty("MV046")
    @ApiModelProperty(value = "备注")
    private String MV046;   //备注	V	255.0	备注

    @JsonProperty("MV047")
    @ApiModelProperty(value = "英文全称")
    private String MV047;   //英文全称	C	30.0	英文全称

    @JsonProperty("MV048")
    @ApiModelProperty(value = "体检日期")
    private String MV048;   //体检日期	C	8.0	体检日期[FORMATE:YMD]

    @JsonProperty("MV049")
    @ApiModelProperty(value = "入境日期")
    private String MV049;   //入境日期	C	8.0	入境日期[FORMATE:YMD]

    @JsonProperty("MV050")
    @ApiModelProperty(value = "到期日期")
    private String MV050;   //到期日期	C	8.0	到期日期[FORMATE:YMD]

    @JsonProperty("MV051")
    @ApiModelProperty(value = "核准文号")
    private String MV051;   //核准文号	C	12.0	核准文号

    @JsonProperty("MV052")
    @ApiModelProperty(value = "试用期满日")
    private String MV052;   //试用期满日	C	8.0	试用期满日[FORMATE:YMD]

    @JsonProperty("MV053")
    @ApiModelProperty(value = "预计退休日")
    private String MV053;   //预计退休日	C	8.0	预计退休日[FORMATE:YMD]

    @JsonProperty("MV054")
    @ApiModelProperty(value = "身份别")
    private String MV054;   //身份别	C	2.0	社会福利金的身份(一般或外籍人士..)

    @JsonProperty("MV055")
    @ApiModelProperty(value = "社保金基数")
    private BigDecimal MV055;   //社保金基数	N	16.2	社保金基数

    @JsonProperty("MV056")
    @ApiModelProperty(value = "公积金基数")
    private BigDecimal MV056;   //公积金基数	N	16.2	公积金基数

    @JsonProperty("MV057")
    @ApiModelProperty(value = "公积金号码")
    private String MV057;   //公积金号码	C	12.0	公积金号码

    @JsonProperty("MV058")
    @ApiModelProperty(value = "合同编号")
    private String MV058;   //合同编号	C	10.0	合同编号

    @JsonProperty("MV059")
    @ApiModelProperty(value = "签约日期")
    private String MV059;   //签约日期	C	8.0	签约日期[FORMATE:YMD]

    @JsonProperty("MV060")
    @ApiModelProperty(value = "到期日期")
    private String MV060;   //到期日期	C	8.0	到期日期[FORMATE:YMD]

    @JsonProperty("MV061")
    @ApiModelProperty(value = "健康状况")
    private String MV061;   //健康状况	C	1.0	1.优,2.良,3.差[DEF:"1"]

    @JsonProperty("MV062")
    @ApiModelProperty(value = "技术职称")
    private String MV062;   //技术职称	C	20.0	技术职称

    @JsonProperty("MV063")
    @ApiModelProperty(value = "资格证书")
    private String MV063;   //资格证书	V	255.0	资格证书

    @JsonProperty("MV064")
    @ApiModelProperty(value = "传送码")
    private String MV064;   //传送码	C	1.0	Y/M/N[DEF:"N"]

    @JsonProperty("MV065")
    @ApiModelProperty(value = "传送日期")
    private String MV065;   //传送日期	C	12.0	日期时间yyyymmdd hh:mm

    @JsonProperty("MV066")
    @ApiModelProperty(value = "有效码")
    private String MV066;   //有效码	C	1.0	Y/N[DEF:"Y"]

    @JsonProperty("MV067")
    @ApiModelProperty(value = "员工照片")
    private String MV067;   //员工照片	C	10.0	员工照片

    @JsonProperty("MV068")
    @ApiModelProperty(value = "门店编号")
    private String MV068;   //门店编号	C	10.0	门店编号

    @JsonProperty("MV069")
    @ApiModelProperty(value = "来源编号")
    private String MV069;   //来源编号	C	6.0	来源编号

    @JsonProperty("MV070")
    @ApiModelProperty(value = "介绍人")
    private String MV070;   //介绍人	C	10.0	介绍人

    @JsonProperty("MV071")
    @ApiModelProperty(value = "学校编号")
    private String MV071;   //学校编号	C	6.0	学校编号

    @JsonProperty("MV072")
    @ApiModelProperty(value = "科系编号")
    private String MV072;   //科系编号	C	6.0	科系编号

    @JsonProperty("MV073")
    @ApiModelProperty(value = "语言能力编号")
    private String MV073;   //语言能力编号	C	6.0	语言能力编号

    @JsonProperty("MV074")
    @ApiModelProperty(value = "证照名称")
    private String MV074;   //证照名称	C	60.0	证照名称

    @JsonProperty("MV075")
    @ApiModelProperty(value = "民族")
    private String MV075;   //民族	C	10.0	民族

    @JsonProperty("MV076")
    @ApiModelProperty(value = "政治面貌")
    private String MV076;   //政治面貌	C	10.0	政治面貌

    @JsonProperty("MV077")
    @ApiModelProperty(value = "预留字段")
    private String MV077;   //预留字段	C	1.0	预留字段

    @JsonProperty("MV078")
    @ApiModelProperty(value = "下发功能解析")
    private String MV078;   //下发功能解析	C	8.0	下发功能解析

    @JsonProperty("MV079")
    @ApiModelProperty(value = "登录者编号")
    private String MV079;   //登录者编号	V	30.0	登录者编号

    @JsonProperty("MV080")
    @ApiModelProperty(value = "预留字段")
    private BigDecimal MV080;   //预留字段	N	16.6	预留字段

    @JsonProperty("MV081")
    @ApiModelProperty(value = "预留字段")
    private BigDecimal MV081;   //预留字段	N	16.6	预留字段

    @JsonProperty("MV082")
    @ApiModelProperty(value = "预留字段")
    private BigDecimal MV082;   //预留字段	N	16.6	预留字段

    @JsonProperty("MV083")
    @ApiModelProperty(value = "开户银行")
    private String MV083;   //开户银行	C	10.0	开户银行

    @JsonProperty("MV084")
    @ApiModelProperty(value = "账号")
    private String MV084;   //账号	C	30.0	账号

    @JsonProperty("MV085")
    @ApiModelProperty(value = "职务分类")
    private String MV085;   //职务分类	C	1.0	1.物管、2.生管、3.业务、4.采购、5.会计、6.出纳、7.仓管、8.研发、9.制造、A.品管、B.管理、C.工程、D.生技、E.船务、F.厂务、G.贸易、H.总务、I.人事、J.保税、K.稽核、L.企划、M.文管、N.产品、O.行政、P.外点(专柜前抬)、Z.其它

}
