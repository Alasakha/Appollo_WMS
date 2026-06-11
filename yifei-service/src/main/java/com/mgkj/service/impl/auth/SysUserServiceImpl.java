package com.mgkj.service.impl.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.auth.SysRole;
import com.mgkj.entity.auth.SysUser;
import com.mgkj.mapper.auth.SysUserMapper;
import com.mgkj.service.auth.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@DS("mes")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    //更新状态
    @Override
    public void updateStatus(Long id, Integer status) {
        //根据userid查询用户对象
        SysUser sysUser = baseMapper.selectById(id);
        //设置修改状态值
        sysUser.setStatus(status);
        //调用方法进行修改
        baseMapper.updateById(sysUser);
    }

    //根据用户名进行查询
    @Override
    public SysUser getUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,username);
        SysUser sysUser = baseMapper.selectOne(wrapper);
        return sysUser;
    }

    @Override
    public Map<String, Object> getCurrentUser() {
        return null;
    }


    /**
     * 获取角色列表
     * @param keyword
     * @return
     */
    public List<SysRole> getRoleList(String keyword) {
        return baseMapper.getRoleList(keyword);
    }

//    @Override
//    public Map<String, Object> getCurrentUser() {
//        SysUser sysUser = baseMapper.selectById(SecurityUtils.getLoginUser().getUsername());
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", sysUser.getName());
//        map.put("phone", sysUser.getPhone());
//        return map;
//    }
}
