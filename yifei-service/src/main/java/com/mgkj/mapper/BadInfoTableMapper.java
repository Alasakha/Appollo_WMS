package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.BadInfoTable;
import com.mgkj.entity.BadTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:19
 * @Description:    单据不良信息 mapper
 */
@Mapper
public interface BadInfoTableMapper extends BaseMapper<BadInfoTable> {

    /**
     * 插入
     * @param badInfoTable
     * @return
     */
    int add(BadInfoTable badInfoTable);

    /**
     * 获取全部 不良信息
     * @return
     */
    @Select("select * from badInfoTable")
    List<BadInfoTable> selectAll();

    /**
     * 查询
     * @param badInfoTable
     * @return
     */
    BadInfoTable find(BadInfoTable badInfoTable);

    /**
     * 更新
     * @param badInfoTable
     * @return
     */
    int modify(BadInfoTable badInfoTable);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete badInfoTable where id = #{id}")
    int delete(int id);
}
