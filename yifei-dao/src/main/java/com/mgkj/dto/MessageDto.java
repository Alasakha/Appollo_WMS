package com.mgkj.dto;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class MessageDto {
    private List<String> touser;
    //    private List<String> toparty;
    private String content;

}

