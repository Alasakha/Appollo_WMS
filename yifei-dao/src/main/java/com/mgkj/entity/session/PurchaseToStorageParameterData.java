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
 * @Create: 2024-12-08 16:58
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EnableSwagger2
public class PurchaseToStorageParameterData {
    private String docNo;
    private String itemNo;
    private String departmentNo;
    private String pagesize = "10";
    private String dateBegin;
    private String dateEnd;

}
