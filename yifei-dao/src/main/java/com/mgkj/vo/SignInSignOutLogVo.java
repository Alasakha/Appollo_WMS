package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SignInSignOutLogVo")
public class SignInSignOutLogVo {


    @ApiModelProperty("人员编号")
    private String creatorno;

    @ApiModelProperty("人员名称")
    private String creatorname;

//    @ApiModelProperty("签到时间")
//    private String signintime;
//
//    @ApiModelProperty("签退时间")
//    private String signouttime;

    @ApiModelProperty("类型，早班中班晚班")
    private String signtype;

    @ApiModelProperty("有效签到时间")
    private String udf05;

    @ApiModelProperty("有效签退时间")
    private String udf06;



}
