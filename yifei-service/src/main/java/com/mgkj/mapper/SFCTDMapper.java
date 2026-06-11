package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.SFCTD;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author : ssy
 * @date: 2023-12-20
 * @Description:
 */
public interface SFCTDMapper extends BaseMapper<SFCTD> {
    @DS("dscsys")
    @Insert("Insert into SFCTD(CREATOR,CREATE_DATE,TD001,TD002,TD003,TD005,TD004,UDF01)" +
            "values (#{CREATOR},#{CREATE_DATE},#{TD001},#{TD002},#{TD003},#{TD005}," +
            "#{TD004},#{UDF01})")
    void save(SFCTD saveSFCTD);

    /**
     * 删除报工单头
     * @param single
     * @param odd
     */
    @DS("dscsys")
    @Delete("DELETE FROM SFCTD WHERE TD001 = #{single} AND TD002 = #{odd}")
    void deleteBg(@Param("single") String single,@Param("odd") String odd);
}
