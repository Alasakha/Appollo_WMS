package com.mgkj.service;

import com.alibaba.fastjson.JSONObject;
import com.mgkj.dto.MiddleReturnDto;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-08 20:07
 * @Version 1.0
 */


public interface PurchaseTwoService {
    JSONObject PurchaseToStorageSubmit(MiddleReturnDto middleReturnDto);

    JSONObject PurchaseScanToStorageSubmit(MiddleReturnDto middleReturnDto, String createBy);

    JSONObject PurchaseReceiptStorageSubmit(MiddleReturnDto middleReturnDto);
}
