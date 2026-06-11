package com.mgkj.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.auth.SysRole;
import com.mgkj.entity.auth.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    //更新状态
    void updateStatus(Long id, Integer status);

    //根据用户名进行查询
    SysUser getUserByUserName(String username);

    Map<String, Object> getCurrentUser();

    List<SysRole> getRoleList(String keyword);
}
