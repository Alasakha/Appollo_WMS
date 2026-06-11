package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleOutSubmitDTO {
    private List<SaleOutStockDto> list;
    private List<String> invBarcodeOperationIdList;
    private String createBy;
}
