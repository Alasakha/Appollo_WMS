package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "品质报表（外检类） Vo")
public class ReportFromOutVo implements Serializable {

    //单别
    private String single;

    //单号
    private String singleNumber;

    //供应商名称
    private String supplierName;

    //总数
    private String count;

    //创建时间
    private String date;

}
