package com.mgkj.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mgkj.dto.MiddleReturnDto;
import com.mgkj.entity.session.*;
import com.mgkj.service.PurchaseTwoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.mgkj.common.constant.WmsConstants.*;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-08 20:15
 * @Version 1.0
 */

@Service
public class PurchaseTwoServiceImpl implements PurchaseTwoService {
    @Override
    public JSONObject PurchaseToStorageSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY, WMS_TYPE,
                new SessionHost(WMS_HOST_PROD, WMS_HOST_IP, WMS_HOST_LANG, WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD, PURCHASE_INSTORAGE_NAME, WMS_SERVICE_IP, WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID, WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "", "", "", "", "", "", "", "", "", "", "", "", ""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),
                                PURCHASE_INSTORAGE_APPMODULE, PURCHASE_INSTORAGE_APPVERSION, PURCHASE_INSTORAGE_NAME, WMS_ENTID, WMS_COMPANYID,
                                middleReturnDto.getErpRemark(), "0", ""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT + "/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    /**
     * 扫码入库-提交E10
     * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject PurchaseScanToStorageSubmit(MiddleReturnDto middleReturnDto, String createBy) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY, WMS_TYPE,
                new SessionHost(WMS_HOST_PROD, WMS_HOST_IP, WMS_HOST_LANG, createBy, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_T100, PURCHASE_SCAN_INSTORAGE_NAME, WMS_SERVICE_IP, WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid() ),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "", "", "", "", "", "", "", "", "", "", "", "", ""
                        ),
                        new ScanToStorageParameterHost(
                                createBy, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),
                                PURCHASE_SCAN_INSTORAGE_APPMODULE, PURCHASE_INSTORAGE_APPVERSION, PURCHASE_SCAN_INSTORAGE_NAME, WMS_ENTID, middleReturnDto.getWmsCompanyid(),
                                middleReturnDto.getErpRemark(), "0", ""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT + "/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;    }

    @Override
    public JSONObject PurchaseReceiptStorageSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY, WMS_TYPE,
                new SessionHost(WMS_HOST_PROD, WMS_HOST_IP, WMS_HOST_LANG, WMS_HOST_ACCT, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_T100, PURCHASE_RECEIPT_NAME, WMS_SERVICE_IP, WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID, middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                        new ScanToStorageParameterData(
                                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),
                                PURCHASE_RECEIPT_APPMODULE, PURCHASE_INSTORAGE_APPVERSION, PURCHASE_RECEIPT_NAME, WMS_ENTID, middleReturnDto.getWmsCompanyid(),
                                middleReturnDto.getErpRemark(), "0", ""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT + "/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }
}
