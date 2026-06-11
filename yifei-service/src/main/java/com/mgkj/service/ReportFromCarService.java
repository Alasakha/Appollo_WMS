package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.ReportFromCarVo;
import com.mgkj.vo.ReportFromOutVo;
import com.mgkj.vo.ReportVo;

import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
public interface ReportFromCarService extends IService<ReportFromCarVo> {
    Map<String, List<ReportVo>> inputQuantity(String workCenter, String start_time, String end_time,String type);

    Map<String,List<ReportVo>> outputQuantity(String workCenter, String start_time, String end_time,String type);

    Map<String,List<ReportVo>> scrapQuantity(String workCenter, String start_time, String end_time,String type);

    List<String> getWorkCenter();

//    Map<String,List<ReportVo>> difference(String workCenter,Map<String, Map<String, Integer>> map);
}
