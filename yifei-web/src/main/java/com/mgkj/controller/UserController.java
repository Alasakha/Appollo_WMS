package com.mgkj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgkj.common.jwt.JwtHelper;
import com.mgkj.common.result.Result;
import com.mgkj.common.utils.MD5;
import com.mgkj.dto.UserLoginDto;
import com.mgkj.entity.CMSMV;
import com.mgkj.entity.Employee;
import com.mgkj.entity.auth.SysMenu;
import com.mgkj.mapper.CMSMVMapper;
import com.mgkj.mapper.auth.SysUserRoleMapper;
import com.mgkj.service.auth.SysMenuService;
import com.mgkj.vo.CMSMVVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ssy
 * @date: 2024-01-09
 * @Description:
 */
@RestController
@Api(tags = "登录接口")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private CMSMVMapper cmsmvMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("员工登入接口")
    @PostMapping("/cmsmv")
    public Result selectlogin(@RequestBody UserLoginDto userLoginDto) {
        // 根据账号查询员工
        CMSMVVo cmsm = cmsmvMapper.selectByUserId(userLoginDto.getUserId());
        Map<String,Object> map = new HashMap<>();
        if (cmsm==null){
            return Result.fail("账号不存在!");
        }
        //设置初始密码为123
        if(cmsm.getUDF01().equals("")) {
            cmsmvMapper.passwordInit(userLoginDto.getUserId());
        }
        //更新完再查一次
        CMSMVVo cmsmv = cmsmvMapper.selectByUserId(userLoginDto.getUserId());

        if (userLoginDto.getPassword()==null) {
            return Result.fail("请输入密码！");
        }

        if (userLoginDto.getPassword().equals(cmsmv.getUDF01())) {
            String token= JwtHelper.createToken(1L,userLoginDto.getUserId());
            map.put("token", token);
            map.put("CMSMVVo",cmsmv);
            //查询对应权限
            List<Long> roleIds = sysUserRoleMapper.getRoleIdByUserId(userLoginDto.getUserId());
            List<SysMenu> menulist = sysMenuService.findMenuByRoleIdList(roleIds);
            map.put("menulist",menulist);
            return Result.ok(map).message("登录成功!");
        }
        return Result.fail().message("密码错误！");
    }



    @ApiOperation("员工修改密码接口")
    @PostMapping("/updagecmsmv")
    public Result updatecmsmv(@RequestBody UserLoginDto userLoginDto){
        // 根据账号查询员工
        CMSMVVo cmsmv = cmsmvMapper.selectByUserId(userLoginDto.getUserId());
        if (userLoginDto.getPassword()==null ||userLoginDto.getOldpassword()==null){
            return Result.fail("新密码和旧密码不能为空！");
        }
        if (userLoginDto.getOldpassword().equals(cmsmv.getUDF01())){
            cmsmv.setUDF01(userLoginDto.getPassword());
            cmsmvMapper.updateByUserId(cmsmv);
            return Result.ok().message("修改密码成功!");
        } else {
            return Result.fail().message("密码错误，不能修改密码！");
        }
    }


    @ApiOperation("获取员工列表")
    @PostMapping("/getEmployee")
    public Result<List<Employee>> getEmployee(@RequestParam String employeeName){
        List<Employee> employeeList = cmsmvMapper.getEmployee(employeeName);
        return Result.ok(employeeList);
    }

}
