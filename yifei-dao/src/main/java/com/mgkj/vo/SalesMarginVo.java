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
@ApiModel(value = "毛利 Vo")
public class SalesMarginVo {
    private String MandY;
    private String SalesMargin;
}
