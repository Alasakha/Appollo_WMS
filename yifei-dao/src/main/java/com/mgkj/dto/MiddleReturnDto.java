package com.mgkj.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/7
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "中间表回调Dto")
public class MiddleReturnDto {
    @ApiModelProperty("docNo")
    private String docNo;

    @ApiModelProperty("appid")
    private String appid;

    @ApiModelProperty("timestamp")
    private String timestamp;

    @ApiModelProperty("erpRemark")
    private String erpRemark;

    @ApiModelProperty("wmsCompanyid")
    private String wmsCompanyid;


    public MiddleReturnDto(String docNo, String appid, String timestamp, String erpRemark) {
        this.docNo = docNo;
        this.appid = appid;
        this.timestamp = timestamp;
        this.erpRemark = erpRemark;
    }

}
