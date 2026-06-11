package com.mgkj.mapper;

import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Cmsmw;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 14501
* @description 针对表【CMSMW】的数据库操作Mapper
* @createDate 2024-07-05 16:41:14
* @Entity com.mgkj.entity.Cmsmw
*/
public interface CmsmwMapper extends BaseMapper<Cmsmw> {

    List<Cmsmw> query(PojoDto pojoDto);

    Cmsmw querys(String gy);

}




