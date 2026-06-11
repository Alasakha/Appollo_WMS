package com.mgkj.service.impl;


import com.mgkj.mapper.SyncMapper;
import com.mgkj.service.SyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class SyncServiceImpl implements SyncService {

    @Resource
    private SyncMapper syncMapper;

    public SyncServiceImpl(SyncMapper syncMapper) {
        this.syncMapper = syncMapper;
    }

    // 每15分钟执行一次
//    @PostConstruct
    @Async
    @Override
//    @Scheduled(cron = "* 0/15 * * * ?")
    @Transactional
    public void syncAll() {
        syncMapper.syncDeliveryList();
        syncMapper.syncDeliveryDetails();
        syncMapper.syncBarcodes();
    }
}
