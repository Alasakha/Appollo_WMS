package com.mgkj.mapper.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.auth.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@DS("mes")
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Select("SELECT DISTINCT role_id FROM sys_user_role WHERE user_id = #{userId} and is_deleted=0")
    List<Long> getRoleIdByUserId(String userId);
}
