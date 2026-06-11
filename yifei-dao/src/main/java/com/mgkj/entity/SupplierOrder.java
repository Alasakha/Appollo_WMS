package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ApiModel(description = "供应商订单实体类")
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@TableName("supplierOrder")
@ApiModel(description = "供应商订单实体类")
public class SupplierOrder {

    /**
     * @ApiModelProperty(value = "唯一键", example = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
     */
    @TableId
    @TableField("id")
    @ApiModelProperty(value = "唯一键,你不给后端会自己生成一个uuid当主键")
    private String id;



    /**
     * @ApiModelProperty(value = "采购单单别")
     */
    @TableField("singetsu")
    @ApiModelProperty(value = "采购单单别")
    private String singetsu;

    /**
     * @ApiModelProperty(value = "采购单单号")
     */
    @TableField("oddNumber")
    @ApiModelProperty(value = "采购单单号")
    private String oddNumber;

    /**
     * @ApiModelProperty(value = "状态 (1=>待采购方确认, 2=>采购方已确认交期, 3=>采购方已拒绝交期)")
     */
    @TableField("status")
    @ApiModelProperty(value = "状态 (1=>待采购方确认, 2=>采购方已确认交期, 3=>采购方已拒绝交期)", notes = "1=待采购方确认, 2=采购方已确认交期, 3=采购方已拒绝交期")
    private String status;

    /**
     * @ApiModelProperty(value = "供应商印章")
     */
    @TableField("supplierSeal")
    @ApiModelProperty(value = "供应商印章")
    private String supplierSeal;

    /**
     * @ApiModelProperty(value = "采购商印章 (阿波罗自己的印章)")
     */
    @TableField("purchaserSeal")
    @ApiModelProperty(value = "采购商印章 (阿波罗自己的印章)")
    private String purchaserSeal;

    /**
     * @ApiModelProperty(value = "确认的交期时间")
     */
    @TableField("confirmedDeliveryDate")
    @ApiModelProperty(value = "确认的交期时间")
    private String confirmedDeliveryDate;

    /**
     * @ApiModelProperty(value = "供应商备注")
     */
    @TableField("supplierRemark")
    @ApiModelProperty(value = "供应商备注")
    private String supplierRemark;

    /**
     * @ApiModelProperty(value = "采购商备注")
     */
    @TableField("purchaserRemark")
    @ApiModelProperty(value = "采购商备注")
    private String purchaserRemark;

    /**
     * @ApiModelProperty(value = "供应商名称")
     */
    @TableField("supplierName")
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * @ApiModelProperty(value = "采购行号")
     */
    @TableField("purchasingBankNumber")
    @ApiModelProperty(value = "采购行号")
    private String purchasingBankNumber;

    /**
     * @ApiModelProperty(value = "单据日期")
     */
    @TableField("documentDate")
    @ApiModelProperty(value = "单据日期")
    private String documentDate;

    @TableField("deliveryDate")
    @ApiModelProperty(value = "预交货日")
    private String deliveryDate;

    /**
     * @ApiModelProperty(value = "产品品号")
     */
    @TableField("articleNumber")
    @ApiModelProperty(value = "产品品号")
    private String articleNumber;

    /**
     * @ApiModelProperty(value = "产品品名")
     */
    @TableField("articleName")
    @ApiModelProperty(value = "产品品名")
    private String articleName;

    /**
     * @ApiModelProperty(value = "产品规格")
     */
    @TableField("articleSpecification")
    @ApiModelProperty(value = "产品规格")
    private String articleSpecification;

    /**
     * @ApiModelProperty(value = "单位")
     */
    @TableField("unit")
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * @ApiModelProperty(value = "订单数量")
     */
    @TableField("orderQuantity")
    @ApiModelProperty(value = "订单数量")
    private String orderQuantity;


    @TableField("supplierNo")
    @ApiModelProperty(value = "供应商的编号")
    private String supplierNo;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private String createTime;

    @ApiModelProperty("打印次数")
    @TableField(exist = false)
    private Integer frequency;

    @ApiModelProperty("附件信息 有就是附件数量 没有就是0 前端判断数量是否存在附件")
    @TableField(exist = false)
    private Integer fj;

    @ApiModelProperty("附件信息 有就是附件数量 没有就是0 前端判断数量是否存在附件")
    @TableField(exist = false)
    private Integer e10fj;

    @ApiModelProperty(value = "客户单号")
    @TableField(exist = false)
    private String khdh;

    @ApiModelProperty(value = "采购员")
    @TableField(exist = false)
    private String empname;
}
