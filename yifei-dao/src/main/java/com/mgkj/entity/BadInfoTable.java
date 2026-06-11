package com.mgkj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:05
 * @Description:    单据不良信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "单据不良信息")
@Builder
public class BadInfoTable implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "单别")
    private String single;

    @ApiModelProperty(value = "单号")
    private String odd;

    @ApiModelProperty(value = "序号")
    private String serialNumber;

    @ApiModelProperty(value = "不良编号")
    private  String badNumber;

    @ApiModelProperty(value = "数量")
    private  Integer number;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "发生部门id")
    private String happenid;

    @ApiModelProperty(value = "责任部门id")
    private String responsibleid;

    @ApiModelProperty(value = "类型")
    private Integer type;
}
