package com.mgkj.mapper;

import com.mgkj.dto.*;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PickingMapper {

    List<WorkInfoToWmsTempVo> getWorkInfoToWmsVo(String docNo);
    List<WorkInfoToWmsTempVo> getWorkInfoToWmsVoOverClaim(String docNo);

    List<WorkInfoToWmsItemVo> getWorkInfoItem(@Param("res") List<WorkInfoToWmsTempVo> res);
    List<WorkInfoToWmsItemVo> getWorkInfoItemOverClaim(@Param("res") List<WorkInfoToWmsTempVo> res);

    void saveMoPickingSummary(MoPickingSummary moPickingSummary);

    void deleteMoPickingSummary(@Param("createBy") String createBy);

    List<MoPickingSummary> queryMoSummary(QueryMoSummaryDTO queryMoSummaryDTO);

    Integer queryUnMatchQtyList(@Param("id") String id);

    void updateMoSummaryOfMatchQty(@Param("id") String id, @Param("qty") BigDecimal qty, @Param("createBy") String createBy,@Param("barcode") String barcode);

    List<InvBarcodeOperationVo> queryInvBarcodeOperationVo(String createBy);

    int updateQtySubtraction(@Param("id") String id, @Param("qty") double qty, @Param("createBy") String createBy);

    int updateQtyZero(@Param("id") String id, @Param("qty") double qty, @Param("createBy") String createBy);

    MoPickingSummary queryMoSummaryOne(QueryMoSummaryDTO queryMoSummaryDTO);

    void deleteInvBarcodeOperationByCreateBy(String createBy);

    int deleteMoPickingSummaryById(String id);

    List<IssueReceiptDVo> queryIssueReceiptReqD(QueryIssueReceiptD dto);

    List<IssueReceiptVo> queryIssueReceiptHeader(String docNo);

    void updateIssueReceiptD(@Param("issueReceiptId") String issueReceiptId,@Param("qty") BigDecimal qty);

    IssueReceiptDVo queryIssueReceiptD(String id);

    Integer queryUnSignCount(QueryIssueReceiptD dto);

    List<MoReceiptVo> queryMoReceipt(QueryMoReceiptD dto);

    MoReceiptVo queryMoReceiptD(String id);

    void updateMoReceiptD(@Param("docNo") String docNo, @Param("qty") double qty);

    List<MoReceiptVo> queryMoReceiptNew(QueryMoReceiptD dto);

    List<MoReceiptVo> queryMoReceipt2(QueryMoReceiptD dto);

    List<MoReceiptDVo> getMoReceiptDList(@Param("docNo") String docNo);

    void updateAcceptedQty(@Param("docDNoID") String docDNoID, @Param("qty") double qty);

    List<String> GetMoReceiptList();

    BigDecimal getQtySumByCreate(@Param("barcode") String barcode, @Param("chagType") int chagType, @Param("createBy") String createBy);
}
