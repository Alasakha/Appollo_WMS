package com.mgkj.qywx;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private Integer errcode;
    private String errmsg;
    private String access_token;
    private Integer expires_in;
}
