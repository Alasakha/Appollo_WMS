package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/3/11
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "工单发料汇总Vo")
public class MoCollectVo {

    @ApiModelProperty("工单单号")
    private String docNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("料号")
    private String itemCode;

    @ApiModelProperty("部门/厂商")
    private String departmentName;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("单身数据")
    private List<MoCollectBodyVo> data;

}
