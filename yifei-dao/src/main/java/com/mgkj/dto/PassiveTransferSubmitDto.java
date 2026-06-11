package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class PassiveTransferSubmitDto {

    private List<PassiveTransferDocSubmitDto> list;
    private String createBy;
}
