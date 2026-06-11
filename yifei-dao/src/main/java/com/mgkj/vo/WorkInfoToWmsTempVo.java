package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("WorkInfoToWmsTempVo")
public class WorkInfoToWmsTempVo {

    @ApiModelProperty(value = "工单单别")
    private String ty001;

    @ApiModelProperty(value = "工单单号")
    private String ty002;

    @ApiModelProperty(value = "排产日期")
    private String ty003;

    @ApiModelProperty(value = "排产数量")
    private Double ty004;

    @ApiModelProperty(value = "排产数据状态")
    private String udf01;

    @ApiModelProperty(value = "有值的话，就是有MOCTY和这个是一组的")
    private String udf02;

    @ApiModelProperty(value = "UUID")
    private String udf05;

    @ApiModelProperty(value = "工作中心ID")
    private String workCenterId;

    @ApiModelProperty(value = "工作中心名称")
    private String workCenterName;

    @ApiModelProperty("产品品号")
    private String productId;

    @ApiModelProperty("产品品名")
    private String productName;

    @ApiModelProperty("产品规格")
    private String specification;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("工单物料数组")
    private List<WorkInfoToWmsItemVo> moctb;

}
