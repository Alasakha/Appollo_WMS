package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.COPTH;
import com.mgkj.entity.COPTJ;
import com.mgkj.vo.RefundVo;
import com.mgkj.vo.SalesMarginVo;

import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
public interface COPTJService extends IService<COPTJ> {

    Map<String, List<RefundVo>> getRefund();

}
