package com.mgkj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/17
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel("销售退货Vo")
public class ListSaleReturnVo {

    @ApiModelProperty("退货单号")
    private String docNo;

    @ApiModelProperty("日期")
    @JsonProperty("docDate")
    private String createDate;

    @ApiModelProperty("客户编号")
    private String customerNo;

    @ApiModelProperty("客户")
    private String customerName;

    @ApiModelProperty("部门编号")
    private String departmentNo;

    @ApiModelProperty("部门")
    private String departmentName;

    @ApiModelProperty("业务员编号")
    private String employeeNo;

    @ApiModelProperty("业务员")
    private String employeeName;
}
