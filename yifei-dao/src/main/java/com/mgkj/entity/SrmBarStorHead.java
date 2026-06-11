package com.mgkj.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 条码仓库库位单头档

 * @TableName srm_bar_stor_head
 */
@Data
public class SrmBarStorHead {
    /**
     * 仓库编号

     */
    private String warehouseNo;

    /**
     * id
     */
    private String id;

    /**
     * 仓库名称/说明
     */
    private String warehouseName;

    /**
     * 允许条码负库存
     */
    private String allowNegStor;

    /**
     * 先进先出管控

     */
    private String fifoControl;

    /**
     * 
     */
    private String createOffice;

    /**
     * 资料创建日

     */
    private Date createDate;

    /**
     * 企业代码

     */
    private String ent;

    /**
     * 资料建立者

     */
    private String createBy;

    /**
     * 最近修改日

     */
    private Date updateDate;

    /**
     * 资料修改者

     */
    private String updateBy;

    /**
     * 
     */
    private String deptName;

    /**
     * 资料所有者

     */
    private String owner;

    /**
     * 营运据点

     */
    private String site;

    /**
     * 状态码

     */
    private String status;

    /**
     * 是否存在库存表中
     */
    private String isExistStore;

    /**
     * 备注

     */
    private String remarks;

    /**
     * 拓展
     */
    private String extension;

    /**
     * 删除标记(0：正常；1：删除 )

     */
    private String delFlag;

    /**
     * 仓库性质
     */
    private String warehouseType;

    /**
     * 标准预留栏位01
     */
    private String udf01;

    /**
     * 标准预留栏位02
     */
    private String udf02;

    /**
     * 标准预留栏位03
     */
    private String udf03;

    /**
     * 标准预留栏位04
     */
    private String udf04;

    /**
     * 标准预留栏位05
     */
    private String udf05;

    /**
     * 标准预留栏位06
     */
    private String udf06;

    /**
     * 标准预留栏位07
     */
    private BigDecimal udf07;

    /**
     * 标准预留栏位08
     */
    private BigDecimal udf08;

    /**
     * 标准预留栏位09
     */
    private BigDecimal udf09;

    /**
     * 标准预留栏位10
     */
    private BigDecimal udf10;

    /**
     * 标准预留栏位11
     */
    private BigDecimal udf11;

    /**
     * 标准预留栏位12
     */
    private BigDecimal udf12;

    /**
     * 标准预留栏位13
     */
    private Date udf13;

    /**
     * 标准预留栏位14
     */
    private Date udf14;

    /**
     * 标准预留栏位15
     */
    private Date udf15;

    /**
     * 标准预留栏位16
     */
    private Date udf16;

    /**
     * 标准预留栏位17
     */
    private Date udf17;

    /**
     * 标准预留栏位18
     */
    private Date udf18;

    /**
     * 个案预留栏位01
     */
    private String xudf01;

    /**
     * 个案预留栏位02
     */
    private String xudf02;

    /**
     * 个案预留栏位03
     */
    private String xudf03;

    /**
     * 个案预留栏位04
     */
    private String xudf04;

    /**
     * 个案预留栏位05
     */
    private String xudf05;

    /**
     * 个案预留栏位06
     */
    private String xudf06;

    /**
     * 个案预留栏位07
     */
    private BigDecimal xudf07;

    /**
     * 个案预留栏位08
     */
    private BigDecimal xudf08;

    /**
     * 个案预留栏位09
     */
    private BigDecimal xudf09;

    /**
     * 个案预留栏位10
     */
    private BigDecimal xudf10;

    /**
     * 个案预留栏位11
     */
    private BigDecimal xudf11;

    /**
     * 个案预留栏位12
     */
    private BigDecimal xudf12;

    /**
     * 个案预留栏位13
     */
    private Date xudf13;

    /**
     * 个案预留栏位14
     */
    private Date xudf14;

    /**
     * 个案预留栏位15
     */
    private Date xudf15;

    /**
     * 个案预留栏位16
     */
    private Date xudf16;

    /**
     * 个案预留栏位17
     */
    private Date xudf17;

    /**
     * 个案预留栏位18
     */
    private Date xudf18;
}