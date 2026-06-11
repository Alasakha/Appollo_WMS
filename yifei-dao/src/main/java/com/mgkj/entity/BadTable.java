package com.mgkj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:04
 * @Description:    LiangPanDeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "不良信息")
@Builder
public class BadTable  implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "不良编号")
    private String badNumber;

    @ApiModelProperty(value = "不良名称")
    private String badName;

    @ApiModelProperty(value = "备注")
    private String remark;
}
