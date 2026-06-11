package com.mgkj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Attachment {
    @ApiModelProperty("附件名称")
    private String fileName;

    @ApiModelProperty("附件内容")
    private String content;
}
