package com.mgkj.mapper;

import com.mgkj.dto.ListNoticeOutStockDto;
import com.mgkj.dto.ListSaleOrderOutStockDto;
import com.mgkj.dto.ListSaleReturnDto;
import com.mgkj.entity.Lsmdt;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/4/15
 * @Description
 */
@Mapper
public interface SaleMapper {

    List<ListSaleOrderOutStockVo> ListSaleOrderOutStock(ListSaleOrderOutStockDto dto);

    List<ListSaleOrderOutStockDetailVo> ListSaleOrderOutStockDetail(String docNo);

    List<ListNoticeOutStockVo> ListNoticeOutStock(ListNoticeOutStockDto dto);

    List<ListNoticeOutStockDetailVo> ListNoticeOutStockDetail(String docNo);

    void insertMiddleTable(Lsmdt lsmdt);
    void insertMiddleTable2(Lsmdt lsmdt);

    List<ListSaleReturnVo> ListSaleReturn(ListSaleReturnDto dto);

    List<ListSaleReturnDetailVo> ListSaleReturnDetail(String docNo);

    void insertMiddleTable1(Lsmdt build);


    @Select("SELECT combination_lot_no FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String selectLotNumber(@Param("barcode") String barcode);
}
