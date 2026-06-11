package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.VYFXJ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
@Mapper
public interface VYFXJMapper extends BaseMapper<VYFXJ> {

    @DS("demo")
    @Select("SELECT SUM(YFJE) FROM V_YFXJ")
    String receivableTotal();

    @DS("demo")
    @Select("SELECT MA002,SUM(YFJE) 'YFJE' FROM V_YFXJ LEFT JOIN PURMA ON TA004=MA001 GROUP BY MA002")
    List<VYFXJ> receivableTotalInfo();
}
