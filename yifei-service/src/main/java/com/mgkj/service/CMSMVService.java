package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.CMSMV;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/11:14
 * @Description:    员工信息档 接口
 */
public interface CMSMVService extends IService<CMSMV> {

    /**
     * 获取员工全部信息
     * @return
     */
    List<CMSMV> getAll(String MV001,String MV002);

}
