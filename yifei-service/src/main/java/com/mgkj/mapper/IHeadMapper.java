package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.mgkj.dto.IHeadPageDTO;
import com.mgkj.entity.IHead;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface IHeadMapper  extends BaseMapper<IHead> {

    Integer add(IHead iHead);

    Page<IHead> page(IHeadPageDTO iHeadPageDTO);

    IHead getByUid(Integer uid);

    IHead getbgd(String dt,String ds,String gx);

    List<IHead> getByUidS(String uid,String ks,String js);

    IHead getByUidWithType(String type, Integer uid);

    List<IHead> queryTa001IHeadList(String TA001,String TA002);

    Integer del(Integer uid);
}
