package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.WarehouseAndBinDto;
import com.mgkj.entity.SrmBarStorHead;
import com.mgkj.vo.WarehouseAndBinVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @author 28464
* @description 针对表【srm_bar_stor_head(条码仓库库位单头档
)】的数据库操作Mapper
* @createDate 2025-05-16 16:02:43
* @Entity com.mgkj.domain.SrmBarStorHead
*/
@Mapper
@DS("backend")
public interface SrmBarStorHeadMapper extends BaseMapper<SrmBarStorHead> {

    List<WarehouseAndBinVO> listWarehouseAndBin(WarehouseAndBinDto dto);
}




