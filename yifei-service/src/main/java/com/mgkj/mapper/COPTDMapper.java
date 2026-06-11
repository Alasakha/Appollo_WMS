package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgkj.entity.COPTD;
import com.mgkj.entity.COPTH;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Mapper
public interface COPTDMapper extends BaseMapper<COPTD> {

    @DS("demo")
    Integer getNumberOfOverdueOrders();

    @DS("demo")
    IPage<SalesOrderVo> getWorkOrderOverdue(Page<SalesOrderVo> page1);

    @DS("demo")
    List<SalesOrderVo> getWorkOrderOverdueInfo(String orderSingle,String orderNumber,String orderSerialNumber);

}
