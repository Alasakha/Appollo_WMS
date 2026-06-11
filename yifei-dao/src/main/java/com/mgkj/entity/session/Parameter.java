package com.mgkj.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/2/19
 * @Description
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    private List<DataKey> dataKeys;

    // 传入单个DataKey，自动转为List
    public Parameter(DataKey dataKey) {
        this.dataKeys = Collections.singletonList(dataKey);
    }

}
