package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.SFCTA;
import com.mgkj.entity.SfctaBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/23:30
 * @Description:    工单工艺信息档 Mapper
 */
@Mapper
public interface SFCTAMapper extends BaseMapper<SFCTA> {

    /**
     * 根据 工单单别 工单单号 加工顺序 来获取对应的 工单工艺详细信息
     * @param TA001
     * @param TA002
     * @param TA003
     * @return
     */
    @DS("dscsys")
    @Select("select * from SFCTA where TA001 = #{TA001} and TA002 = #{TA002} and TA003 = #{TA003}")
    SFCTA getByTA001WithTA002WithTA003(String TA001, String TA002, String TA003);

    /**
     * 根据 工单单别 工单单号 来获取对应的 工单工艺详细信息
     * @param dh
     * @return
     */
    @DS("dscsys")
    @Select("SELECT MO_ROUTING_D_ID,TA001+'-'+TA002 AS dh,TA003,TA004,TA005,\n" +
            "        (SELECT MW002 FROM CMSMW WHERE MW001 = TA004) AS name,TA007 AS gz\n" +
            " FROM SFCTA WHERE TA001+'-'+TA002 = #{dh}")
    List<SfctaBo> selectListByDh(String dh);

    @Update("UPDATE SFCTE SET UDF09 ='N' WHERE TE001 = #{single} AND TE002 = #{odd} AND TE003 = #{xh}")
    void updateUdf09(String single, String odd, String xh);
}
