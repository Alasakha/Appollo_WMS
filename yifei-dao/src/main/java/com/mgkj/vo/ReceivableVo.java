package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-16
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应收未收订单 Vo")
public class ReceivableVo implements Serializable {

    //单别
    private String single;

    //单号
    private String singleNumber;

    //单据日期
    private String documentDate;

    //客户编号
    private String customerNo;

    //客户名称
    private String customerName;

    //币种
    private String currency;

    //币种名称
    private String currencyName;

    //未收账款
    private String unAccount;

    //预收款日
    private String advancePay;
}
