package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.Implementation;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-23
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "报表 Vo")
public class ReportVo implements Serializable {
    private String date;
    private String count;
}
