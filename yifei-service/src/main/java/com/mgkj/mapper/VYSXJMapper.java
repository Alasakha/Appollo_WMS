package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.VYSXJ;
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
public interface VYSXJMapper extends BaseMapper<VYSXJ> {

    @DS("demo")
    @Select("SELECT SUM(YSJE) FROM V_YSXJ")
    String receivableTotal();

    @DS("demo")
    @Select("SELECT MA002,SUM(YSJE) 'YSJE' FROM V_YSXJ LEFT JOIN COPMA ON TA004=MA001 GROUP BY MA002")
    List<VYSXJ> receivableTotalInfo();
}
