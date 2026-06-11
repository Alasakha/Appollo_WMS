package com.mgkj.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.auth.SysRole;
import com.mgkj.vo.auth.AssginRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    //1 查询所有角色 和 当前用户所属角色
    Map<String, Object> findRoleDataByUserId(Long userId);

    //2 为用户分配角色
    void doAssign(AssginRoleVo assginRoleVo);
}
