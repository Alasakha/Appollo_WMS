package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.common.result.Result;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Invmb;

import java.util.List;
import java.util.Map;

public interface InvmbService extends IService<Invmb> {

    Result<Map<String,Invmb>> query(PojoDto pojoDto);
}
