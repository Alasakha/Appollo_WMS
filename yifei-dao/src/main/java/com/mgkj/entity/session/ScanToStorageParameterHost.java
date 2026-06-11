package com.mgkj.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyyjcg
 * @date 2024/3/7
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class ScanToStorageParameterHost extends ParameterHost{
    private String acct;
    private String timestamp;
    private String appid;
    private String appmodule;
    private String appversion;
    private String name;
    private String EntId;
    private String CompanyId;
    private String erpRemark;
    private String giftFlag;
    private String manyWoNo;
}
