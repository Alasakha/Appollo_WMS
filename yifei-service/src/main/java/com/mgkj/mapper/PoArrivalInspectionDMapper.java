package com.mgkj.mapper;

import com.mgkj.entity.PoArrivalInspectionD;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 逆水寒
* @description 针对表【PO_ARRIVAL_INSPECTION_D(采购到货检验单计数单身/CHT/採購到貨檢驗單計數單身)】的数据库操作Mapper
* @createDate 2025-05-14 11:01:10
* @Entity com.mgkj.entity.PoArrivalInspectionD
*/


@Mapper
public interface PoArrivalInspectionDMapper extends BaseMapper<PoArrivalInspectionD> {

    void updatePoArrivalInspectionDById(@Param("uuid")String uuid,@Param("decision") Boolean decision,@Param("defectiveQty") BigDecimal defectiveQty);

    void updateBatchById(@Param("list")List<PoArrivalInspectionD> poArrivalInspectionDs);
}




