package com.mgkj.entity;

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
 * @TableName INVMB
 */
@TableName(value ="INVMB")
@Data
@Api(tags = "品号bean")
public class Invmb implements Serializable {
    /**
     * 
     */
    @TableId(value = "MB001")
    @ApiModelProperty("品号")
    @JsonProperty("mb001")
    private String MB001;

    /**
     * 
     */
    @TableField(value = "COMPANY")
    private String COMPANY;

    /**
     * 
     */
    @TableField(value = "CREATOR")
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
    @TableField(value = "MB002")
    @ApiModelProperty("品名")
    @JsonProperty("mb002")
    private String MB002;

    /**
     * 
     */
    @TableField(value = "MB003")
    @ApiModelProperty("规格")
    @JsonProperty("mb003")
    private String MB003;

    /**
     * 
     */
    @TableField(value = "MB004")
    private String MB004;

    /**
     * 
     */
    @TableField(value = "MB005")
    private String MB005;

    /**
     * 
     */
    @TableField(value = "MB006")
    private String MB006;

    /**
     * 
     */
    @TableField(value = "MB007")
    private String MB007;

    /**
     * 
     */
    @TableField(value = "MB008")
    private String MB008;

    /**
     * 
     */
    @TableField(value = "MB009")
    private String MB009;

    /**
     * 
     */
    @TableField(value = "MB010")
    private String MB010;

    /**
     * 
     */
    @TableField(value = "MB011")
    private String MB011;

    /**
     * 
     */
    @TableField(value = "MB012")
    private String MB012;

    /**
     * 
     */
    @TableField(value = "MB013")
    private String MB013;

    /**
     * 
     */
    @TableField(value = "MB014")
    private BigDecimal MB014;

    /**
     * 
     */
    @TableField(value = "MB015")
    private String MB015;

    /**
     * 
     */
    @TableField(value = "MB016")
    private String MB016;

    /**
     * 
     */
    @TableField(value = "MB017")
    private String MB017;

    /**
     * 
     */
    @TableField(value = "MB018")
    private String MB018;

    /**
     * 
     */
    @TableField(value = "MB019")
    private String MB019;

    /**
     * 
     */
    @TableField(value = "MB020")
    private String MB020;

    /**
     * 
     */
    @TableField(value = "MB021")
    private String MB021;

    /**
     * 
     */
    @TableField(value = "MB022")
    private String MB022;

    /**
     * 
     */
    @TableField(value = "MB023")
    private Integer MB023;

    /**
     * 
     */
    @TableField(value = "MB024")
    private Integer MB024;

    /**
     * 
     */
    @TableField(value = "MB025")
    private String MB025;

    /**
     * 
     */
    @TableField(value = "MB026")
    private String MB026;

    /**
     * 
     */
    @TableField(value = "MB027")
    private String MB027;

    /**
     * 
     */
    @TableField(value = "MB028")
    private String MB028;

    /**
     * 
     */
    @TableField(value = "MB029")
    private String MB029;

    /**
     * 
     */
    @TableField(value = "MB030")
    private String MB030;

    /**
     * 
     */
    @TableField(value = "MB031")
    private String MB031;

    /**
     * 
     */
    @TableField(value = "MB032")
    private String MB032;

    /**
     * 
     */
    @TableField(value = "MB033")
    private String MB033;

    /**
     * 
     */
    @TableField(value = "MB034")
    private String MB034;

    /**
     * 
     */
    @TableField(value = "MB035")
    private String MB035;

    /**
     * 
     */
    @TableField(value = "MB036")
    private BigDecimal MB036;

    /**
     * 
     */
    @TableField(value = "MB037")
    private BigDecimal MB037;

    /**
     * 
     */
    @TableField(value = "MB038")
    private BigDecimal MB038;

    /**
     * 
     */
    @TableField(value = "MB039")
    private BigDecimal MB039;

    /**
     * 
     */
    @TableField(value = "MB040")
    private BigDecimal MB040;

