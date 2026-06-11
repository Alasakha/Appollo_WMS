package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yyyjcg
 * @date 2023/12/27
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "人员信息--staffGroupVo")
public class StraffGroupVo {
    @ApiModelProperty(value = "数据库名")
    private String ent ;
    @ApiModelProperty(value = "1")
    private String site ;
    @ApiModelProperty(value = "默认为zh_CN")
    private String lang = "zh_CN";
    private String appmodule;
    private String groupNo;

}
