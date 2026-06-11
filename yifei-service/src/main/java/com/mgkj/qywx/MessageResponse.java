package com.mgkj.qywx;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MessageResponse {
    /**
     * 返回码
     * 0表示成功，其他值表示失败
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer errcode;

    /**
     * 对返回码的文本描述内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errmsg;

    /**
     * 无效的成员ID列表
     * 成员不存在时返回
     * 多个成员ID用'|'分隔
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String invaliduser;

    /**
     * 无效的部门ID列表
     * 部门不存在时返回
     * 多个部门ID用'|'分隔
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String invalidparty;

    /**
     * 无效的标签ID列表
     * 标签不存在时返回
     * 多个标签ID用'|'分隔
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String invalidtag;

    /**
     * 未授权的成员ID列表
     * 成员无接收消息的权限时返回
     * 多个成员ID用'|'分隔
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String unlicenseduser;

    /**
     * 消息ID
     * 用于消息追踪
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msgid;

    /**
     * 仅消息类型为"按钮交互型"，"投票选择型"和"多项选择型"的模板卡片消息返回
     * 应用可使用response_code调用更新模版卡片消息接口，24小时内有效
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String response_code;
}
