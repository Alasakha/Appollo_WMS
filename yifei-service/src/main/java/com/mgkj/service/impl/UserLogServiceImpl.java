package com.mgkj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.UserLog;
import com.mgkj.mapper.UserLogMapper;
import com.mgkj.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-04-07
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public void mySave(UserLog userLog) {
        userLogMapper.insert(userLog);
    }
}
