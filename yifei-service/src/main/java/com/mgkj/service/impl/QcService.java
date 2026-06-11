package com.mgkj.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QcService {

    public JSONObject Qc(String docNo, String qcTimes){
        // 请求的URL
        String url = "http://60.191.200.182:9990/CROSS/RESTful";

        // 设置请求头
        String digiKey = "0D5A8F6609E8F05F674E230CCA0901DB";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YDHJY\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ydhjy.po_arrival_inspection.confirm\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        // 请求体
        String requestBody = "{\n" +
                "  \"std_data\": {\n" +
                "    \"parameter\": {\n" +
                "      \"dataKeys\": [\n" +
                "        {\n" +
                "          \"DOC_NO\": \"" + docNo + "\",\n" +
                "          \"QC_TIMES\": " + qcTimes + "\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        // 发送POST请求
        HttpResponse response = HttpRequest.post(url)
                .header("digi-key", digiKey)
                .header("digi-host", digiHost)
                .header("digi-service", digiService)
                .header("digi-data-exchange-protocol", digiDataExchangeProtocol)
                .header("Content-Type", contentType)
                .header("digi-type", digiType)
                .body(requestBody)
                .execute();

        return JSON.parseObject(response.body());
    }

}
