package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.*;
import com.mgkj.dto.agv.PlanMoInfo;
import com.mgkj.dto.agv.QueryMoList;
import com.mgkj.entity.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yyyjcg
 * @date 2024/3/8
 * @Description
 */
@Mapper
public interface MoMapper extends BaseMapper {

    List<MoInStorageVo> selectListMoInStorage(MoInStorageDto dto);

    void insertMiddleTable(Lsmdt lsmdt);
    void insertMiddleTable2(Lsmdt lsmdt);

    String selectUnitNo(String jobNo);

    WarehouseVo selectWarehouseAndBin(String itemNo);


    List<MoSimpleVo> ListMoSimpleInfo(MoSimpleDto moSimpleDto);

    MoCollectVo getMoSimpleInfoByDocNo(String docNo);

    List<MoCollectBodyVo> ListMoCollectInfo(String docNo);

    List<MoDetailVo> getMoDetailInfo(MoDetailDto moDetailDto);
    List<IssueReceiptSimpleVo> ListIssueReceiptSimpleInfo(IssueReceiptSimpleDto issueReceiptSimpleDto);
    List<MoShelvesSimpleVo> ListMoShelvesSimpleInfo(MoShelvesSimpleDto moShelvesSimpleDto);

    List<IssueReceiptCollectBodyVo> ListIssueReceiptCollectBodyInfo(String docNo);

    IssueReceiptCollectVo ListIssueReceiptSimpleInfoByDocNo(String docNo);

    List<MoReturnSimpleVo> ListMoReturnSimpleInfo(MoReturnSimpleDto moReturnSimpleDto);

    List<MoReturnCollectVo> ListMoReturnCollectInfoByDoc(MoReturnSimpleDto moReturnSimpleDto);

    List<IssueReceiptReturnSimpleVo> ListIssueReceiptReturnSimpleInfo(IssueReceiptReturnSimpleDto dto);

    IssueReceiptReturnCollectVo ListIssueReceiptReturnCollectInfo(String docNo);

    List<IssueReceiptReturnVo> ListIssueReceiptReturnCollectBodyInfo(String docNo);

    List<IssueReceiptDetailVo> ListIssueReceiptDetailInfo(IssueReceiptDeatailDto issueReceiptDeatailDto);

    List<String> selectWarehouseCode(String docNo);

    int updateMoInStorageCode(@Param("docNo") String docNo, @Param("warehouseCode") String warehouseCode);


    List<MoShelvesDetailVo> ListMoShelvesDetailInfo(String docNo);

    List<MoCheckHeader> ListMoStorageCheck(String applyDocNo);

    List<MoCheckLine> ListMoCheckLine(String checkDocNo);

    void UpdateMoStorageCheckHeader(MoCheckHeader header);

    void UpdateMoStorageCheckLine(MoCheckLine line);

    void UpdateMoApplyStorage(MoCheckHeader header);

    List<MoInspectionSd1> ListMoCheckSD1Line(String checkDocNo, String uuid);

    MoCheckHeader ListMoStorageCheckOne(String checkDocNo);

    List<MoInStorageVo> selectListMoInStorageByBarcode(MoInStorageByBarcodeDto dto);

    @Select("SELECT status FROM inspectionHead WHERE arriveOddNmber COLLATE Chinese_PRC_CS_AS = (SELECT lot_att15 FROM bm_barcode_detail WHERE barcode = #{barcode}) ")
    @DS("ABLTEST")
    String selectMoZsStatus(@Param("barcode") String barcode);

    List<IssueReceiptReqVo> getIssueReceiptReq(IssueReceiptReqDto pageDTO);

    List<issueReceiptReqDVo> getIssueReceiptReqD(IssueReceiptReqDDto dto);

    int getissueReceiptReqDByCreateBy(IssueReceiptReqDDto dto);

    void deleteReceiptReqDByCreateBy(String createBy);


    int savePickingSummary(@Param("detail") issueReceiptReqDVo detail,@Param("createBy") String createBy);

