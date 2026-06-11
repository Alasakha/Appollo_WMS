package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.Lsmdt;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */
@Mapper
public interface StockMapper extends BaseMapper {

    BarCodeDetailVo getBarCodeDetailByBarCode(@Param("barCode")  String barcode);

    @Select("SELECT ISNULL(combination_lot_no,'') FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String selectLotNumber(@Param("barcode") String barcode);

    List<InvBarcodeOperationVo> queryInvBarcodeOperationVo(@Param("createBy")  String createBy);

    Double getQtySum(@Param("barcode") String barcode,@Param("chatType") Integer chatType);

    List<InvBarcodeOperation> queryTransNotSubimtByTypeAndCreateBy(@Param("chagType") int chagType, @Param("createBy") String createBy, @Param("barcode")  String barcode);

    @Update("UPDATE inv_barcode_operation SET status_code = '1' WHERE id = #{id}")
    boolean updateByid(@Param("id") String id);

    int updateBmBarcodeDetailOfQty(@Param("barcode") String barcode,@Param("qty") double qty);

    List<ListStockVo> ListStock(ListStockDto dto);

    List<OtherInStockVo> ListOtherInStock(OtherInStockDto dto);

    List<OtherInStockDetailVo> OtherInStockDetail(String docNo);

    void insertMiddleTable(Lsmdt lsmdt);
    void insertMiddleTable2(Lsmdt lsmdt);

    List<OtherOutStockVo> ListOtherOutStock(OtherOutStockDto dto);

    List<OtherOutStockDetailVo> OtherOutStockDetail(String docNo);

    List<ListTransferDocVo> ListTransferDoc(ListTransferDocDto dto);

    List<ListTransferDocDetailVo> ListTransferDocDetailVo(String docNo);

    @Select("SELECT IT.LOT_CODE\n" +
            "FROM TRANSACTION_DOC T\n" +
            "LEFT JOIN TRANSACTION_DOC_D TD ON TD.TRANSACTION_DOC_ID = T.TRANSACTION_DOC_ID\n" +
            "LEFT JOIN ITEM_LOT IT ON  IT.ITEM_LOT_ID = TD.ITEM_LOT_ID\n" +
            "LEFT JOIN ITEM I ON I.ITEM_BUSINESS_ID = TD.ITEM_ID\n" +
            "WHERE T.DOC_NO = #{docNo} AND I.ITEM_CODE = #{itemCode}")
    String selectPh(String docNo, String itemCode);

    List<TransferDoc> getTransferDoc();

    List<TransferDocDetail> getTransferDocDetail(TransferDocDetailDTO detailDTO);

    void deleteInvBarcodeOperationByCreateBy(@Param("createBy") String createBy,@Param("chagType") Integer chagType);

    List<TransferDocDetail> getTransferDocDetailByDocNoAndItemCode(@Param("docNo")String docNo,@Param("docId")String docId, @Param("itemCode")String itemCode);
}
