package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.PurchaseSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PurchaseSummaryMapper extends BaseMapper<PurchaseSummary> {

    int bacodeisExist(@Param("createBy") String createBy,@Param("barcode") String barcode);


    @Select("select * from purchase_summary where id=#{id}")
    PurchaseSummary selectPurchaseSummary(@Param("id") String id);
}
