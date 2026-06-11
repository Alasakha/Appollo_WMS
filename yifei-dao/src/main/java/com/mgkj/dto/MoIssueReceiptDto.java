package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2025-04-16 09:58
 * @Version 1.0
 */

@Data
@ApiModel(value = "领料下架提交Dto")
public class MoIssueReceiptDto {
    private List<MoIssueReceiptSubmitDto> list;
    private List<String> invBarcodeOperationIdList;
    private String createBy;
}
