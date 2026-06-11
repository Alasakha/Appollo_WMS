package com.mgkj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.UserLog;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-04-07
 */

public interface UserLogService extends IService<UserLog> {

    @Async
    void mySave(UserLog userLog);

}
