package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.mapper.ReportFromCarMapper;
import com.mgkj.mapper.ReportFromOutMapper;
import com.mgkj.service.ReportFromCarService;
import com.mgkj.service.ReportFromOutService;
import com.mgkj.vo.ReportFromCarVo;
import com.mgkj.vo.ReportFromOutVo;
import com.mgkj.vo.ReportVo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2024-01-12
 * @Description:
 */
@Service
public class ReportFromCarServiceImpl extends ServiceImpl<ReportFromCarMapper, ReportFromCarVo> implements ReportFromCarService {

    @Resource
    private ReportFromCarMapper reportFromCarMapper;

    @Override
    public Map<String,List<ReportVo>> inputQuantity(String workCenter, String start_time, String end_time,String type) {
        Map<String,List<ReportVo>> map = new HashMap<>();
        if (workCenter != null && !workCenter.isEmpty()) {
            if ("金工车间".equals(workCenter) || "综合车间".equals(workCenter)) {
                map.put(workCenter,reportFromCarMapper.inputQuantity1(workCenter, start_time, end_time,type));
            } else if ("抛光车间(方同喜)".equals(workCenter) || "抛光车间(顾浩)".equals(workCenter)) {
                map.put(workCenter,reportFromCarMapper.inputQuantity2(workCenter, start_time, end_time,type));
            } else if ("喷漆车间".equals(workCenter)) {
                map.put(workCenter,reportFromCarMapper.inputQuantity3(start_time, end_time,type));
            } else if ("包装车间".equals(workCenter)) {
                map.put(workCenter,reportFromCarMapper.inputQuantity4(start_time, end_time,type));
            }
            return map;
        }

        map.put("金工车间", reportFromCarMapper.inputQuantity1("金工车间", start_time, end_time,type));
        map.put("综合车间", reportFromCarMapper.inputQuantity1("综合车间", start_time, end_time,type));
        map.put("抛光车间(方同喜)", reportFromCarMapper.inputQuantity2("抛光车间(方同喜)", start_time, end_time,type));
        map.put("抛光车间(顾浩)", reportFromCarMapper.inputQuantity2("抛光车间(顾浩)", start_time, end_time,type));
        map.put("喷漆车间", reportFromCarMapper.inputQuantity3(start_time, end_time,type));
        map.put("包装车间", reportFromCarMapper.inputQuantity4(start_time, end_time,type));
        return map;
    }

    @Override
    public Map<String,List<ReportVo>> outputQuantity(String workCenter, String start_time, String end_time,String type) {
        Map<String,List<ReportVo>> map = new HashMap<>();
        if (workCenter != null && !workCenter.isEmpty()) {
            if ("金工车间".equals(workCenter) || "综合车间".equals(workCenter)) {
              map.put(workCenter,reportFromCarMapper.outputQuantity1(workCenter, start_time, end_time,type));
            } else if ("抛光车间(方同喜)".equals(workCenter)) {
                map.put(workCenter,reportFromCarMapper.outputQuantity2(workCenter, start_time, end_time,type));
                List<ReportVo> sum = reportFromCarMapper.outputQuantityByFG(start_time, end_time,type);
                List<ReportVo> list = map.get(workCenter);
                List<ReportVo> newList = new ArrayList<>();

                if (list != null) {
                    for (ReportVo reportVo : list) {
                        for (ReportVo reportSum : sum) {
                            if (reportVo.getDate().equals(reportSum.getDate())) {
                                Integer count1 = Integer.parseInt(reportVo.getCount());
                                Integer  count2 = Integer.parseInt(reportSum.getCount());
                                Integer count = count1 + count2;

                                ReportVo report = new ReportVo();
                                report.setDate(reportVo.getDate());
                                report.setCount(count.toString());

                                newList.add(report);
                            }
                        }
                    }
                }

                map.put(workCenter,list);
            } else if ("抛光车间(顾浩)".equals(workCenter)) {
                map.put(workCenter,reportFromCarMapper.outputQuantity2(workCenter, start_time, end_time,type));
            } else if ("喷漆车间".equals(workCenter)) {
               map.put(workCenter,reportFromCarMapper.outputQuantity3(start_time, end_time,type));
            } else if ("包装车间".equals(workCenter)) {
                map.put(workCenter, reportFromCarMapper.outputQuantity4(start_time, end_time,type));
            }
            return map;
        }

        map.put("金工车间", reportFromCarMapper.outputQuantity1("金工车间", start_time, end_time,type));
        map.put("综合车间", reportFromCarMapper.outputQuantity1("综合车间", start_time, end_time,type));
        map.put("抛光车间(方同喜)", reportFromCarMapper.outputQuantity2("抛光车间(方同喜)", start_time, end_time,type));
        map.put("抛光车间(顾浩)", reportFromCarMapper.outputQuantity2("抛光车间(顾浩)", start_time, end_time,type));
        map.put("喷漆车间", reportFromCarMapper.outputQuantity3(start_time, end_time,type));
        map.put("包装车间", reportFromCarMapper.outputQuantity4(start_time, end_time,type));
        return map;
    }

