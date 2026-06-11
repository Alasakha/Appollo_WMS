package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "销售 Vo")
public class SalesVo implements Serializable {
    private String MandY;
    private String TH013;
}
