package com.mgkj.mapper;

import com.mgkj.entity.PoArrivalInspectionSd1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 逆水寒
* @description 针对表【PO_ARRIVAL_INSPECTION_SD1(到货检验单测量值/CHT/到貨檢驗單測量值/ENU/Measurement Value for Goods Arrival Inspection Form)】的数据库操作Mapper
* @createDate 2025-07-02 13:33:15
* @Entity com.mgkj.entity.PoArrivalInspectionSd1
*/
@Mapper
public interface PoArrivalInspectionSd1Mapper extends BaseMapper<PoArrivalInspectionSd1> {

    List<PoArrivalInspectionSd1> getMeasurementValueByCheckDUuid(@Param("checkDUuid") String checkDUuid);
}




