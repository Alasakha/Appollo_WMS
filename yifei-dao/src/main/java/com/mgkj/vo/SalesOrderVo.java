package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-17
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "销售订单 Vo")
public class SalesOrderVo implements Serializable {

    //序号
    private String orderSerialNumber;

    //单别
    private String orderSingle;

    //订单日期
    private String orderDate;

    //订单单号
    private String orderNumber;

    //控制单号
    private String controlNumber;

    //客户简称
    private String customerName;

    //客户品号
    private String customerNumber;

    //品号
    private String orderNum;

    //品名
    private String orderName;

    //规格
    private String specification;

    //订单数量
    private String orderQuantity;

    //已交数量
    private String deliveredQuantity;

    //预计交货日期
    private String estimateDeliveryDate;
}
