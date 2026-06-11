package com.mgkj.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class E10ApiService {

    private final String base_url = "http://60.191.200.182:9990/CROSS/RESTful";

    /**
     * 创建到货单
     * @param jsonBody
     * @return
     */
    public JSONObject createArrival(String jsonBody) {

        String digiKey = "E48B08CCF2A43BB7EDAABA9AD8A4567A";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YWHDHD\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ywhdhd.purchase_arrival.create\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        HttpResponse response = HttpRequest.post(base_url)
                .header("digi-key", digiKey)
                .header("digi-host", digiHost)
                .header("digi-service", digiService)
                .header("digi-data-exchange-protocol", digiDataExchangeProtocol)
                .header("Content-Type", contentType)
                .header("digi-type", digiType)
                .body(jsonBody)
                .execute();

        return JSON.parseObject(response.body());
    }

    /**
     * 审核到货单
     * @param docNo
     * @return
     */
    public JSONObject confirmArrival(String docNo) {

        String digiKey = "E96F25FF614A8829792CA6E5B7F4E2FF";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YWHDHD\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ywhdhd.purchase_arrival.confirm\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        String requestBody = "{\n" +
                "  \"std_data\": {\n" +
                "    \"parameter\": {\n" +
                "      \"dataKeys\": [\n" +
                "        {\n" +
                "          \"DOC_NO\": \"" + docNo + "\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpResponse response = HttpRequest.post(base_url)
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

    /**
     * 删除到货单
     * @param docNo
     * @return
     */
    public JSONObject deleteArrival(String docNo) {

        String digiKey = "EE26FC5ABA446F623295361AA68D7676";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YWHDHD\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ywhdhd.purchase_arrival.delete\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        String requestBody = "{\n" +
                "  \"std_data\": {\n" +
                "    \"parameter\": {\n" +
                "      \"dataKeys\": [\n" +
                "        {\n" +
                "          \"DOC_NO\": \"" + docNo + "\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpResponse response = HttpRequest.post(base_url)
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

    /**
     * 创建到货检验单
     * @param jsonBody
     * @return
     */
    public JSONObject createArrivalInspection(String jsonBody) {

        String digiKey = "84232321418EC1A749D6A131E7D26D9A";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YDHJY\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ydhjy.po_arrival_inspection.create\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        HttpResponse response = HttpRequest.post(base_url)
                .header("digi-key", digiKey)
                .header("digi-host", digiHost)
                .header("digi-service", digiService)
                .header("digi-data-exchange-protocol", digiDataExchangeProtocol)
                .header("Content-Type", contentType)
                .header("digi-type", digiType)
                .body(jsonBody)
                .execute();

        return JSON.parseObject(response.body());
    }

    /**
     * 审核到货检验单
     * @param docNo
     * @param qcTimes
     * @return
     */
    public JSONObject confirmArrivalInspection(String docNo, String qcTimes) {

        String digiKey = "0D5A8F6609E8F05F674E230CCA0901DB";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YDHJY\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ydhjy.po_arrival_inspection.confirm\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

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

        HttpResponse response = HttpRequest.post(base_url)
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

    /**
     * 撤审到货检验单
     * @param docNo
     * @param qcTimes
     * @return
     */
    public JSONObject disConfirmArrivalInspection(String docNo, String qcTimes) {

        String digiKey = "107A552F0C0CB6DF520CF64183144600";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YDHJY\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"ydhjy.po_arrival_inspection.disconfirm\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

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

        HttpResponse response = HttpRequest.post(base_url)
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

    /**
     * 撤审生产入库单
     * @param docNo
     * @return
     */
    public JSONObject disConfirmMoReceipt(String docNo) {

        String digiKey = "01059BB2F8826E5DB9F05570B9B51A0A";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YSCRK\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"yscrk.mo_receipt.disconfirm\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        String requestBody = "{\n" +
                "  \"std_data\": {\n" +
                "    \"parameter\": {\n" +
                "      \"dataKeys\": [\n" +
                "        {\n" +
                "          \"DOC_NO\": \"" + docNo + "\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpResponse response = HttpRequest.post(base_url)
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

    /**
     * 审核生产入库单
     * @param docNo
     * @return
     */
    public JSONObject confirmMoReceipt(String docNo) {

        String digiKey = "AC696770F0051B3EE27AFBF137D8357D";
        String digiHost = "{\"ver\":\"5.7\",\"prod\":\"YSCRK\",\"timezone\":\"+8\",\"ip\":\"192.168.1.151\",\"id\":\"\",\"lang\":\"zh_CN\",\"acct\":\"dcms\",\"timestamp\":\"2018071990007275\"}";
        String digiService = "{\"prod\":\"E10\",\"ip\":\"192.168.1.197\",\"name\":\"yscrk.mo_receipt.confirm\",\"id\":\"ABLTEST_External\"}";
        String digiDataExchangeProtocol = "1.0";
        String contentType = "application/json";
        String digiType = "sync";

        String requestBody = "{\n" +
                "  \"std_data\": {\n" +
                "    \"parameter\": {\n" +
                "      \"dataKeys\": [\n" +
                "        {\n" +
                "          \"DOC_NO\": \"" + docNo + "\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpResponse response = HttpRequest.post(base_url)
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
