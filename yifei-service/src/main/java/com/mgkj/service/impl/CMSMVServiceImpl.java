package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.CMSME;
import com.mgkj.entity.CMSMV;
import com.mgkj.mapper.CMSMEMapper;
import com.mgkj.mapper.CMSMVMapper;
import com.mgkj.service.CMSMVService;
import com.mgkj.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/11:14
 * @Description:    员工信息档实现类
 */
@Service
public class CMSMVServiceImpl extends ServiceImpl<CMSMVMapper, CMSMV> implements CMSMVService {

    @Resource
    private CMSMVMapper cmsmvMapper;

    @Resource
    private CMSMEMapper cmsmeMapper;


    @Override
    public List<CMSMV> getAll(String MV001,String MV002) {
        List<CMSMV> cmsmvs = cmsmvMapper.getAll(MV001, MV002);
        return cmsmvs;
    }

}
