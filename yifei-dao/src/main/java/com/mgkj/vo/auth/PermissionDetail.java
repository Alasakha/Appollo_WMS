package com.mgkj.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("具体权限")
public class PermissionDetail {

    /**
     * 新增 --> 9
     * 查询 --> 1
     * 更改 --> 2
     * 删除 --> 3
     * 审核 --> 4
     * 撤审 --> 5
     * 作废 --> 12
     * 输出 --> 6
     * 订阅 --> 13
     * 成本(只读) --> 7
     * 售价(只读) --> 8
     * 附件(查看) --> 14
     */
    @ApiModelProperty("运行权限")
    private String canRun;

    @ApiModelProperty("新增")
    private String add;
    @ApiModelProperty("查询")
    private String select;
    @ApiModelProperty("更改")
    private String update;
    @ApiModelProperty("删除")
    private String del;
    @ApiModelProperty("审核")
    private String check;
    @ApiModelProperty("撤审")
    private String uncheck;
    @ApiModelProperty("作废")
    private String invalid;

    @ApiModelProperty("输出（导出）")
    private String output;
    @ApiModelProperty("订阅")
    private String subscribe;
    @ApiModelProperty("送签")
    private String signature;

    /**
     *
     */
    @ApiModelProperty("成本  Y正常  R只读  N隐藏")
    private String cost;
    /**
     *
     */
    @ApiModelProperty("售价 Y正常  R只读  N隐藏")
    private String price;
    /**
     *
     */
    @ApiModelProperty("附件 Y正常  R只读  N隐藏")
    private String attached;
}
