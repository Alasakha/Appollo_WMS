package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.OutPutVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-15
 * @Description:
 */
public interface OutPutMapper extends BaseMapper<OutPutVo> {

    List<OutPutVo> getOutPut(Integer type,String workShop);

    List<OutPutVo> getOutPutInfo(Integer type,Integer page,Integer pageSize,String warehousingDate,String workShop);

    Integer getCount(Integer type,String warehousingDate,String workShop);

    Integer getOutPutCount();

    List<OutPutVo> getAllWorkShop();
}
