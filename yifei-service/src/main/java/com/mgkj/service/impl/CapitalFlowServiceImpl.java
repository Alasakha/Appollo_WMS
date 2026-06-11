package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.common.result.Result;
import com.mgkj.mapper.CapitalFlowMapper;
import com.mgkj.mapper.VYSXJMapper;
import com.mgkj.service.CapitalFlowService;
import com.mgkj.service.VYSXJService;
import com.mgkj.vo.CapitalFlowVo;
import com.mgkj.vo.VYSXJ;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/1/9
 * @Description
 */
@Service
public class CapitalFlowServiceImpl extends ServiceImpl<CapitalFlowMapper, CapitalFlowVo> implements CapitalFlowService {

    @Resource
    private CapitalFlowMapper capitalFlowMapper;

    @Override
    public List<CapitalFlowVo> getCapitalFlowInfo() {
        List<CapitalFlowVo> capitalFlowVoList = capitalFlowMapper.getCapitalFlowInfo();
        return capitalFlowVoList;

    }

    @Override
    public List<CapitalFlowVo> getSumCapitalFlow() {
        List<CapitalFlowVo> list = capitalFlowMapper.getSumCapitalFlow();
        return list;
    }
}
