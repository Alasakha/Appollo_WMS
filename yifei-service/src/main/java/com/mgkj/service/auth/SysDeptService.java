package com.mgkj.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.entity.auth.SysDept;

import java.util.List;

/**
 * @author : ssy
 * @date: 2024-03-27
 * @Description:
 */
public interface SysDeptService extends IService<SysDept> {
    List<SysDept> findNodes();

    void removeDeptById(Long id);

    void updateStatus(Long id, Integer status);
}
