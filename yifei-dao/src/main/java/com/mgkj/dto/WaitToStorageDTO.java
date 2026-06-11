package com.mgkj.dto;

import com.annotation.SensitiveField;
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
@ApiModel(description = "检验合格后待入库DTO")
public class WaitToStorageDTO {

    /**
     * @ApiModelProperty(value = "唯一键", example = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
     */
    @ApiModelProperty(value = "唯一键,你不给后端会自己生成一个uuid当主键",required = false)
    private String id;

    /**
     * @ApiModelProperty(value = "采购单单别")
     */
    @ApiModelProperty(value = "采购单单别" ,required = true)
    private String singetsu;

    /**
     * @ApiModelProperty(value = "采购单单号")
     */
    @ApiModelProperty(value = "采购单单号", required = true)
    private String oddNumber;

    @ApiModelProperty(value = "到货单单号", required = true)
    private String arrivalNumber;

    @ApiModelProperty(value = "待入库物料", required = true)
    private String itemName;

    /**
     * @ApiModelProperty(value = "状态 (1=>待采购方确认, 2=>采购方已确认交期, 3=>采购方已拒绝交期)")
     */
    @ApiModelProperty(value = "状态 (1=>待采购方确认, 2=>采购方已确认交期, 3=>采购方已拒绝交期)", notes = "1=待采购方确认, 2=采购方已确认交期, 3=采购方已拒绝交期",required = true)
    private String status;

    /**
     * @ApiModelProperty(value = "供应商印章")
     */
    @ApiModelProperty(value = "供应商印章",required = false)
    private String supplierSeal;

    /**
     * @ApiModelProperty(value = "采购商印章 (阿波罗自己的印章)")
     */
    @ApiModelProperty(value = "采购商印章 (阿波罗自己的印章)",required = false)
    private String purchaserSeal;

    /**
     * @ApiModelProperty(value = "确认的交期时间")
     */
    @ApiModelProperty(value = "确认的交期时间 日期格式统一YYYYMMDD20241213",required = true)
    private String confirmedDeliveryDate;

    /**
     * @ApiModelProperty(value = "供应商备注")
     */
    @ApiModelProperty(value = "供应商备注",required = true)
    private String supplierRemark;

    /**
     * @ApiModelProperty(value = "采购商备注")
     */
    @ApiModelProperty(value = "采购商备注",required = false)
    private String purchaserRemark;

    /**
     * @ApiModelProperty(value = "供应商名称")
     */
    @ApiModelProperty(value = "供应商名称",required = true)
    @SensitiveField(fieldName = "供应商名称", desensitizedValue = "*****")
    private String supplierName;

    /**
     * @ApiModelProperty(value = "采购行号")
     */
    @ApiModelProperty(value = "采购行号",required = true)
    private String purchasingBankNumber;

    /**
     * @ApiModelProperty(value = "单据日期")
     */
    @ApiModelProperty(value = "单据日期 日期格式统一YYYYMMDD20241213",required = true)
    private String documentDate;

    @ApiModelProperty(value = "预交货日期",required = true)
    private String deliveryDate;

    /**
     * @ApiModelProperty(value = "产品品号")
     */
    @ApiModelProperty(value = "产品品号",required = true)
    private String articleNumber;

    /**
     * @ApiModelProperty(value = "产品品名")
     */
    @ApiModelProperty(value = "产品品名",required = true)
    private String articleName;

    /**
     * @ApiModelProperty(value = "产品规格")
     */
    @ApiModelProperty(value = "产品规格",required = true)
    private String articleSpecification;

    /**
     * @ApiModelProperty(value = "单位")
     */
    @ApiModelProperty(value = "单位",required = false)
    private String unit;

    /**
     * @ApiModelProperty(value = "订单数量")
     */
    @ApiModelProperty(value = "订单数量",required = true)
    private String orderQuantity;

    @ApiModelProperty(value = "采购员名称",required = true)
    private String cgname;

    @ApiModelProperty(value = "审核日期",required = true)
    private String approveDate;

}