    /**
     * 
     */
    @TableField(value = "MB041")
    private BigDecimal MB041;

    /**
     * 
     */
    @TableField(value = "MB042")
    private String MB042;

    /**
     * 
     */
    @TableField(value = "MB043")
    private String MB043;

    /**
     * 
     */
    @TableField(value = "MB044")
    private String MB044;

    /**
     * 
     */
    @TableField(value = "MB045")
    private BigDecimal MB045;

    /**
     * 
     */
    @TableField(value = "MB046")
    private BigDecimal MB046;

    /**
     * 
     */
    @TableField(value = "MB047")
    private BigDecimal MB047;

    /**
     * 
     */
    @TableField(value = "MB048")
    private String MB048;

    /**
     * 
     */
    @TableField(value = "MB049")
    private BigDecimal MB049;

    /**
     * 
     */
    @TableField(value = "MB050")
    private BigDecimal MB050;

    /**
     * 
     */
    @TableField(value = "MB051")
    private BigDecimal MB051;

    /**
     * 
     */
    @TableField(value = "MB052")
    private String MB052;

    /**
     * 
     */
    @TableField(value = "MB053")
    private BigDecimal MB053;

    /**
     * 
     */
    @TableField(value = "MB054")
    private BigDecimal MB054;

    /**
     * 
     */
    @TableField(value = "MB055")
    private BigDecimal MB055;

    /**
     * 
     */
    @TableField(value = "MB056")
    private BigDecimal MB056;

    /**
     * 
     */
    @TableField(value = "MB057")
    private BigDecimal MB057;

    /**
     * 
     */
    @TableField(value = "MB058")
    private BigDecimal MB058;

    /**
     * 
     */
    @TableField(value = "MB059")
    private BigDecimal MB059;

    /**
     * 
     */
    @TableField(value = "MB060")
    private BigDecimal MB060;

    /**
     * 
     */
    @TableField(value = "MB061")
    private BigDecimal MB061;

    /**
     * 
     */
    @TableField(value = "MB062")
    private BigDecimal MB062;

    /**
     * 
     */
    @TableField(value = "MB063")
    private BigDecimal MB063;

    /**
     * 
     */
    @TableField(value = "MB064")
    private BigDecimal MB064;

    /**
     * 
     */
    @TableField(value = "MB065")
    private BigDecimal MB065;

    /**
     * 
     */
    @TableField(value = "MB066")
    private String MB066;

    /**
     * 
     */
    @TableField(value = "MB067")
    private String MB067;

    /**
     * 
     */
    @TableField(value = "MB068")
    private String MB068;

    /**
     * 
     */
    @TableField(value = "MB069")
    private BigDecimal MB069;

    /**
     * 
     */
    @TableField(value = "MB070")
    private BigDecimal MB070;

    /**
     * 
     */
    @TableField(value = "MB071")
    private BigDecimal MB071;

    /**
     * 
     */
    @TableField(value = "MB072")
    private String MB072;

    /**
     * 
     */
    @TableField(value = "MB073")
    private BigDecimal MB073;

    /**
     * 
     */
    @TableField(value = "MB074")
    private BigDecimal MB074;

    /**
     * 
     */
    @TableField(value = "MB075")
    private BigDecimal MB075;

    /**
     * 
     */
    @TableField(value = "MB076")
    private Integer MB076;

    /**
     * 
     */
    @TableField(value = "MB077")
    private String MB077;

    /**
     * 
     */
    @TableField(value = "MB078")
    private Integer MB078;

    /**
     * 
     */
    @TableField(value = "MB079")
    private Integer MB079;

    /**
     * 
     */
    @TableField(value = "MB080")
    private String MB080;

    /**
     * 
     */
    @TableField(value = "MB081")
    private String MB081;

    /**
     * 
     */
    @TableField(value = "MB082")
    private BigDecimal MB082;

    /**
     * 
     */
    @TableField(value = "MB083")
    private String MB083;

