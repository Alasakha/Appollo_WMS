package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransferSubmitDto {

    private List<TransferDocSubmitDto> list;
    private String createBy;
}
