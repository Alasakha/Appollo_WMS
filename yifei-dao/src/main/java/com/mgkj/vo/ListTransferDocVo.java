package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/5/17
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("库存调拨vo")
public class ListTransferDocVo {
    @ApiModelProperty("调拨单号")
    private String docNo;

    @ApiModelProperty("申请人")
    private String employeeName;

    @ApiModelProperty("部门")
    private String departmentName;

    @ApiModelProperty("日期")
    private String docDate;
}
