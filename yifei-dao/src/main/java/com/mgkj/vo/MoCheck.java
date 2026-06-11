package com.mgkj.vo;

import com.mgkj.entity.MoInspectionSd1;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/3/1
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "生产入库检验单")
public class MoCheck {
    private MoCheckHeader header;
    private List<MoCheckLine> lines;
}
