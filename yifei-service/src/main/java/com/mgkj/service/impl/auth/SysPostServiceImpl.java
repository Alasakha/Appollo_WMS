package com.mgkj.service.impl.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.auth.SysPost;
import com.mgkj.mapper.auth.SysPostMapper;
import com.mgkj.service.auth.SysPostService;
import org.springframework.stereotype.Service;

@Service
@DS("mes")
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {


}
