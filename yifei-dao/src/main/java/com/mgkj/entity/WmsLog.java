package com.mgkj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName wms_log
 */
@TableName(value ="wms_log")
@Data
public class WmsLog implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "request_path")
    private String requestPath;

    /**
     * 
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 
     */
    @TableField(value = "request_time")
    private Date requestTime;

    /**
     * 
     */
    @TableField(value = "source_ip")
    private String sourceIp;

    /**
     * 
     */
    @TableField(value = "service_info")
    private String serviceInfo;

    /**
     * 
     */
    @TableField(value = "request_params")
    private String requestParams;

    /**
     * 
     */
    @TableField(value = "response_body")
    private String responseBody;

    /**
     * 
     */
    @TableField(value = "duration_ms")
    private Long durationMs;

    /**
     * 
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}