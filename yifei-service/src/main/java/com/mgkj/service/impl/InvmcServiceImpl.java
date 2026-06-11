package com.mgkj.service.impl;

import com.mgkj.dto.SelectDto;
import com.mgkj.entity.Invmb;
import com.mgkj.entity.Invmc;
import com.mgkj.mapper.INVMBMapper;
import com.mgkj.mapper.InvmcMapper;
import com.mgkj.service.InvmcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
@Service
public class InvmcServiceImpl extends ServiceImpl<InvmcMapper, Invmc> implements InvmcService {

    @Resource
    private INVMBMapper invmbMapper;
    @Override
    public List<Invmb> getOrderInfoByTrim(SelectDto selectDto) {
        List<Invmb> list = invmbMapper.selectList(null);
        return list;
    }
}
