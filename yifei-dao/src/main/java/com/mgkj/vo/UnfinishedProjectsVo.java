package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "无源出库项目信息Vo")
public class UnfinishedProjectsVo {


    @ApiModelProperty(value = "项目uuid")
    private String projectId="";

    @ApiModelProperty(value = "项目编号")
    private String projectCode="";

    @ApiModelProperty(value = "项目名称")
    private String projectName="";

    @ApiModelProperty(value = "项目描述")
    private String projectDesc="";

}
