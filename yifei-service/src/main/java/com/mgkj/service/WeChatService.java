package com.mgkj.service;


import com.mgkj.dto.MessageDto;
import org.springframework.stereotype.Component;

@Component
public interface WeChatService {

    public void sendWXMessage(MessageDto messageDto);

}
