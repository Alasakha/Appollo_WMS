package com.mgkj.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeChatMapper {

     String getNoFromQYWX(String name);

}
