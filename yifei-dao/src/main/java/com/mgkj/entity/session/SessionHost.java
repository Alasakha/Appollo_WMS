package com.mgkj.entity.session;

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
@ApiModel(value = "ETen访问的host")
public class SessionHost {
    private String prod;
    private String ip;
    private String lang;
    private String acct;
    private String timestamp ;
    private String erpRemark;

    public SessionHost(String prod, String ip, String lang, String acct, String timestamp) {
        this.prod = prod;
        this.ip = ip;
        this.lang = lang;
        this.acct = acct;
        this.timestamp = timestamp;
    }
}
