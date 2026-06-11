package com.mgkj.service.impl;

import com.mgkj.common.result.Result;
import com.mgkj.entity.CMSME;
import com.mgkj.mapper.CMSMEMapper;
import com.mgkj.service.CmsMeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CmsMeServiceImpl implements CmsMeService{

    @Resource
    private CMSMEMapper cmsmeMapper;

    @Override
    public Result<List<CMSME>> getAll() {
       try {
           List<CMSME> all = this.cmsmeMapper.getAll();
           if (all.isEmpty()){
               return Result.fail();
           }
           return Result.ok(all);
       }catch (Exception e){
           throw new RuntimeException();
       }
    }
}
