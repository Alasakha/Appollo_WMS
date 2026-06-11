package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/3/18
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "领料单汇总信息Vo")
public class IssueReceiptCollectVo {
    @ApiModelProperty("领料单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("申请人")
    private String employeeName;

    @ApiModelProperty("部门")
    private String departmentName;

    @ApiModelProperty("单身数据")
    private List<IssueReceiptCollectBodyVo> data;
}
