//package com.mgkj.controller.WMS;
//
//import com.mgkj.entity.User;
//import com.mgkj.result.Result;
//import com.mgkj.service.UserService;
//import com.mgkj.utils.JwtUtil;
//import io.jsonwebtoken.Claims;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author yyyjcg
// * @date 2024/1/12
// * @Description
// */
//@RestController
//@RequestMapping("/user")
//@Api("用户相关接口")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public Result login(@RequestBody User user){
//        //登录
//        return userService.login(user);
//    }
//
//    /**
//     * 退出登录
//     * @return
//     */
//    @GetMapping("/logout")
//    public Result logout(){
//        return userService.logout();
//    }
//
//    @PostMapping("/registerUser")
//    public Result registerUser(@RequestBody User user){
//        return userService.registerUser(user);
//    }
//
//    @PostMapping("/getUserInfo")
//    public User getUserInfo(HttpServletRequest request){
//        String token = request.getHeader("token");
//        //解析token
//        String userId;
//        try {
//            //解析给定的JWT字符串
//            Claims claims = JwtUtil.parseJWT(token);
//            //获取JWT的Payload部分中包含的用户ID（Subject）
//            userId = claims.getSubject();
//        } catch (Exception e) {
//            throw new RuntimeException("token非法");
//        }
//        return userService.getLoginUserByUserId(userId);
//    }
//
//
//
//
//}
