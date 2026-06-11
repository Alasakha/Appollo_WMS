package com.mgkj.dto;

import lombok.Data;

import java.util.List;

@Data
public class PassiveOutSubmitDto {

    private List<PassiveOutStorageDto> list;
    private String createBy;
    private String typeCode;
    private String remark;
}