    /**
     * 
     */
    @TableField(value = "MB084")
    private BigDecimal MB084;

    /**
     * 
     */
    @TableField(value = "MB085")
    private String MB085;

    /**
     * 
     */
    @TableField(value = "MB086")
    private BigDecimal MB086;

    /**
     * 
     */
    @TableField(value = "MB087")
    private String MB087;

    /**
     * 
     */
    @TableField(value = "MB088")
    private BigDecimal MB088;

    /**
     * 
     */
    @TableField(value = "MB089")
    private BigDecimal MB089;

    /**
     * 
     */
    @TableField(value = "MB090")
    private String MB090;

    /**
     * 
     */
    @TableField(value = "MB091")
    private String MB091;

    /**
     * 
     */
    @TableField(value = "MB092")
    private String MB092;

    /**
     * 
     */
    @TableField(value = "MB093")
    private BigDecimal MB093;

    /**
     * 
     */
    @TableField(value = "MB094")
    private BigDecimal MB094;

    /**
     * 
     */
    @TableField(value = "MB095")
    private BigDecimal MB095;

    /**
     * 
     */
    @TableField(value = "MB096")
    private BigDecimal MB096;

    /**
     * 
     */
    @TableField(value = "MB100")
    private String MB100;

    /**
     * 
     */
    @TableField(value = "MB101")
    private String MB101;

    /**
     * 
     */
    @TableField(value = "MB102")
    private String MB102;

    /**
     * 
     */
    @TableField(value = "MB103")
    private String MB103;

    /**
     * 
     */
    @TableField(value = "MB104")
    private String MB104;

    /**
     * 
     */
    @TableField(value = "MB105")
    private String MB105;

    /**
     * 
     */
    @TableField(value = "MB106")
    private String MB106;

    /**
     * 
     */
    @TableField(value = "MB107")
    private String MB107;

    /**
     * 
     */
    @TableField(value = "MB108")
    private String MB108;

    /**
     * 
     */
    @TableField(value = "MB109")
    private String MB109;

    /**
     * 
     */
    @TableField(value = "MB110")
    private String MB110;

    /**
     * 
     */
    @TableField(value = "MB111")
    private BigDecimal MB111;

    /**
     * 
     */
    @TableField(value = "MB112")
    private String MB112;

    /**
     * 
     */
    @TableField(value = "MB113")
    private String MB113;

    /**
     * 
     */
    @TableField(value = "MB114")
    private String MB114;

    /**
     * 
     */
    @TableField(value = "MB115")
    private String MB115;

    /**
     * 
     */
    @TableField(value = "MB116")
    private String MB116;

    /**
     * 
     */
    @TableField(value = "MB117")
    private String MB117;

    /**
     * 
     */
    @TableField(value = "MB118")
    private String MB118;

    /**
     * 
     */
    @TableField(value = "MB119")
    private String MB119;

    /**
     * 
     */
    @TableField(value = "MB120")
    private String MB120;

    /**
     * 
     */
    @TableField(value = "MB121")
    private String MB121;

    /**
     * 
     */
    @TableField(value = "MB122")
    private String MB122;

    /**
     * 
     */
    @TableField(value = "MB123")
    private String MB123;

    /**
     * 
     */
    @TableField(value = "MB124")
    private String MB124;

    /**
     * 
     */
    @TableField(value = "MB125")
    private String MB125;

    /**
     * 
     */
    @TableField(value = "MB126")
    private String MB126;

    /**
     * 
     */
    @TableField(value = "MB127")
    private String MB127;

    /**
     * 
     */
    @TableField(value = "MB128")
    private String MB128;

    /**
     * 
     */
    @TableField(value = "MB129")
    private String MB129;

    /**
     * 
     */
    @TableField(value = "MB130")
    private String MB130;

    /**
     * 
     */
    @TableField(value = "MB131")
    private String MB131;

    /**
     * 
     */
    @TableField(value = "MB132")
    private String MB132;

