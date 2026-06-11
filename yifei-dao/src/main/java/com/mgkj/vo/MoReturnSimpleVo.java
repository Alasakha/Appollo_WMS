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
 * @date 2024/3/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "工单退料简易信息Vo")
public class MoReturnSimpleVo {
    @ApiModelProperty("工单单号")
    private String docNo;
    //单据日期
    @ApiModelProperty("单据日期")
    private String docDate;
    //日期
    @ApiModelProperty("日期")
    private String createDate;
    //品号
    @ApiModelProperty("品号")
    private String itemCode;
    //品名
    @ApiModelProperty("品号")
    private String itemName;
    //部门
    @ApiModelProperty("部门")
    private String departmentName;

}
