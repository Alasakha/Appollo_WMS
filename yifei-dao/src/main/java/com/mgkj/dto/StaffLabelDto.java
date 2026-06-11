package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/1/29
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "人员信息信息标签Dto")
public class StaffLabelDto {
    @ApiModelProperty("员工编号")
    @JSONField(ordinal = 1)
    private String staffNo;
    @ApiModelProperty("员工名称")
    @JSONField(ordinal = 2)
    private String staffName;
    @ApiModelProperty("部门编号")
    @JSONField(ordinal = 3)
    private String departmentNo;
    @ApiModelProperty("部门名称")
    @JSONField(ordinal = 4)
    private String departmentName;
}
