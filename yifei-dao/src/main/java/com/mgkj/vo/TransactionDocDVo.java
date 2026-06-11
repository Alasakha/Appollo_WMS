package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "其它出库单身对象")
public class TransactionDocDVo {

    private String transactionDocDId;

    private String itemId;

}
