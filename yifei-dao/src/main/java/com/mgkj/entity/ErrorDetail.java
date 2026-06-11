package com.mgkj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 失败详情辅助类
 */
@Data
@AllArgsConstructor
public class ErrorDetail {
    private String docNo; // 申请单号
    private String errorMsg; // 失败原因
}