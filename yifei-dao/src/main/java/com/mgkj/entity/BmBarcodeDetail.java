package com.mgkj.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 条码管理表
 * @TableName bm_barcode_detail
 */

@TableName(value = "bm_barcode_detail")
@Data
public class BmBarcodeDetail implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 组织编号
     */
    @TableField(value = "org_no")
    private String orgNo;

    /**
     * 条码标签
     */
    @TableField(value = "barcode")
    private String barcode;

    /**
     * 物料编码
     */
    @TableField(value = "item_no")
    private String itemNo;

    /**
     * 单位编码
     */
    @TableField(value = "unit_no")
    private String unitNo;

    /**
     * 单位名称
     */
    @TableField(value = "unit_name")
    private String unitName;

    /**
     * 供应商编号
     */
    @TableField(value = "supplier_no")
    private String supplierNo;

    /**
     * 批次日期
     */
    @TableField(value = "lot_date")
    private Date lotDate;

    /**
     * 客户编码
     */
    @TableField(value = "customer_no")
    private String customerNo;

    /**
     * 货主(预留)
     */
    @TableField(value = "owner_no")
    private String ownerNo;

    /**
     * 条码类型 1.物料级 2.批次级 3.单件级 4.单箱级 5.容器级
     */
    @TableField(value = "bar_type")
    private Integer barType;

    /**
     * 供应商生产日期
     */
    @TableField(value = "supplier_product_date")
    private LocalDate supplierProductDate;

    /**
     * 供应商生产批次
     */
    @TableField(value = "supplier_product_lot")
    private String supplierProductLot;

    /**
     * 批次补充说明
     */
    @TableField(value = "lot_desc")
    private String lotDesc;

    /**
     * 有效期止
     */
    @TableField(value = "barcode_vld_date")
    private LocalDate barcodeVldDate;

    /**
     * 条码产生时机(同标签列印)
     */
    @TableField(value = "gen_moment")
    private Integer genMoment;

    /**
     * 来源单号
     */
    @TableField(value = "source_no")
    private String sourceNo;

