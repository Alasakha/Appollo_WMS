package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.DeliverySummary;
import com.mgkj.vo.DeliveryDetailsBarcodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DeliverySummaryMapper extends BaseMapper<DeliverySummary> {
    DeliverySummary getDeliverySummary(@Param("barcode") String barcode);

    List<DeliveryDetailsBarcodeVo> getDeliveryDetailsBarcode(@Param("uuid") String uuid);

    int deleteByDeliveryNumer(@Param("deliveryNumer") String deliveryNumer);

    List<DeliverySummary> deliveryIsExist(@Param("number") String number);

    BigDecimal selectQty(@Param("id") String id);

    int updateQty(@Param("qty") BigDecimal qty,@Param("id") String id);

    int isDeliverySummaryRecord(@Param("deliveryNumer") String deliveryNumer);
}