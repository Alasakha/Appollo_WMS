package com.mgkj.entity.session;

/**
 * 完整请求封装（包含头部和请求体）
 */
public class DigiRequest {
    // 头部信息
    private String digiKey;
    private DigiHost digiHost;
    private DigiService digiService;
    private final String digiDataExchangeProtocol = "1.0"; // 固定值
    private final String contentType = "application/json"; // 固定值
    private final String digiType = "sync"; // 固定值

    // 请求体
    private StdData stdData;

    public DigiRequest(String digiKey, DigiHost digiHost, DigiService digiService, StdData stdData) {
        this.digiKey = digiKey;
        this.digiHost = digiHost;
        this.digiService = digiService;
        this.stdData = stdData;
    }

    // Getter方法（用于设置HTTP头部和序列化）
    public String getDigiKey() { return digiKey; }
    public DigiHost getDigiHost() { return digiHost; }
    public DigiService getDigiService() { return digiService; }
    public String getDigiDataExchangeProtocol() { return digiDataExchangeProtocol; }
    public String getContentType() { return contentType; }
    public String getDigiType() { return digiType; }
    public StdData getStdData() { return stdData; }
}