package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.COPTH;
import com.mgkj.entity.COPTJ;
import com.mgkj.vo.RefundVo;
import com.mgkj.vo.SalesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : ssy
 * @date: 2023-12-19
 * @Description:
 */
@Mapper
public interface COPTJMapper extends BaseMapper<COPTJ> {
    @Select("SELECT SUBSTRING(TI003, 1, 6) AS MandY, CAST(ISNULL(SUM(TJ012), 0) AS REAL) AS TJ012 \n" +
            "FROM COPTJ \n" +
            "LEFT JOIN COPTI ON TI001 = TJ001 AND TI002 = TJ002 \n" +
            "WHERE (SUBSTRING(TI003, 1, 4) = SUBSTRING(CONVERT(CHAR(8), DATEADD(YEAR, 0, GETDATE()), 112), 1, 4) \n" +
            "       OR SUBSTRING(TI003, 1, 4) = SUBSTRING(CONVERT(CHAR(8), DATEADD(YEAR, -1, GETDATE()), 112), 1, 4))\n" +
            "GROUP BY SUBSTRING(TI003, 1, 6)\n" +
            "ORDER BY SUBSTRING(TI003, 1, 6)")
    List<RefundVo> getRefund();
}
