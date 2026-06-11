package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.SalesMarginInfoVo;
import com.mgkj.vo.SalesMarginVo;
import com.mgkj.vo.SalesVo;
import com.mgkj.entity.COPTH;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Mapper
public interface COPTHMapper extends BaseMapper<COPTH> {
    @Select("SELECT SUBSTRING(TG003, 1, 6) AS MandY, CAST(ISNULL(SUM(TH013), 0) AS REAL) AS TH013 \n" +
            "FROM COPTH \n" +
            "LEFT JOIN COPTG ON TH001 = TG001 AND TH002 = TG002 \n" +
            "WHERE (SUBSTRING(TG003, 1, 4) = SUBSTRING(CONVERT(CHAR(8), DATEADD(YEAR, 0, GETDATE()), 112), 1, 4) \n" +
            "OR SUBSTRING(TG003, 1, 4) = SUBSTRING(CONVERT(CHAR(8), DATEADD(YEAR, -1, GETDATE()), 112), 1, 4))\n" +
            "GROUP BY SUBSTRING(TG003, 1, 6)\n" +
            "ORDER BY SUBSTRING(TG003, 1, 6)")
    @DS("demo")
    List<SalesVo> getSales();

    @DS("demo")
    @Select(" SELECT " +
            "    SUBSTRING(TG003, 1, 6) MandY," +
            "    SUM(TH013 - LA013) SalesMargin " +
            "FROM " +
            "    COPTH " +
            "    LEFT JOIN COPTG ON TH001 = TG001 AND TH002 = TG002 " +
            "    LEFT JOIN COPMA ON TG004 = MA001 " +
            "    LEFT JOIN INVLA ON TH001 = LA006 AND TH002 = LA007 AND TH003 = LA008 " +
            "WHERE (MONTH(TG003) = MONTH(GETDATE()) OR MONTH(TG003) = MONTH(DATEADD(MONTH, -1, GETDATE()))) " +
            "GROUP BY SUBSTRING(TG003, 1, 6)")
    List<SalesMarginVo> getSalesMarginByNowMonth();

    @DS("demo")
    @Select("SELECT SUBSTRING(TG003,1,6) MandY,SUM(TH013-LA013) SalesMargin " +
            "FROM COPTH LEFT JOIN COPTG " +
            "ON TH001=TG001 " +
            "AND TH002=TG002 " +
            "LEFT JOIN COPMA " +
            "ON TG004=MA001 " +
            "LEFT JOIN INVLA " +
            "ON TH001=LA006 " +
            "AND TH002=LA007 " +
            "AND TH003=LA008 " +
            "WHERE SUBSTRING(TG003,1,6)>=SUBSTRING(CONVERT(CHAR(8),DATEADD(MONTH,-12,GETDATE()),112),1,4) GROUP BY SUBSTRING(TG003,1,6)")
    List<SalesMarginVo> getSalesMarginByNowYear();

    @DS("demo")
    @Select("SELECT MA002,SUM(TH013-LA013) 'SalesMargin' " +
            "FROM COPTH " +
            "LEFT JOIN COPTG " +
            "ON TH001=TG001 " +
            "AND TH002=TG002 " +
            "LEFT JOIN COPMA " +
            "ON TG004=MA001 " +
            "LEFT JOIN INVLA " +
            "ON TH001=LA006 " +
            "AND TH002=LA007 " +
            "AND TH003=LA008 " +
            "WHERE SUBSTRING(TG003,1,6)=SUBSTRING(CONVERT(CHAR(8),DATEADD(MONTH,#{month},GETDATE()),112),1,6) " +
            "GROUP BY MA002")
    List<SalesMarginInfoVo> getSalesMarginInfo(Integer month);

//    @DS("demo")
//    @Select("SELECT \n" +
//            "    (\n" +
//            "        (SUM(CASE WHEN MONTH(TG003) = MONTH(GETDATE()) THEN TH013 - LA013 ELSE 0 END) -\n" +
//            "        SUM(CASE WHEN MONTH(TG003) = MONTH(DATEADD(MONTH, -1, GETDATE())) THEN TH013 - LA013 ELSE 0 END))\n" +
//            "        / NULLIF(SUM(CASE WHEN MONTH(TG003) = MONTH(DATEADD(MONTH, -1, GETDATE())) THEN TH013 - LA013 ELSE 0 END), 0)\n" +
//            "    ) AS Huanbi\n" +
//            "FROM\n" +
//            "    COPTH\n" +
//            "LEFT JOIN \n" +
//            "    COPTG ON TH001 = TG001 AND TH002 = TG002\n" +
//            "LEFT JOIN \n" +
//            "    COPMA ON TG004 = MA001\n" +
//            "LEFT JOIN \n" +
//            "    INVLA ON TH001 = LA006 AND TH002 = LA007 AND TH003 = LA008")
//    Double getSalesMarginHuanBi();
}
