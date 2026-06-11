package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.COPTD;
import com.mgkj.mapper.COPTDMapper;
import com.mgkj.service.COPTDService;
import com.mgkj.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Service
public class COPTDServiceImpl extends ServiceImpl<COPTDMapper, COPTD> implements COPTDService {

    @Resource
    private COPTDMapper coptdMapper;

    @Override
    public Integer getNumberOfOverdueOrders() {
        return coptdMapper.getNumberOfOverdueOrders();
    }

    @Override
    public IPage<SalesOrderVo> getWorkOrderOverdue(Page<SalesOrderVo> page1) {
        return coptdMapper.getWorkOrderOverdue(page1);
    }

    @Override
    public List<SalesOrderVo> getWorkOrderOverdueInfo(String orderSingle,String orderNumber,String orderSerialNumber) {
        return coptdMapper.getWorkOrderOverdueInfo(orderSingle,orderNumber,orderSerialNumber);
    }
}
