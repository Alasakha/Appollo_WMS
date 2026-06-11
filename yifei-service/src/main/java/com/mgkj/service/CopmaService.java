package com.mgkj.service;

import com.mgkj.dto.SelectDto;
import com.mgkj.entity.Copma;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.CustomVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
public interface CopmaService extends IService<Copma> {

    CustomVo getDetailedCustomerInfo(String ma001);

    List<CustomVo> getCustomerInfoByTerm(SelectDto selectDto);
}