    BarCodeDetailVo getBarCodeDetailByBarCode(@Param("barCode") String barCode);

    List<PickingSummary> getPickingSummaryByItemCode(@Param("itemCode")String itemCode, @Param("createBy") String createBy);

    int updatePickingSummaryOfMatchQty(@Param("id") String id,
                                     @Param("qty") BigDecimal qty,
                                     @Param("createBy") String createBy,
                                     @Param("barCode") String barCode, @Param("nowQty") BigDecimal nowQty);

    int updatePickingSummaryOfMatchQtyTo(@Param("id") String id,
                                       @Param("qty") BigDecimal qty,
                                       @Param("createBy") String createBy,
                                       @Param("barCode") String barCode, @Param("nowQty") BigDecimal nowQty);

    int updateBmBarcodeDetailOfQty(@Param("barcode") String barcode, @Param("allocateQty") double allocateQty);

    int saveInvBarCodeOperation(@Param("barCodeDetailVo") BarCodeDetailVo barCodeDetailVo,@Param("createBy") String createBy);

    List<PickingSummary> getPickingSummaryByCreateBy(@Param("createBy") String createBy);

    List<IssueReceiptWarehouse> getIssueReceiptWarehouseList(@Param("docNoList")List<String> docNoList);

    @Update("UPDATE bm_barcode_detail SET sn_warehouse_no = #{warehouseCode}, sn_cell_no = #{binCode},unit_no = #{unitNo} ,unit_name = #{unitName},lot_att07 = #{moReceiptNo},lot_att30 ='4' WHERE barcode = #{barcode}")
    int updateBarcodeWBU(MoInStorageSubmitDto listDto);

    @Update("UPDATE bm_barcode_detail SET sn_warehouse_no = #{warehouseCode}, sn_cell_no = #{binCode},unit_no = #{unitCode} ,lot_att07 = #{receiptNo},lot_att30 ='4' WHERE barcode = #{barcode}")
    int updateBarcodeWBUNew(FinishedGoodsInboundDetail dto);

    @Delete("DELETE FROM picking_summary WHERE create_by = #{createBy}")
    int deleteDeliveryDetailSummaryByCreateBy(@Param("createBy") String createBy);

    void insertSubBarcode(@Param("subBarcode")BmBarcodeDetail subBarcode,@Param("allocateQty")Double allocateQty);

    @Select("SELECT COUNT(*) FROM bm_barcode_detail WHERE create_by = #{barCode}")
    int getSubBarcodeCountByBarCode(@Param("barCode")String barCode);


