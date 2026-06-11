package com.mgkj.dto;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.mgkj.entity.PurchaseArrivalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QcInspectionOrder {

    /**
     * 单号
     */
    private String DOC_NO;

    /**
     * 检验次数
     */
    private Integer QC_TIMES;

    /**
     * 组织类型.工厂/储运.工厂/储运编号
     */
    private String Owner_Org_PLANT_PLANT_CODE;

    /**
     * 检验日期
     */
    private LocalDate INSPECTION_DATE;

    /**
     * 源单.序号
     */
    private Integer SOURCE_ID_SequenceNumber;

    /**
     * 源单.到货单.单号
     */
    private String SOURCE_ID_Parent_DOC_NO;

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
     * 单据日期
     */
    private LocalDateTime DOC_DATE;

    /**
     * 单据类型.单据类型
     */
    private String DOC_ID_DOC_CODE;

    /**
     * 单据性质
     */
    private String CATEGORY;

    /**
     * 备注
     */
    private String REMARK;

    /**
     * 单据类型.组织类型.工厂/储运.工厂/储运编号
     */
    private String DOC_ID_Owner_Org_PLANT_PLANT_CODE;

    /**
     * 合格数量
     */
    private BigDecimal ACCEPTABLE_QTY;

    /**
     * 判定结果
     */
    private String DECISION;

    /**
     * 判定说明
     */
    private String DECISION_DESCRIPTION;

    /**
     * 扣已到货量
     */
    private boolean DEDUCT_ARRIVED_QTY;

    /**
     * 破坏数量
     */
    private BigDecimal DESTROYED_QTY;

    /**
     * 最大抽样次数
     */
    private Integer INSPECTION_TIMES;

    /**
     * 品管单位.单位编号
     */
    private String INSPECTION_UNIT_ID_UNIT_CODE;

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
     * 规格
     */
    private String ITEM_SPECIFICATION;

    /**
     * 业务判定状态
     */
    private String RESULT_STATUS;

    /**
     * 供应商.供应商编号
     */
    private String SUPPLIER_ID_SUPPLIER_CODE;

    /**
     * 自定义字段6
     */
    private String UDF021;

    /**
     * 送检数量
     */
    private BigDecimal INSPECTION_QTY;

    /**
     * 质检方案ID.品管类别编号.品管类别编号
     */
    private String INSPECTION_PLAN_ID_QC_CATEGORY_ID_QC_CATEGORY_CODE;

    /**
     * 质检方案ID.组织类型.工厂/储运.工厂/储运编号
     */
    private String INSPECTION_PLAN_ID_Owner_Org_PLANT_PLANT_CODE;

    /**
     * 质检方案ID.品号特征码.特征码
     */
    private String INSPECTION_PLAN_ID_ITEM_FEATURE_ID_ITEM_FEATURE_CODE;

    /**
     * 质检方案ID.品号特征码.通用品号信息.品号
     */
    private String INSPECTION_PLAN_ID_ITEM_FEATURE_ID_Parent_ITEM_CODE;

    /**
     * 质检方案ID.工艺.工艺编号
     */
    private String INSPECTION_PLAN_ID_OPERATION_ID_OPERATION_CODE;

    /**
     * 质检方案ID.品号.品号
     */
    private String INSPECTION_PLAN_ID_ITEM_ID_ITEM_CODE;

    /**
     * 送检部门.行政单元编码
     */
    private String SUBMIT_DEPT_ID_ADMIN_UNIT_CODE;

    /**
     * 送检部门.生效日期
     */
    private LocalDate SUBMIT_DEPT_ID_START_DATE;

    /**
     * 送检人员.员工编号
     */
    private String SUBMIT_EMP_ID_EMPLOYEE_CODE;

    /**
     * 检验期限
     */
    private LocalDate INSPECTION_DUE_DATE;

    /**
     * 公司.公司编号
     */
    private String COMPANY_ID_COMPANY_CODE;

    public static String toJson(QcInspectionOrder stdData) {
        Map<String, Object> root = new HashMap<>();

        Map<String, Object> parameter = new HashMap<>();
        List<QcInspectionOrder> datas = new ArrayList<>();
        datas.add(stdData);
        parameter.put("datas", datas);

        root.put("std_data", Collections.singletonMap("parameter", parameter));

        JSONConfig jsonConfig = JSONConfig.create();
        jsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return JSONUtil.toJsonStr(root, jsonConfig);
    }
}