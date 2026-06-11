package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransferOutSubmitDto {

    private List<TransferDocOutSubmitDto> list;
    private String createBy;
}
