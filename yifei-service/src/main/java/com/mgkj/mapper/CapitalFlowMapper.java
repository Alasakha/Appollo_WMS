package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.CapitalFlowVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/1/9
 * @Description
 */
@Mapper
public interface CapitalFlowMapper extends BaseMapper<CapitalFlowVo> {

    List<CapitalFlowVo> getCapitalFlowInfo();

    List<CapitalFlowVo> getSumCapitalFlow();

}
