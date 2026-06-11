package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgkj.entity.MOCTA;
import com.mgkj.mapper.MOCTAMapper;
import com.mgkj.service.MoctaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.vo.WorkOrderOvertimeVo;
import com.mgkj.vo.WorkOrderVo;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mgkj
 * @since 2024-01-09
 */
@Service
public class MoctaServiceImpl extends ServiceImpl<MOCTAMapper, MOCTA> implements MoctaService {

    @Autowired
    private MOCTAMapper moctaMapper;

    @Override
    public Integer getOverdueNum() {
        return moctaMapper.getOverdueNum();
    }

    @Override
    public List<WorkOrderVo> getOverdueDetails() {
        return moctaMapper.getOverdueDetails();
    }

    @Override
    public IPage<WorkOrderVo> getOverdueDetailsByWorkCenter(Page<WorkOrderVo> page, String workCenter) {
        return moctaMapper.getOverdueDetailsByWorkCenter(page, workCenter);
    }

    @Override
    public List<WorkOrderVo> getOverdueInfo() {
        return moctaMapper.getOverdueInfo();
    }

    @Override
    public String getOverdueCount(String workCenter) {
        return moctaMapper.getOverdueCount(workCenter);
    }

    @Override
    public List<WorkOrderOvertimeVo> getOverdueDetailsInfo(String workCenter,String single, String singleNumber) {
        return moctaMapper.getOverdueDetailsInfo(workCenter,single,singleNumber);
    }
}
