package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class PassiveToSubmitDto {

    private List<PassiveToStorageDto> list;
    private String createBy;
    private String typeCode;
}
