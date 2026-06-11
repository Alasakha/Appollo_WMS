package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.mapper.OutPutMapper;
import com.mgkj.service.OutPutService;
import com.mgkj.vo.OutPutVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-15
 * @Description:
 */
@Service
public class OutPutServiceImpl extends ServiceImpl<OutPutMapper, OutPutVo> implements OutPutService {

    @Resource
    private OutPutMapper outPutMapper;

    @Override
    public List<OutPutVo> getOutPut(Integer type,String workShop) {
        return outPutMapper.getOutPut(type,workShop);
    }

    @Override
    public List<OutPutVo> getOutPutInfo(Integer page,Integer pageSize,String warehousingDate,Integer type,String workShop) {
        return outPutMapper.getOutPutInfo(type,page,pageSize,warehousingDate,workShop);
    }

    @Override
    public Integer getCount(String warehousingDate,Integer type,String workShop) {
        return outPutMapper.getCount(type,warehousingDate,workShop);
    }

    @Override
    public Integer getOutPutCount() {
        return outPutMapper.getOutPutCount();
    }

    @Override
    public List<OutPutVo> getAllWorkShop() {
        return outPutMapper.getAllWorkShop();
    }
}
