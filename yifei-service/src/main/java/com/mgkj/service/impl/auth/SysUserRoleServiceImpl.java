package com.mgkj.service.impl.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.auth.SysUserRole;
import com.mgkj.mapper.auth.SysUserRoleMapper;
import com.mgkj.service.auth.SysUserRoleService;
import org.springframework.stereotype.Service;

@Service
@DS("mes")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
