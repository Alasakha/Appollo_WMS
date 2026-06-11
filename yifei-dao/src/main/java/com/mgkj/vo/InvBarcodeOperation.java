package com.mgkj.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yyyjcg
 * @date 2024/3/1
 * @Description 条码履历操作表的 DTO 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "InvBarcodeOperationDTO", description = "条码履历操作表的数据传输对象")
public class InvBarcodeOperation {

    @ApiModelProperty(value = "唯一标识")
    @TableField("id")
    @JSONField(ordinal = 1)
    private String id;

    @ApiModelProperty(value = "来源组织")
    @TableField("doc_org_no")
    @JSONField(ordinal = 2)
    private String docOrgNo;

    @ApiModelProperty(value = "组织编码")
    @TableField("org_no")
    @JSONField(ordinal = 3)
    private String orgNo;

    @ApiModelProperty(value = "仓库编码", required = true)
    @TableField("warehouse_no")
    @JSONField(ordinal = 4)
    private String warehouseNo;

    @ApiModelProperty(value = "储位编码")
    @TableField("cell_no")
    @JSONField(ordinal = 5)
    private String cellNo;

    @ApiModelProperty(value = "料件编号")
    @TableField("item_no")
    @JSONField(ordinal = 6)
    private String itemNo;

    @ApiModelProperty(value = "源单对应料号")
    @TableField("doc_item_no")
    @JSONField(ordinal = 7)
    private String docItemNo;

    @ApiModelProperty(value = "条码标签", required = true)
    @TableField("barcode")
    @JSONField(ordinal = 8)
    private String barcode;

    @ApiModelProperty(value = "条码类型")
    @TableField("bar_type")
    @JSONField(ordinal = 9)
    private Integer barType;

    @ApiModelProperty(value = "交易与库存单位换算率(预留)", required = true)
    @TableField("stock_rate")
    @JSONField(ordinal = 10)
    private BigDecimal stockRate;

    @ApiModelProperty(value = "小数位数保留(预留)", required = true)
    @TableField("stock_keep")
    @JSONField(ordinal = 11)
    private BigDecimal stockKeep;

    @ApiModelProperty(value = "SN来源仓库")
    @TableField("sn_from_warehouse_no")
    @JSONField(ordinal = 12)
    private String snFromWarehouseNo;

    @ApiModelProperty(value = "SN来源储位")
    @TableField("sn_from_cell_no")
    @JSONField(ordinal = 13)
    private String snFromCellNo;

    @ApiModelProperty(value = "SN来源状态")
    @TableField("sn_from_status")
    @JSONField(ordinal = 14)
    private String snFromStatus;

    @ApiModelProperty(value = "SN目的仓库")
    @TableField("sn_to_warehouse_no")
    @JSONField(ordinal = 15)
    private String snToWarehouseNo;

    @ApiModelProperty(value = "SN目的储位")
    @TableField("sn_to_cell_no")
    @JSONField(ordinal = 16)
    private String snToCellNo;

    @ApiModelProperty(value = "SN目的状态")
    @TableField("sn_to_status")
    @JSONField(ordinal = 17)
    private String snToStatus;

    @ApiModelProperty(value = "任务号")
    @TableField("doc_no")
    @JSONField(ordinal = 18)
    private String docNo;

    @ApiModelProperty(value = "任务号项次")
    @TableField("doc_seq_no")
    @JSONField(ordinal = 19)
    private String docSeqNo;

    @ApiModelProperty(value = "wms单号")
    @TableField("wms_no")
    @JSONField(ordinal = 20)
    private String wmsNo;

    @ApiModelProperty(value = "wms项次号")
    @TableField("wms_seq_no")
    @JSONField(ordinal = 21)
    private Integer wmsSeqNo;

    @ApiModelProperty(value = "交易数量", required = true)
    @TableField("qty")
    @JSONField(ordinal = 22)
    private BigDecimal qty;

    @ApiModelProperty(value = "交易单位")
    @TableField("unit_no")
    @JSONField(ordinal = 23)
    private String unitNo;

    @ApiModelProperty(value = "参考单位数量", required = true)
    @TableField("reference_qty")
    @JSONField(ordinal = 24)
    private BigDecimal referenceQty;

    @ApiModelProperty(value = "参考单位")
    @TableField("reference_unit_no")
    @JSONField(ordinal = 25)
    private String referenceUnitNo;

    @ApiModelProperty(value = "仓库(调拨另一方仓库)")
    @TableField("to_warehouse_no")
    @JSONField(ordinal = 26)
    private String toWarehouseNo;

    @ApiModelProperty(value = "储位(调拨另一方仓库)")
    @TableField("to_cell_no")
    @JSONField(ordinal = 27)
    private String toCellNo;

    @ApiModelProperty(value = "出入库类型(0.非出入 1.入库 2.出库)", required = true)
    @TableField("chag_type")
    @JSONField(ordinal = 28)
    private Integer chagType;

