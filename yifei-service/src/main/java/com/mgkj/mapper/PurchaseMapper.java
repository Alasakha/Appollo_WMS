package com.mgkj.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.Attachment;
import com.mgkj.entity.DeliverySummary;
import com.mgkj.entity.BarcodeDetails;
import com.mgkj.entity.Lsmdt;
import com.mgkj.entity.*;
import com.mgkj.entity.LsmdtPurchase;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.*;
import com.mgkj.vo.*;
import com.mgkj.vo.InvBarcodeOperation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
@Mapper
public interface PurchaseMapper {
    //    采购收货
    List<PurchaseSumDto> getAllPurchaseInfo1(PurchaseVo purchaseVo);

    //    扫码入库
    List<PurchaseSumDto> getAllPurchaseInfo2(PurchaseVo purchaseVo);

    //    采购仓退
    List<PurchaseSumDto> getAllPurchaseInfo3(PurchaseVo purchaseVo);

    List<PurchaseDetailDto> getPurchaseDetailInfo(PurchaseVo purchaseVo);

    List<PurchaseOrderSimpleVo> getPurcharseInfo(PurchaseOrderSimpleDto purchaseOrderSimpleDto);

    String getItemLot(String itemId);


    List<PurchaseOrderDetailVo> getPurcharseDetail(PurchaseOrderDetailDto Dto);


    List<PurchaseArrivalSimpleVo> getPurcharseArrivalInfo(PurchaseArrivalSimpleDto purchaseArrivalSimpleDto);

    List<PurchaseArrivalDetailVo> getPurchaseArrivalDetail(PurchaseArrivalDetailDto purchaseArrivalDetailDto);

    List<PurchaseArrivalDetailCollectVo> getPurcharseArrivalDetailByDocNo(PurchaseArrivalDetailDto purchaseArrivalDetailDto);

    void insertMiddleTable(Lsmdt lsmdt);

    void insertMiddleTable2(Lsmdt lsmdt);


    List<PurchaseArrivalPrintVo> printPurchaseArrival(String docNo);


    void producePurchaseInspectionOrder1(@Param("uuid") String uuid, @Param("dto") PurchaseInspectionOrderDto dto);

    void producePurchaseInspectionOrder2(@Param("uuid1") String uuid1, @Param("uuid2") String uuid2, @Param("dto") PurchaseInspectionOrderDto dto);

    void producePurchaseInspectionOrder3(@Param("uuid2") String uuid2, @Param("uuid3") String uuid3, @Param("dto") PurchaseInspectionOrderDto dto);

    PurchaseReceiveVo getPurchaseReceiveInfo(PurchaseReceiveDto purchaseReceiveDto);

    List<PurchaseArrivalDetailVo> ListCheckPurchase(ListCheckPurchaseDto dto);

    void updatePurchaseArrival(PurchaseInspectionOrderDto dto);

    void updatePurchaseInspectionOrder2(PurchaseInspectionOrderDto dto);

    void updatePurchaseInspectionOrder3(PurchaseInspectionOrderDto dto);

    List<ListPurchaseReturnVo> ListPurchaseReturn(ListPurchaseReturnDto dto);

    List<ListPurchaseReturnDetailVo> ListPurchaseReturnDetail(String docNo);

    void PurchaseToStorageInsertMiddleTable(LsmdtPurchase lsmdtPurchase);

    void insertMiddleTable3(Lsmdt build);

    List<PurchaseDetailVo> getPurchaseDetail(String docNo);


    List<PurchaseReceiptStorageVo> getBarCodeByDeliveryNo(PurcharseDeliveryDto dto);

    PurchaseDetailVo getPurchaseDeliveryDetail(PurchaseDeliveryDto arr);

    List<PurchaseReceiptStorageVo> getBarCodeOperation();

    int inserinvBarcodeOperation(List<InvBarcodeOperation> invBarcodeOperationList);

    List<PurchaseArrivalCheckHeader> ListPurchaseArrivalCheckHeader(PurchaseArrivalCheckHeader purchaseArrivalCheckHeader);

    List<PurchaseArrivalCheckLine> ListPurchaseArrivalCheckLine(String checkDocNo);

    int UpdatePurchaseArrivalStorageCheckHeader(PurchaseArrivalCheckHeader header);

