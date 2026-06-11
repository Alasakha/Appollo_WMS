package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.Pojo;
import com.mgkj.entity.Mocty;
import com.mgkj.entity.Qmsmg;
import org.apache.ibatis.annotations.Select;

/**
* @author 14501
* @description 针对表【QMSMG】的数据库操作Mapper
* @createDate 2024-03-22 13:56:23
* @Entity com.Mg.entity.Qmsmg
*/
@DS("ZJDF")
public interface QmsmgMapper extends BaseMapper<Qmsmg> {

    @Select("WITH Results AS (\n" +
            "    SELECT\n" +
            "        TY001,\n" +
            "        TY002,\n" +
            "        TY003,\n" +
            "        TY009,\n" +
            "        TY019,\n" +
            "        MOCTA.TA006,\n" +
            "        MOCTA.TA021,\n" +
            "        MOCTA.TA035,\n" +
            "        MOCTA.TA034,\n" +
            "        MAX(QMSMG.MG008) AS TA004,\n" +
            "ROW_NUMBER() OVER (ORDER BY TY001, TY002, TY003) AS RowNum\n" +
            "    FROM MOCTY\n" +
            "LEFT JOIN MOCTA ON MOCTY.TY001 = MOCTA.TA001\n" +
            "               AND MOCTY.TY002 = MOCTA.TA002\n" +
            "lEFT JOIN QMSMG ON MOCTA.TA006 = QMSMG.MG002\n" +
            "JOIN SFCTA ON MOCTY.TY001 = SFCTA.TA001 AND MOCTY.TY002 = SFCTA.TA002\n" +
            "WHERE MOCTY.TY003 = #{time} AND  MG020 = #{checky}\n" +
            "AND RTRIM(TY001)+'-'+RTRIM(TY002)+'-'+RTRIM(TY003)+'-'+RTRIM(TY009)+'-'+RTRIM(TY019) = #{code}\n"+
            "    GROUP BY TY001, TY002, TY003, TY009, TY019, MOCTA.TA006,MOCTA.TA021,MOCTA.TA034,MOCTA.TA035\n" +
            ")\n" +
            "SELECT TY001, TY002, TY003, TY009, TY019, TA006,TA021,TA034,TA035,TA004\n" +
            "FROM Results  JOIN MOCTY_STATUS ON RTRIM(TY001)+'-'+RTRIM(TY002)+'-'+RTRIM(TY003)+'-'+RTRIM(TY009)+'-'+RTRIM(TY019) != MOCTY_STATUS.pid")
    Mocty selectQMsg(Pojo pojo);
}




