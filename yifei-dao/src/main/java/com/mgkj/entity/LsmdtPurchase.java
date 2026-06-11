package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yyyjcg
 * @date 2024/3/4
 * @Description
 */
@TableName(value="tb_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="采购入库中间表")
public class LsmdtPurchase {
    @ApiModelProperty(value = "主键")
    @TableId(value = "LSMD_T_ID")
    private String lsmdTId;

    @ApiModelProperty(value = "条码编号")
    private String lsmd001;

    @ApiModelProperty(value = "仓库")
    private String lsmd002;

    @ApiModelProperty(value = "库位")
    private String lsmd003;

    @ApiModelProperty(value = "批号")
    private String lsmd004;

    @ApiModelProperty(value = "异动数量")
    private Double lsmd005;

    @ApiModelProperty(value = "异动类型")
    private Integer lsmd006;

    @ApiModelProperty(value = "来源单号")
    private String lsmd007;

//    @ApiModelProperty(value = "来源项次")
//    private Integer lsmd008;
//
//    @ApiModelProperty(value = "目的单号")
//    private String lsmd009;
//
//    @ApiModelProperty(value = "目的项次")
//    private Integer lsmd010;


    @ApiModelProperty(value = "PDA操作人代号")
    private String lsmd013;

    @ApiModelProperty(value = "扫描日期")
    private String lsmd014;

    @ApiModelProperty(value = "时间（时:分:秒.毫秒）")
    private String lsmd015;

    @ApiModelProperty(value = "扣账资料否")
    private String lsmd019;

    @ApiModelProperty(value = "异动数量单位")
    private String lsmd024;

    @ApiModelProperty(value = "异动与库存单位换算率")
    private Double lsmd025;

    @ApiModelProperty(value = "终端设备编号")
    private String lsmd026;

    @ApiModelProperty(value = "APP申请串号")
    private String lsmd027;

    @ApiModelProperty(value = "APP申请所属模组")
    private String lsmd028;

    @ApiModelProperty(value = "ERP异动码")
    private String lsmd029;

    @ApiModelProperty(value = "备注")
    private String lsmd030;

    @ApiModelProperty(value = "条码异动类型")
    private String lsmd031;

    @ApiModelProperty(value = "APP申请序列号")
    private String lsmd033;

    private String lsmd039;

    private String lsmd042;

    private String lsmd046;

    private String lsmd047;

    private Double lsmd053;

    private Double lsmd055;

    private String lsmd058;

    @ApiModelProperty(value = "物料代码")
    private String lsmd083;

    @ApiModelProperty(value = "字符保留栏位")
    private String lsmd084;

    @ApiModelProperty(value = "过账日期")
    private String lsmd085;

    @ApiModelProperty(value = "日期保留栏位")
    private String lsmd086;

    @ApiModelProperty(value = "资料创建日")
    private String lsmdcrtdt;

    @ApiModelProperty(value = "资料建立者")
    private String lsmdcrtid;

    @ApiModelProperty(value = "企业代码")
    private String lsmdent;

    @ApiModelProperty(value = "最近修改日")
    private String lsmdmoddt;

    @ApiModelProperty(value = "资料修改者")
    private String lsmdmodid;

    @ApiModelProperty(value = "资料所属部门")
    private String lsmdowndp;

    @ApiModelProperty(value = "资料所有者")
    private String lsmdownid;

    @ApiModelProperty(value = "营运据点")
    private String lsmdsite;

    @ApiModelProperty(value = "状态码")
    private String lsmdstus;




//    @ApiModelProperty(value = "自定义栏位(文字)001")
//    private String lsmdud001;
//
//    @ApiModelProperty(value = "自定义栏位(文字)002")
//    private String lsmdud002;
//
//    @ApiModelProperty(value = "自定义栏位(文字)003")
//    private String lsmdud003;
//
//    @ApiModelProperty(value = "自定义栏位(文字)004")
//    private String lsmdud004;
//
//    @ApiModelProperty(value = "自定义栏位(文字)005")
//    private String lsmdud005;
//
//    @ApiModelProperty(value = "自定义栏位(文字)006")
//    private String lsmdud006;
//
//    @ApiModelProperty(value = "自定义栏位(文字)007")
//    private String lsmdud007;
//
//    @ApiModelProperty(value = "自定义栏位(文字)008")
//    private String lsmdud008;
//
//    @ApiModelProperty(value = "自定义栏位(文字)009")
//    private String lsmdud009;
//
//    @ApiModelProperty(value = "自定义栏位(文字)010")
//    private String lsmdud010;
//
//    @ApiModelProperty(value = "自定义栏位(数字)011")
//    private Double lsmdud011;
//
//    @ApiModelProperty(value = "自定义栏位(数字)012")
//    private Double lsmdud012;
//
//    @ApiModelProperty(value = "自定义栏位(数字)013")
//    private Double lsmdud013;
//
//    @ApiModelProperty(value = "自定义栏位(数字)014")
//    private Double lsmdud014;
//
//    @ApiModelProperty(value = "自定义栏位(数字)015")
//    private Double lsmdud015;
//
//    @ApiModelProperty(value = "自定义栏位(数字)016")
//    private Double lsmdud016;
//
//    @ApiModelProperty(value = "自定义栏位(数字)017")
//    private Double lsmdud017;
//
//    @ApiModelProperty(value = "自定义栏位(数字)018")
//    private Double lsmdud018;
//
//    @ApiModelProperty(value = "自定义栏位(数字)019")
//    private Double lsmdud019;
//
//    @ApiModelProperty(value = "自定义栏位(数字)020")
//    private Double lsmdud020;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)021")
//    private LocalDateTime lsmdud021;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)022")
//    private LocalDateTime lsmdud022;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)023")
//    private LocalDateTime lsmdud023;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)024")
//    private LocalDateTime lsmdud024;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)025")
//    private LocalDateTime lsmdud025;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)026")
//    private LocalDateTime lsmdud026;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)027")
//    private LocalDateTime lsmdud027;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)028")
//    private LocalDateTime lsmdud028;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)029")
//    private LocalDateTime lsmdud029;
//
//    @ApiModelProperty(value = "自定义栏位(日期时间)030")
//    private LocalDateTime lsmdud030;
//
//    @ApiModelProperty(value = "创建日期")
//    @TableField("CreateDate")
//    private LocalDateTime createdate;
//
//    @ApiModelProperty(value = "最后修改日期")
//    @TableField("LastModifiedDate")
//    private LocalDateTime lastmodifieddate;
//
//    @ApiModelProperty(value = "修改日期")
//    @TableField("ModifiedDate")
//    private LocalDateTime modifieddate;
//
//    @ApiModelProperty(value = "创建者")
//    @TableField("CreateBy")
//    private String createby;
//
//    @ApiModelProperty(value = "最后修改者")
//    @TableField("LastModifiedBy")
//    private String lastmodifiedby;
//
//    @ApiModelProperty(value = "修改者")
//    @TableField("ModifiedBy")
//    private String modifiedby;
//
//    @ApiModelProperty(value = "附件")
//    @TableField("Attachments")
//    private String attachments;
//
//    @ApiModelProperty(value = "自定义字段0")
//    private Double udf001;
//
//    @ApiModelProperty(value = "自定义字段1")
//    private Double udf002;
//
//    @ApiModelProperty(value = "自定义字段2")
//    private Double udf003;
//
//    @ApiModelProperty(value = "自定义字段3")
//    private Double udf011;
//
//    @ApiModelProperty(value = "自定义字段4")
//    private Double udf012;
//
//    @ApiModelProperty(value = "自定义字段5")
//    private Double udf013;
//
//    @ApiModelProperty(value = "自定义字段12")
//    private LocalDateTime udf041;
//
//    @ApiModelProperty(value = "自定义字段13")
//    private LocalDateTime udf042;
//
//    @ApiModelProperty(value = "自定义字段14")
//    private String udf051;
//
//    @ApiModelProperty(value = "自定义字段15")
//    private String udf052;
//
//    @ApiModelProperty(value = "自定义字段16")
//    private String udf053;
//
//    @ApiModelProperty(value = "自定义字段17")
//    private String udf054;
//
//    @ApiModelProperty(value = "表单所在的流程实例的编号")
//    @TableField("ProcessInstanceId")
//    private String processinstanceid;
//
//    @ApiModelProperty(value = "版本号，不要随意更改")
//    @TableField("Version")
//    private LocalDateTime version;
//
//    @ApiModelProperty(value = "单据状态属性")
//    @TableField("ApproveStatus")
//    private String approvestatus;
//
//    @ApiModelProperty(value = "修改日期")
//    @TableField("ApproveDate")
//    private LocalDateTime approvedate;
//
//    @ApiModelProperty(value = "修改人")
//    @TableField("ApproveBy")
//    private String approveby;
}
