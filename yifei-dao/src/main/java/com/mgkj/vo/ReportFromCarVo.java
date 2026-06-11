package com.mgkj.vo;

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
@ApiModel(value = "品质报表（车间类） Vo")
public class ReportFromCarVo implements Serializable {

    //工作中心
    private String workCenter;

    //投入数量
    private String inputQuantity;

    //产出数量
    private String outputQuantity;

    //报废数量
    private String scrapQuantity;

    //差异数
    private String differenceNumber;

    //总数
    private String count;

}
