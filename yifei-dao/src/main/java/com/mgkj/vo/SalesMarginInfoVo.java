package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ssy
 * @date: 2024-01-09
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "毛利详细信息 Vo")
public class SalesMarginInfoVo {
    private String MA002;
    private String SalesMargin;
}
