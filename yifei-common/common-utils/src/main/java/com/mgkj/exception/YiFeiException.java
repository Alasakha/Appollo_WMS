package com.mgkj.exception;

import com.mgkj.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author : ssy
 * @date: 2023-12-18
 * @Description:
 */
@Data
public class YiFeiException extends RuntimeException {

    private Integer code;//状态码
    private String msg;//描述信息

    public YiFeiException(Integer code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public YiFeiException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "YiFeiException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