    /**
     * 
     */
    @TableField(value = "MB133")
    private String MB133;

    /**
     * 
     */
    @TableField(value = "MB134")
    private String MB134;

    /**
     * 
     */
    @TableField(value = "MB135")
    private String MB135;

    /**
     * 
     */
    @TableField(value = "MB136")
    private String MB136;

    /**
     * 
     */
    @TableField(value = "MB137")
    private String MB137;

    /**
     * 
     */
    @TableField(value = "MB138")
    private String MB138;

    /**
     * 
     */
    @TableField(value = "MB139")
    private String MB139;

    /**
     * 
     */
    @TableField(value = "MB140")
    private String MB140;

    /**
     * 
     */
    @TableField(value = "MB141")
    private String MB141;

    /**
     * 
     */
    @TableField(value = "MB142")
    private String MB142;

    /**
     * 
     */
    @TableField(value = "MB143")
    private String MB143;

    /**
     * 
     */
    @TableField(value = "MB144")
    private String MB144;

    /**
     * 
     */
    @TableField(value = "MB145")
    private String MB145;

    /**
     * 
     */
    @TableField(value = "MB146")
    private String MB146;

    /**
     * 
     */
    @TableField(value = "MB147")
    private Integer MB147;

    /**
     * 
     */
    @TableField(value = "MB148")
    private String MB148;

    /**
     * 
     */
    @TableField(value = "MB149")
    private String MB149;

    /**
     * 
     */
    @TableField(value = "MB150")
    private String MB150;

    /**
     * 
     */
    @TableField(value = "MB151")
    private Integer MB151;

    /**
     * 
     */
    @TableField(value = "MB152")
    private Integer MB152;

    /**
     * 
     */
    @TableField(value = "MB179")
    private String MB179;

    /**
     * 
     */
    @TableField(value = "MB180")
    private String MB180;

    /**
     * 
     */
    @TableField(value = "MB181")
    private String MB181;

    /**
     * 
     */
    @TableField(value = "MB182")
    private String MB182;

    /**
     * 
     */
    @TableField(value = "MB183")
    private String MB183;

    /**
     * 
     */
    @TableField(value = "MB184")
    private String MB184;

    /**
     * 
     */
    @TableField(value = "MB185")
    private String MB185;

    /**
     * 
     */
    @TableField(value = "MB186")
    private String MB186;

    /**
     * 
     */
    @TableField(value = "MB187")
    private String MB187;

    /**
     * 
     */
    @TableField(value = "MB188")
    private String MB188;

    /**
     * 
     */
    @TableField(value = "MB189")
    private String MB189;

    /**
     * 
     */
    @TableField(value = "MB190")
    private String MB190;

    /**
     * 
     */
    @TableField(value = "MB191")
    private String MB191;

    /**
     * 
     */
    @TableField(value = "MB192")
    private String MB192;

    /**
     * 
     */
    @TableField(value = "MB193")
    private String MB193;

    /**
     * 
     */
    @TableField(value = "MB194")
    private String MB194;

    /**
     * 
     */
    @TableField(value = "MB195")
    private String MB195;

    /**
     * 
     */
    @TableField(value = "MB196")
    private String MB196;

    /**
     * 
     */
    @TableField(value = "MB197")
    private String MB197;

    /**
     * 
     */
    @TableField(value = "MB198")
    private String MB198;

    /**
     * 
     */
    @TableField(value = "MB199")
    private String MB199;

    /**
     * 
     */
    @TableField(value = "MB401")
    private BigDecimal MB401;

    /**
     * 
     */
    @TableField(value = "MB402")
    private BigDecimal MB402;

    /**
     * 
     */
    @TableField(value = "MB403")
    private BigDecimal MB403;

    /**
     * 
     */
    @TableField(value = "MB404")
    private BigDecimal MB404;

    /**
     * 
     */
    @TableField(value = "MB405")
    private String MB405;

    /**
     * 
     */
    @TableField(value = "MB406")
    private String MB406;

