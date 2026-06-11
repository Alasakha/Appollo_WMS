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
@TableName("V_YFXJ")
public class VYFXJ implements Serializable {
    private String MA002;
    private String YFJE;
}
