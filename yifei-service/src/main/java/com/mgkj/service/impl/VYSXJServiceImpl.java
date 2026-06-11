package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.mapper.*;
import com.mgkj.service.VYSXJService;
import com.mgkj.vo.VYSXJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VYSXJServiceImpl extends ServiceImpl<VYSXJMapper, VYSXJ> implements VYSXJService {

    @Autowired
    private VYSXJMapper vysxjMapper;

    @Override
    public String receivableTotal() {
        return vysxjMapper.receivableTotal();
    }

    @Override
    public List<VYSXJ> receivableTotalInfo() {
        return vysxjMapper.receivableTotalInfo();
    }
}
