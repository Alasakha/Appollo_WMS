package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class InvBarcodeOperationVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 单号
     */
    private String docNo;

    /**
     * 仓库编号
     */
    private String warehouseNo;

    /**
     * 库位
     */
    private String cellNo;

    /**
     * 品号
     */
    private String itemNo;

    /**
     * 品名
     */
    private String itemName;

    /**
     * 规格
     */
    private String itemSpec;

    /**
     * 条码
     */
    private String barcode;

    /**
     * 数量
     */
    private BigDecimal qty;
    private BigDecimal matchQty;

    /**
     * 单位编号
     */
    private String unitNo;
    /**
     * 单位编号
     */
    private String unitNmae;

    /**
     * 操作类型 3:销货出库
     */
    private Integer chagType;

    /**
     * 状态 1:已提交 0:未提交
     */
    private Integer statusCode;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 调出仓库
     */
    private String fromWarehouseCode;

    /**
     * 调出库位
     */
    private String fromBinCode;

    /**
     * 调入仓库
     */
    private String toWarehouseCode;

    /**
     * 调入库位
     */
    private String toBinCode;

    /**
     * 人员编号
     */
    private String employeeCode;

    /**
     * 部门编号
     */
    private String departmentCode;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 收货机构
     */
    private String shjg;
    /**
     * 调出收货机构
     */
    private String fromShjg;
    /**
     * 调入收货机构
     */
    private String toShjg;






    private static final long serialVersionUID = 1L;
}