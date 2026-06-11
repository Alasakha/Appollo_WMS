package com.mgkj.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

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
public class FastDeliveryParameter extends Parameter{
    private String ent;
    private String site;
    private String doc_no;
    private List<ParameterDataList> data ;
    private String postDate;
    private String updateErpDocDate;
    private String erpRemark;
    private ParameterHost host = new ParameterHost();
}
