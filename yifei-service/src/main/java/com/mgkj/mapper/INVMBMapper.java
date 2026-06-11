package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Invmb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : ssy
 * @date: 2023-12-22
 * @Description:
 */
@Mapper
public interface INVMBMapper extends BaseMapper<Invmb> {
    @DS("dscsys")
    @Select("SELECT MB029 FROM INVMB WHERE MB001 = #{TE007}")
    String getMB029(String TE007);

    @DS("dscsys")
    List<Invmb> query(PojoDto pojoDto);

    @DS("dscsys")
    Invmb querys(String ph);
}
