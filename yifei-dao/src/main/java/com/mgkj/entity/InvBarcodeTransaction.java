package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName inv_barcode_transaction
 */
@TableName(value ="inv_barcode_transaction")
@Data
public class InvBarcodeTransaction implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 来源组织
     */
    @TableField(value = "doc_org_no")
    private String docOrgNo;

    /**
     * 组织编码
     */
    @TableField(value = "org_no")
    private String orgNo;

    /**
     * 单据类型
     */
    @TableField(value = "classify_type")
    private String classifyType;

    /**
     * 来源单据类别
     */
    @TableField(value = "doc_type")
    private String docType;

    /**
     * 仓库编码
     */
    @TableField(value = "warehouse_no")
    private String warehouseNo;

    /**
     * 储位编码
     */
    @TableField(value = "cell_no")
    private String cellNo;

    /**
     * 料件编号
     */
    @TableField(value = "item_no")
    private String itemNo;

    /**
     * 源单对应料号
     */
    @TableField(value = "doc_item_no")
    private String docItemNo;

    /**
     * 条码标签
     */
    @TableField(value = "barcode")
    private String barcode;

    /**
     * 条码类型
     */
    @TableField(value = "bar_type")
    private Integer barType;

    /**
     * 交易与库存单位换算率(预留)
     */
    @TableField(value = "stock_rate")
    private BigDecimal stockRate;

    /**
     * 小数位数保留(预留)
     */
    @TableField(value = "stock_keep")
    private BigDecimal stockKeep;

    /**
     * 任务单号
     */
    @TableField(value = "source_no")
    private String sourceNo;

    /**
     * 任务号
     */
    @TableField(value = "doc_no")
    private String docNo;

    /**
     * 任务号项次
     */
    @TableField(value = "doc_seq_no")
    private String docSeqNo;

    /**
     * wms单号
     */
    @TableField(value = "wms_no")
    private String wmsNo;

    /**
     * wms项次号
     */
    @TableField(value = "wms_seq_no")
    private Integer wmsSeqNo;

    /**
     * 交易数量
     */
    @TableField(value = "qty")
    private BigDecimal qty;

    /**
     * 交易单位
     */
    @TableField(value = "unit_no")
    private String unitNo;

    /**
     * 参考单位数量
     */
    @TableField(value = "reference_qty")
    private BigDecimal referenceQty;

    /**
     * 参考单位
     */
    @TableField(value = "reference_unit_no")
    private String referenceUnitNo;

    /**
     * 目的仓库(调拨)
     */
    @TableField(value = "to_warehouse_no")
    private String toWarehouseNo;

    /**
     * 目的储位(调拨)
     */
    @TableField(value = "to_cell_no")
    private String toCellNo;

    /**
     * 出入库类型(0.非出入 1.入库 2.出库)
     */
    @TableField(value = "chag_type")
    private Integer chagType;

    /**
     * 回传标记（0-不回传 1-回传）
     */
    @TableField(value = "doc_return_flag")
    private Integer docReturnFlag;

    /**
     * 状态：10.初始 30.完成
     */
    @TableField(value = "status_code")
    private Integer statusCode;

    /**
     * 设备号
     */
    @TableField(value = "device_no")
    private String deviceNo;

    /**
     * 模组号
     */
    @TableField(value = "module_no")
    private String moduleNo;

    /**
     * 容器号
     */
    @TableField(value = "box_no")
    private String boxNo;

    /**
     * 批号拼接值
     */
    @TableField(value = "combination_lot_no")
    private String combinationLotNo;

    /**
     * WMS批次号
     */
    @TableField(value = "wms_lot_no")
    private String wmsLotNo;

    /**
     * 批次属性1(生产日期)
     */
    @TableField(value = "lot_att01")
    private String lotAtt01;

    /**
     * 批次属性2(供应商)
     */
    @TableField(value = "lot_att02")
    private String lotAtt02;

    /**
     * 批次属性3
     */
    @TableField(value = "lot_att03")
    private String lotAtt03;

    /**
     * 批次属性4
     */
    @TableField(value = "lot_att04")
    private String lotAtt04;

    /**
     * 批次属性5
     */
    @TableField(value = "lot_att05")
    private String lotAtt05;

    /**
     * 批次属性6
     */
    @TableField(value = "lot_att06")
    private String lotAtt06;

    /**
     * 批次属性7
     */
    @TableField(value = "lot_att07")
    private String lotAtt07;

    /**
     * 批次属性8
     */
    @TableField(value = "lot_att08")
    private String lotAtt08;

    /**
     * 批次属性9
     */
    @TableField(value = "lot_att09")
    private String lotAtt09;

    /**
     * 批次属性10
     */
    @TableField(value = "lot_att10")
    private String lotAtt10;

    /**
     * 批次属性11
     */
    @TableField(value = "lot_att11")
    private String lotAtt11;

    /**
     * 批次属性12
     */
    @TableField(value = "lot_att12")
    private String lotAtt12;

    /**
     * 批次属性13
     */
    @TableField(value = "lot_att13")
    private String lotAtt13;

    /**
     * 批次属性14
     */
    @TableField(value = "lot_att14")
    private String lotAtt14;

    /**
     * 批次属性15
     */
    @TableField(value = "lot_att15")
    private String lotAtt15;

    /**
     * 批次属性16
     */
    @TableField(value = "lot_att16")
    private String lotAtt16;

    /**
     * 批次属性17
     */
    @TableField(value = "lot_att17")
    private String lotAtt17;

    /**
     * 批次属性18
     */
    @TableField(value = "lot_att18")
    private String lotAtt18;

    /**
     * 
     */
    @TableField(value = "lot_att19")
    private String lotAtt19;

    /**
     * 
     */
    @TableField(value = "lot_att20")
    private String lotAtt20;

    /**
     * 
     */
    @TableField(value = "lot_att21")
    private String lotAtt21;

    /**
     * 
     */
    @TableField(value = "lot_att22")
    private String lotAtt22;

    /**
     * 
     */
    @TableField(value = "lot_att23")
    private String lotAtt23;

    /**
     * 
     */
    @TableField(value = "lot_att24")
    private String lotAtt24;

    /**
     * 
     */
    @TableField(value = "lot_att25")
    private String lotAtt25;

    /**
     * 
     */
    @TableField(value = "lot_att26")
    private String lotAtt26;

    /**
     * 
     */
    @TableField(value = "lot_att27")
    private String lotAtt27;

    /**
     * 
     */
    @TableField(value = "lot_att28")
    private String lotAtt28;

    /**
     * 
     */
    @TableField(value = "lot_att29")
    private String lotAtt29;

    /**
     * 
     */
    @TableField(value = "lot_att30")
    private String lotAtt30;

    /**
     * 
     */
    @TableField(value = "lot_att31")
    private String lotAtt31;

    /**
     * 
     */
    @TableField(value = "lot_att32")
    private String lotAtt32;

    /**
     * 
     */
    @TableField(value = "lot_att33")
    private String lotAtt33;

    /**
     * 
     */
    @TableField(value = "lot_att34")
    private String lotAtt34;

    /**
     * 
     */
    @TableField(value = "lot_att35")
    private String lotAtt35;

    /**
     * 
     */
    @TableField(value = "lot_att36")
    private String lotAtt36;

    /**
     * 
     */
    @TableField(value = "lot_att37")
    private String lotAtt37;

    /**
     * 
     */
    @TableField(value = "lot_att38")
    private String lotAtt38;

    /**
     * 
     */
    @TableField(value = "lot_att39")
    private String lotAtt39;

    /**
     * 
     */
    @TableField(value = "lot_att40")
    private String lotAtt40;

    /**
     * 
     */
    @TableField(value = "lot_att41")
    private String lotAtt41;

    /**
     * 
     */
    @TableField(value = "lot_att42")
    private String lotAtt42;

    /**
     * 
     */
    @TableField(value = "lot_att43")
    private String lotAtt43;

    /**
     * 
     */
    @TableField(value = "lot_att44")
    private String lotAtt44;

    /**
     * 
     */
    @TableField(value = "lot_att45")
    private String lotAtt45;

    /**
     * 
     */
    @TableField(value = "lot_att46")
    private String lotAtt46;

    /**
     * 
     */
    @TableField(value = "lot_att47")
    private String lotAtt47;

    /**
     * 
     */
    @TableField(value = "lot_att48")
    private String lotAtt48;

    /**
     * 
     */
    @TableField(value = "lot_att49")
    private String lotAtt49;

    /**
     * 
     */
    @TableField(value = "lot_att50")
    private String lotAtt50;

    /**
     * 
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 
     */
    @TableField(value = "pda_op_code")
    private String pdaOpCode;

    /**
     * 
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 
     */
    @TableField(value = "create_name")
    private String createName;

    /**
     * 
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 
     */
    @TableField(value = "update_name")
    private String updateName;

    /**
     * 
     */
    @TableField(value = "update_date")
    private Date updateDate;

    /**
     * 
     */
    @TableField(value = "create_flag")
    private Integer createFlag;

    /**
     * 
     */
    @TableField(value = "standard_col01")
    private String standardCol01;

    /**
     * 
     */
    @TableField(value = "standard_col02")
    private String standardCol02;

    /**
     * 
     */
    @TableField(value = "standard_col03")
    private String standardCol03;

    /**
     * 
     */
    @TableField(value = "standard_col04")
    private String standardCol04;

    /**
     * 
     */
    @TableField(value = "standard_col05")
    private String standardCol05;

    /**
     * 
     */
    @TableField(value = "standard_col06")
    private String standardCol06;

    /**
     * 
     */
    @TableField(value = "standard_col07")
    private String standardCol07;

    /**
     * 
     */
    @TableField(value = "standard_col08")
    private String standardCol08;

    /**
     * 
     */
    @TableField(value = "standard_col09")
    private String standardCol09;

    /**
     * 
     */
    @TableField(value = "standard_col10")
    private String standardCol10;

    /**
     * 
     */
    @TableField(value = "standard_col11")
    private BigDecimal standardCol11;

    /**
     * 
     */
    @TableField(value = "standard_col12")
    private BigDecimal standardCol12;

    /**
     * 
     */
    @TableField(value = "standard_col13")
    private BigDecimal standardCol13;

    /**
     * 
     */
    @TableField(value = "standard_col14")
    private Date standardCol14;

    /**
     * 
     */
    @TableField(value = "standard_col15")
    private Date standardCol15;

    /**
     * 
     */
    @TableField(value = "guest_col01")
    private String guestCol01;

    /**
     * 
     */
    @TableField(value = "guest_col02")
    private String guestCol02;

    /**
     * 
     */
    @TableField(value = "guest_col03")
    private String guestCol03;

    /**
     * 
     */
    @TableField(value = "guest_col04")
    private String guestCol04;

    /**
     * 
     */
    @TableField(value = "guest_col05")
    private String guestCol05;

    /**
     * 
     */
    @TableField(value = "guest_col06")
    private String guestCol06;

    /**
     * 
     */
    @TableField(value = "guest_col07")
    private BigDecimal guestCol07;

    /**
     * 
     */
    @TableField(value = "guest_col08")
    private BigDecimal guestCol08;

    /**
     * 
     */
    @TableField(value = "guest_col09")
    private Date guestCol09;

    /**
     * 
     */
    @TableField(value = "guest_col10")
    private Date guestCol10;

    /**
     * 
     */
    @TableField(value = "doc_wo_no")
    private String docWoNo;

    /**
     * 
     */
    @TableField(value = "org_name")
    private String orgName;

    /**
     * 
     */
    @TableField(value = "org_short_name")
    private String orgShortName;

    /**
     * 
     */
    @TableField(value = "doc_org_name")
    private String docOrgName;

    /**
     * 
     */
    @TableField(value = "doc_org_short_name")
    private String docOrgShortName;

    /**
     * 
     */
    @TableField(value = "classify_type_name")
    private String classifyTypeName;

    /**
     * 
     */
    @TableField(value = "doc_type_name")
    private String docTypeName;

    /**
     * 
     */
    @TableField(value = "warehouse_name")
    private String warehouseName;

    /**
     * 
     */
    @TableField(value = "cell_name")
    private String cellName;

    /**
     * 
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 
     */
    @TableField(value = "item_spec")
    private String itemSpec;

    /**
     * 
     */
    @TableField(value = "unit_name")
    private String unitName;

    /**
     * 
     */
    @TableField(value = "reference_unit_name")
    private String referenceUnitName;

    /**
     * 
     */
    @TableField(value = "to_warehouse_name")
    private String toWarehouseName;

    /**
     * 
     */
    @TableField(value = "to_cell_name")
    private String toCellName;

    /**
     * 
     */
    @TableField(value = "sn_from_warehouse_name")
    private String snFromWarehouseName;

    /**
     * 
     */
    @TableField(value = "sn_from_cell_name")
    private String snFromCellName;

    /**
     * 
     */
    @TableField(value = "sn_to_cell_name")
    private String snToCellName;

    /**
     * 
     */
    @TableField(value = "version")
    private Long version;

    /**
     * 
     */
    @TableField(value = "tenantsid")
    private Long tenantsid;

    /**
     * 
     */
    @TableField(value = "transaction_no")
    private String transactionNo;

    /**
     * 
     */
    @TableField(value = "sn_from_warehouse_no")
    private String snFromWarehouseNo;

    /**
     * 
     */
    @TableField(value = "sn_from_cell_no")
    private String snFromCellNo;

    /**
     * 
     */
    @TableField(value = "sn_from_status")
    private String snFromStatus;

    /**
     * 
     */
    @TableField(value = "sn_to_warehouse_no")
    private String snToWarehouseNo;

    /**
     * 
     */
    @TableField(value = "sn_to_cell_no")
    private String snToCellNo;

    /**
     * 
     */
    @TableField(value = "sn_to_status")
    private String snToStatus;

    /**
     * 
     */
    @TableField(value = "check_data_source")
    private String checkDataSource;

    /**
     * 
     */
    @TableField(value = "cell_outside_no")
    private String cellOutsideNo;

    /**
     * 
     */
    @TableField(value = "is_to_erp")
    private Integer isToErp;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att01")
    private Integer isToErpAtt01;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att02")
    private Integer isToErpAtt02;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att03")
    private Integer isToErpAtt03;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att04")
    private Integer isToErpAtt04;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att05")
    private Integer isToErpAtt05;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att06")
    private Integer isToErpAtt06;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att07")
    private Integer isToErpAtt07;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att08")
    private Integer isToErpAtt08;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att09")
    private Integer isToErpAtt09;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att10")
    private Integer isToErpAtt10;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att11")
    private Integer isToErpAtt11;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att12")
    private Integer isToErpAtt12;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att13")
    private Integer isToErpAtt13;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att14")
    private Integer isToErpAtt14;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att15")
    private Integer isToErpAtt15;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att16")
    private Integer isToErpAtt16;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att17")
    private Integer isToErpAtt17;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att18")
    private Integer isToErpAtt18;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att19")
    private Integer isToErpAtt19;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att20")
    private Integer isToErpAtt20;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att21")
    private Integer isToErpAtt21;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att22")
    private Integer isToErpAtt22;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att23")
    private Integer isToErpAtt23;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att24")
    private Integer isToErpAtt24;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att25")
    private Integer isToErpAtt25;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att26")
    private Integer isToErpAtt26;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att27")
    private Integer isToErpAtt27;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att28")
    private Integer isToErpAtt28;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att29")
    private Integer isToErpAtt29;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att30")
    private Integer isToErpAtt30;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att31")
    private Integer isToErpAtt31;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att32")
    private Integer isToErpAtt32;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att33")
    private Integer isToErpAtt33;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att34")
    private Integer isToErpAtt34;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att35")
    private Integer isToErpAtt35;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att36")
    private Integer isToErpAtt36;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att37")
    private Integer isToErpAtt37;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att38")
    private Integer isToErpAtt38;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att39")
    private Integer isToErpAtt39;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att40")
    private Integer isToErpAtt40;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att41")
    private Integer isToErpAtt41;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att42")
    private Integer isToErpAtt42;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att43")
    private Integer isToErpAtt43;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att44")
    private Integer isToErpAtt44;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att45")
    private Integer isToErpAtt45;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att46")
    private Integer isToErpAtt46;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att47")
    private Integer isToErpAtt47;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att48")
    private Integer isToErpAtt48;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att49")
    private Integer isToErpAtt49;

    /**
     * 
     */
    @TableField(value = "is_to_erp_att50")
    private Integer isToErpAtt50;

    /**
     * 
     */
    @TableField(value = "unit_dicimal_digit")
    private Integer unitDicimalDigit;

    /**
     * 
     */
    @TableField(value = "stock_unit_no")
    private String stockUnitNo;

    /**
     * 
     */
    @TableField(value = "stock_unit_name")
    private String stockUnitName;

    /**
     * 
     */
    @TableField(value = "stock_dicimal_digit")
    private Integer stockDicimalDigit;

    /**
     * 
     */
    @TableField(value = "reference_dicimal_digit")
    private Integer referenceDicimalDigit;

    /**
     * 
     */
    @TableField(value = "from_unit_pt")
    private BigDecimal fromUnitPt;

    /**
     * 
     */
    @TableField(value = "to_unit_pt")
    private BigDecimal toUnitPt;

    /**
     * 
     */
    @TableField(value = "from_refunit_pt")
    private BigDecimal fromRefunitPt;

    /**
     * 
     */
    @TableField(value = "to_refunit_pt")
    private BigDecimal toRefunitPt;

    /**
     * 
     */
    @TableField(value = "save_id")
    private String saveId;

    /**
     * 
     */
    @TableField(value = "lot_desc")
    private String lotDesc;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}