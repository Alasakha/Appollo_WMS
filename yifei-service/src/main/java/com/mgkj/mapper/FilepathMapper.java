package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mgkj.entity.Filepath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 14501
* @description 针对表【filePath】的数据库操作Mapper
* @createDate 2024-12-26 15:38:42
* @Entity com.mgkj.entity.Filepath
*/
@Mapper
@DS("db3")
public interface FilepathMapper extends BaseMapper<Filepath> {

}




