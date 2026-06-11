package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.exception.YiFeiException;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Cmsmw;
import com.mgkj.mapper.CmsmwMapper;
import com.mgkj.service.CmsmwService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 14501
* @description 针对表【CMSMW】的数据库操作Service实现
* @createDate 2024-07-05 16:41:14
*/
@Service
public class CmsmwServiceImpl extends ServiceImpl<CmsmwMapper, Cmsmw> implements CmsmwService {

    @Resource
    private CmsmwMapper cmsmwMapper;

    @Override
    public Result query(PojoDto pojoDto) {
        try {
            Map<String,Object> map =new HashMap<>();
            PageHelper.startPage(pojoDto.getCurrent(),pojoDto.getSize());
            List<Cmsmw> cmsmwIPage = this.cmsmwMapper.query(pojoDto);
            PageInfo<Cmsmw> pageInfo = new PageInfo<>(cmsmwIPage);
            map.put("page",pageInfo.getPages());
            map.put("data",pageInfo.getList());
            map.put("total",pageInfo.getTotal());
            return Result.ok(map);
        }catch (Exception e){
            throw new YiFeiException(500,"分页发生异常");
        }
    }
}




