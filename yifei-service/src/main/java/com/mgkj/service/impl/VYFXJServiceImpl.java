package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.mapper.VYFXJMapper;
import com.mgkj.mapper.VYSXJMapper;
import com.mgkj.service.VYFXJService;
import com.mgkj.service.VYSXJService;
import com.mgkj.vo.VYFXJ;
import com.mgkj.vo.VYSXJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VYFXJServiceImpl extends ServiceImpl<VYFXJMapper, VYFXJ> implements VYFXJService {

    @Autowired
    private VYFXJMapper vyfxjMapper;

    @Override
    public String payableTotal() {
        return vyfxjMapper.receivableTotal();
    }

    @Override
    public List<VYFXJ> payableTotalInfo() {
        return vyfxjMapper.receivableTotalInfo();
    }
}
