package com.mgkj.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2025-04-01 16:18
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("不良原因类")
public class DefectiveReasons {
    @ApiModelProperty("不良原因编号")
    private String defectiveReasonsCode;
    @ApiModelProperty("不良原因名称")
    private String description;
    @ApiModelProperty("不良原因类型")
    private String defectiveType;
    @ApiModelProperty("不良原因uuid")
    private String defectReasonUuid;

}
