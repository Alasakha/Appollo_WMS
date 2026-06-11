package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 通用品号信息/CHT/通用品號資料/ENU/General Item Data
 * @TableName ITEM
 */
@TableName(value ="ITEM")
@Data
public class Item implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "ITEM_BUSINESS_ID")
    private Object itemBusinessId;

    /**
     * 关联部门
     */
    @TableField(value = "Owner_Dept")
    private Object ownerDept;

    /**
     * 关联员工
     */
    @TableField(value = "Owner_Emp")
    private Object ownerEmp;

    /**
     * 存货管理
     */
    @TableField(value = "INVENTORY_MANAGEMENT")
    private Boolean inventoryManagement;

    /**
     * 品号
     */
    @TableField(value = "ITEM_CODE")
    private String itemCode;

    /**
     * 
     */
    @TableField(value = "ITEM_NAME")
    private String itemName;

    /**
     * 备注
     */
    @TableField(value = "REMARK")
    private String remark;

    /**
     * 归类品
     */
    @TableField(value = "CHANGE_ITEM_SPEC")
    private Boolean changeItemSpec;

    /**
     * 
     */
    @TableField(value = "SHORTCUT")
    private String shortcut;

    /**
     * 
     */
    @TableField(value = "ITEM_SPECIFICATION")
    private String itemSpecification;

    /**
     * 批号管理
     */
    @TableField(value = "LOT_CONTROL")
    private String lotControl;

    /**
     * 批号有效天数
     */
    @TableField(value = "LOT_EXPIRE_DAY")
    private Integer lotExpireDay;

    /**
     * 批号等待天数
     */
    @TableField(value = "LOT_WAITING_DAY")
    private Integer lotWaitingDay;

    /**
     * 批号复检天数
     */
    @TableField(value = "LOT_REINSPECTION_DAY")
    private Integer lotReinspectionDay;

    /**
     * 修改批号有效日期
     */
    @TableField(value = "LOT_FAILURE_CONTROL")
    private String lotFailureControl;

    /**
     * 计量体系
     */
    @TableField(value = "UNIT_MODE")
    private String unitMode;

    /**
     * 库存检查方式
     */
    @TableField(value = "INVENTORY_CHECK_BY")
    private String inventoryCheckBy;

    /**
     * 序列号管理
     */
    @TableField(value = "ITEM_SN_MANAGEMENT")
    private Boolean itemSnManagement;

    /**
     * 序列号输入模式
     */
    @TableField(value = "SN_ENTRY_MODE")
    private String snEntryMode;

    /**
     * 启用特征码
     */
    @TableField(value = "ITEM_FEATURE_CONTROL")
    private Boolean itemFeatureControl;

    /**
     * 以包装方式输入数量
     */
    @TableField(value = "PACKING_UNIT")
    private Boolean packingUnit;

    /**
     * 状态
     */
    @TableField(value = "STATUS")
    private String status;

    /**
     * 工程品号
     */
    @TableField(value = "E_ITEM")
    private String eItem;

    /**
     * 工程码
     */
    @TableField(value = "E_CODE")
    private String eCode;

    /**
     * 特征码产生模式
     */
    @TableField(value = "FEATURE_GENERATE_MODE")
    private String featureGenerateMode;

    /**
     * CKD母件
     */
    @TableField(value = "CKD")
    private Boolean ckd;

    /**
     * 图片
     */
    @TableField(value = "ITEM_PICTURE")
    private String itemPicture;

    /**
     * 启用联产品
     */
    @TableField(value = "JOINT_PRODUCT_CONTROL")
    private Boolean jointProductControl;

    /**
     * 需要做料件认可
     */
    @TableField(value = "NEED_CERTIFICATION")
    private Boolean needCertification;

    /**
     * 批号编码规则
     */
    @TableField(value = "LOT_NO_RULE_ID")
    private Object lotNoRuleId;

    /**
     * 序号编码规则
     */
    @TableField(value = "SN_NO_RULE_ID")
    private Object snNoRuleId;

    /**
     * 品号群组
     */
    @TableField(value = "FEATURE_GROUP_ID")
    private Object featureGroupId;

    /**
     * 第二单位
     */
    @TableField(value = "SECOND_UNIT_ID")
    private Object secondUnitId;

    /**
     * 库存单位
     */
    @TableField(value = "STOCK_UNIT_ID")
    private Object stockUnitId;

    /**
     * 生命周期
     */
    @TableField(value = "LIFECYCLE_ID")
    private Object lifecycleId;

    /**
     * 物流单位
     */
    @TableField(value = "LOGISTIC_UNIT_ID")
    private Object logisticUnitId;

    /**
     * 电子称重
     */
    @TableField(value = "ISWEIGHT")
    private Boolean isweight;

    /**
     * 电子称变价
     */
    @TableField(value = "WEIGHT_PRICE")
    private Boolean weightPrice;

    /**
     * 电子称PLU
     */
    @TableField(value = "WEIGHT_PLU")
    private String weightPlu;

    /**
     * 电子称单位
     */
    @TableField(value = "WEIGHT_UNIT_ID")
    private Object weightUnitId;

    /**
     * 品号来源
     */
    @TableField(value = "SOURCE")
    private String source;

    /**
     * PLM传输批次号
     */
    @TableField(value = "PLM_DATAKEY")
    private String plmDatakey;

    /**
     * 品号图片
     */
    @TableField(value = "ITEM_PIC")
    private String itemPic;

    /**
     * 商品描述(作废)
     */
    @TableField(value = "ITEM_DESC2")
    private String itemDesc2;

    /**
     * 品号描述
     */
    @TableField(value = "ITEM_DESC")
    private String itemDesc;

    /**
     * 图号
     */
    @TableField(value = "DRAWING_NO")
    private String drawingNo;

    /**
     * 图号取自
     */
    @TableField(value = "DRAWING_NO_FROM")
    private String drawingNoFrom;

    /**
     * 品号净重
     */
    @TableField(value = "ITEM_NET_WEIGHT")
    private BigDecimal itemNetWeight;

    /**
     * 重量单位
     */
    @TableField(value = "NET_WEIGHT_UNIT_ID")
    private Object netWeightUnitId;

    /**
     * 服务品号
     */
    @TableField(value = "SERVICE_ITEM")
    private Boolean serviceItem;

    /**
     * 资产
     */
    @TableField(value = "ASSET")
    private Boolean asset;

    /**
     * 自定义字段0
     */
    @TableField(value = "UDF001")
    private BigDecimal udf001;

    /**
     * 自定义字段1
     */
    @TableField(value = "UDF002")
    private BigDecimal udf002;

    /**
     * 自定义字段2
     */
    @TableField(value = "UDF003")
    private BigDecimal udf003;

    /**
     * 自定义字段3
     */
    @TableField(value = "UDF011")
    private BigDecimal udf011;

    /**
     * 自定义字段4
     */
    @TableField(value = "UDF012")
    private BigDecimal udf012;

    /**
     * 自定义字段5
     */
    @TableField(value = "UDF013")
    private BigDecimal udf013;

    /**
     * 自定义字段6
     */
    @TableField(value = "UDF021")
    private String udf021;

    /**
     * 自定义字段7
     */
    @TableField(value = "UDF022")
    private String udf022;

    /**
     * 自定义字段8
     */
    @TableField(value = "UDF023")
    private String udf023;

    /**
     * 自定义字段9
     */
    @TableField(value = "UDF024")
    private String udf024;

    /**
     * 自定义字段10
     */
    @TableField(value = "UDF025")
    private String udf025;

    /**
     * 自定义字段11
     */
    @TableField(value = "UDF026")
    private String udf026;

    /**
     * 自定义字段12
     */
    @TableField(value = "UDF041")
    private LocalDateTime udf041;

    /**
     * 自定义字段13
     */
    @TableField(value = "UDF042")
    private LocalDateTime udf042;

    /**
     * 自定义字段14
     */
    @TableField(value = "UDF051")
    private Object udf051;

    /**
     * 自定义字段15
     */
    @TableField(value = "UDF052")
    private Object udf052;

    /**
     * 自定义字段16
     */
    @TableField(value = "UDF053")
    private Object udf053;

    /**
     * 自定义字段17
     */
    @TableField(value = "UDF054")
    private Object udf054;

    /**
     * 创建日期
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createdate;

    /**
     * 最后修改日期
     */
    @TableField(value = "LastModifiedDate")
    private LocalDateTime lastmodifieddate;

    /**
     * 修改日期
     */
    @TableField(value = "ModifiedDate")
    private LocalDateTime modifieddate;

    /**
     * 创建者
     */
    @TableField(value = "CreateBy")
    private Object createby;

    /**
     * 最后修改者
     */
    @TableField(value = "LastModifiedBy")
    private Object lastmodifiedby;

    /**
     * 修改者
     */
    @TableField(value = "ModifiedBy")
    private Object modifiedby;

    /**
     * 附件
     */
    @TableField(value = "Attachments")
    private String attachments;

    /**
     * 表单所在的流程实例的编号
     */
    @TableField(value = "ProcessInstanceId")
    private Object processinstanceid;

    /**
     * 版本号，不要随意更改
     */
    @TableField(value = "Version")
    private LocalDateTime version;

    /**
     * 单据状态属性
     */
    @TableField(value = "ApproveStatus")
    private String approvestatus;

    /**
     * 修改日期
     */
    @TableField(value = "ApproveDate")
    private LocalDateTime approvedate;

    /**
     * 修改人
     */
    @TableField(value = "ApproveBy")
    private Object approveby;

    /**
     * 
     */
    @TableField(value = "Owner_Org_RTK")
    private String ownerOrgRtk;

    /**
     * 
     */
    @TableField(value = "Owner_Org_ROid")
    private Object ownerOrgRoid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}