package com.mgkj.mapper.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.auth.SysRole;
import com.mgkj.entity.auth.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-02-02
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @DS("mes")
    List<SysRole> getRoleList(@Param("keyword") String keyword);
}
