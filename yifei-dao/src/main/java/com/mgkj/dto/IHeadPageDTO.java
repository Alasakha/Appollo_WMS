package com.mgkj.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/07/15:08
 * @Description:
 */
@Data
public class IHeadPageDTO {
    // 页数
    Integer page;
    // 每页几条
    Integer pagesize;
    // type
    String type;
    // 工单单别
    private String  ta001;
    // 工单单号
    private String  ta002;
    // 品号
    private String  ta006;
    // 品名
    private String  mb002;
    // 场所
    private String address;
    // 检验人员
    private String people;
    // 到货单别
    private String arriveSgetsu;
    // 到货单单号
    private String arriveOddNmber;
    //   检验地点
    String udf06;
}
