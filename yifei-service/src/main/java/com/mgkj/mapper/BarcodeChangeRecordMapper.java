package com.mgkj.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.PurchaseCountApprovalDto;
import com.mgkj.entity.BarcodeChangeRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("backend")
public interface BarcodeChangeRecordMapper extends BaseMapper<BarcodeChangeRecord> {

}