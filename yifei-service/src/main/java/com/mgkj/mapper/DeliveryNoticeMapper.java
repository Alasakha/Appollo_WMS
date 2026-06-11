package com.mgkj.mapper;

import com.mgkj.dto.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.SalesSummary;
import com.mgkj.entity.Salesoutboundtaskdetail;
import com.mgkj.vo.BarCodeDetailVo;
import com.mgkj.vo.DeliveryDetailVo;
import com.mgkj.vo.InvBarcodeOperationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DeliveryNoticeMapper {

    List<DeliveryNotice> getDeliveryNoticeList(DeliveryNoticeDTO dto);

    List<DeliveryWarehouse> getDeliveryWarehouseList(@Param("docNoList") List<String> docNoList);

    List<DeliveryDetail> getDeliveryDetail(DeliveryDetailDTO detailDTO);

    BarCodeDetailVo getBarCodeDetailByBarCode(@Param("barCode") String barCode);

    boolean saveDeliveryDetailSummary(@Param("detail") DeliveryDetailVo detail, @Param("createBy") String createBy);

    List<DeliveryDetail> getDeliveryDetailSummaryByCreateBy(@Param("createBy") String createBy);

    int deleteDeliveryDetailSummaryByCreateBy(@Param("createBy") String createBy);

    int saveInvBarCodeOperation(@Param("barCodeDetailVo")BarCodeDetailVo barCodeDetailVo,@Param("createBy") String createBy);

    List<BarCodeDetailVo> getBarCodeDetailByCreateBy(@Param("createBy") String createBy);

    List<SalesSummary> getSalesSummaryByItemCode(@Param("itemCode") String itemCode, @Param("createBy") String createBy);

    int saveSalesSummary(@Param("detail") DeliveryDetail detail,@Param("createBy") String createBy);

    int updateBmBarcodeDetailOfQty(@Param("barcode") String barcode,@Param("qty") double qty);

    int updateSalesSummaryOfMatchQty(@Param("id") String id,@Param("qty") BigDecimal qty,@Param("createBy") String createBy,@Param("barCode") String barCode);

    List<SalesSummary> getSalesSummaryByCreateBy(@Param("createBy") String createBy);

    String getMaxBarCodeDetail(@Param("queryBarCode") String queryBarCode);

    List<InvBarcodeOperationVo> getInvBarcodeOperationVo(@Param("createBy") String createBy);

    int deleteInvBarcodeOperationByCreateBy(@Param("createBy") String createBy);

    BigDecimal getQtySum(@Param("barCode") String barCode, @Param("chagType") int chagType);

    Integer getUnMatchQtyList(@Param("createBy") String createBy);

    InvBarcodeOperation getInvBarcodeOperationById(@Param("id") String id);

    boolean deleteInvBarcodeOperationById(@Param("id") String id);

    int updateQtySubtraction(@Param("id") String id, @Param("qty") BigDecimal v, @Param("createBy") String createBy);

    Integer isScanningCodeRecord(@Param("createBy") String createBy);

    int updateQtyZero(@Param("id") String id,@Param("qty") BigDecimal qty,@Param("createBy") String createBy);

    String selectPlantId(@Param("moNo")String moNo);

    List<DeliveryDetail> getDeliveryDetailByTimed(DeliveryDetailDTO detailDTO);

    DeliveryDetail getDeliveryDetailByDocNo(@Param("docNo") String docNo, @Param("itemCode") String itemCode);

    List<SalesOutboundDocSummary> getSalesOutboundDocSummary(@Param("docNoList") List<String> docNoList);

    void upsertSalesSummaryForV2(@Param("createBy") String createBy,
                                  @Param("docNo") String docNo,
                                  @Param("itemCode") String itemCode,
                                  @Param("itemName") String itemName,
                                  @Param("itemSpec") String itemSpec,
                                  @Param("businessQty") BigDecimal businessQty,
                                  @Param("deliveryQty") BigDecimal deliveryQty,
                                  @Param("unitCode") String unitCode,
                                  @Param("binCode") String binCode,
                                  @Param("warehouseCode") String warehouseCode,
                                  @Param("customerNo") String customerNo,
                                  @Param("shjg") String shjg,
                                  @Param("currentNum") BigDecimal currentNum,
                                  @Param("unitName") String unitName,
                                  @Param("barcode") String barcode);

    List<DeliveryDetail> getDeliveryDetailWithScanned(@Param("docNo") String docNo,
                                                      @Param("docNoList") List<String> docNoList,
                                                      @Param("warehouseCodeList") List<String> warehouseCodeList);

    List<Salesoutboundtaskdetail> getMyScannedList(@Param("docNo") String docNo,
                                                    @Param("createBy") String createBy);

    Integer countUnsyncedRecords(@Param("docNo") String docNo);

    List<Salesoutboundtaskdetail> getUnsyncedRecords(@Param("limit") int limit);

    int batchMarkSynced(@Param("idList") List<String> idList);

    /**
     * 查询某条码在 SalesOutboundTaskDetail 中已累计扫码的数量
     */
    BigDecimal getBarcodeScannedSum(@Param("barcode") String barcode, @Param("docNo") String docNo);

}
