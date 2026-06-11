package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.Doc;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yyyjcg
 * @date 2024/1/24
 * @Description
 */
@Mapper
public interface DocMapper extends BaseMapper<Doc> {
    Doc selectBydocCode(String docCode);
    Doc selectBydocName(String docName);
}
