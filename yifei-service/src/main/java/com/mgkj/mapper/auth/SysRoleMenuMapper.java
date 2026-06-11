package com.mgkj.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.auth.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysRoleMenu> selectListByRoleIdList(@Param("roleIdList") List<Long> roleIdList);

}
