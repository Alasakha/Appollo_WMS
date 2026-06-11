package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.common.result.Result;
import com.mgkj.entity.BadInfoTable;
import com.mgkj.entity.BadTable;
import com.mgkj.mapper.BadInfoTableMapper;
import com.mgkj.mapper.BadTableMapper;
import com.mgkj.service.BadInfoTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:17
 * @Description:    单据不良信息 实现类
 */
@Service
public class BadInfoTableServiceImpl extends ServiceImpl<BadInfoTableMapper,BadInfoTable> implements BadInfoTableService {
    @Resource
    private BadInfoTableMapper badInfoTableMapper;

    @Transactional
    @Override
    public Result add(List<BadInfoTable> badInfoTables) {
        for (BadInfoTable badInfoTable : badInfoTables) {
            int insert = badInfoTableMapper.add(badInfoTable);
        }
        return Result.ok();
    }

    @Override
    public Result<List<BadInfoTable>> selectAll() {
        List<BadInfoTable> badTables = badInfoTableMapper.selectAll();
        return Result.ok(badTables);
    }

    @Override
    public Result select(BadInfoTable badInfoTable) {
        BadInfoTable select = badInfoTableMapper.find(badInfoTable);
        if (select ==null) return Result.fail("未查到相关数据");
        return Result.ok(select);
    }

    @Override
    public Result update(BadInfoTable badInfoTable) {
        int update = badInfoTableMapper.modify(badInfoTable);
        if (update == 0) return Result.fail("修改失败");
        return Result.ok();
    }

    @Override
    public Result delete(int id) {
        int delete = badInfoTableMapper.delete(id);
        if (delete == 0)  return Result.fail("删除失败");
        return Result.ok();
    }

}
