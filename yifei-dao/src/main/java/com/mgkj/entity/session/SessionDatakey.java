package com.mgkj.entity.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/2/19
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
@ApiModel(value = "ETen访问的客户数据库信息")
public class SessionDatakey {
    @JsonProperty("EntId")
    private String EntId = "SF";
    @JsonProperty("CompanyId")
    private String CompanyId = "1";

}