    int UpdatePurchaseArrivalStorageCheckLine(PurchaseArrivalCheckLine line);

    int UpdatePurchaseReceiptStorage(PurchaseArrivalCheckHeader header);

    List<DefectiveReasons> ListDefectiveReasons(String reasons);

    @Update("UPDATE bm_barcode_detail SET lot_att04 = #{docNo}  WHERE barcode = #{barcode}")
    void updateBarcode(@Param("docNo") String docNo, @Param("barcode") String barcode);

    List<PurchaseReceiptStorageVo> getArrivalNo(PurcharseDeliveryDto dto);
    @DS("ABLTEST")
    List<Attachment> getAttachments(@Param("itemCode") String itemCode);

    @Select("SELECT item_no itemCode,lot_att04 arrivalDocNo,* FROM bm_barcode_detail WHERE  barcode = #{barcode}")
    PurchaseArrivalCheckHeader getItemCodeAndArrivalDocNo(@Param("barcode") String barcode);

    @Update("UPDATE bm_barcode_detail SET sn_warehouse_no = #{warehouseCode}, sn_cell_no = #{binCode},unit_no = #{unitCode}, unit_name = #{unitName},lot_att30 = '3' WHERE barcode = #{barcode}")
    int updateBarcodeWBU(PurchaseToStorageDto listDto);

