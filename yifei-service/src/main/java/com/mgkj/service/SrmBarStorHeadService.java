package com.mgkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.common.result.Result;
import com.mgkj.dto.WarehouseAndBinDto;
import com.mgkj.entity.SrmBarStorHead;

/**
* @author 28464
* @description 针对表【srm_bar_stor_head(条码仓库库位单头档
)】的数据库操作Service
* @createDate 2025-05-16 16:02:43
*/
public interface SrmBarStorHeadService extends IService<SrmBarStorHead> {

    Result listWarehouseAndBin(WarehouseAndBinDto dto);
}