    /**
     * 
     */
    @TableField(value = "MB407")
    private String MB407;

    /**
     * 
     */
    @TableField(value = "MB408")
    private String MB408;

    /**
     * 
     */
    @TableField(value = "MB409")
    private String MB409;

    /**
     * 
     */
    @TableField(value = "MB410")
    private BigDecimal MB410;

    /**
     * 
     */
    @TableField(value = "MB411")
    private String MB411;

    /**
     * 
     */
    @TableField(value = "MB412")
    private String MB412;

    /**
     * 
     */
    @TableField(value = "MB413")
    private String MB413;

    /**
     * 
     */
    @TableField(value = "MB414")
    private String MB414;

    /**
     * 
     */
    @TableField(value = "MB415")
    private BigDecimal MB415;

    /**
     * 
     */
    @TableField(value = "MB416")
    private BigDecimal MB416;

    /**
     * 
     */
    @TableField(value = "MB417")
    private BigDecimal MB417;

    /**
     * 
     */
    @TableField(value = "MB418")
    private BigDecimal MB418;

    /**
     * 
     */
    @TableField(value = "MB419")
    private BigDecimal MB419;

    /**
     * 
     */
    @TableField(value = "MB420")
    private BigDecimal MB420;

    /**
     * 
     */
    @TableField(value = "MB421")
    private String MB421;

    /**
     * 
     */
    @TableField(value = "MB422")
    private String MB422;

    /**
     * 
     */
    @TableField(value = "MB423")
    private String MB423;

    /**
     * 
     */
    @TableField(value = "MB424")
    private String MB424;

    /**
     * 
     */
    @TableField(value = "MB425")
    private Integer MB425;

    /**
     * 
     */
    @TableField(value = "MB426")
    private Integer MB426;

    /**
     * 
     */
    @TableField(value = "MB427")
    private Integer MB427;

    /**
     * 
     */
    @TableField(value = "MB428")
    private BigDecimal MB428;

    /**
     * 
     */
    @TableField(value = "MB429")
    private BigDecimal MB429;

    /**
     * 
     */
    @TableField(value = "MB430")
    private BigDecimal MB430;

    /**
     * 
     */
    @TableField(value = "MB431")
    private BigDecimal MB431;

    /**
     * 
     */
    @TableField(value = "MB432")
    private BigDecimal MB432;

    /**
     * 
     */
    @TableField(value = "MB433")
    private BigDecimal MB433;

    /**
     * 
     */
    @TableField(value = "MB434")
    private BigDecimal MB434;

    /**
     * 
     */
    @TableField(value = "MB435")
    private String MB435;

    /**
     * 
     */
    @TableField(value = "MB436")
    private String MB436;

    /**
     * 
     */
    @TableField(value = "MB437")
    private String MB437;

    /**
     * 
     */
    @TableField(value = "MB438")
    private BigDecimal MB438;

    /**
     * 
     */
    @TableField(value = "MB439")
    private BigDecimal MB439;

    /**
     * 
     */
    @TableField(value = "MB440")
    private BigDecimal MB440;

    /**
     * 
     */
    @TableField(value = "MB441")
    private String MB441;

    /**
     * 
     */
    @TableField(value = "MB442")
    private String MB442;

    /**
     * 
     */
    @TableField(value = "MB443")
    private String MB443;

    /**
     * 
     */
    @TableField(value = "MB444")
    private String MB444;

    /**
     * 
     */
    @TableField(value = "MB445")
    private String MB445;

    /**
     * 
     */
    @TableField(value = "MB446")
    private BigDecimal MB446;

    /**
     * 
     */
    @TableField(value = "MB447")
    private BigDecimal MB447;

    /**
     * 
     */
    @TableField(value = "MB448")
    private BigDecimal MB448;

    /**
     * 
     */
    @TableField(value = "MB449")
    private BigDecimal MB449;

    /**
     * 
     */
    @TableField(value = "MB450")
    private String MB450;

