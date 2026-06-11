package com.mgkj.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/4/3
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class MoStorageApplySimpleParameter extends Parameter{
    private MoStorageApplyParameterData data;
    private MoStorageApplyParameterHost host;
}
