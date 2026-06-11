package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.COPTH;
import com.mgkj.entity.COPTJ;
import com.mgkj.mapper.COPTHMapper;
import com.mgkj.mapper.COPTJMapper;
import com.mgkj.service.COPTHService;
import com.mgkj.service.COPTJService;
import com.mgkj.vo.RefundVo;
import com.mgkj.vo.SalesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Service
public class COPTJServiceImpl extends ServiceImpl<COPTJMapper, COPTJ> implements COPTJService {

    @Autowired
    private COPTJMapper coptjMapper;

    @Override
    public Map<String, List<RefundVo>> getRefund() {
        List<RefundVo> dataList = coptjMapper.getRefund();
        Map<String, List<RefundVo>> resultMap = new HashMap<>();

        // 遍历查询结果列表
        for (RefundVo data : dataList) {
            // 获取年份（假设 MandY 是一个字符串表示的年月，比如202301）
            String year = data.getMandY().substring(0, 4);
            String month = data.getMandY().substring(4, 6);

            // 如果该年份已经在 resultMap 中存在，则获取该年份对应的列表
            // 并将当前数据添加到该年份对应的列表中
            if (resultMap.containsKey("year"+"_"+year)) {
                List<RefundVo> refundVoList = resultMap.get("year"+"_"+year);
                refundVoList.add(new RefundVo(year+month, data.getTJ012()));
            }
            // 如果该年份在 resultMap 中不存在，则创建一个新的列表，并将当前数据添加到其中
            else {
                List<RefundVo> refundVoList = new ArrayList<>();
                refundVoList.add(new RefundVo(year+month, data.getTJ012()));
                resultMap.put("year"+"_"+year, refundVoList);
            }
        }

        // 返回封装好的 Map
        return resultMap;
    }

}
