package com.mgkj.mapper;

import com.mgkj.entity.IBody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/11/28/22:43
 * @Description:
 */
public interface IBodyMapper {
    Integer add(IBody iBody);

    List<IBody> getAllByI_uid(Integer i_uid);

    List<IBody> getAllByI_uids(Integer i_uid);

    Integer del(Integer i_uid);
}
