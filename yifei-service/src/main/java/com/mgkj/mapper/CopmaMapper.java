package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mgkj.dto.SelectDto;
import com.mgkj.entity.Copma;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.CustomVo;
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
public interface CopmaMapper extends BaseMapper<Copma> {

    @DS("demo")
    @Select("SELECT MA002,MA001,MA003,MA102,MAB01,MA066,MA065,MA026,MA024,MA064,MA014,MA075,MA036,MA037,MA043,MA038,MA041,MA031\n" +
            "       ,MA095,MA115,MA021,MA022,MA028,MA029,MA032,MA033,MA034,MA088,MA089,MA010,MA076,MA017,MA018,MA004,MA016,MA085\n" +
            "\t   ,MA071,MA005,MA006,MA008,MA007,MA114,MA009,MA025,MA023,MA027,MF001,NJ001,NJ002,NA002,NA003,IA001,IA002\n" +
            " FROM COPMA LEFT JOIN CMSMR\n" +
            " ON MR002 = MA097\n" +
            " LEFT JOIN CMSMV\n" +
            " ON MA097 = MV001\n" +
            " LEFT JOIN CMSMF\n" +
            " ON MF001 = MA014\n" +
            " LEFT JOIN CMSNJ\n" +
            " ON MA041 = NJ001\n" +
            " LEFT JOIN CMSNA\n" +
            " ON MA031 = NA002\n" +
            " LEFT JOIN CMSIA\n" +
            " ON MA115 = IA001" +
            " WHERE MA001 = #{MA001}")
    CustomVo getDetailedCustomerInfo(String ma001);

    @DS("demo")
    List<CustomVo> getCustomerInfoByTerm(SelectDto selectDto);
}
