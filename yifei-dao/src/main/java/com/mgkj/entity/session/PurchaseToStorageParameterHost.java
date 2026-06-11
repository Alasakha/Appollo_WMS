package com.mgkj.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-08 16:59
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class PurchaseToStorageParameterHost extends ParameterHost{
    private String acct;
    private String timestamp;
    private String appid;
    private String appmodule;
    private String appversion;
    private String name;
    private String EntId;
    private String CompanyId;
}
