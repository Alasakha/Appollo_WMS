package com.mgkj.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : ssy
 * @date: 2024-01-09
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("V_YSXJ")
public class VYSXJ implements Serializable {
    private String MA002;
    private String YSJE;
}
