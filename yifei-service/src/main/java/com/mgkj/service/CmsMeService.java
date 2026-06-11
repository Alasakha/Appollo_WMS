package com.mgkj.service;

import com.mgkj.common.result.Result;
import com.mgkj.entity.CMSME;

import java.util.List;

public interface CmsMeService {

    Result<List<CMSME>> getAll();
}
