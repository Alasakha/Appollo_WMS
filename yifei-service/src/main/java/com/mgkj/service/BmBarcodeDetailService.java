package com.mgkj.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgkj.common.result.Result;
import com.mgkj.dto.ListPrintBarcodeDto;
import com.mgkj.dto.PrintBarcodeDocCjmDto;
import com.mgkj.dto.PrintBarcodeDocDto;
import com.mgkj.dto.PrintBarcodeDto;
import com.mgkj.entity.BmBarcodeDetail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【bm_barcode_detail(条码管理表)】的数据库操作Service
* @createDate 2024-11-14 10:19:56
*/

@Service
public interface BmBarcodeDetailService extends IService<BmBarcodeDetail> {


    Result insertPrintBarcodeByJobNo(List<PrintBarcodeDocDto> list);

    Result insertPrintBarcodeByCjm(List<PrintBarcodeDocCjmDto> list);

    Result listBarcodeInfo(ListPrintBarcodeDto dto, Integer pageNum, Integer pageSize);

}
