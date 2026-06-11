package com.mgkj.vo;


import com.mgkj.entity.Qmsmg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@Api("检验视图bean")
public class MocVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("单别")
    private String singetsu;

    @ApiModelProperty("单号")
    private String odd;

//    @ApiModelProperty("类型")
//    private String type;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("品号")
    private String pinhao;

    @ApiModelProperty("时间")
    private String shijian;

    @ApiModelProperty("设备码")
    private String sbm;

    @ApiModelProperty("工作中心")
    private String zx;

    @ApiModelProperty("工艺")
    private String gy;

    @ApiModelProperty("工序")
    private String gx;

    @ApiModelProperty("品名")
    private String pm;
    @ApiModelProperty("规格")
    private String gg;

    @ApiModelProperty("检验项目")
    private String jy;

    @ApiModelProperty("当前时间")
    private String time;

    @ApiModelProperty("检验项目List集合")
    private List<Qmsmg> qmsmgList;

    @ApiModelProperty
    private String scx;

}
