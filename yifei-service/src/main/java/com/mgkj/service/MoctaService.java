package com.mgkj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgkj.entity.MOCTA;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.vo.WorkOrderOvertimeVo;
import com.mgkj.vo.WorkOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mgkj
 * @since 2024-01-09
 */
public interface MoctaService extends IService<MOCTA> {

    Integer getOverdueNum();

    List<WorkOrderVo> getOverdueDetails();

    IPage<WorkOrderVo> getOverdueDetailsByWorkCenter(Page<WorkOrderVo> page, String workCenter);

    List<WorkOrderVo> getOverdueInfo();

    String getOverdueCount(String workCenter);

    List<WorkOrderOvertimeVo> getOverdueDetailsInfo(String workCenter,String single, String singleNumber);
}
