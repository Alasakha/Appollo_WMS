package com.mgkj.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mgkj.common.utils.HttpClientUtil;
import com.mgkj.dto.MessageDto;
import com.mgkj.exception.BaseException;
import com.mgkj.qywx.*;
import com.mgkj.service.WeChatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class WeChatServiceImpl implements WeChatService {


    @Autowired
    private CorpiProperties corpiProperties;

    public void sendWXMessage(MessageDto messageDto) {
        String accessToken = getAccessToken();
        if (accessToken == null) {
//            log.info("获取access_token失败");
            throw new BaseException(500, "获取access_token失败,corpid和corpsecret有误！");
        }
        StringBuilder touser=new StringBuilder();
        for (String string : messageDto.getTouser()) {
            touser.append(string);
            touser.append("|");
        }
        TextMessage textMessage=new TextMessage();
        textMessage.setAgentid(Integer.valueOf(corpiProperties.getAgentid()));//企业应用的id，整型。企业内部开发，可在应用的设置页面查看
        textMessage.setTouser(touser.toString());//指定接收消息的成员，成员ID列表
        TextContent textContent=new TextContent();
        textContent.setContent(messageDto.getContent());//消息内容，最长不超过2048个字节，超过将截断
        textMessage.setText(textContent);
        StringBuilder url=new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=")
                .append(accessToken);
        String s;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(textMessage);
            s = HttpClientUtil.postRequestMes(url.toString(),jsonString);
        }catch (Exception e){
            throw new BaseException(500, "请求发送异常");
        }
        MessageResponse response = JSON.parseObject(s, MessageResponse.class);
        // 检查发送结果
        if (response.getErrcode() == 0) {
            // 检查是否有无效的接收者
            if (response.getInvaliduser() != null) {
                StringBuilder responseString=new StringBuilder("存在无效的接收者：");
                responseString.append(response.getInvaliduser());
                throw new BaseException(500, responseString.toString());
            }
            if (response.getUnlicenseduser() != null) {
                StringBuilder responseString=new StringBuilder("存在未授权的接收者：");
                responseString.append(response.getUnlicenseduser());
                throw new BaseException(500, responseString.toString());
            }
        } else {
            throw new BaseException(500, "消息发送失败！");
        }
    }

    private String getAccessToken() {

//        // 先从Redis获取
//        String accessToken = (String) redisCache.get("access_token");
//        if (accessToken != null) {
//            return accessToken;
//        }

        // Redis没有，则重新获取
        StringBuilder url = new StringBuilder("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=")
                .append(corpiProperties.getCorpid())
                .append("&corpsecret=")
                .append(corpiProperties.getCorpsecret());

        try {
            String response = HttpClientUtil.getRequest(url.toString());
            AccessTokenResponse tokenResponse = JSON.parseObject(response, AccessTokenResponse.class);

            if (tokenResponse.getErrcode() == 0) {
                // 存入Redis，设置过期时间为7000秒（比微信的7200秒少200秒）
//                redisCache.set("access_token", tokenResponse.getAccess_token(), 7000);
                return tokenResponse.getAccess_token();
            } else {
                log.error("获取access_token失败：{}", tokenResponse.getErrmsg());
                return null;
            }
        } catch (IOException e) {
            log.error("获取access_token异常", e);
            return null;
        }
    }

}
