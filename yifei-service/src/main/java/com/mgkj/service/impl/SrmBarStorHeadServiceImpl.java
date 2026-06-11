package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.WarehouseAndBinDto;
import com.mgkj.entity.SrmBarStorHead;
import com.mgkj.mapper.SrmBarStorHeadMapper;
import com.mgkj.service.SrmBarStorHeadService;
import com.mgkj.vo.ListPrintBarcodeVO;
import com.mgkj.vo.WarehouseAndBinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
* @author 28464
* @description 针对表【srm_bar_stor_head(条码仓库库位单头档
)】的数据库操作Service实现
* @createDate 2025-05-16 16:02:43
*/
@Service
public class SrmBarStorHeadServiceImpl extends ServiceImpl<SrmBarStorHeadMapper, SrmBarStorHead>
    implements SrmBarStorHeadService{


    @Autowired
    private SrmBarStorHeadMapper srmBarStorHeadMapper;

    @Override
    public Result listWarehouseAndBin(WarehouseAndBinDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<WarehouseAndBinVO> resultList =  srmBarStorHeadMapper.listWarehouseAndBin(dto);
        PageInfo<WarehouseAndBinVO> pageInfo = new PageInfo<>(resultList);
        return Result.ok(pageInfo).message("查询成功");
    }
}




