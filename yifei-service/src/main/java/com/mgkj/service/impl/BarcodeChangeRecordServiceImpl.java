package com.mgkj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.BarcodeChangeRecord;
import com.mgkj.mapper.BarcodeChangeRecordMapper;
import com.mgkj.service.BarcodeChangeRecordService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BarcodeChangeRecordServiceImpl 
    extends ServiceImpl<BarcodeChangeRecordMapper, BarcodeChangeRecord>
    implements BarcodeChangeRecordService {

}