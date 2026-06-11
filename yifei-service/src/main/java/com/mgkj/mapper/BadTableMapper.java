package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.BadTable;
import com.mgkj.service.BadTableService;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:19
 * @Description:    单据不良信息 mapper
 */
@Mapper
public interface BadTableMapper extends BaseMapper<BadTable> {
    /**
     * 插入
     * @param badTable
     * @return
     */
    int add(BadTable badTable);

    /**
     * 获取全部 不良信息
     * @return
     */
    @Select("select * from badTable")
    List<BadTable> selectAll();

    /**
     * 查询
     * @param badTable
     * @return
     */
    BadTable find(BadTable badTable);

    /**
     * 更新
     * @param badTable
     * @return
     */
    int modify(BadTable badTable);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete badTable where id = #{id}")
    int delete(int id);
}
