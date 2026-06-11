package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.VYSXJ;

import java.util.List;

public interface VYSXJService extends IService<VYSXJ> {

    String receivableTotal();

    List<VYSXJ> receivableTotalInfo();

}
