package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.vo.SalesMarginInfoVo;
import com.mgkj.vo.SalesMarginVo;
import com.mgkj.vo.SalesVo;
import com.mgkj.entity.COPTH;
import com.mgkj.mapper.COPTHMapper;
import com.mgkj.service.COPTHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Service
public class COPTHServiceImpl extends ServiceImpl<COPTHMapper, COPTH> implements COPTHService {

    @Autowired
    private COPTHMapper copthMapper;

    @Override
    public Map<String,List<SalesVo>> getSales() {
        List<SalesVo> dataList = copthMapper.getSales();
        Map<String, List<SalesVo>> resultMap = new HashMap<>();

        // 遍历查询结果列表
        for (SalesVo data : dataList) {
            // 获取年份（假设 MandY 是一个字符串表示的年月，比如202301）
            String year = data.getMandY().substring(0, 4);
            String month = data.getMandY().substring(4, 6);

            // 如果该年份已经在 resultMap 中存在，则获取该年份对应的列表
            // 并将当前数据添加到该年份对应的列表中
            if (resultMap.containsKey("year"+"_"+year)) {
                List<SalesVo> salesList = resultMap.get("year"+"_"+year);
                salesList.add(new SalesVo(year+month, data.getTH013()));
            }
            // 如果该年份在 resultMap 中不存在，则创建一个新的列表，并将当前数据添加到其中
            else {
                List<SalesVo> salesList = new ArrayList<>();
                salesList.add(new SalesVo(year+month, data.getTH013()));
                resultMap.put("year"+"_"+year, salesList);
            }
        }

        // 返回封装好的 Map
        return resultMap;
    }

    @Override
    public Map<String, List<SalesMarginVo>> getSalesMarginByNowMonth() {
        List<SalesMarginVo> dataList = copthMapper.getSalesMarginByNowMonth();
        Map<String, List<SalesMarginVo>> resultMap1 = new HashMap<>();
        // 遍历查询结果列表
        for (SalesMarginVo data : dataList) {
            // 获取年份（假设 MandY 是一个字符串表示的年月，比如202301）
            String year = data.getMandY().substring(0, 4);
            String month = data.getMandY().substring(4, 6);

            // 如果该年份已经在 resultMap 中存在，则获取该年份对应的列表
            // 并将当前数据添加到该年份对应的列表中
            if (resultMap1.containsKey("year"+"_"+year)) {
                List<SalesMarginVo> salesList = resultMap1.get("year"+"_"+year);
                salesList.add(new SalesMarginVo(year+month, data.getSalesMargin()));
            }
            // 如果该年份在 resultMap 中不存在，则创建一个新的列表，并将当前数据添加到其中
            else {
                List<SalesMarginVo> salesList = new ArrayList<>();
                salesList.add(new SalesMarginVo(year+month, data.getSalesMargin()));
                resultMap1.put("year"+"_"+year, salesList);
            }
        }

        // 返回封装好的 Map
        return resultMap1;
    }

    @Override
    public Map<String, List<SalesMarginVo>> getSalesMarginByNowYear() {
        List<SalesMarginVo> dataList = copthMapper.getSalesMarginByNowYear();
        Map<String, List<SalesMarginVo>> resultMap = new HashMap<>();

        // 遍历查询结果列表
        for (SalesMarginVo data : dataList) {
            // 获取年份（假设 MandY 是一个字符串表示的年月，比如202301）
            String year = data.getMandY().substring(0, 4);
            String month = data.getMandY().substring(4, 6);

            // 如果该年份已经在 resultMap 中存在，则获取该年份对应的列表
            // 并将当前数据添加到该年份对应的列表中
            if (resultMap.containsKey("year"+"_"+year)) {
                List<SalesMarginVo> salesList = resultMap.get("year"+"_"+year);
                salesList.add(new SalesMarginVo(year+month, data.getSalesMargin()));
            }
            // 如果该年份在 resultMap 中不存在，则创建一个新的列表，并将当前数据添加到其中
            else {
                List<SalesMarginVo> salesList = new ArrayList<>();
                salesList.add(new SalesMarginVo(year+month, data.getSalesMargin()));
                resultMap.put("year"+"_"+year, salesList);
            }
        }

        // 返回封装好的 Map
        return resultMap;
    }

    @Override
    public List<SalesMarginInfoVo> getSalesMarginInfo(Integer month) {
        return copthMapper.getSalesMarginInfo(month);
    }

}
