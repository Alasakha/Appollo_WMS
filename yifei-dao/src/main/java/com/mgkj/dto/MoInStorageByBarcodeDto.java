package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/3
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableSwagger2
@ApiModel(value = "根据条码查工单入库提交需要的信息Dto")
public class MoInStorageByBarcodeDto {
//    @ApiModelProperty(value = "工单单号")
//    private String docNo;
    @ApiModelProperty(value = "条码",required = true)
    private String barcode;
    @ApiModelProperty(value = "创建人", required = true)
    private String createBy;
//    @ApiModelProperty(value = "开始时间",required = true)
//    private String dateBegin;
//    @ApiModelProperty(value = "结束时间",required = true)
//    private String dateEnd;
/*合代码新增*/
//    @ApiModelProperty(value = "品号")
//    private String itemNo;
//    @ApiModelProperty(value = "部门编号")
//    private String departmentNo;
//    @ApiModelProperty(value = "开始时间")
//    private String dateBegin;
//    @ApiModelProperty(value = "结束时间")
//    private String dateEnd;
//
//    @ApiModelProperty(value = "收货机构(工厂号)",required = true)
//    private String shjg;


}
