package com.mgkj.dto;

import com.mgkj.entity.session.ParameterDataList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/2/22
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "生成e10快速收货订单Dto")
public class FastDeliveryDto {

    @ApiModelProperty("单头备注")
    private String remark;

    @ApiModelProperty("单身数据")
    private List<ParameterDataList> data;


}
