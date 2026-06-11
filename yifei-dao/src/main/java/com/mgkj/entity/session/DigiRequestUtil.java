package com.mgkj.entity.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 工具类：发送Digi格式请求，对外提供简单调用入口
 */
public class DigiRequestUtil {
    // 固定配置（可根据实际需求改为配置文件读取）
    private static final String DIGI_KEY = "0D5A8F6609E8F05F674E230CCA0901DB";
    private static final DigiHost DEFAULT_HOST = new DigiHost(
            "5.7", "YDHJY", "+8", "192.168.1.151",
            "", "zh_CN", "dcms", "2018071990007275"
    );
    private static final DigiService DEFAULT_SERVICE = new DigiService(
            "E10", "192.168.1.197",
            "ydhjy.po_arrival_inspection.confirm", "ABLTEST_External"
    );
    private static final ObjectMapper objectMapper = new ObjectMapper(); // Jackson实例

    /**
     * 对外暴露的核心方法：仅需传入DOC_NO和QC_TIMES即可发送请求
     * @param url 目标接口URL
     * @param docNo 业务编号（DOC_NO）
     * @param qcTimes 质检次数（QC_TIMES）
     * @return 接口响应字符串
     * @throws IOException 发送请求或序列化失败时抛出
     */
    public static String send(String url, String docNo, int qcTimes) throws IOException {
        // 1. 构建业务数据
        DataKey dataKey = new DataKey(docNo, qcTimes);
        // 2. 构建请求体
        StdData stdData = new StdData(new Parameter(dataKey));
        // 3. 构建完整请求对象
        DigiRequest request = new DigiRequest(DIGI_KEY, DEFAULT_HOST, DEFAULT_SERVICE, stdData);
        // 4. 发送请求并返回结果
        return executeRequest(url, request);
    }

    /**
     * 内部方法：执行HTTP请求
     */
    private static String executeRequest(String url, DigiRequest request) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // 设置HTTP头部
            httpPost.setHeader("digi-key", request.getDigiKey());
            httpPost.setHeader("digi-host", objectMapper.writeValueAsString(request.getDigiHost()));
            httpPost.setHeader("digi-service", objectMapper.writeValueAsString(request.getDigiService()));
            httpPost.setHeader("digi-data-exchange-protocol", request.getDigiDataExchangeProtocol());
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, request.getContentType());
            httpPost.setHeader("digi-type", request.getDigiType());

            // 设置请求体
            String requestBody = objectMapper.writeValueAsString(request.getStdData());
            httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }
        }
    }
}