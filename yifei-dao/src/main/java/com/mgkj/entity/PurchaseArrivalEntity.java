package com.mgkj.entity;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购到货单标准数据结构
 */
@Data
public class PurchaseArrivalEntity {

    /**
     * 最外层结构，对应 JSON 中的 std_data
     */
    @Data
    public static class StdData {
        private Parameter parameter;
    }

    /**
     * parameter 节点
     */
    @Data
    public static class Parameter {
        private List<PurchaseArrival> datas;
    }

    /**
     * 采购到货单主表
     */
    @Data
    public static class PurchaseArrival {
        /**
         * 单号
         */
        private String DOC_NO;
        /**
         * 付款条件.类别
         */
        private String PAYMENT_TERM_ID_PAYMENT_TERM_TYPE;
        /**
         * 付款条件.编号
         */
        private String PAYMENT_TERM_ID_PAYMENT_TERM_CODE;
        /**
         * 收货工厂/储运.工厂/储运编号
         */
        private String PLANT_ID_PLANT_CODE;
        /**
         * 供应商.供应商编号
         */
        private String SUPPLIER_ID_SUPPLIER_CODE;
        /**
         * 本币税额
         */
        private BigDecimal TAX_BC;
        /**
         * 税种.税种编号
         */
        private String TAX_ID_TAX_CODE;
        /**
         * 含税
         */
        private Boolean TAX_INCLUDED;
        /**
         * 原币税额
         */
        private BigDecimal TAX_OC;
        /**
         * 组织类型.采购域.采购域编号
         */
        private String Owner_Org_SUPPLY_CENTER_SUPPLY_CENTER_CODE;
        /**
         * 收货机构.工厂/储运.工厂/储运编号
         */
        private String RECEIVE_Owner_Org_PLANT_PLANT_CODE;
        /**
         * 单据类型.单据类型
         */
        private String DOC_ID_DOC_CODE;
        /**
         * 单据类型.组织类型.采购域.采购域编号
         */
        private String DOC_ID_Owner_Org_SUPPLY_CENTER_SUPPLY_CENTER_CODE;
        /**
         * 单据日期
         */
        private LocalDateTime DOC_DATE;
        /**
         * 币种.货币编号
         */
        private String CURRENCY_ID_CURRENCY_CODE;
        /**
         * 结算公司.公司编号
         */
        private String INVOICE_COMPANY_ID_COMPANY_CODE;
        /**
         * 结算供应商.供应商编号
         */
        private String INVOICE_SUPPLIER_ID_SUPPLIER_CODE;
        /**
         * 供应商全称
         */
        private String SUPPLIER_FULL_NAME;
        /**
         * 汇率
         */
        private BigDecimal EXCHANGE_RATE;

        /* ========== 源单相关字段 ========== */
        /**
         * 源单.内部订单.序号
         */
        private String SOURCE_ID_INNER_ORDER_DOC_INNER_ORDER_DOC_D_INNER_ORDER_DOC_SD_SequenceNumber;
        /**
         * 源单.内部订单.内部订单单身.序号
         */
        private String SOURCE_ID_INNER_ORDER_DOC_INNER_ORDER_DOC_D_INNER_ORDER_DOC_SD_Parent_SequenceNumber;
        /**
         * 源单.内部订单.内部订单单身.内部订单.单号
         */
        private String SOURCE_ID_INNER_ORDER_DOC_INNER_ORDER_DOC_D_INNER_ORDER_DOC_SD_Parent_Parent_DOC_NO;
        /**
         * 源单.委外工单.单号
         */
        private String SOURCE_ID_MO_DOC_NO;
        /**
         * 源单.委外供料.序号
         */
        private String SOURCE_ID_MO_MO_D_SequenceNumber;
        /**
         * 源单.委外供料.工单.单号
         */
        private String SOURCE_ID_MO_MO_D_Parent_DOC_NO;
        /**
         * 源单.工单工艺.序号
         */
        private String SOURCE_ID_MO_ROUTING_MO_ROUTING_D_SequenceNumber;
        /**
         * 源单.工单工艺.工单工艺信息.工单.单号
         */
        private String SOURCE_ID_MO_ROUTING_MO_ROUTING_D_Parent_MO_ID_DOC_NO;
        /**
         * 源单.采购入库单.序号
         */
        private String SOURCE_ID_PURCHASE_RECEIPT_PURCHASE_RECEIPT_D_SequenceNumber;
        /**
         * 源单.采购入库单.采购入库单.单号
         */
        private String SOURCE_ID_PURCHASE_RECEIPT_PURCHASE_RECEIPT_D_Parent_DOC_NO;
        /**
         * 源单.销货出库单.序号
         */
        private String SOURCE_ID_SALES_ISSUE_SALES_ISSUE_D_SequenceNumber;
        /**
         * 源单.销货出库单.销货出库单.单号
         */
        private String SOURCE_ID_SALES_ISSUE_SALES_ISSUE_D_Parent_DOC_NO;
        /**
         * 源单.备货计划.序号
         */
        private String SOURCE_ID_STOCKING_PLAN_STOCKING_PLAN_D_SequenceNumber;
        /**
         * 源单.备货计划.备货计划.备货计划编号
         */
        private String SOURCE_ID_STOCKING_PLAN_STOCKING_PLAN_D_Parent_STOCKING_PLAN_NO;

