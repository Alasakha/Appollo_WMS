package com.mgkj.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 业务数据实体（包含DOC_NO和QC_TIMES）
 */
@Data
@AllArgsConstructor
public class DataKey {
    private String DOC_NO;
    private int QC_TIMES;
}