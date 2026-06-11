package com.mgkj.vo;

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
 * @date 2024/3/18
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "入库上架单详细信息Vo")
public class MoShelvesDetailVo {

    @ApiModelProperty("入库申请单号")
    private String docNo;

    @ApiModelProperty("日期")
    private String docDate;

    @ApiModelProperty("部门编号")
    private String departmentNo;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("申请人编号")
    private String employeeNo;

    @ApiModelProperty("申请人")
    private String employeeName;


    @ApiModelProperty("品号")
    private String itemNo;

    @ApiModelProperty("品名")
    private String itemName;

    @ApiModelProperty("规格")
    private String itemSpec;

    @ApiModelProperty("单位")
    private String unitNo;

    @ApiModelProperty("数量")
    private Double applyQty;

    @ApiModelProperty("炉号")
    private String lotDesc;


    @ApiModelProperty(value = "仓库编号")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库库位")
    private String binCode;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

}