    @ApiModelProperty(value = "回传标记（0-不回传 1-回传）", required = true)
    @TableField("doc_return_flag")
    @JSONField(ordinal = 29)
    private Integer docReturnFlag;

    @ApiModelProperty(value = "状态：10.初始 30.完成", required = true)
    @TableField("status_code")
    @JSONField(ordinal = 30)
    private Integer statusCode;

    @ApiModelProperty(value = "设备号")
    @TableField("device_no")
    @JSONField(ordinal = 31)
    private String deviceNo;

    @ApiModelProperty(value = "模组号")
    @TableField("module_no")
    @JSONField(ordinal = 32)
    private String moduleNo;

    @ApiModelProperty(value = "容器号")
    @TableField("box_no")
    @JSONField(ordinal = 33)
    private String boxNo;

    @ApiModelProperty(value = "批号拼接值")
    @TableField("combination_lot_no")
    @JSONField(ordinal = 34)
    private String combinationLotNo;

    @ApiModelProperty(value = "WMS批次号")
    @TableField("wms_lot_no")
    @JSONField(ordinal = 35)
    private String wmsLotNo;

    @ApiModelProperty(value = "供应商编号")
    private String lotAtt02;

    @ApiModelProperty(value = "供应商名称")

    private String lotAtt03;

    @ApiModelProperty(value = "品名")
    private String lotAtt04;

    @ApiModelProperty(value = "规格")
    private String lotAtt05;

    @ApiModelProperty(value = "到货数量（匹配量）")
    private String lotAtt06;





    // 以下省略了大量的批次属性字段，你可以根据需要取消注释并完善
    // @ApiModelProperty(value = "批次属性1(生产日期)")
    // @TableField("lot_att01")
    // @JSONField(ordinal = 36)
    // private String lotAtt01;
    //...

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    @JSONField(ordinal = 100) // 假设这些字段顺序靠后
    private String remark;

    @ApiModelProperty(value = "APP登录人员")
    @TableField("pda_op_code")
    @JSONField(ordinal = 101)
    private String pdaOpCode;

    @ApiModelProperty(value = "单据类别")
    @TableField("classify_type")
    @JSONField(ordinal = 102)
    private String classifyType;

    @ApiModelProperty(value = "来源单据类别")
    @TableField("doc_type")
    @JSONField(ordinal = 103)
    private String docType;

    @ApiModelProperty(value = "创建人ID", required = true)
    @TableField("create_by")
    @JSONField(ordinal = 104)
    private String createBy;

    @ApiModelProperty(value = "创建人员")
    @TableField("create_name")
    @JSONField(ordinal = 105)
    private String createName;

    @ApiModelProperty(value = "创建日期", required = true)
    @TableField("create_date")
    @JSONField(ordinal = 106)
    private String createDate;

    @ApiModelProperty(value = "修改人ID", required = true)
    @TableField("update_by")
    @JSONField(ordinal = 107)
    private String updateBy;

    @ApiModelProperty(value = "修改人员")
    @TableField("update_name")
    @JSONField(ordinal = 108)
    private String updateName;

    @ApiModelProperty(value = "修改日期", required = true)
    @TableField("update_date")
    @JSONField(ordinal = 109)
    private Date updateDate;

    @ApiModelProperty(value = "传入标记", required = true)
    @TableField("create_flag")
    @JSONField(ordinal = 110)
    private Integer createFlag;

    // 以下省略了大量的预留字段，你可以根据需要取消注释并完善
    // @ApiModelProperty(value = "标准预留字段1")
    // @TableField("standard_col01")
    // @JSONField(ordinal = 111)
    // private String standardCol01;
    //...

    @ApiModelProperty(value = "源工单号")
    @TableField("doc_wo_no")
    @JSONField(ordinal = 150) // 假设顺序
    private String docWoNo;

    @ApiModelProperty(value = "组织名称")
    @TableField("org_name")
    @JSONField(ordinal = 151)
    private String orgName;

    @ApiModelProperty(value = "组织简称")
    @TableField("org_short_name")
    @JSONField(ordinal = 152)
    private String orgShortName;

    @ApiModelProperty(value = "来源组织名称")
    @TableField("doc_org_name")
    @JSONField(ordinal = 153)
    private String docOrgName;

    @ApiModelProperty(value = "来源组织简称")
    @TableField("doc_org_short_name")
    @JSONField(ordinal = 154)
    private String docOrgShortName;

    @ApiModelProperty(value = "单据类别说明")
    @TableField("classify_type_name")
    @JSONField(ordinal = 155)
    private String classifyTypeName;

    @ApiModelProperty(value = "来源单据类别说明")
    @TableField("doc_type_name")
    @JSONField(ordinal = 156)
    private String docTypeName;

    @ApiModelProperty(value = "仓库名称")
    @TableField("warehouse_name")
    @JSONField(ordinal = 157)
    private String warehouseName;

