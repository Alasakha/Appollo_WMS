package com.mgkj.service.impl.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.auth.SysDept;
import com.mgkj.mapper.auth.SysDeptMapper;
import com.mgkj.service.auth.SysDeptService;
import com.mgkj.service.impl.auth.utils.DeptHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-03-27
 * @Description:
 */
@Service
@DS("mes")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDept> findNodes() {
        //1 查询所有菜单数据
        List<SysDept> sysDeptList = baseMapper.selectList(null);
        List<SysDept> resultList = DeptHelper.buildTree(sysDeptList);
        return resultList;
    }

    @Override
    public void removeDeptById(Long id) {
        //判断当前菜单是否有下一层菜单
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDept::getParentId,id);
        Long count = baseMapper.selectCount(wrapper);
        baseMapper.deleteById(id);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        LambdaQueryWrapper<SysDept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDept::getId,id);
        SysDept sysDept = baseMapper.selectOne(wrapper);
        sysDept.setStatus(status);
        baseMapper.update(sysDept,wrapper);
    }
}
