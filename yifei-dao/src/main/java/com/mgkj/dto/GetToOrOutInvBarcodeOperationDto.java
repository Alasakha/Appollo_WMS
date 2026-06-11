package com.mgkj.dto;

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
@ApiModel(value = "查询无源出入库箱码信息")
public class GetToOrOutInvBarcodeOperationDto {

    @ApiModelProperty("登录人")
    private String createBy;

    @ApiModelProperty(value = "操作业务(5无源调拨、6无源入库、7无源出库)")
    private Integer chagType;

    @ApiModelProperty(value = "操作业务状态")
    private Integer statusCode;


}
