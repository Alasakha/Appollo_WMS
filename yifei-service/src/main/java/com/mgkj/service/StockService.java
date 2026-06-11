package com.mgkj.service;

import com.alibaba.fastjson.JSONObject;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.vo.OtherInStockDetailVo;
import com.mgkj.vo.OtherOutStockDetailVo;


import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */
public interface StockService {
    Result ListStock(ListStockDto dto);

    Result ListOtherInStock(OtherInStockDto dto);

    Result<List<OtherInStockDetailVo>> OtherInStockDetail(String docNo);
    //其他入库插入中间表
    MiddleReturnDto insertMiddleTable(List<OtherInstockSubmitDto> list);
    //其他入库提交
    JSONObject OtherInStockSubmit(MiddleReturnDto middleReturnDto);

    Result ListOtherOutStock(OtherOutStockDto dto);

    Result<List<OtherOutStockDetailVo>> OtherOutStockDetail(String docNo);

    MiddleReturnDto OtherOutStockInsertMiddleTable(List<OtherOutStockSubmitDto> list);

    JSONObject OtherOutStockSubmit(MiddleReturnDto middleReturnDto);

    Result ListTransferDoc(ListTransferDocDto dto);

    Result ListTransferDocDetail(String docNo);

    MiddleReturnDto insertTransferDocMiddleTable(List<TransferDocSubmitDto> list);

    JSONObject ListTransferDocSubmit(MiddleReturnDto middleReturnDto);

    MiddleReturnDto insertTransferDocOutMiddleTable(List<TransferDocOutSubmitDto> list);

    JSONObject ListTransferDocOutSubmit(MiddleReturnDto middleReturnDto);


}