//    @TableField(value = "job_no")
//    private String jobNo;

    /**
     * 来源项次
     */
    @TableField(value = "source_seq")
    private String sourceSeq;

    /**
     * 标签重量/体积
     */
    @TableField(value = "barcode_weight")
    private BigDecimal barcodeWeight;

    /**
     * 参考数量
     */
    @TableField(value = "reference_qty")
    private BigDecimal referenceQty;
    /**
     * 参考单位
     */
    @TableField(value = "reference_unit_no")
    private String referenceUnitNo;


    /**
     * 客户条码
     */
    @TableField(value = "customer_barcode")
    private String customerBarcode;

    /**
     * 供应商条码
     */
    @TableField(value = "supplier_barcode")
    private String supplierBarcode;

    /**
     * 数量
     */
    @TableField(value = "qty")
    private BigDecimal qty;

    /**
     * SN仓库位置
     */
    @TableField(value = "sn_warehouse_no")
    private String snWarehouseNo;

    /**
     * SN储位位置
     */
    @TableField(value = "sn_cell_no")
    private String snCellNo;

    /**
     * SN码流转状态
     */
    @TableField(value = "sn_barcode_status")
    private Integer snBarcodeStatus;

    /**
     * 批次拼接值/批次号
     */
    @TableField(value = "combination_lot_no")
    private String combinationLotNo;

    /**
     * wms批次号
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
     * 批次属性3(热处理品号)
     */
    @TableField(value = "lot_att03")
    private String lotAtt03;

    /**
     * 批次属性4(材料批号)
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
     * 批次属性19
     */
    @TableField(value = "lot_att19")
    private String lotAtt19;

    /**
     * 批次属性20
     */
    @TableField(value = "lot_att20")
    private String lotAtt20;

    /**
     * 批次属性21
     */
    @TableField(value = "lot_att21")
    private String lotAtt21;

    /**
     * 批次属性22
     */
    @TableField(value = "lot_att22")
    private String lotAtt22;

    /**
     * 批次属性23
     */
    @TableField(value = "lot_att23")
    private String lotAtt23;

    /**
     * 批次属性24
     */
    @TableField(value = "lot_att24")
    private String lotAtt24;

    /**
     * 批次属性25
     */
    @TableField(value = "lot_att25")
    private String lotAtt25;

    /**
     * 批次属性26
     */
    @TableField(value = "lot_att26")
    private String lotAtt26;

    /**
     * 批次属性27
     */
    @TableField(value = "lot_att27")
    private String lotAtt27;

    /**
     * 批次属性28
     */
    @TableField(value = "lot_att28")
    private String lotAtt28;

    /**
     * 批次属性29
     */
    @TableField(value = "lot_att29")
    private String lotAtt29;

    /**
     * 批次属性30
     */
    @TableField(value = "lot_att30")
    private String lotAtt30;

    /**
     * 批次属性31
     */
    @TableField(value = "lot_att31")
    private String lotAtt31;

    /**
     * 批次属性32
     */
    @TableField(value = "lot_att32")
    private String lotAtt32;

    /**
     * 批次属性33
     */
    @TableField(value = "lot_att33")
    private String lotAtt33;

    /**
     * 批次属性34
     */
    @TableField(value = "lot_att34")
    private String lotAtt34;

    /**
     * 批次属性35
     */
    @TableField(value = "lot_att35")
    private String lotAtt35;

    /**
     * 批次属性36
     */
    @TableField(value = "lot_att36")
    private String lotAtt36;

    /**
     * 批次属性37
     */
    @TableField(value = "lot_att37")
    private String lotAtt37;

    /**
     * 批次属性38
     */
    @TableField(value = "lot_att38")
    private String lotAtt38;

    /**
     * 批次属性39
     */
    @TableField(value = "lot_att39")
    private String lotAtt39;

    /**
     * 批次属性40
     */
    @TableField(value = "lot_att40")
    private String lotAtt40;

    /**
     * 批次属性41
     */
    @TableField(value = "lot_att41")
    private String lotAtt41;

    /**
     * 批次属性42
     */
    @TableField(value = "lot_att42")
    private String lotAtt42;

    /**
     * 批次属性43
     */
    @TableField(value = "lot_att43")
    private String lotAtt43;

    /**
     * 批次属性44
     */
    @TableField(value = "lot_att44")
    private String lotAtt44;

    /**
     * 批次属性45
     */
    @TableField(value = "lot_att45")
    private String lotAtt45;

    /**
     * 批次属性46
     */
    @TableField(value = "lot_att46")
    private String lotAtt46;

    /**
     * 批次属性47
     */
    @TableField(value = "lot_att47")
    private String lotAtt47;

    /**
     * 批次属性48
     */
    @TableField(value = "lot_att48")
    private String lotAtt48;

    /**
     * 批次属性49
     */
    @TableField(value = "lot_att49")
    private String lotAtt49;

    /**
     * 批次属性50
     */
    @TableField(value = "lot_att50")
    private String lotAtt50;

    /**
     * 状态码
     */
    @TableField(value = "status_code")
    private Integer statusCode;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 传入标记
     */
    @TableField(value = "create_flag")
    private Integer createFlag;

    /**
     * 创建人ID
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建人员
     */
    @TableField(value = "create_name")
    private String createName;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private DateTime createDate;

    /**
     * 修改人ID
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 修改人员
     */
    @TableField(value = "update_name")
    private String updateName;

    /**
     * 修改日期
     */
    @TableField(value = "update_date")
    private DateTime updateDate;

    /**
     * 标准纳标，ICD料件分类
     */
    @TableField(value = "standard_col01")
    private String standardCol01;

    /**
     * 标准预留字段2
     */
    @TableField(value = "standard_col02")
    private String standardCol02;

    /**
     * 标准预留字段3
     */
    @TableField(value = "standard_col03")
    private String standardCol03;

    /**
     * 标准预留字段4
     */
    @TableField(value = "standard_col04")
    private String standardCol04;

    /**
     * 标准预留字段5
     */
    @TableField(value = "standard_col05")
    private String standardCol05;

    /**
     * 标准预留字段6
     */
    @TableField(value = "standard_col06")
    private String standardCol06;

    /**
     * 标准预留字段7
     */
    @TableField(value = "standard_col07")
    private String standardCol07;

    /**
     * 标准预留字段8
     */
    @TableField(value = "standard_col08")
    private String standardCol08;

    /**
     * 标准预留字段9
     */
    @TableField(value = "standard_col09")
    private String standardCol09;

    /**
     * 标准预留字段10
     */
    @TableField(value = "standard_col10")
    private String standardCol10;

    /**
     * 标准预留字段11
     */
    @TableField(value = "standard_col11")
    private BigDecimal standardCol11;

    /**
     * 标准预留字段12
     */
    @TableField(value = "standard_col12")
    private BigDecimal standardCol12;

    /**
     * 标准预留字段13
     */
    @TableField(value = "standard_col13")
    private BigDecimal standardCol13;

    /**
     * 标准预留字段14
     */
    @TableField(value = "standard_col14")
    private LocalDateTime standardCol14;

    /**
     * 标准预留字段15
     */
    @TableField(value = "standard_col15")
    private LocalDateTime standardCol15;

    /**
     * 客制预留字段1
     */
    @TableField(value = "guest_col01")
    private String guestCol01;

    /**
     * 客制预留字段2
     */
    @TableField(value = "guest_col02")
    private String guestCol02;

    /**
     * 客制预留字段3
     */
    @TableField(value = "guest_col03")
    private String guestCol03;

    /**
     * 客制预留字段4
     */
    @TableField(value = "guest_col04")
    private String guestCol04;

    /**
     * 客制预留字段5
     */
    @TableField(value = "guest_col05")
    private String guestCol05;

    /**
     * 客制预留字段6
     */
    @TableField(value = "guest_col06")
    private String guestCol06;

    /**
     * 客制预留字段7
     */
    @TableField(value = "guest_col07")
    private BigDecimal guestCol07;

    /**
     * 客制预留字段8
     */
    @TableField(value = "guest_col08")
    private BigDecimal guestCol08;

    /**
     * 客制预留字段9
     */
    @TableField(value = "guest_col09")
    private LocalDateTime guestCol09;

    /**
     * 客制预留字段10
     */
    @TableField(value = "guest_col10")
    private LocalDateTime guestCol10;

    @TableField(value = "org_name")
    private String orgName;

    /**
     * 组织简称
     */
    @TableField(value = "org_short_name")
    private String orgShortName;

    /**
     * 物料名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 物料规格
     */
    @TableField(value = "item_spec")
    private String itemSpec;

    /**
     * 供应商名称
     */
    @TableField(value = "supplier_name")
    private String supplierName;

    /**
     * 供应商简称
     */
    @TableField(value = "supplier_short_name")
    private String supplierShortName;

    /**
     * 客户名称
     */
    @TableField(value = "customer_name")
    private String customerName;

    /**
     * 客户简称
     */
    @TableField(value = "customer_short_name")
    private String customerShortName;

    /**
     * 参考单位名称
     */
    @TableField(value = "reference_unit_name")
    private String referenceUnitName;

    /**
     * SN仓库名称
     */
    @TableField(value = "sn_warehouse_name")
    private String snWarehouseName;

    /**
     * SN储位名称
     */
    @TableField(value = "sn_cell_name")
    private String snCellName;

    /**
     * 乐观锁(版本)
     */
    @TableField(value = "version")
    private Long version;

    /**
     * 租户ID
     */
    @TableField(value = "tenantsid")
    private Long tenantsId;

    /**
     * 标签补打拆分标志
     */
    @TableField(value = "print_split_flag")
    private String printSplitFlag;

    @TableField(exist = false)
    private String warehouseCode;

    @TableField(exist = false)
    private String binCode;

    @TableField(exist = false)
    private String warehouseName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}