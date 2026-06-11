package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.common.result.Result;
import com.mgkj.entity.BadTable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:17
 * @Description: 不良信息 接口
 */
public interface BadTableService extends IService<BadTable> {
    /**
     * 添加
     * @param badTable
     * @return
     */
    Result add(BadTable badTable);

    /**
     * 查询全部不良信息
     * @return
     */
    Result<List<BadTable>> selectAll();

    /**
     * 查询不良信息
     * @param badTable
     * @return
     */
    Result select(BadTable badTable);

    /**
     * 修改
     * @param badTable
     * @return
     */
    Result update(BadTable badTable);

    /**
     * 删除
     * @param id
     * @return
     */
    Result delete(int id);
}
