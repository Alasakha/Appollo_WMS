package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.DeliverySummary;
import com.mgkj.mapper.DeliverySummaryMapper;
import com.mgkj.service.DeliverySummaryService;
import org.springframework.stereotype.Service;

@Service
public class DeliverySummaryServiceImpl extends ServiceImpl<DeliverySummaryMapper, DeliverySummary>
        implements DeliverySummaryService {
    // 无需实现基础方法，ServiceImpl已提供
    // 可添加自定义业务逻辑
}