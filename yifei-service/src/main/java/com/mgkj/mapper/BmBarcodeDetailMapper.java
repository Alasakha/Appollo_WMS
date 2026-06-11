package com.mgkj.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.ListPrintBarcodeDto;
import com.mgkj.entity.BmBarcodeDetail;
import com.mgkj.vo.ListPrintBarcodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author Administrator
* @description 针对表【bm_barcode_detail(条码管理表)】的数据库操作Mapper
* @createDate 2024-11-14 10:19:56
* @Entity generator.domain.BmBarcodeDetail
*/
@Mapper
@DS("ABLTEST")
public interface BmBarcodeDetailMapper extends BaseMapper<BmBarcodeDetail> {

//    @DS("db3")
    Integer getMaxLabelSeq(@Param("itemNo") String itemNo, @Param("date") DateTime date);

    @Update("UPDATE MO SET ITEM_ROUTING_CONTROL='0',ROUTING_CONTROL='0' WHERE DOC_NO=#{jobNo}")
    void updateByJobNo(@Param("jobNo") String jobNo);


    String selectTA021(@Param("jobNo") String jobNo);

    List<ListPrintBarcodeVO> listBarcodeInfo(ListPrintBarcodeDto dto);

    void updateQty(@Param("barcode") String barcode, @Param("qty") Double qty);

    String getBarType(@Param("number") String number);
}




