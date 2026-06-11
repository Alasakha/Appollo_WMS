package com.mgkj.service.impl.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.auth.SysRoleMenu;
import com.mgkj.mapper.auth.SysRoleMenuMapper;
import com.mgkj.service.auth.SysRoleMenuService;
import org.springframework.stereotype.Service;

@Service
@DS("mes")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}