    @ApiModelProperty(value = "储位名称")
    @TableField("cell_name")
    @JSONField(ordinal = 158)
    private String cellName;

    @ApiModelProperty(value = "物料名称")
    @TableField("item_name")
    @JSONField(ordinal = 159)
    private String itemName;

    @ApiModelProperty(value = "物料规格")
    @TableField("item_spec")
    @JSONField(ordinal = 160)
    private String itemSpec;

    @ApiModelProperty(value = "单位名称")
    @TableField("unit_name")
    @JSONField(ordinal = 161)
    private String unitName;

    @ApiModelProperty(value = "参考单位名称")
    @TableField("reference_unit_name")
    @JSONField(ordinal = 162)
    private String referenceUnitName;

    @ApiModelProperty(value = "目的仓库名称(调拨)")
    @TableField("to_warehouse_name")
    @JSONField(ordinal = 163)
    private String toWarehouseName;

    @ApiModelProperty(value = "目的储位名称(调拨)")
    @TableField("to_cell_name")
    @JSONField(ordinal = 164)
    private String toCellName;

    @ApiModelProperty(value = "SN来源仓库名称")
    @TableField("sn_from_warehouse_name")
    @JSONField(ordinal = 165)
    private String snFromWarehouseName;

    @ApiModelProperty(value = "SN来源储位名称")
    @TableField("sn_from_cell_name")
    @JSONField(ordinal = 166)
    private String snFromCellName;

    @ApiModelProperty(value = "SN目的储位名称")
    @TableField("sn_to_cell_name")
    @JSONField(ordinal = 167)
    private String snToCellName;

    @ApiModelProperty(value = "数据保存来源作业名")
    @TableField("check_data_source")
    @JSONField(ordinal = 168)
    private String checkDataSource;

    @ApiModelProperty(value = "外部储位编码")
    @TableField("cell_outside_no")
    @JSONField(ordinal = 169)
    private String cellOutsideNo;

    @ApiModelProperty(value = "批次关联ERP 0-不回传 1-回传", required = true)
    @TableField("is_to_erp")
    @JSONField(ordinal = 170)
    private Integer isToErp;

    // 以下省略了大量的批次属性回传标记字段，你可以根据需要取消注释并完善
    // @ApiModelProperty(value = "批次属性1 0-不回传 1-回传", required = true)
    // @TableField("is_to_erp_att01")
    // @JSONField(ordinal = 171)
    // private Integer isToErpAtt01;
    //...

    @ApiModelProperty(value = "交易单位小数位数", required = true)
    @TableField("unit_dicimal_digit")
    @JSONField(ordinal = 220) // 假设顺序
    private Integer unitDicimalDigit;

    @ApiModelProperty(value = "库存单位")
    @TableField("stock_unit_no")
    @JSONField(ordinal = 221)
    private String stockUnitNo;

    @ApiModelProperty(value = "库存单位名称")
    @TableField("stock_unit_name")
    @JSONField(ordinal = 222)
    private String stockUnitName;

    @ApiModelProperty(value = "库存单位小数位数", required = true)
    @TableField("stock_dicimal_digit")
    @JSONField(ordinal = 223)
    private Integer stockDicimalDigit;

    @ApiModelProperty(value = "参考单位小数位数", required = true)
    @TableField("reference_dicimal_digit")
    @JSONField(ordinal = 224)
    private Integer referenceDicimalDigit;

    @ApiModelProperty(value = "来源数量(交易与库存转换)", required = true)
    @TableField("from_unit_pt")
    @JSONField(ordinal = 225)
    private BigDecimal fromUnitPt;

    @ApiModelProperty(value = "目的数量(交易与库存转换)", required = true)
    @TableField("to_unit_pt")
    @JSONField(ordinal = 226)
    private BigDecimal toUnitPt;

    @ApiModelProperty(value = "来源数量(参考与库存转换)", required = true)
    @TableField("from_refunit_pt")
    @JSONField(ordinal = 227)
    private BigDecimal fromRefunitPt;

    @ApiModelProperty(value = "目的数量(参考与库存转换)", required = true)
    @TableField("to_refunit_pt")
    @JSONField(ordinal = 228)
    private BigDecimal toRefunitPt;

    @ApiModelProperty(value = "乐观锁（版本）", required = true)
    @TableField("version")
    @JSONField(ordinal = 229)
    private Long version;

    @ApiModelProperty(value = "租户ID", required = true)
    @TableField("tenantsid")
    @JSONField(ordinal = 230)
    private Long tenantsid;

    @ApiModelProperty(value = "出入库结果单号")
    @TableField("transaction_no")
    @JSONField(ordinal = 231)
    private String transactionNo;

    @ApiModelProperty(value = "保存标记")
    @TableField("save_id")
    @JSONField(ordinal = 232)
    private String saveId;

    @ApiModelProperty(value = "批次补充说明")
    @TableField("lot_desc")
    @JSONField(ordinal = 233)
    private String lotDesc;
}