package com.mgkj.qywx;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class TextContent {
    /**
     * 消息内容，最长不超过2048个字节，超过将截断（支持id转译）
     * 必填
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
}
