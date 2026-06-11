package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.common.result.Result;
import com.mgkj.vo.CapitalFlowVo;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/1/9
 * @Description
 */
public interface CapitalFlowService extends IService<CapitalFlowVo> {

    List<CapitalFlowVo> getCapitalFlowInfo();

    List<CapitalFlowVo> getSumCapitalFlow();
}
