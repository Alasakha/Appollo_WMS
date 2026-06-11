package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "采购信息--PurchaseVo")
public class PurchaseVo {
    /*
    POST
        {
          "appModule": "A001",
          "ent": "SF",
          "lang": "zh_CN",
          "site": "1",
          "docNo":"3501-220408001",
          "warehouseNo":""
        }
    */
    @ApiModelProperty(value = "数据库(默认SF)")
    private String ent = "SF";
    private String site = "1";
    @ApiModelProperty(value = "字体(简体中文)")
    private String lang = "zh_CN";
    @ApiModelProperty(value = "A001:采购收货  A005:扫码入库  A008:采购仓退")
    private String appModule;
    @ApiModelProperty(value = "采购单号")
    private String docNo;
    @ApiModelProperty(value = "仓库编号")
    private String warehouseNo;
}
