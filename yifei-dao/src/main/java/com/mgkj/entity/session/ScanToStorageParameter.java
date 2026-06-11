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
public class ScanToStorageParameter extends Parameter{
    private String wo_no;
    private String unit_no;
    private String item_no;
    private String subop_no;
    private String op_seq;
    private String undefect_qty;
    private String defect_qty;
    private String report_min;
    private String classNo;
    private String machineNo;
    private String groupNo;
    private String okTime;
    private String noTime;
    private String docTypeNo;
    private ScanToStorageParameterData data;
    private ScanToStorageParameterHost host;
}
