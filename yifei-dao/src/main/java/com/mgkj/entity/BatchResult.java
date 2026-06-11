package com.mgkj.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BatchResult {
    private int totalCount; // 总处理申请单数
    private int successCount; // 成功数
    private int failCount; // 失败数
    private Map<String, String> successDetails = new HashMap<>(); // 成功详情（单号→消息）
    private Map<String, String> failDetails = new HashMap<>(); // 失败详情（单号→原因）

    // 添加成功详情
    public void addSuccessDetail(String docNo, String message) {
        successDetails.put(docNo, message);
    }

    // 添加失败详情
    public void addFailDetail(String docNo, String errorMsg) {
        failDetails.put(docNo, errorMsg);
    }
}