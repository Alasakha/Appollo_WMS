package com.mgkj.mapper;

import com.mgkj.entity.AgvTaskInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author JINQQ
* @description 针对表【agv_task_info】的数据库操作Mapper
* @createDate 2026-01-27 13:19:34
* @Entity com.mgkj.entity.AgvTaskInfo
*/
public interface AgvTaskInfoMapper extends BaseMapper<AgvTaskInfo> {

    AgvTaskInfo getAgvTaskInfoByBarcode(@Param("barcode") String barcode);

    List<AgvTaskInfo> getAgvTaskList(@Param("docNo") String docNo);
}