    @Select("SELECT ISNULL(combination_lot_no,'') FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String selectLotNumber(@Param("barcode")String barcode);


    void UpdateIssueReceiptReqDLot(@Param("docNo")String docNo);

    String getMaxBarCodeDetail(@Param("queryBarcode") String queryBarcode);

    @Update("update inv_barcode_operation set statusCode = 1  where barcode = #{barcode}")
    void updateInvBarCodeOperation(@Param("barcode") String barcode);


    @Delete("delete from inv_barcode_operation where id = #{id}")
    Boolean deleteBarCodeOperation(@Param("id")String id);

    @Delete("delete from picking_summary where create_by = #{createBy}")
    void deletePickingSummary(@Param("createBy")String createBy);

    List<issueReceiptReqDVo> getIssueReceiptReqSummaryByCreateBy(@Param("createBy") String createBy);

    Integer isScanningCodeRecord(@Param("createBy")String createBy);

    BigDecimal getQtySum(@Param("barcode")String barcode, @Param("createBy")String createBy);

    Integer getUnMatchQtyList(@Param("createBy")String createBy);
    @Select("SELECT id, warehouse_no warehouseNo, cell_no cellNo, item_no itemNo,\n" +
            "               barcode, qty, unit_no unitNo, chag_type chagType, status_code statusCode,\n" +
            "               create_by createBy, create_date createDate, item_name itemName, item_spec itemSpec\n" +
            "        FROM inv_barcode_operation\n" +
            "        WHERE id = #{id}")
    InvBarcodeOperation selectInvBarCodeOperation(@Param("id") String id);

    List<InvBarcodeOperationVo> getInvBarcodeOperationVo(@Param("createBy") String createBy);

    void deleteInvBarcodeOperationByCreateBy(@Param("createBy")String createBy);

    @Select("SELECT TA021  FROM MOCTA WHERE MO_ID IN (SELECT MO_ID FROM MO WHERE DOC_NO IN ( SELECT source_no FROM bm_barcode_detail WHERE barcode = #{barcode} ))")
    String selectMoDocNo(@Param("barcode")String barcode);

    @Select("SELECT TOP 1 status FROM inspectionHead WHERE type IN ('CPJ','XXJ') AND address = #{barcode} AND TA006 LIKE '1%'")
    @DS("ABLTEST")
    String selectMoBzStatus(@Param("barcode")String barcode);

    List<MoLjzpInStorageSubmitDto> selectLjMo(@Param("barcode") String barcode);

    int insertPackageIssuestorage(@Param("barcode") String barcode, @Param("bareStorageDocNo") String bareStorageDocNo,
                                  @Param("bareIssueDocNo") String bareIssueDocNo, @Param("packageStorageDocNo") String packageStorageDocNo,
                                  @Param("docNo") String docNo);

    int selectBarcodeBooleanPackageIssuestorage(@Param("barcode") String barcode);

    List<String> getWarehouseCodeByEmployeeNo(@Param("employeeNo") String employeeNo);

    MoInventoryQtyWarehouse selectItemNumber(@Param("itemCode") String itemCode,@Param("lotNumber") String lotNumber, @Param("shjg")String shjg, @Param("warehouseCode")String warehouseCode);

    void updatePickingSummaryOfCurrectQty(String createBy);

    MoInStorageCountVo queryCount(String barcode);

    Double queryInventory(String itemCode);

    PickingSummary getSummyById(String id);

    PickingSummary getPickingSummary(@Param("createBy")String createBy, @Param("itemNo")String itemNo);

    MoInStorageCountVo queryCountBaoZhuang(@Param("barcode") String barcode);

    List<String> queryAlreadyScanCode(@Param("barcode") String barcode);

    List<String> queryNoScanCode(@Param("barcode") String barcode);

    MoInStorageVo queryMoInStorage(MoInStorageDto dto);

    int insertMoInStorageSummary(@Param("dto") MoInStorageVo dto, @Param("createBy") String createBy);

    List<MoInStorageVo> queryMoInStorageSummary(@Param("createBy") String createBy, @Param("barcode") String barcode);

    void deleteMoInStorageSummary(String id);

    void deleteMoInStorageSummaryByCreatBy(String createBy);

    String queryBinCodeByItemCode(@Param("itemNo") String itemNo);

    MaterialBarCodeDetailVo getMaterialBarCodeDetailByBarCode(String barCode);

    void updaateBarcodeLotAtt30(String parentBarcode);

    List<MoProductInfoDto> queryMoProduct(@Param("docNoList") Set<String> docNoList);

    List<MoLjzpInStorageSubmitDto> selectLjMoBatch(@Param("barcodes") List<String> barcodes);

    Set<String> queryQywxUser(@Param("creatorList") List<String> creatorList);

    String queryWorkCenter(@Param("docNo") String docNo);

    List<String> queryNoScanBarCodeList(@Param("barcode") String barcode);

    List<PlanMoInfo> getMoListByMocty(@Param("planDate") String planDate, @Param("productionLine") String productionLine);

    MaterialBarCodeDetailVo queryBarCodeInMo(@Param("barcode") String barcode, @Param("docNo") String docNo);

    AgvWarehouse queryAgvTerminusByMo(@Param("barcode") String barcode, @Param("docNo") String docNo);

    Map<String, Object> getSnCodeInfo(String barcode);
}
