package com.mgkj.service;

import com.mgkj.common.result.Result;
import com.mgkj.dto.IHeadDTO;
import com.mgkj.dto.IHeadPageDTO;
import com.mgkj.dto.Pojo;
import com.mgkj.entity.PageResult;

import java.util.List;

public interface IHeadService {

    void add(IHeadDTO iHeadDTO);

    PageResult page(IHeadPageDTO iHeadPageDTO);

    IHeadDTO getAll(Integer uid);

    List<IHeadDTO> getAlllList(List<Integer> ids);

    IHeadDTO allId(Integer uid);

    List<IHeadDTO> allIds(Pojo pojo);

    Result del(Integer id);
}
