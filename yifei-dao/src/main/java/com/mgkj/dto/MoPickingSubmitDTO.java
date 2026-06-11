package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class MoPickingSubmitDTO {
    private List<MoLjZpIssueSubmitDto> list;
    private List<String> invBarcodeOperationIdList;
    private String createBy;
}
