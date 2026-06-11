package com.mgkj.controller;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mgkj.common.result.Result;
import com.mgkj.entity.CMSMV;
import com.mgkj.entity.User;
import com.mgkj.mapper.UserMapper;
import com.mgkj.service.CMSMVService;
import com.mgkj.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/11:11
 * @Description:    员工信息相关接口
 */
@Slf4j
@RestController
@RequestMapping("/api/cmsmv")
@Api(tags = "员工信息相关接口")
public class CMSMVController {
    @Resource
    private CMSMVService cmsmvService;
    @Resource
    private UserMapper userMapper;


    @GetMapping("/all")
    @ApiOperation("根据条件获取部门员工信息")
    public Result getAll(String MV001, String MV002){
        return Result.ok(cmsmvService.getAll(MV001,MV002));
    }
    //MV001 UDF01
    @PostMapping("/login")
    @ApiOperation("核对登录信息")
    public Result login(@RequestBody LoginVo loginVo){
////         易飞
//        LambdaQueryWrapper<CMSMV> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(CMSMV::getMV001, loginVo.getMV001())
//                .eq(CMSMV::getUDF04,loginVo.getUDF01());
//        CMSMV cmsmv = cmsmvService.getOne(wrapper);
//        String password = StringUtils.toStringTrim(cmsmv.getUDF04());
//        if (password.equals(loginVo.getUDF01())){
//            return Result.ok(loginVo.getMV001());
//        }else {
//            return Result.fail();
//        }
//    }
        // E10
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getLOGONNAME,loginVo.getMV001()).select(User::getLOGONNAME,User::getUDF026);
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
        if (user!=null){
                if (user.getUDF026().equals(loginVo.getUDF01())){
                    return Result.ok(loginVo.getMV001());
                }
        }
        return Result.fail();
    }
}
