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
public class ScanToStorageParameterData extends ParameterData{
    private String doc_type_no;
    private String doc_no ;
    private String docNo ;
    private String seq ;
    private String orderSeq ;
    private String measureValue ;
    private String string ;
    private String decision ;
    private String statu ;
    private String defectReason ;
    private String defectReasonName ;
    private String defectReasonQty ;
    private String qty ;
    private String qcNo ;
    private String remark ;
    private String inspectionItem ;
}
