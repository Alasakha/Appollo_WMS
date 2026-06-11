package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.common.result.Result;
import com.mgkj.entity.BadTable;
import com.mgkj.mapper.BadTableMapper;
import com.mgkj.service.BadTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:17
 * @Description:    不良信息 实现类
 */
@Service
public class BadTableServiceImpl extends ServiceImpl<BadTableMapper, BadTable>  implements BadTableService {

    @Resource
    private BadTableMapper badTableMapper;

    @Override
    public Result add(BadTable badTable) {

        if (badTable.getBadName() == null || badTable.getBadName() =="") return Result.fail("不良编号不能为空！");

        BadTable build = BadTable.builder().badNumber(badTable.getBadNumber()).build();

        BadTable select = badTableMapper.find(build);
        if (select != null ) return Result.fail("已经存在不良编号");

        int insert = badTableMapper.add(badTable);
        return Result.ok();
    }

    @Override
    public Result<List<BadTable>> selectAll() {
        List<BadTable> badTables = badTableMapper.selectAll();
        return Result.ok(badTables);
    }

    @Override
    public Result select(BadTable badTable) {
        BadTable select = badTableMapper.find(badTable);
        if (select ==null) return Result.fail("未查到相关数据");
        return Result.ok(select);
    }

    @Override
    public Result update(BadTable badTable) {
        int update = badTableMapper.modify(badTable);
        if (update == 0) return Result.fail("修改失败");
        return Result.ok();
    }

    @Override
    public Result delete(int id) {
        int delete = badTableMapper.delete(id);
        if (delete == 0)  return Result.fail("删除失败");
        return Result.ok();
    }
}
