package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("WorkSignLogItemVo")
@Data
public class WorkSignLogItemVo {

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 人员编号
     */
    @ApiModelProperty("人员编号")
    private String creatorNo;

    /**
     * 人员名称
     */
    @ApiModelProperty("人员名称")
    private String creatorName;

    @ApiModelProperty("系数")
    private BigDecimal xs;

}
