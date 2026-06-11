package com.mgkj.dto;

import lombok.Data;

@Data
public class QueryFinishedGoodsInboundDetail {

    private String orgNo;

    private String docNo;

    private String customerDocNo;

    private String createBy;

    /**
     * 0:待提交 1:提交成功
     */
    private Integer status;

    private String startTime;

    private String endTime;
}
