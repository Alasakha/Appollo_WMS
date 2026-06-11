package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.PurchaseOrder;
import com.mgkj.entity.PurchaseOrderD;
import com.mgkj.vo.WarehouseInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/1/24
 * @Description
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {
    List<PurchaseOrder> getErpPurchaseOrder();

    List<PurchaseOrderD> getErpPurchaseOrderD();

    List<WarehouseInfoVo> listWarehouse();

}
