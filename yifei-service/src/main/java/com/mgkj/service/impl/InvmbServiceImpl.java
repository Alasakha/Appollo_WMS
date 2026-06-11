package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.exception.YiFeiException;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Invmb;
import com.mgkj.mapper.INVMBMapper;
import com.mgkj.service.InvmbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvmbServiceImpl extends ServiceImpl<INVMBMapper, Invmb> implements InvmbService {

    @Resource
    private INVMBMapper invmbMapper;

    @Override
    public Result query(PojoDto pojoDto) {
        try {
            Map<String,Object> map =new HashMap<>();
            PageHelper.startPage(pojoDto.getCurrent(),pojoDto.getSize());
            List<Invmb> query = this.invmbMapper.query(pojoDto);
            PageInfo<Invmb> pageInfo = new PageInfo<>(query);
            map.put("page",pageInfo.getPages());
            map.put("data",pageInfo.getList());
            map.put("total",pageInfo.getTotal());
            return Result.ok(map);
        }catch (Exception e){
            throw new YiFeiException(500,"分页发生异常");
        }
    }
}
