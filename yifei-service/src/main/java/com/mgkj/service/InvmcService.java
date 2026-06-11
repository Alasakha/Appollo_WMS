package com.mgkj.service;

import com.mgkj.dto.SelectDto;
import com.mgkj.entity.Invmb;
import com.mgkj.entity.Invmc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
public interface InvmcService extends IService<Invmc> {

    List<Invmb> getOrderInfoByTrim(SelectDto selectDto);

}
