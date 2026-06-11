package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.common.result.Result;
import com.mgkj.entity.BadInfoTable;
import com.mgkj.entity.BadTable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:17
 * @Description:    单据不良信息 接口
 */
public interface BadInfoTableService extends IService<BadInfoTable> {
    /**
     * 添加
     * @param badInfoTables
     * @return
     */
    Result add(List<BadInfoTable> badInfoTables);

    /**
     * 查询全部不良信息
     * @return
     */
    Result<List<BadInfoTable>> selectAll();

    /**
     * 查询不良信息
     * @param badInfoTable
     * @return
     */
    Result select(BadInfoTable badInfoTable);

    /**
     * 修改
     * @param badInfoTable
     * @return
     */
    Result update(BadInfoTable badInfoTable);

    /**
     * 删除
     * @param id
     * @return
     */
    Result delete(int id);
}
