package com.mgkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.ReceivableVo;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-16
 * @Description:
 */
public interface ReceivableService extends IService<ReceivableVo> {
    IPage<ReceivableVo> getReceivable(Page<ReceivableVo> page1);


    List<ReceivableVo> getReceivableInfo(String single, String singleNumber);
}
