package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgkj.entity.AgvTaskDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.vo.MaterialBarCodeDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author JINQQ
* @description 针对表【agv_task_detail】的数据库操作Mapper
* @createDate 2026-01-27 13:01:31
* @Entity com.mgkj.entity.AgvTaskDetail
*/
public interface AgvTaskDetailMapper extends BaseMapper<AgvTaskDetail> {

    List<MaterialBarCodeDetailVo> getAgvTaskDetailListByCreatedBy(@Param("createBy") String createBy);

    List<AgvTaskDetail> getAgvTaskDetailList(@Param("taskCode") String taskCode);

}




