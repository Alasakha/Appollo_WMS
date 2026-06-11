package com.mgkj.common.enums;

import lombok.Getter;

@Getter
public enum YiFeiResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
            ;

    private Integer code;
    private String message;

    private YiFeiResultCodeEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

}
