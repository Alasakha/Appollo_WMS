package com.mgkj.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 未审核的工单领料单实体类
 *
 * @author system
 * @date 2026-06-27
 */
@Data
public class UnreviewedMoReceipt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工厂名称
     */
    private String plantName;

    /**
     * 单据名称
     */
    private String docName;

    /**
     * 单据编号
     */
    private String docNo;

    /**
     * 单据日期（格式：yyyy-MM-dd）
     */
    private String docDate;

    /**
     * 工作中心名称
     */
    private String workCenterName;

    private String id;

    private List<UnreviewedMoReceiptItem> itemList;

}