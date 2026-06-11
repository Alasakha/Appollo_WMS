package com.mgkj.dto;

import lombok.Data;

@Data
public class QueryScanBarcodeSaveTableDTO {
    private String docNo;
    private String customerDocNo;
    private Integer status;
    private String salesNo;
}
