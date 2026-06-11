package com.mgkj.entity.session;

/**
 * digi-host头部信息实体
 */
public class DigiHost {
    private String ver;
    private String prod;
    private String timezone;
    private String ip;
    private String id;
    private String lang;
    private String acct;
    private String timestamp;

    public DigiHost(String ver, String prod, String timezone, String ip, String id, String lang, String acct, String timestamp) {
        this.ver = ver;
        this.prod = prod;
        this.timezone = timezone;
        this.ip = ip;
        this.id = id;
        this.lang = lang;
        this.acct = acct;
        this.timestamp = timestamp;
    }

    // Getter方法（Jackson序列化需要）
    public String getVer() { return ver; }
    public String getProd() { return prod; }
    public String getTimezone() { return timezone; }
    public String getIp() { return ip; }
    public String getId() { return id; }
    public String getLang() { return lang; }
    public String getAcct() { return acct; }
    public String getTimestamp() { return timestamp; }
}