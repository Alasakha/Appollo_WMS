package com.mgkj.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.DeliverySummary;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author yyyjcg
 * @date 2023/12/26
 * @Description
 */
public interface PurchaseService {

    Result getPurchaseReceiveInfo(PurchaseReceiveDto purchaseReceiveDto);

//    Result getAllPurchaseInfo(PurchaseVo purchaseVo);

    List<PurchaseDetailDto> getPurchaseDetailInfo(PurchaseVo purchaseVo);

    //快速收货
    //获取采购单简洁信息
    Result getPurcharseInfo(PurchaseOrderSimpleDto purchaseOrderSimpleDto);
    //获取采购单详细信息
    Result<List<PurchaseOrderDetailVo>> getPurcharseDetail(PurchaseOrderDetailDto dto);

//    //生成采购到货单
//    Result createPurchaseArrival(PurchaseArrivalDto purchaseArrivalDto);
//    //生成采购到货单和采购入库单
//    Result createPurchaseReceipt(PurchaseArrivalDto purchaseArrivalDto);


    //扫码入库
    //获取到货单简洁信息
    Result<List<PurchaseArrivalSimpleVo>>  getPurcharseArrivalInfo(PurchaseArrivalSimpleDto purchaseArrivalSimpleDto);
    //获取到货单详细信息
    Result<List<PurchaseArrivalDetailVo>> getPurchaseArrivalDetail(PurchaseArrivalDetailDto purchaseArrivalDetailDto);
    //通过到货单号获取汇总信息
    Result getPurcharseArrivalDetailByDocNo(PurchaseArrivalDetailDto purchaseArrivalDetailDto);

//    MiddleReturnDto  insertMiddleTable(List<ScanToStorageDto> list);

    //    JSONObject scanToStorageSubmit(MiddleReturnDto middleReturnDto);
    JSONObject scanToStorageSubmit(MiddleReturnDto middleReturnDto);
    JSONObject PurchaseToStorageSubmit(MiddleReturnDto middleReturnDto);

//    Result printPurchaseArrival(String docNo);

    Result FastDeliveryGenerateDoc(FastDeliveryDto fastDeliveryDto);



    Result producePurchaseInspectionOrder(PurchaseInspectionOrderDto dto);

    Result<List<PurchaseArrivalDetailVo>> ListCheckPurchase(ListCheckPurchaseDto dto);

    Result ListPurchaseReturn(ListPurchaseReturnDto dto);

    Result ListPurchaseReturnDetail(String docNo);

    MiddleReturnDto PurchaseReturnInsertMiddleTable(List<PurchaseReturnSubmitDto> list);

    JSONObject PurchaseReturnStockSubmit(MiddleReturnDto middleReturnDto);

    MiddleReturnDto PurchaseToStorageInsertMiddleTable(List<PurchaseToStorageDto> list);

    MiddleReturnDto PurchaseScanToStorageInsertMiddleTable(List<PurchaseToStorageDto> list, String createBy);

    Result<List<PurchaseDetailVo>> getPurchaseDetail(String docNo);

    MiddleReturnDto PurchaseReceiptStorageInsertMiddleTable(List<PurchaseReceiptStorageDto> list);


    List<PurchaseReceiptStorageVo> getPurcharsedeliveryNo(PurcharseDeliveryDto dto);

    int inserinvBarcodeOperation(Result<List<PurchaseReceiptStorageVo>> purcharsedeliveryNo);

    Result<List<PurchaseReceiptStorageVo>> getBarCodeOperation();

    Result<PageInfo<PurchaseArrivalCheckHeader>> ListPurchaseArrivalStorageCheck(PurchaseArrivalStorageCheckDto dto);

    Result<List<PurchaseArrivalCheck>> ListPurchaseArrivalStorageCheckAll(String checkDocNo);

    Result UpdatePurchaseArrivalStorageCheck(PurchaseArrivalCheck dto);

    Result<PageInfo<DefectiveReasons>> ListDefectiveReasons(DefectiveReasonsDto dto);


    Result<List<PurchaseReceiptStorageVo>> getArrivalNo(PurcharseDeliveryDto dto);

    Result<PageInfo<PurchaseArrivalCheckHeader>> ListPurchaseArrivalStorageWaitCheck(PurchaseArrivalStorageWaitCheckDto dto);

    Result<PageInfo<PurchaseReceiptStorageWaitCheckVo>> ListPurchaseReceiptStorageWaitCheck(PurchaseReceiptStorageWaitCheckDto dto);

    Result<PageInfo<PurchaseReceiptStorageWaitCheckItemVo>> ListPurchaseReceiptStorageWaitCheckItem(PurchaseReceiptStorageWaitCheckItemDto dto);

    Result<PageInfo<Supplier>> ListSupplierCode(Supplier dto);

    Result<PageInfo<PRSVo>> getArrivalInfo(PRSDto dto);

    Result<PageInfo<PRSDVo>> getArrivalInfoD(PRSDDto dto);

    List<PurchaseReceiptStorageVo> getPurcharseByBarcode(PurcharseDeliveryDto dto);
}
