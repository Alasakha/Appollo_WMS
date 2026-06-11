package com.mgkj.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "采购收货提交Dto")
public class PurchaseReceiptDto {
    private List<PurchaseReceiptStorageDto> list;
    private List<String> invBarcodeOperationIdList;
    private String createBy;
}
