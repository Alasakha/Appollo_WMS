package com.mgkj.service;

import com.mgkj.common.result.Result;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Cmsmw;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 14501
* @description 针对表【CMSMW】的数据库操作Service
* @createDate 2024-07-05 16:41:14
*/
public interface CmsmwService extends IService<Cmsmw> {

    Result<Map<String,Cmsmw>> query(PojoDto pojoDto);

}