    @Select("SELECT combination_lot_no FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String selectLotNumber(@Param("barcode") String barcode);

    List<PurchaseArrivalCheckHeader> ListPurchaseArrivalWaitCheckHeader(@Param("dto") PurchaseArrivalStorageWaitCheckDto dto, @Param("resultStatusList") List<String> resultStatus);


    List<WaitToStorageVo> selectOddNumber(@Param("inspectionUuid") String inspectionUuid);

    WaitToInspectedVo getWaitToInspected(@Param("docNo") String docNo);

    List<PurchaseArrivalDVo> getWaitToInspectedD(@Param("docNo") String docNo);

    List<String> getDeliveryUuid(@Param("docNo") String docNo);

    void updateDeliveryUuid(@Param("docNo") String docNo, @Param("uuid") String uuid);

    @Select("SELECT bar_type FROM bm_barcode_detail WHERE barcode = #{barcode}")
    int selectBarType(@Param("barcode") String barcode);


    @Select("SELECT barcode  FROM bm_barcode_detail WHERE standard_col10 = #{uuid}")
    List<String> selectBarChild(@Param("uuid") String uuid);

    List<PurchaseReceiptStorageVo> getArrivalNoByChildlist(@Param("childList") List<String> childList);

    void batchUpdateBarcode(@Param("list") List<Map<String, String>> list);

    void batchUpdateDeliveryUuid(@Param("list") List<Map<String, String>> list);

    List<String> getDeliveryUuidsByDocNos(@Param("docNoList") List<String> docNoList);

    List<PurchaseReceiptStorageWaitCheckVo> ListPurchaseReceiptStorageWaitCheck(PurchaseReceiptStorageWaitCheckDto dto);

    @Select("SELECT id FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String selectFUuid(@Param("barcode") String barcode);

    List<PurchaseReceiptStorageWaitCheckItemVo> ListPurchaseReceiptStorageWaitCheckItem(@Param("arrivalNo")  String arrivalNo);

    List<String> getInspectorByWarehouse(@Param("warehouseList") List<String> warehouseList);

    List<String> getCGQWByWarehouse(@Param("warehouseList") List<String> warehouseList);

    @Update("UPDATE bm_barcode_detail SET lot_att30 = '1' WHERE barcode = #{barcode}")
    void updateBarcodelot30(@Param("barcode") String barcode);

    List<PurchaseArrivalCheckHeader> selectAllInspectionHeadersByDocNoAndItemCode(@Param("arrivalDocNo") String arrivalDocNo, @Param("itemCode") String itemCode);

    @Delete("delete from LSMD_T where LSMD027 = #{timestamp} AND   (LSMD029='N' or  LSMD029='P' ) ")
    void deleteBytimestamp(@Param("timestamp") String timestamp);

    @Select("select lot_att30 from bm_barcode_detail where barcode = #{barcode}")
    String selectBarcodelotatt30(@Param("barcode") String barcode);

    List<Supplier> ListSupplierCode(@Param("supplierName") String supplierName);

    @Update("update bm_barcode_detail set lot_att30 = '2' where barcode = #{barcode}")
    int UpdateBarcodeLotatt30(@Param("barcode") String barcode);

    BmBarcodeDetail SelectBarcode(@Param("barcode") String barcode);

    @Select("SELECT lot_att30 FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String SelectBarcodeLotatt30(@Param("barcode") String barcode);

    List<String> selectArrivalBarcode(@Param("arrivalDocNo") String arrivalDocNo, @Param("itemCode") String itemCode);

    int UpdateBarcodeLotatt30ByList(@Param("barcodeList")List<String> barcodeList);
    int UpdateBarcodeDecisionLotatt30ByList(@Param("barcodeList") List<String> barcodeList,
                                            @Param("lotatt08") String lotatt08,@Param("lotatt30") String lotatt30);

    String getContainerBarcodeByChildBarcode(@Param("barcode") String barcode);

    List<PRSVo> getArrivalInfo(PRSDto dto);

    List<PRSDVo> getArrivalInfoD(PRSDDto dto);

    List<WmsMeasurementValue> getWmsCheckByArrivalDocNo(@Param("checkDUuid") String checkDUuid);

   int selectDeOrBa(@Param("number") String number);

    List<PurchaseReceiptStorageVo> getPurcharsedeliveryBarcode(@Param("number") String number);

    List<PurchaseReceiptStorageVo> getPurcharseByBarcode( PurcharseDeliveryDto dto);

    List<PurchaseReceiptStorageVo> getPurcharseByChildBarcode(@Param("barcode") String barcode);

    int selectDistinct(@Param("barcodes") Set<String> barcodes);

    List<PurchaseReceiptStorageVo> getInvBarcodeOperationVo(PurchaseReceiptStorageDtoOne dto);

    void deleteInvBarcodeOperationByCreateBy(@Param("createBy") String createBy);

    Boolean deleteBarCodeOperation(@Param("id") String id);

    Integer isScanningCodeRecord(@Param("createBy")String createBy);

    String getPlantByPURCHASE_ARRIVAL_D_ID(@Param("arrivalDUuid") String arrivalDUuid);

    void updateLotatt30To1AndLot_att04(@Param("barcode") String barcode, @Param("docNo") String docNo);

    @Select("SELECT Count(*) FROM bm_barcode_detail WHERE barcode = #{number}")
    int getPurcharseBarcodeIsNull(@Param("number") String number);

    List<WaitToStorageVo> selectOddNumberUndecision(@Param("inspectionUuid")String inspectionUuid);

    @Select("SELECT ISNULL(MAX(SequenceNumber),0)+1  FROM PO_ARRIVAL_INSPECTION_SD1 WHERE PO_ARRIVAL_INSPECTION_D_ID = #{uuid}")
    int selectSd1SequenceNumber(@Param("uuid") String uuid);

    BarcodeDetails getBarcodeDetails(@Param("barcode") String barcode);

    List<DeliverySummary> getByDeliveryNo(PurcharseDeliveryDto dto);
    List<DeliverySummary> getByDeliveryNoC(PurcharseDeliveryDto dto);

    int selectIsClude(@Param("number") String number, @Param("createBy") String createBy);

    void updatelotAtt31(@Param("barcode") String barcode);

    void updateLotAtt31Null(@Param("createBy") String createBy);

    int updateLotAtt31NullSingle(@Param("id") String id);

    String getPurchaseDByBarcode(@Param("barcode") String barcode);

    PurchaseInfo queryPurchaseInfo(@Param("docNo") String docNo,@Param("orderNum") String orderNum);

    String queryQcUserByWarehouseCode(@Param("warehouseCode") String warehouseCode);

    List<String> getCGQWBySupplierCode(String supplierCode);

    void updateDeliveryNoteDetails(@Param("barcode") String barcode, @Param("docNo") String docNo);

    int updateCreateByDocNo(@Param("docNo") String docNo, @Param("createBy") String createBy);

    int updatePoArrivalInspectionCreateByDocNo(@Param("docNo") String docNo, @Param("createBy") String createBy);
}
