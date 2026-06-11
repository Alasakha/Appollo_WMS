package com.mgkj.service;

import com.alibaba.fastjson.JSONObject;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/4/15
 * @Description
 */
public interface SaleService {
    Result ListSaleOrderOutStock(ListSaleOrderOutStockDto dto);

    Result ListSaleOrderOutStockDetail(String docNo);

    Result ListNoticeOutStock(ListNoticeOutStockDto dto);

    Result ListNoticeOutStockDetail(String docNo);

//    MiddleReturnDto OtherOutStockInsertMiddleTable(List<NoticeOutStockSubmitDto> list);

    JSONObject OtherOutStockSubmit(MiddleReturnDto middleReturnDto);

    Result ListSaleReturn(ListSaleReturnDto dto);

    Result ListSaleReturnDetail(String docNo);

    MiddleReturnDto SaleReturnInsertMiddleTable(List<SaleReturnSubmitDto> list);

    JSONObject SaleReturnSubmit(MiddleReturnDto middleReturnDto);

    MiddleReturnDto SaleOutStockInsertMiddleTable(List<SaleOutStockDto> list, String createBy);
    JSONObject SaleOutStockSubmit(MiddleReturnDto middleReturnDto, String createBy);

    MiddleReturnDto SaleOrderOutStockInsertMiddleTable(List<SaleOrderOutDto> list);
    JSONObject SaleOrderOutStockSubmit(MiddleReturnDto middleReturnDto);
}
