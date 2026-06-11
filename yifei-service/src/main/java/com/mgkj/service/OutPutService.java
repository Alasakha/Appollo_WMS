package com.mgkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.OutPutVo;
import com.mgkj.vo.WorkOrderOvertimeVo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-15
 * @Description:
 */
public interface OutPutService extends IService<OutPutVo> {

    List<OutPutVo> getOutPut(Integer type,String workShop);

    List<OutPutVo> getOutPutInfo(Integer page,Integer pageSize,String warehousingDate,Integer type,String workShop);

    Integer getCount(String warehousingDate,Integer type,String workShop);

    Integer getOutPutCount();

    List<OutPutVo> getAllWorkShop();
}
