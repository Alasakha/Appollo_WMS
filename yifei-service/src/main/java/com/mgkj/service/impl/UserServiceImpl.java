package com.mgkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.User;
import com.mgkj.mapper.UserMapper;
import com.mgkj.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 14501
* @description 针对表【USER(登录用户/CHT/登錄使用者/ENU/Login User)】的数据库操作Service实现
* @createDate 2024-05-10 14:04:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




