package com.mgkj.mapper;

import com.mgkj.entity.IBasics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/11/28/22:43
 * @Description:
 */
public interface IBasicsMapper {

    void add(IBasics iBasics);

    List<IBasics> getByi_uid(Integer i_uid);

    Integer del(Integer i_uid);

}
