package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * @TableName FinishedGoodsInboundDetail
 */
@TableName(value ="FinishedGoodsInboundDetail")
@Data
public class FinishedGoodsInboundDetail implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 条码
     */
    @TableField(value = "barcode")
    private String barcode;

    /**
     * 仓库编号
     */
    @TableField(value = "warehouseCode")
    private String warehouseCode;

    /**
     * 仓库名称
     */
    @TableField(value = "warehouseName")
    private String warehouseName;

    /**
     * 仓位编号
     */
    @TableField(value = "binCode")
    private String binCode;

    /**
     * 批号
     */
    @TableField(value = "lotNo")
    private String lotNo;

    /**
     * 数量
     */
    @TableField(value = "qty")
    private BigDecimal qty;

    /**
     * 单据编号
     */
    @TableField(value = "docNo")
    private String docNo;

    /**
     * 客户单据编号
     */
    @TableField(value = "customerDocNo")
    private String customerDocNo;

    /**
     * 单位编号
     */
    @TableField(value = "unitCode")
    private String unitCode;

    /**
     * 品号
     */
    @TableField(value = "itemCode")
    private String itemCode;

    /**
     * 品名
     */
    @TableField(value = "itemName")
    private String itemName;

    /**
     * 规格
     */
    @TableField(value = "itemSpec")
    private String itemSpec;

    /**
     * 收货机构(工厂编号)
     */
    @TableField(value = "orgNo")
    private Integer orgNo;

    /**
     * 0:待提交 1:提交成功
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private String createTime;

    /**
     * 创建人
     */
    @TableField(value = "createBy")
    private String createBy;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark = "定时任务";

    /**
     * 出库单编号
     */
    @TableField(value = "issueNo")
    private String issueNo;

    /**
     * 入库单编号
     */
    @TableField(value = "receiptNo")
    private String receiptNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}