package com.mgkj.entity.session;

/**
 * digi-service头部信息实体
 */
public class DigiService {
    private String prod;
    private String ip;
    private String name;
    private String id;

    public DigiService(String prod, String ip, String name, String id) {
        this.prod = prod;
        this.ip = ip;
        this.name = name;
        this.id = id;
    }

    // Getter方法
    public String getProd() { return prod; }
    public String getIp() { return ip; }
    public String getName() { return name; }
    public String getId() { return id; }
}