        /**
         * 到货单明细
         */
        private List<PurchaseArrivalD> PURCHASE_ARRIVAL_D;

        /**
         * 备注
         */
        private String REMARK;

        /**
         * 单据性质
         */
        private String CATEGORY;

        /**
         * 关联部门.行政单元编码
         */
        private String Owner_Dept_ADMIN_UNIT_CODE;

        /**
         * 关联部门.生效日期
         */
        private String Owner_Dept_START_DATE;

        /**
         * 关联员工.员工编号
         */
        private String Owner_Emp_EMPLOYEE_CODE;

        /**
         * 可抵扣VAT标识
         */
        private boolean DEDUCTIBLE_INDICATOR;

        /**
         * 发票种类.税务发票种类编号
         */
        private String TAX_INVOICE_CATEGORY_ID_TAX_INVOICE_CATEGORY_CODE;
    }

    /**
     * 采购到货单明细
     */
    @Data
    public static class PurchaseArrivalD {
        /**
         * 序号
         */
        private Integer SequenceNumber;
        /**
         * 单据状态属性
         */
        private String ApproveStatus;
        /**
         * 业务数量
         */
        private BigDecimal BUSINESS_QTY;
        /**
         * 业务单位.单位编号
         */
        private String BUSINESS_UNIT_ID_UNIT_CODE;
        /**
         * 折扣后单价
         */
        private BigDecimal DISCOUNTED_PRICE;
        /**
         * 单据来源
         */
        private String GENERATE_SOURCE;
        /**
         * 检验状态
         */
        private String INSPECTION_STATUS;
        /**
         * 库存单位数量
         */
        private BigDecimal INVENTORY_QTY;
        /**
         * 品名
         */
        private String ITEM_DESCRIPTION;
        /**
         * 品号.品号
         */
        private String ITEM_ID_ITEM_CODE;
        /**
         * 批号.品号.品号
         */
        private String ITEM_LOT_ID_ITEM_ID_ITEM_CODE;
        /**
         * 批号.特征码.特征码
         */
        private String ITEM_LOT_ID_ITEM_FEATURE_ID_ITEM_FEATURE_CODE;
        /**
         * 批号.特征码.通用品号信息.品号
         */
        private String ITEM_LOT_ID_ITEM_FEATURE_ID_Parent_ITEM_CODE;
        /**
         * 批号.批号
         */
        private String ITEM_LOT_ID_LOT_CODE;
        /**
         * 规格
         */
        private String ITEM_SPECIFICATION;
        /**
         * 商品类型
         */
        private String ITEM_TYPE;
        /**
         * 单价
         */
        private BigDecimal PRICE;
        /**
         * 计价单位.单位编号
         */
        private String PRICE_UNIT_ID_UNIT_CODE;
        /**
         * 采购订单ID.单号
         */
        private String PURCHASE_ORDER_ID_DOC_NO;
        /**
         * 采购类型
         */
        private String PURCHASE_TYPE;
        /**
         * 标准单价
         */
        private BigDecimal STANDARD_PRICE;
        /**
         * 税种.税种编号
         */
        private String TAX_ID_TAX_CODE;
        /**
         * 税率
         */
        private BigDecimal TAX_RATE;
        /**
         * 入库仓库.仓库编号
         */
        private String WAREHOUSE_ID_WAREHOUSE_CODE;
        /**
         * 入库仓库.组织类型.工厂/储运.工厂/储运编号
         */
        private String WAREHOUSE_ID_Owner_Org_PLANT_PLANT_CODE;
        /**
         * 计价数量
         */
        private BigDecimal PRICE_QTY;

        /* ========== 源单相关字段 ========== */
        /**
         * 源单.采购订单.序号
         */
        private Integer SOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_SequenceNumber;
        /**
         * 源单.采购订单.采购明细.序号
         */
        private Integer SOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_Parent_SequenceNumber;
        /**
         * 源单.采购订单.采购明细.采购订单.单号
         */
        private String SOURCE_ID_PURCHASE_ORDER_PURCHASE_ORDER_D_PURCHASE_ORDER_SD_Parent_Parent_DOC_NO;

        /**
         * 源单.委外工单.单号
         */
        private String REFERENCE_SOURCE_ID_MO_DOC_NO;
        private String REFERENCE_SOURCE_ID_RTK;
        private String MO_ID_DOC_NO;
    }

    public static String toJson(StdData stdData) {
        Map<String, StdData> root = new HashMap<>();
        root.put("std_data", stdData);

        /* 5. 输出 */
        JSONConfig jsonConfig = JSONConfig.create();
        jsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return JSONUtil.toJsonStr(root, jsonConfig);
    }
}