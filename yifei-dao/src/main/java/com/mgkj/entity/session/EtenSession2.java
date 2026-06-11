package com.mgkj.entity.session;

import com.mgkj.entity.Lsmdt;
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
@ApiModel(value = "访问e10快速收货接口Vo")
public class EtenSession2 {
    private String key ;
    private String type ;
    private SessionHost host = new SessionHost();
    private SessionService service = new SessionService();
    private SessionDatakey datakey = new SessionDatakey();
    private SessionPayload payload = new SessionPayload();

    //为了无源入库能成功调用e10接口，增加参数
    private Lsmdt lsmdt;
}
