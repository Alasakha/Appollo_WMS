package com.mgkj.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yyyjcg
 * @date 2023/12/27
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "人员信息--StaffDetailDTO")
public class StaffDetailDto {
    private String staffNo;
    private String staffName;
    private String departmentNo;
    private String departmentName;

}
