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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EnableSwagger2
public class MoStorageApplyParameterData extends ParameterData{
    private String docNo;
    private String itemNo;
    private String departmentNo;
    private String pageSize;
    private String dateBegin;
    private String dateEnd;
    private String allowMultiOut;

    public MoStorageApplyParameterData(String docNo,String itemNo,String departmentNo,String pageSize,String dateBegin,String dateEnd) {
        this.docNo = docNo;
        this.itemNo = itemNo;
        this.departmentNo = departmentNo;
        this.pageSize = pageSize;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }
    public MoStorageApplyParameterData(String docNo,String allowMultiOut) {
        this.docNo = docNo;
        this.allowMultiOut = allowMultiOut;
    }
}
