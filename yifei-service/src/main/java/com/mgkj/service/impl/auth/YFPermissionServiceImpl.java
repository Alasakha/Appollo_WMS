package com.mgkj.service.impl.auth;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mgkj.common.utils.StringUtils;
import com.mgkj.entity.Admme;
import com.mgkj.entity.Admmf;
import com.mgkj.entity.Admmg;
import com.mgkj.entity.Dscma;
import com.mgkj.mapper.AdmmeMapper;
import com.mgkj.mapper.AdmmfMapper;
import com.mgkj.mapper.AdmmgMapper;
import com.mgkj.mapper.DscmaMapper;
import com.mgkj.service.auth.YFPermissionService;
import com.mgkj.vo.auth.MesPermission;
import com.mgkj.vo.auth.PermissionDetail;
import com.mgkj.vo.auth.YFPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YFPermissionServiceImpl implements YFPermissionService {

    @Autowired
    private DscmaMapper dscmaMapper;
    @Autowired
    private AdmmeMapper admmeMapper;

    @Autowired
    private AdmmfMapper admmfMapper;

    @Autowired
    private AdmmgMapper admmgMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DS("demo")
    public YFPermission getPermissonOnYF(String username) {
        YFPermission res = new YFPermission();
        //MyUser myUser = SecurityUtils.getLoginUser();
        Admmf admmf = admmfMapper.selectOne(new LambdaQueryWrapper<Admmf>().eq(Admmf::getMf001, username)
                .eq(Admmf::getMf008, "1"));
        if(admmf == null) {
            res.setAdmin("illegal");
            return res;
        }
        res.setGroupId(admmf.getMf004());
        if(StringUtils.isNotEmpty(res.getGroupId())) {
            Admme admme = admmeMapper.selectOne(new LambdaQueryWrapper<Admme>()
                    .eq(Admme::getMe001, res.getGroupId()));
            if(admme != null) res.setGroupName(admme.getMe002());
        }
        Admmf admmfGroup = admmfMapper.selectOne(new LambdaQueryWrapper<Admmf>().eq(Admmf::getMf001, "**********")
                .eq(Admmf::getMf008, "2").eq(Admmf::getMf004, admmf.getMf004()));
        if("Y".equals(admmf.getMf005()) || (admmfGroup != null && "Y".equals(admmfGroup.getMf005()))) {
            res.setAdmin("admin");
            return res;
        }
        res.setAdmin("common");
        res.setPURI07(getDetail(username, "PURI07", admmf.getMf004()));
        res.setCOPI06(getDetail(username, "COPI06", admmf.getMf004()));
        res.setPURI06(getDetail(username, "PURI06", admmf.getMf004()));
        res.setPURI06group(getDetailgroup(username, "PURI06", admmf.getMf004()));
        res.setCOPI06group(getDetailgroup(username, "COPI06", admmf.getMf004()));
        res.setPURI07group(getDetailgroup(username, "PURI07", admmf.getMf004()));
        res.setPURI06stranger(getDetailstranger(username, "PURI06", admmf.getMf004()));
        res.setCOPI06stranger(getDetailstranger(username, "COPI06", admmf.getMf004()));
        res.setPURI07stranger(getDetailstranger(username, "PURI07", admmf.getMf004()));
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DS("DSCSYS")
    public MesPermission getMesPermission(String username) {
        MesPermission mesPermission = new MesPermission();
        Dscma dscma = dscmaMapper.selectOne(new LambdaQueryWrapper<Dscma>().eq(Dscma::getMa001, username));
//        mesPermission.setWorkCenterPermission(dscma.getUdf05());
        return mesPermission;
    }

    private PermissionDetail getDetail(String username, String programId, String groupId) {
        PermissionDetail res = new PermissionDetail();
        Admmg admmg = admmgMapper.selectOne(new LambdaQueryWrapper<Admmg>()
                .eq(Admmg::getMg001, username).eq(Admmg::getMg002, programId));
        if(admmg != null) {
            String mg004 = admmg.getMg004();
            if("N".equals(mg004)) {
                res.setCanRun("N");
            }else if("Y".equals(mg004)) {
                res.setCanRun("Y");
                String mg006 = admmg.getMg006();
                res.setAdd("" + mg006.charAt(8));
                res.setSelect("" + mg006.charAt(0));
                res.setUpdate("" + mg006.charAt(1));
                res.setDel("" + mg006.charAt(2));
                res.setCheck("" + mg006.charAt(3));
                res.setUncheck("" + mg006.charAt(4));
                res.setInvalid("" + mg006.charAt(11));
                res.setOutput("" + mg006.charAt(5));
                res.setSubscribe("" + mg006.charAt(12));
                res.setSignature("" + mg006.charAt(9));
                res.setCost("" + mg006.charAt(6));
                res.setPrice("" + mg006.charAt(7));
                res.setAttached("" + mg006.charAt(13));
            }
        }else{
            res.setCanRun("N");
        }
//        System.out.println(username + "::" + programId + "::" + groupId);
        admmg = admmgMapper.selectOne(new LambdaQueryWrapper<Admmg>()
                .eq(Admmg::getMg001, "**********").eq(Admmg::getMg002, programId).eq(Admmg::getMg009, groupId));

//        System.out.println(admmg);
        if(admmg == null) return res;
        String mg004 = admmg.getMg004();
        if("Y".equals(mg004)) {
            res.setCanRun("Y");
            String mg006 = admmg.getMg006();
            if("Y".equals(""+mg006.charAt(8))) res.setAdd("" + mg006.charAt(8));
            if("Y".equals(""+mg006.charAt(0))) res.setSelect("" + mg006.charAt(0));
            if("Y".equals(""+mg006.charAt(1))) res.setUpdate("" + mg006.charAt(1));
            if("Y".equals(""+mg006.charAt(2))) res.setDel("" + mg006.charAt(2));
            if("Y".equals(""+mg006.charAt(3))) res.setCheck("" + mg006.charAt(3));
            if("Y".equals(""+mg006.charAt(4))) res.setUncheck("" + mg006.charAt(4));
            if("Y".equals(""+mg006.charAt(11))) res.setInvalid("" + mg006.charAt(11));
            if("Y".equals(""+mg006.charAt(5))) res.setOutput("" + mg006.charAt(5));
            if("Y".equals(""+mg006.charAt(12))) res.setSubscribe("" + mg006.charAt(12));
            if("Y".equals(""+mg006.charAt(9))) res.setSignature("" + mg006.charAt(9));
            if("Y".equals(""+mg006.charAt(6)) || "R".equals(""+mg006.charAt(6))) res.setCost("" + mg006.charAt(6));
            if("Y".equals(""+mg006.charAt(7)) || "R".equals(""+mg006.charAt(7))) res.setPrice("" + mg006.charAt(7));
            if("Y".equals(""+mg006.charAt(13)) || "R".equals(""+mg006.charAt(13))) res.setAttached("" + mg006.charAt(13));
        }

        if(StringUtils.isEmpty(res.getAdd())) res.setAdd("N");
        if(StringUtils.isEmpty(res.getSelect())) res.setSelect("N");
        if(StringUtils.isEmpty(res.getUpdate())) res.setUpdate("N");
        if(StringUtils.isEmpty(res.getDel())) res.setDel("N");
        if(StringUtils.isEmpty(res.getCheck())) res.setCheck("N");
        if(StringUtils.isEmpty(res.getUncheck())) res.setUncheck("N");
        if(StringUtils.isEmpty(res.getInvalid())) res.setInvalid("N");
        if(StringUtils.isEmpty(res.getOutput())) res.setOutput("N");
        if(StringUtils.isEmpty(res.getSubscribe())) res.setSubscribe("N");
        if(StringUtils.isEmpty(res.getSignature())) res.setSignature("N");
        if(StringUtils.isEmpty(res.getCost())) res.setCost("N");
        if(StringUtils.isEmpty(res.getPrice())) res.setPrice("N");
        if(StringUtils.isEmpty(res.getAttached())) res.setAttached("N");

        return res;
    }

    private PermissionDetail getDetailgroup(String username, String programId, String groupId) {
        PermissionDetail res = new PermissionDetail();
        Admmg admmg = admmgMapper.selectOne(new LambdaQueryWrapper<Admmg>()
                .eq(Admmg::getMg001, username).eq(Admmg::getMg002, programId));
        if(admmg != null) {
            String mg004 = admmg.getMg004();
            if("N".equals(mg004)) {
                res.setCanRun("N");
            }else if("Y".equals(mg004)) {
                res.setCanRun("Y");
                String mg007 = admmg.getMg007();
                res.setSelect("" + mg007.charAt(0));
                res.setUpdate("" + mg007.charAt(1));
                res.setDel("" + mg007.charAt(2));
                res.setCheck("" + mg007.charAt(3));
                res.setUncheck("" + mg007.charAt(4));
                res.setInvalid("" + mg007.charAt(6));
            }
        }else{
            res.setCanRun("N");
        }
//        System.out.println(username + "::" + programId + "::" + groupId);
        admmg = admmgMapper.selectOne(new LambdaQueryWrapper<Admmg>()
                .eq(Admmg::getMg001, "**********").eq(Admmg::getMg002, programId).eq(Admmg::getMg009, groupId));

//        System.out.println(admmg);
        if(admmg == null) return res;
        String mg004 = admmg.getMg004();
        if("Y".equals(mg004)) {
            res.setCanRun("Y");
            String mg007 = admmg.getMg007();
            if("Y".equals(""+mg007.charAt(0))) res.setSelect("" + mg007.charAt(0));
            if("Y".equals(""+mg007.charAt(1))) res.setUpdate("" + mg007.charAt(1));
            if("Y".equals(""+mg007.charAt(2))) res.setDel("" + mg007.charAt(2));
            if("Y".equals(""+mg007.charAt(3))) res.setCheck("" + mg007.charAt(3));
            if("Y".equals(""+mg007.charAt(4))) res.setUncheck("" + mg007.charAt(4));
            if("Y".equals(""+mg007.charAt(6))) res.setInvalid("" + mg007.charAt(6));
        }

        if(StringUtils.isEmpty(res.getAdd())) res.setAdd("N");
        if(StringUtils.isEmpty(res.getSelect())) res.setSelect("N");
        if(StringUtils.isEmpty(res.getUpdate())) res.setUpdate("N");
        if(StringUtils.isEmpty(res.getDel())) res.setDel("N");
        if(StringUtils.isEmpty(res.getCheck())) res.setCheck("N");
        if(StringUtils.isEmpty(res.getUncheck())) res.setUncheck("N");
        if(StringUtils.isEmpty(res.getInvalid())) res.setInvalid("N");
        if(StringUtils.isEmpty(res.getOutput())) res.setOutput("N");
        if(StringUtils.isEmpty(res.getSubscribe())) res.setSubscribe("N");
        if(StringUtils.isEmpty(res.getSignature())) res.setSignature("N");
        if(StringUtils.isEmpty(res.getCost())) res.setCost("N");
        if(StringUtils.isEmpty(res.getPrice())) res.setPrice("N");
        if(StringUtils.isEmpty(res.getAttached())) res.setAttached("N");

        return res;
    }

    private PermissionDetail getDetailstranger(String username, String programId, String groupId) {
        PermissionDetail res = new PermissionDetail();
        Admmg admmg = admmgMapper.selectOne(new LambdaQueryWrapper<Admmg>()
                .eq(Admmg::getMg001, username).eq(Admmg::getMg002, programId));
        if(admmg != null) {
            String mg004 = admmg.getMg004();
            if("N".equals(mg004)) {
                res.setCanRun("N");
            }else if("Y".equals(mg004)) {
                res.setCanRun("Y");
                String mg007 = admmg.getMg007();
                res.setSelect("" + mg007.charAt(0));
                res.setUpdate("" + mg007.charAt(1));
                res.setDel("" + mg007.charAt(2));
                res.setCheck("" + mg007.charAt(3));
                res.setUncheck("" + mg007.charAt(4));
                res.setInvalid("" + mg007.charAt(6));
            }
        }else{
            res.setCanRun("N");
        }
//        System.out.println(username + "::" + programId + "::" + groupId);
        admmg = admmgMapper.selectOne(new LambdaQueryWrapper<Admmg>()
                .eq(Admmg::getMg001, "**********").eq(Admmg::getMg002, programId).eq(Admmg::getMg009, groupId));

//        System.out.println(admmg);
        if(admmg == null) return res;
        String mg004 = admmg.getMg004();
        if("Y".equals(mg004)) {
            res.setCanRun("Y");
            String mg007 = admmg.getMg007();
            if("Y".equals(""+mg007.charAt(0))) res.setSelect("" + mg007.charAt(0));
            if("Y".equals(""+mg007.charAt(1))) res.setUpdate("" + mg007.charAt(1));
            if("Y".equals(""+mg007.charAt(2))) res.setDel("" + mg007.charAt(2));
            if("Y".equals(""+mg007.charAt(3))) res.setCheck("" + mg007.charAt(3));
            if("Y".equals(""+mg007.charAt(4))) res.setUncheck("" + mg007.charAt(4));
            if("Y".equals(""+mg007.charAt(6))) res.setInvalid("" + mg007.charAt(6));
        }

        if(StringUtils.isEmpty(res.getAdd())) res.setAdd("N");
        if(StringUtils.isEmpty(res.getSelect())) res.setSelect("N");
        if(StringUtils.isEmpty(res.getUpdate())) res.setUpdate("N");
        if(StringUtils.isEmpty(res.getDel())) res.setDel("N");
        if(StringUtils.isEmpty(res.getCheck())) res.setCheck("N");
        if(StringUtils.isEmpty(res.getUncheck())) res.setUncheck("N");
        if(StringUtils.isEmpty(res.getInvalid())) res.setInvalid("N");
        if(StringUtils.isEmpty(res.getOutput())) res.setOutput("N");
        if(StringUtils.isEmpty(res.getSubscribe())) res.setSubscribe("N");
        if(StringUtils.isEmpty(res.getSignature())) res.setSignature("N");
        if(StringUtils.isEmpty(res.getCost())) res.setCost("N");
        if(StringUtils.isEmpty(res.getPrice())) res.setPrice("N");
        if(StringUtils.isEmpty(res.getAttached())) res.setAttached("N");

        return res;
    }
}
