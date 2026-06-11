package com.mgkj.qywx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "sky.corp")
@Data
public class CorpiProperties {

    /**
     * 这里是公司id编号
     */
    private String corpid;
    private String corpsecret;
    private String template_id;
    private List<String> list_userid;
    private String agentid;

}
