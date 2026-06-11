package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.SalesMarginInfoVo;
import com.mgkj.vo.SalesMarginVo;
import com.mgkj.vo.SalesVo;
import com.mgkj.entity.COPTH;

import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
public interface COPTHService extends IService<COPTH> {
    Map<String,List<SalesVo>> getSales();

    Map<String, List<SalesMarginVo>> getSalesMarginByNowMonth();

    Map<String, List<SalesMarginVo>> getSalesMarginByNowYear();

    List<SalesMarginInfoVo> getSalesMarginInfo(Integer month);

}