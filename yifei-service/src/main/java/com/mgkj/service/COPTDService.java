package com.mgkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.COPTD;
import com.mgkj.vo.*;

import java.util.List;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
public interface COPTDService extends IService<COPTD> {

    Integer getNumberOfOverdueOrders();

    IPage<SalesOrderVo> getWorkOrderOverdue(Page<SalesOrderVo> page1);

    List<SalesOrderVo> getWorkOrderOverdueInfo(String orderSingle,String orderNumber,String orderSerialNumber);
}