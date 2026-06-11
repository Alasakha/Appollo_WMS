package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgkj.vo.ReceivableVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-16
 * @Description:
 */
@Mapper
public interface ReceivableMapper extends BaseMapper<ReceivableVo> {

    IPage<ReceivableVo> getReceivable(Page<ReceivableVo> page1);

    List<ReceivableVo> getReceivableInfo(String single, String singleNumber);
}
