package com.mgkj.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/09/17:14
 * @Description:    http请求工具类
 */
public class HttpClientUtil {


    public static String postRequestMes(String url, String aramsJson) throws IOException {
        // 创建HttpPost对象
        System.out.println("创建HttpPost对象");
        HttpPost httpPost = new HttpPost(url);
        System.out.println("httpPost: " + httpPost);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

//        httpPost.setHeader("method", "ServicesCUS.Module_CUS.CUS_wo_create");
//        httpPost.setHeader("account", "dj001");
//        httpPost.setHeader("password", "123");
//        httpPost.setHeader("version", "6.0.1.2024051301");
//        httpPost.setHeader("mac", "WebClient");
//        httpPost.setHeader("lang", "zh_CN");
//        httpPost.setHeader("is_debug", String.valueOf(true));
//        httpPost.setHeader("platform", "web");

        // 将参数转换为json字符串并设置到httpPost实体中
        //String jsonParams = objectMapper.writeValueAsString(params);
        httpPost.setEntity(new StringEntity(aramsJson, "UTF-8"));

        System.out.println("发送请求并获取响应");

        System.out.println("http:" + httpPost);
        System.out.println(httpPost.getEntity());

        // 设置连接超时时间和套接字超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        httpPost.setConfig(requestConfig);


        // 发送请求并获取响应
        //CloseableHttpResponse response = httpClient.execute(httpPost);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response;
        response =  httpClient.execute(httpPost);
        //CloseableHttpResponse response = httpClient.execute(httpPost)
        System.out.println("response结果 :" + response);

        // 获取响应体
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        //System.out.println("res");
        System.out.println("responseBody :" + responseBody);
        return responseBody;

    }

    /**
     * http get请求
     * @param url 请求URL
     * @param params 请求参数 (可选)
     * @return 请求结果
     * @throws IOException
     */
    public static String getRequest(String url, Map<String, String> params) throws IOException {
        try {
            // 创建URIBuilder
            URIBuilder uriBuilder = new URIBuilder(url);

            // 添加请求参数
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }

            // 创建HttpGet对象
            System.out.println("创建HttpGet对象");
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            System.out.println("httpGet: " + httpGet);

            // 设置请求头
            httpGet.setHeader("Content-Type", "application/json;charset=utf8");

            // 设置连接超时时间和套接字超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            httpGet.setConfig(requestConfig);

            // 发送请求并获取响应
            System.out.println("发送请求并获取响应");
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println("response结果 :" + response);

            // 获取响应体
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("responseBody :" + responseBody);

            // 关闭资源
            response.close();
            httpClient.close();

            return responseBody;

        } catch (Exception e) {
            System.out.println("GET请求发生异常：" + e.getMessage());
            throw new IOException("GET请求失败", e);
        }
    }

    /**
     * 简化版http get请求（无参数）
     * @param url 请求URL
     * @return 请求结果
     * @throws IOException
     */
    public static String getRequest(String url) throws IOException {
        return getRequest(url, null);
    }

}