    @Override
    public Map<String,List<ReportVo>> scrapQuantity(String workCenter, String start_time, String end_time,String type) {
        Integer count = 0;
        Map<String,List<ReportVo>> map = new HashMap<>();
        if (workCenter != null && !workCenter.isEmpty()) {
            map.put(workCenter, reportFromCarMapper.scrapQuantity(workCenter, start_time, end_time,type));
            return map;
        }
        map.put("金工车间", reportFromCarMapper.scrapQuantity("金工车间", start_time, end_time,type));
        map.put("综合车间", reportFromCarMapper.scrapQuantity("综合车间", start_time, end_time,type));
        map.put("抛光车间(方同喜)", reportFromCarMapper.scrapQuantity("抛光车间(方同喜)", start_time, end_time,type));
        map.put("抛光车间(顾浩)", reportFromCarMapper.scrapQuantity("抛光车间(顾浩)", start_time, end_time,type));
        map.put("喷漆车间", reportFromCarMapper.scrapQuantity("喷漆车间", start_time, end_time,type));
        map.put("包装车间", reportFromCarMapper.scrapQuantity("包装车间", start_time, end_time,type));
        return map;
    }

    @Override
    public List<String> getWorkCenter() {
        return reportFromCarMapper.getWorkCenter();
    }

//    @Override
//    public Map<String,List<ReportVo>> difference(String workCenter, Map<String, Map<String, Integer>> map) {
//        Integer count = 0;
//        Map<String, Integer> map1 = new HashMap<>();
//        if (workCenter != null && !workCenter.isEmpty()) {
//            count = map.get("inputCount").get(workCenter)
//                    - map.get("outputCount").get(workCenter)
//                    - map.get("scrapQuantity").get(workCenter);
//            map1.put(workCenter, count);
//            return map1;
//        }
//        map1.put("金工车间", map.get("inputCount").get("金工车间")
//                - map.get("outputCount").get("金工车间")
//                - map.get("scrapQuantity").get("金工车间"));
//        map1.put("综合车间", map.get("inputCount").get("综合车间")
//                - map.get("outputCount").get("综合车间")
//                - map.get("scrapQuantity").get("综合车间"));
//        map1.put("抛光车间(方同喜)", map.get("inputCount").get("抛光车间(方同喜)")
//                - map.get("outputCount").get("抛光车间(方同喜)")
//                - map.get("scrapQuantity").get("抛光车间(方同喜)"));
//        map1.put("抛光车间(顾浩)", map.get("inputCount").get("抛光车间(顾浩)")
//                - map.get("outputCount").get("抛光车间(顾浩)")
//                - map.get("scrapQuantity").get("抛光车间(顾浩)"));
//        map1.put("喷漆车间", map.get("inputCount").get("喷漆车间")
//                - map.get("outputCount").get("喷漆车间")
//                - map.get("scrapQuantity").get("喷漆车间"));
//        map1.put("包装车间", map.get("inputCount").get("包装车间")
//                - map.get("outputCount").get("包装车间")
//                - map.get("scrapQuantity").get("包装车间"));
//        return map1;
//    }
}
