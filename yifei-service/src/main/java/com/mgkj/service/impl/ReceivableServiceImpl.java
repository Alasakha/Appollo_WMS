package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.mapper.ReceivableMapper;
import com.mgkj.service.ReceivableService;
import com.mgkj.vo.ReceivableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-16
 * @Description:
 */
@Service
public class ReceivableServiceImpl extends ServiceImpl<ReceivableMapper, ReceivableVo> implements ReceivableService {

    @Resource
    private ReceivableMapper receivableMapper;

    @Override
    public IPage<ReceivableVo> getReceivable(Page<ReceivableVo> page1) {
        return receivableMapper.getReceivable(page1);
    }


    @Override
    public List<ReceivableVo> getReceivableInfo(String single, String singleNumber) {
        return receivableMapper.getReceivableInfo(single,singleNumber);
    }
}
