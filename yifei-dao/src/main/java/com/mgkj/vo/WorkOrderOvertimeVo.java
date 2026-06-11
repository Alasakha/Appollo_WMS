package com.mgkj.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-10
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("工单超时 Vo")
public class WorkOrderOvertimeVo implements Serializable {

    //工单逾期数量
    private String overdueOrderNumber;

    //单别
    private String single;

    //单号
    private String singleNumber;

    //序号
    private String workSerialNumber;

    //单据日期
    private String singleDate;

    //部门编号
    private String departmentNum;

    //部门名称
    private String departmentName;

    //品号
    private String orderNum;

    //品名
    private String orderName;

    //规格
    private String specification;

    //单位
    private String unit;

    //预计产量
    private String expectedOutput;

    //预计交货日
    private String preDeliveryDate;

    //预计完工
    private String expectedCompletion;

    //币种
    private String currency;

    //币种名称
    private String currencyName;

    //未收金额 TODO 数据库未确定字段
    private String uncollectedMoney;

    //预收款日期 TODO 数据库没有此字段
    private String preSaleDate;
}
