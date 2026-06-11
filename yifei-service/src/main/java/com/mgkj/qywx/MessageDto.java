package com.mgkj.qywx;


import lombok.Data;

import java.util.List;

@Data
public class MessageDto extends com.mgkj.dto.MessageDto {

    private List<String> touser;
    //    private List<String> toparty;
    private String content;

}