    /**
     * 
     */
    @TableField(value = "MB451")
    private String MB451;

    /**
     * 
     */
    @TableField(value = "MB452")
    private BigDecimal MB452;

    /**
     * 
     */
    @TableField(value = "MBD01")
    private String MBD01;

    /**
     * 
     */
    @TableField(value = "MBD02")
    private String MBD02;

    /**
     * 
     */
    @TableField(value = "MBE01")
    private String MBE01;

    /**
     * 
     */
    @TableField(value = "MB453")
    private String MB453;

    /**
     * 
     */
    @TableField(value = "MB454")
    private String MB454;

    /**
     * 
     */
    @TableField(value = "MBH01")
    private String MBH01;

    /**
     * 
     */
    @TableField(value = "MBH02")
    private String MBH02;

    /**
     * 
     */
    @TableField(value = "MBH03")
    private String MBH03;

    /**
     * 
     */
    @TableField(value = "MB455")
    private String MB455;

    /**
     * 
     */
    @TableField(value = "MB456")
    private String MB456;

    /**
     * 
     */
    @TableField(value = "MB457")
    private String MB457;

    /**
     * 
     */
    @TableField(value = "MB458")
    private String MB458;

    /**
     * 
     */
    @TableField(value = "MB459")
    private String MB459;

    /**
     * 
     */
    @TableField(value = "MB460")
    private String MB460;

    /**
     * 
     */
    @TableField(value = "MB461")
    private String MB461;

    /**
     * 
     */
    @TableField(value = "MB462")
    private String MB462;

    /**
     * 
     */
    @TableField(value = "MB463")
    private String MB463;

    /**
     * 
     */
    @TableField(value = "MB464")
    private String MB464;

    /**
     * 
     */
    @TableField(value = "MB465")
    private String MB465;

    /**
     * 
     */
    @TableField(value = "MB466")
    private String MB466;

    /**
     * 
     */
    @TableField(value = "MB467")
    private String MB467;

    /**
     * 
     */
    @TableField(value = "MB468")
    private String MB468;

    /**
     * 
     */
    @TableField(value = "MB469")
    private String MB469;

    /**
     * 
     */
    @TableField(value = "MB470")
    private String MB470;

    /**
     * 
     */
    @TableField(value = "MB471")
    private String MB471;

    /**
     * 
     */
    @TableField(value = "MB472")
    private String MB472;

    /**
     * 
     */
    @TableField(value = "MB473")
    private String MB473;

    /**
     * 
     */
    @TableField(value = "MB474")
    private String MB474;

    /**
     * 
     */
    @TableField(value = "MB475")
    private String MB475;

    /**
     * 
     */
    @TableField(value = "MBL01")
    private Integer MBL01;

    /**
     * 
     */
    @TableField(value = "MBL02")
    private String MBL02;

    /**
     * 
     */
    @TableField(value = "MBL03")
    private String MBL03;

    /**
     * 
     */
    @TableField(value = "MBL04")
    private String MBL04;

    /**
     * 
     */
    @TableField(value = "MB476")
    private Integer MB476;

    /**
     * 
     */
    @TableField(value = "MB477")
    private String MB477;

    /**
     * 
     */
    @TableField(value = "MB478")
    private String MB478;

    /**
     * 
     */
    @TableField(value = "MB479")
    private String MB479;

    /**
     * 
     */
    @TableField(value = "MB480")
    private String MB480;

    /**
     * 
     */
    @TableField(value = "UDF01")
    private String UDF01;

    /**
     * 
     */
    @TableField(value = "UDF02")
    private String UDF02;

    /**
     * 
     */
    @TableField(value = "UDF03")
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
    @TableField(value = "UDF06")
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
    @TableField(value = "UDF08")
    private String UDF08;

    /**
     * 
     */
    @TableField(value = "UDF09")
    private String UDF09;

    /**
     * 
     */
    @TableField(value = "UDF10")
    private String UDF10;

    /**
     * 
     */
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