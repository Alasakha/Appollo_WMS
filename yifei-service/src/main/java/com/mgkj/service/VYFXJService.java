package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.VYFXJ;
import com.mgkj.vo.VYSXJ;

import java.util.List;

public interface VYFXJService extends IService<VYFXJ> {

    String payableTotal();

    List<VYFXJ> payableTotalInfo();
}
