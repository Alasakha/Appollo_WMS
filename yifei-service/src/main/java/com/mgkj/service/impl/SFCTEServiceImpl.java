package com.mgkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.common.utils.DateUtils;
import com.mgkj.exception.YiFeiException;
import com.mgkj.dto.*;
import com.mgkj.entity.*;
import com.mgkj.mapper.*;
import com.mgkj.service.CmsmwService;
import com.mgkj.service.SFCTEService;
import com.mgkj.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/15/15:14
 * @Description:    派工/报工 service 接口实现类
 */
@Slf4j
@Service
public class SFCTEServiceImpl extends ServiceImpl<SFCTEMapper, SFCTE> implements SFCTEService {

    private final String url = "http://192.168.1.168:8080/img/";
    @Resource
    private SFCTAMapper sfctaMapper;
    @Resource
    private SFCTEMapper sfcteMapper;
    @Resource
    private MOCTAMapper moctaMapper;
    @Resource
    private CMSMDMapper cmsmdMapper;
    @Resource
    private CMSMEMapper cmsmeMapper;
    @Resource
    private SFCTDMapper sfctdMapper;
    @Resource
    private INVMBMapper invmbMapper;
    @Resource
    private QmsmgMapper qmsmgMapper;
    @Resource
    private IHeadMapper iHeadMapper;
    @Resource
    private IBodyMapper iBodyMapper;
    @Resource
    private IBasicsMapper iBasicsMapper;

    @Resource
    private CmsmwService cmsmwService;
    @Resource
    private SfcteBoServiceImpl sfcteBoService;
    @Resource
    private SfcteBoMapper sfcteBoMapper;

    @Override
    public Result quxiao(String code) {
        this.sfcteMapper.updateYourEntityS(code);
        return Result.ok();
    }

    @Override
    public Result queryById(Pojo pojo) {
        SFCTE sfcte = this.sfcteMapper.queryById(pojo);
        if (sfcte==null){
            return Result.ok();
        }
        QueryWrapper<Qmsmg> qmsmgQueryWrapper = new QueryWrapper<>();
        qmsmgQueryWrapper.eq("MG002", pojo.getArticle().trim());// 品号
        qmsmgQueryWrapper.eq("MG020", pojo.getChecky().trim()); //检验项目
        qmsmgQueryWrapper.eq("MG008", pojo.getGy().trim()); // 区分工艺
        if (!StringUtils.isEmpty(pojo.getRomm())){
            qmsmgQueryWrapper.eq("UDF06", pojo.getRomm()); // 场地
        }
        List<Qmsmg> qmsmgs = this.qmsmgMapper.selectList(qmsmgQueryWrapper);
        if (!qmsmgs.isEmpty()) {
            sfcte.setQmsmgList(qmsmgs);
        }
        MocVo mocVo = new MocVo();
        mocVo.setSingetsu(sfcte.getTE001());
        mocVo.setOdd(sfcte.getTE002());
        mocVo.setShijian(sfcte.getTE023());
        mocVo.setSbm(sfcte.getTE005());
        mocVo.setGx(sfcte.getTE008());
        mocVo.setPinhao(sfcte.getTE017());
        mocVo.setGy(sfcte.getTE009());
        mocVo.setGg(sfcte.getTE019());
        mocVo.setPm(sfcte.getTE018());
        mocVo.setJy(pojo.getChecky());
        mocVo.setZx(sfcte.getUDF10());
        mocVo.setScx(sfcte.getUDF09());
        mocVo.setQmsmgList(sfcte.getQmsmgList());
        mocVo.setStatus(sfcte.getStatus());
        return Result.ok(mocVo);
    }

    @Override
    public Result test(String code) {
        this.sfcteMapper.updateYourEntity(code);
        return Result.ok();
    }

    public Result queryPageList(Pojo pojo) {
        try {
            PageHelper.startPage(pojo.getCurrent(),pojo.getSize());
            Map<String,Object> map = new HashMap<>();
            List<SFCTE> sfcteList = this.sfcteMapper.queryPageTest(pojo);
            PageInfo<SFCTE> pageInfo = new PageInfo<>(sfcteList);
            List<MocVo> mocVoList = pageInfo.getList().stream()
                    .map(arr -> {
                        MocVo mocVo = new MocVo();
                        mocVo.setSingetsu(arr.getTE001());
                        mocVo.setOdd(arr.getTE002());
                        mocVo.setShijian(arr.getTE023());
                        mocVo.setSbm("1");
                        mocVo.setGx(arr.getTE008());
                        mocVo.setPinhao(arr.getTE017());
                        mocVo.setGy(arr.getTE009());
                        mocVo.setGg(arr.getTE019());
                        mocVo.setPm(arr.getTE018());
                        mocVo.setJy(pojo.getChecky());
                        mocVo.setZx(arr.getUDF10());
                        if (arr.getStatus().equals("已取消")){
                            mocVo.setStatus(arr.getStatus());
                        }else{
                            mocVo.setStatus("待检验");
                        }
                        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                        String format = dateFormat.format(new Date());
                        mocVo.setTime(format);
                        mocVo.setScx(arr.getUDF09());
                        return mocVo;
                    })
                    .collect(Collectors.toList());
            map.put("total",pageInfo.getTotal());
            map.put("page",pageInfo.getPages());
            map.put("data",mocVoList);
            return Result.ok(map);
        }catch (Exception e){
            throw new YiFeiException(500,"品号分页查询发生异常错误"+e.getMessage());
        }
    }

    @Override
    public Result<SFCTE> detailPB(String TE001, String TE002) {
        try {
            SFCTE sfcte = this.sfcteMapper.selectOne(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002));
            if(sfcte.getUDF10() != null) sfcte.setSonName(this.sfcteMapper.getMw002ByMw001(sfcte.getUDF10()));
            if(sfcte.getTE009() != null) sfcte.setName(this.sfcteMapper.getMw002ByMw001(sfcte.getTE009()));
            if (sfcte!=null){
                String s = this.sfcteMapper.queryMOROUTINGDID(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
                List<SfcteBo> sonList = this.sfcteBoMapper.selectList(new LambdaQueryWrapper<SfcteBo>().eq(SfcteBo::getMO_ROUTING_D_ID, s));
//                System.out.println(sonList);
                sfcte.setSonList(sonList);
            }
            return Result.ok(sfcte);
        }catch (Exception e){
            throw new YiFeiException(500,"详情查询发生异常"+e.getMessage());
        }
    }

    //    @Override
    public Result deleteDp(String TE001, String TE002) {
        try{
            SFCTE sfcte = this.sfcteMapper.selectOne(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002));
            if (sfcte!=null){
                List<SFCTE> sfcteList = this.sfcteMapper.querySfCte(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
                if (!sfcteList.isEmpty()){
                    int delete = this.sfcteMapper.delete(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002));
                    if (delete>0){
                        return Result.ok(TE001+"-"+TE002+"派工单已删除!");
                    }
                }
                return Result.fail("此"+TE001+"-"+TE002+"派工单已存在报工单,无法删除!");
            }
            return Result.fail("此"+TE001+"-"+TE002+"派工单不存在,请重试!");
        }catch (Exception e){
            throw new YiFeiException(500,"报工单删除发生异常"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result deleteDb(String TE001, String TE002, String TE003) {
        try{
            if (TE001.equals("D410")){
                SFCTE sfcte = this.sfcteMapper.selectOne(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002).eq("TE003", TE003));
                if (sfcte==null){
                    return Result.fail("此"+TE001+"-"+TE002+"报工单不存在,无法删除!");
                }
                int delete = this.sfcteMapper.delete(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002).eq("TE003", TE003));
                if (delete>0){
                    Integer deletesfctd = this.sfcteMapper.deletesfctd(TE001, TE002);
                    if (deletesfctd>0){
                        SFCTE pg = this.sfcteMapper.getByTE001WithTE002WithTE003(sfcte.getUDF09(), sfcte.getUDF10(), sfcte.getUDF11());
                        BigDecimal te011bg = this.sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008(pg.getTE001(), pg.getTE002(), pg.getTE003());
                        if (Double.parseDouble(String.valueOf(pg.getTE011()))>Double.parseDouble(String.valueOf(te011bg))){
                            this.sfcteMapper.updateBg(sfcte.getTE001()+"-"+sfcte.getTE002()+"-"+sfcte.getTE003(),"N");
                        }
                        return Result.ok(TE001+"-"+TE002+"报工单已删除!");
                    }else {
                        return Result.fail(TE001+"-"+TE002+"报工单删除失败!");
                    }
                }
                return Result.fail(TE001+"-"+TE002+"报工单删除失败!");
            } else {   // 派工单
                SFCTE sfcte = this.sfcteMapper.selectOne(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002).eq("TE003", TE003));
                if (sfcte==null){
                    return Result.fail("此"+TE001+"-"+TE002+"-"+TE003+"派工单不存在,无法删除!");
                }
                BigDecimal te011 = this.sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008(sfcte.getTE001(), sfcte.getTE002(), sfcte.getTE003());
                if (Double.parseDouble(String.valueOf(te011)) == 0.0){
                    int delete = this.sfcteMapper.delete(new QueryWrapper<SFCTE>().eq("TE001", TE001).eq("TE002", TE002).eq("TE003", TE003));
                    if (delete>0){
                        String s = this.sfcteMapper.queryMOROUTINGDID(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
                        this.sfcteBoMapper.delete(new LambdaQueryWrapper<SfcteBo>().eq(SfcteBo::getMO_ROUTING_D_ID, s));
                        List<IHead> iHeads = this.iHeadMapper.queryTa001IHeadList(TE001, TE002);
                        if (!iHeads.isEmpty()){
                            iHeads.forEach(arr -> {
                                Integer del = iHeadMapper.del(arr.getUid());
                                if (del>0){
                                    List<IBody> iBodyList = iBodyMapper.getAllByI_uid(arr.getUid());
                                    for (IBody iBody : iBodyList) {
                                        Integer i_uid = iBody.getUid();
                                        iBasicsMapper.del(i_uid);
                                    }
                                    Integer del1 = iBodyMapper.del(arr.getUid());
                                }
                            });
                        }
                        return Result.ok(TE001+"-"+TE002+"-"+TE003+"派工单已删除!");
                    }
                }
                return Result.fail("此"+TE001+"-"+TE002+"-"+TE003+"派工单已存在报工单,无法删除!");
            }
        }catch (Exception e){
            throw new YiFeiException(500,"删除发生异常"+e.getMessage());
        }
    }

    @Override
    public Result queryRejects(SelectDto selectDto) {
        Map<String,Object> map =new HashMap<>();
        IPage<SFCTE> iPage = new Page<>(selectDto.getCurrent(),selectDto.getSize());

        QueryWrapper<SFCTE> sfcteQueryWrapper = new QueryWrapper<>();
        sfcteQueryWrapper.eq("TE001","D410");
        sfcteQueryWrapper.ne("UDF51",0);

        // 通过工单号进行查询
        if (!StringUtils.isEmpty(selectDto.getNumber())){
            sfcteQueryWrapper.like("TE006 + '-' + TE007",selectDto.getNumber());
        }
        // 通过工艺行查询
        if (!StringUtils.isEmpty(selectDto.getCraft())){
            sfcteQueryWrapper.like("TE009",selectDto.getCraft());
        }
        // 通过工作中心进行查询
        if (!StringUtils.isEmpty(selectDto.getWorkCenter())){
            sfcteQueryWrapper.like("UDF10",selectDto.getWorkCenter());
        }
        // 通过订单号进行查询
        if (!StringUtils.isEmpty(selectDto.getOrderNumber())){
            sfcteQueryWrapper.like("UDF04",selectDto.getOrderNumber());
        }
        // 通过品号查询
        if (!StringUtils.isEmpty(selectDto.getItemNo())){
            sfcteQueryWrapper.like("TE017",selectDto.getItemNo());
        }
        // 通过员工姓名查询
        if (!StringUtils.isEmpty(selectDto.getName())){
            sfcteQueryWrapper.like("UDF07",selectDto.getName());
        }
        // 通过时间区域查询
        if (!StringUtils.isEmpty(selectDto.getStartTime())&&!StringUtils.isEmpty(selectDto.getStopTime())){
            sfcteQueryWrapper.between("TE023",selectDto.getStartTime(),selectDto.getStopTime());
        }


        // 通过时间排序
        sfcteQueryWrapper.orderByDesc("TE023");
        IPage<SFCTE> sfcteiPage = this.sfcteMapper.selectPage(iPage, sfcteQueryWrapper);

        map.put("page",sfcteiPage.getPages());
        map.put("data",sfcteiPage.getRecords());
        map.put("total",sfcteiPage.getTotal());

        return Result.ok(map);
    }

    public Result querySfCtaIPag(PojoDto2 pojoDto) {
        //排序条件不为空
        if(pojoDto.getSjStr() != "" && pojoDto.getSjStr() != null
                && pojoDto.getSjstatus() != "" && pojoDto.getSjstatus() != null){
            String orderStr = pojoDto.getSjStr()+" "+pojoDto.getSjstatus();
            PageHelper.startPage(pojoDto.getCurrent(),pojoDto.getSize(),orderStr);
        }else {
            PageHelper.startPage(pojoDto.getCurrent(),pojoDto.getSize());
        }
        //工单查询
        List<SfctaMoctaTwoVo> sfctaMoctaVos = this.sfcteMapper.querySfCtaIPag2(pojoDto);
        sfctaMoctaVos.stream().forEach(arr-> {
                    //添加部门名称
                    CMSME cmsme = cmsmeMapper.getByME001(arr.getTa064());
                    if (cmsme != null) {
                        arr.setDname(cmsme.getME002());
                    }
                    //工单工艺数据插入
                    List<SfctaBo> sfctaList = sfctaMapper.selectListByDh(arr.getDh());
                    sfctaList.stream().forEach(a-> {
                        //添加子工序
                        List<SfcteBo> sonList = this.sfcteMapper.getSonList(a.getMO_ROUTING_D_ID());
                        a.setSonList(sonList);
                    });
                    arr.setSfctaList(sfctaList);
                }
        );
        PageInfo<SfctaMoctaTwoVo> pageInfo = new PageInfo<>(sfctaMoctaVos);
        Map<String,Object> map = new HashMap<>();
        map.put("page",pageInfo.getPages());
        map.put("data",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return Result.ok(map);
    }

    @Override
    public List<SFCTA> querySfCta(String TA001, String TA002) {
        QueryWrapper<SFCTA> sfctaQueryWrapper = new QueryWrapper<>();
        sfctaQueryWrapper.eq("TA001",TA001);
        sfctaQueryWrapper.eq("TA002",TA002);
        List<SFCTA> sfctas = this.sfctaMapper.selectList(sfctaQueryWrapper);
        if (sfctas.isEmpty()){
            throw new YiFeiException(500,"此单别单号暂无工单工艺信息!");
        }
        return sfctas;
    }

    @Override
    @Transactional
    public Result insertSfcte(List<SFCTE> sfcte) {
        try{
            // 派工信息返回值
            List<String> msgList = new ArrayList<>();
            String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").
                    format(new Date()).replaceAll("-", "").
                    replaceAll(":", "").replaceAll(" ", "");
            if (!sfcte.isEmpty()){
                sfcte.stream().forEach(arr -> {
                    Long ta015 = this.sfcteMapper.querymocta(arr.getTE006(), arr.getTE007());
                    if (ta015!=null){
                        Integer integer = this.sfcteMapper.querySfCtePgNum(arr.getTE006(),arr.getTE007(),arr.getTE008(), arr.getUDF09());
                        int num = Integer.parseInt(String.valueOf(ta015)) - integer;
                        if (num>=Integer.parseInt(String.valueOf(arr.getTE011()))&&Integer.parseInt(String.valueOf(arr.getTE011()))>0){
                            arr.setTE001("D420");
                            arr.setTE002(sfcteMapper.getNewTE002Year4("D420"));
                            arr.setTE003(String.format("%04d", sfcteMapper.getTe003ByTe001Te002(arr.getTE001(), arr.getTE002())));
                            arr.setUDF54(new BigDecimal(0));
                            arr.setUDF55(new BigDecimal(0));
                            arr.setUDF56(new BigDecimal(0));
                            arr.setUDF57(new BigDecimal(ta015));
                            //设置派工日期
                            arr.setTE023(nowTime.substring(0,8));
                            arr.setCREATE_DATE(nowTime);
                            int insert = this.sfcteMapper.insert(arr);
                            if (arr.getSonList()!=null){
                                sfcteBoService.saveBatch(arr.getSonList());
                            }
                            if (insert>0){
                                msgList.add(arr.getTE006()+"-"+arr.getTE007()+"-"+arr.getTE008()+"派工成功");
                            }
                        }else{
                            msgList.add(arr.getTE006()+"-"+arr.getTE007()+"-"+arr.getTE008()+"派工失败,此工序还可派工数量为:"+num+",请重试!");
                        }
                    }else {
                        msgList.add(arr.getTE006()+"-"+arr.getTE007()+"-"+arr.getTE008()+"派工失败,此工序不存在");
                    }
                });
            }else{
                throw new YiFeiException(500, "派工失败,请输入正确的参数");
            }
            return Result.ok(msgList);
        }catch (Exception e){
            throw new YiFeiException(500,"派工单新增发生异常,请联系管理员"+e);
        }
    }





    public Result querySfCtAMoCtaList(Integer pageNum, Integer pageSize,String TA001,String TA002,String TA006) {
        PageHelper.startPage(pageNum,pageSize);
        List<SfctaMoctaVo> sfctaMoctaVos = this.sfcteMapper.querySfCtAMoCtaList(TA001,TA002,TA006);
        sfctaMoctaVos.stream().forEach(arr-> {
                    // 此工单工艺的报工数量
                    Integer integer = this.sfcteMapper.querySfCteNum(arr.getTa001(), arr.getTa002(), arr.getTa003());
                    arr.setNum(BigDecimal.valueOf(integer));
                    double v = Double.parseDouble(String.valueOf(integer)) / Double.parseDouble(String.valueOf(arr.getTa015()));
                    DecimalFormat df = new DecimalFormat("#.0");
                    String formattedValue = df.format(v*100.00);
                    arr.setPercent(formattedValue);
                    String pdf = invmbMapper.getMB029(arr.getTa006());
                    arr.setPdf(url + StringUtils.toStringTrim(pdf)+".pdf");
                    CMSME cmsme = cmsmeMapper.getByME001(arr.getTa064());
                    String UDF03 = "" ;
                    if (cmsme != null) {
                        UDF03 = cmsme.getME002();
                    }
                    arr.setDname(UDF03);
                }
        );
        PageInfo<SfctaMoctaVo> pageInfo = new PageInfo<>(sfctaMoctaVos);
        Map<String,Object> map = new HashMap<>();
        map.put("page",pageInfo.getPages());
        map.put("data",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return Result.ok(map);
    }

    @Override
    public List<SFCTE> querySfCte(String odd,String Single,String Gx) {
        List<SFCTE> sfcteList = this.sfcteMapper.querySfCte(odd, Single, Gx);
        return sfcteList;
    }

    public List<SFCTE> querySfCteS(String odd,String Single,String Gx) {
        List<SFCTE> sfcteList = this.sfcteMapper.querySfCte(odd, Single, Gx);
        return sfcteList;
    }

    @Override
    public Result queryIPage(SelectDto selectDto) {
        Map<String,Object> map =new HashMap<>();
        IPage<SFCTE> iPage = new Page<>(selectDto.getCurrent(),selectDto.getSize());

        QueryWrapper<SFCTE> sfcteQueryWrapper = new QueryWrapper<>();
        sfcteQueryWrapper.eq("TE001","D410");

        // 通过工单号进行查询
        if (!StringUtils.isEmpty(selectDto.getNumber())){
            sfcteQueryWrapper.like("TE006 + '-' + TE007",selectDto.getNumber());
        }
        // 通过工艺行查询
        if (!StringUtils.isEmpty(selectDto.getCraft())){
            sfcteQueryWrapper.like("TE009",selectDto.getCraft());
        }
        // 通过工作中心进行查询
        if (!StringUtils.isEmpty(selectDto.getWorkCenter())){
            sfcteQueryWrapper.like("UDF10",selectDto.getWorkCenter());
        }
        // 通过订单号进行查询
        if (!StringUtils.isEmpty(selectDto.getOrderNumber())){
            sfcteQueryWrapper.like("UDF04",selectDto.getOrderNumber());
        }
        // 通过品号查询
        if (!StringUtils.isEmpty(selectDto.getItemNo())){
            sfcteQueryWrapper.like("TE017",selectDto.getItemNo());
        }
        // 通过员工姓名查询
        if (!StringUtils.isEmpty(selectDto.getName())){
            sfcteQueryWrapper.like("UDF07",selectDto.getName());
        }
        // 通过时间区域查询
        if (!StringUtils.isEmpty(selectDto.getStartTime())&&!StringUtils.isEmpty(selectDto.getStopTime())){
            sfcteQueryWrapper.between("TE023",selectDto.getStartTime(),selectDto.getStopTime());
        }

        // 通过时间排序
        sfcteQueryWrapper.orderByDesc("TE023");
        IPage<SFCTE> sfcteiPage = this.sfcteMapper.selectPage(iPage, sfcteQueryWrapper);
        for(SFCTE sfcte : sfcteiPage.getRecords()) {
            if(sfcte.getTE009() != null) {
                sfcte.setName(sfcteMapper.getMw002ByMw001(sfcte.getTE009()));
            }
            if(sfcte.getUDF09() != null && sfcte.getUDF10() != null && sfcte.getUDF11() != null) {
                SFCTE one = sfcteMapper.selectOne(new LambdaQueryWrapper<SFCTE>().eq(SFCTE::getTE001, sfcte.getUDF09()).eq(SFCTE::getTE002, sfcte.getUDF10()).eq(SFCTE::getTE003, sfcte.getUDF11()));
                if(one != null) {
                    sfcte.setSonId(one.getUDF10());
                    sfcte.setSonName(sfcteMapper.getMw002ByMw001(one.getUDF10()));
                }
            }
        }
        map.put("page",sfcteiPage.getPages());
        map.put("data",sfcteiPage.getRecords());
        map.put("total",sfcteiPage.getTotal());

        return Result.ok(map);
    }

    @Override
    public Result queryIPageS(SelectDto selectDto) {
        Map<String,Object> map =new HashMap<>();
        IPage<SFCTE> iPage = new Page<>(selectDto.getCurrent(),selectDto.getSize());

        QueryWrapper<SFCTE> sfcteQueryWrapper = new QueryWrapper<>();
        sfcteQueryWrapper.eq("TE001","D420");

        // 通过工单号进行查询
        if (!StringUtils.isEmpty(selectDto.getNumber())){
            sfcteQueryWrapper.like("TE006 + '-' + TE007",selectDto.getNumber());
        }
        // 通过工艺行查询
        if (!StringUtils.isEmpty(selectDto.getCraft())){
            sfcteQueryWrapper.like("TE009",selectDto.getCraft());
        }
        // 通过工作中心进行查询
        if (!StringUtils.isEmpty(selectDto.getWorkCenter())){
            sfcteQueryWrapper.like("UDF10",selectDto.getWorkCenter());
        }
        // 通过订单号进行查询
        if (!StringUtils.isEmpty(selectDto.getOrderNumber())){
            sfcteQueryWrapper.like("UDF04",selectDto.getOrderNumber());
        }
        // 通过品号查询
        if (!StringUtils.isEmpty(selectDto.getItemNo())){
            sfcteQueryWrapper.like("TE017",selectDto.getItemNo());
        }
        // 通过员工姓名查询
        if (!StringUtils.isEmpty(selectDto.getName())){
            sfcteQueryWrapper.like("UDF07",selectDto.getName());
        }
        // 通过时间区域查询
        if (!StringUtils.isEmpty(selectDto.getStartTime())&&!StringUtils.isEmpty(selectDto.getStopTime())){
            sfcteQueryWrapper.between("TE023",selectDto.getStartTime(),selectDto.getStopTime());
        }


        // 通过时间排序
        sfcteQueryWrapper.orderByDesc("TE023");
        IPage<SFCTE> sfcteiPage = this.sfcteMapper.selectPage(iPage, sfcteQueryWrapper);

        map.put("page",sfcteiPage.getPages());
        map.put("data",sfcteiPage.getRecords());
        map.put("total",sfcteiPage.getTotal());

        return Result.ok(map);
    }

    @Override
    public List<SFCTE> selectListByDbDh(String single, String odd) {
        List<SFCTE> sfcteList = sfcteMapper.getListByTE001WithTE002(single,odd);
        for (SFCTE sfcte : sfcteList) {
            // 查询已报工数量
            BigDecimal TE011 = sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008("D420",sfcte.getTE002(),sfcte.getTE003());
            // 设置工单数量 select TA015 from where TA001 = '' and TA002 = ''
            MOCTA mocta = moctaMapper.getByTA001WithTA002(sfcte.getTE006(), sfcte.getTE007());
            sfcte.setUDF58(mocta.getTA015());
            sfcte.setUDF59(TE011);
            sfcte.setUDF57(sfcte.getTE011());
            double v = Double.parseDouble(String.valueOf(TE011)) / Double.parseDouble(String.valueOf(sfcte.getTE011()));
            DecimalFormat df = new DecimalFormat("#.0");
            String formattedValue = df.format(v*100.00);
            sfcte.setPercent(formattedValue);

            // 根据工单单笔单号加工序号查询对应的工单工艺详情
            SFCTA sfcta = sfctaMapper.getByTA001WithTA002WithTA003(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
            //TODO 客户TA019有区别，后续可能还需要切换并且修改实体类
            sfcte.setUDF12(sfcta.getTA019());
            if (StringUtils.isBlank(sfcte.getUDF12())){
                String pdf = invmbMapper.getMB029(sfcte.getTE017());
                sfcte.setUDF04(url + StringUtils.toStringTrim(pdf)+".pdf");
            }else {
                String pdf = invmbMapper.getMB029(sfcte.getUDF12());
                sfcte.setUDF04(url + StringUtils.toStringTrim(pdf)+".pdf");
            }
            //根据sfcta中的工作中心找到对应的部门编号
            CMSMD cmsmd = cmsmdMapper.getByMD001(sfcta.getTA006());
            // 根据工作中心的部门找到对应的部门编号 和 部门名称
            // UDF02我们设置部门编号
            if (cmsmd!=null){
                sfcte.setUDF02(cmsmd.getMD015());
                CMSME cmsme = cmsmeMapper.getByME001(cmsmd.getMD015());
                String UDF03 = "" ;
                if (cmsme != null) {
                    UDF03 = cmsme.getME002();
                }
                // UDF03我们设置为部门名称
                sfcte.setUDF03(UDF03);
//            sfcte.setUDF06(sfcte.getTE007());
                System.out.println(UDF03);
            }
            String s = this.sfcteMapper.queryMOROUTINGDID(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
            List<SfcteBo> sonList = this.sfcteBoMapper.selectList(new LambdaQueryWrapper<SfcteBo>().eq(SfcteBo::getMO_ROUTING_D_ID, s));
//                System.out.println(sonList);
            sfcte.setSonList(sonList);
        }

        return sfcteList;
    }

    @Override
    public List<SFCTE> selectListStaff(String code,String dh,String ph) {
        List<SFCTE> sfcteList = sfcteMapper.selectListStaff(code,dh,ph);
        for (SFCTE sfcte : sfcteList) {
            // 查询已报工数量
            BigDecimal TE011 = sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008("D420",sfcte.getTE002(),sfcte.getTE003());
            // 设置工单数量 select TA015 from where TA001 = '' and TA002 = ''
            MOCTA mocta = moctaMapper.getByTA001WithTA002(sfcte.getTE006(), sfcte.getTE007());
            if (mocta!=null){
                sfcte.setUDF58(mocta.getTA015());
            }
            sfcte.setUDF59(TE011);
            sfcte.setUDF57(sfcte.getTE011());
            double v = Double.parseDouble(String.valueOf(TE011)) / Double.parseDouble(String.valueOf(sfcte.getTE011()));
            DecimalFormat df = new DecimalFormat("#.0");
            String formattedValue = df.format(v*100.00);
            System.out.println("值2"+v);
            System.out.println("值："+formattedValue);
            sfcte.setPercent(formattedValue);

            // 根据工单单笔单号加工序号查询对应的工单工艺详情
            SFCTA sfcta = sfctaMapper.getByTA001WithTA002WithTA003(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
            if (sfcta!=null){
                //TODO 客户TA019有区别，后续可能还需要切换并且修改实体类
                sfcte.setUDF12(sfcta.getTA019());
                if (StringUtils.isBlank(sfcte.getUDF12())){
                    String pdf = invmbMapper.getMB029(sfcte.getTE017());
                    sfcte.setUDF04(url + StringUtils.toStringTrim(pdf)+".pdf");
                }else {
                    String pdf = invmbMapper.getMB029(sfcte.getUDF12());
                    sfcte.setUDF04(url + StringUtils.toStringTrim(pdf)+".pdf");
                }
                //根据sfcta中的工作中心找到对应的部门编号
                CMSMD cmsmd = cmsmdMapper.getByMD001(sfcta.getTA006());
                // 根据工作中心的部门找到对应的部门编号 和 部门名称
                // UDF02我们设置部门编号
                sfcte.setUDF02(cmsmd.getMD015());
                CMSME cmsme = cmsmeMapper.getByME001(cmsmd.getMD015());
                String UDF03 = "" ;
                if (cmsme != null) {
                    UDF03 = cmsme.getME002();
                }
                // UDF03我们设置为部门名称
                sfcte.setUDF03(UDF03);
//            sfcte.setUDF06(sfcte.getTE007());
                System.out.println(UDF03);
            }

            String s = this.sfcteMapper.queryMOROUTINGDID(sfcte.getTE006(), sfcte.getTE007(), sfcte.getTE008());
            List<SfcteBo> sonList = this.sfcteBoMapper.selectList(new LambdaQueryWrapper<SfcteBo>().eq(SfcteBo::getMO_ROUTING_D_ID, s));
//                System.out.println(sonList);
            sfcte.setSonList(sonList);
        }

        return sfcteList;
    }

    @Override
    @Transactional
    public Result add(SFCTE sfcte) {
        try {
            synchronized (this) {
                log.info(sfcte.toString());
                //查派工
                SFCTE findSFcte = sfcteMapper.getByTE001WithTE002WithTE003(sfcte.getTE001(),sfcte.getTE002(),sfcte.getTE003());
                if (findSFcte==null){
                    return Result.fail("派工单号不存在", "派工单号不存在");
                }

                //龙虎需求
//            if(!sfcte.getTE003().equals("0001")) {
//
//
//                //获取前工序已报工数量
//                BigDecimal fro =sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008("D420",sfcte.getTE002(),frontNo);
//                System.out.println(now);
//                System.out.println(fro);
//                //(判断当前工序已报工数量+报工数量)是否大于前道工序已报工数量
//                if(now.compareTo(fro) > 0){
//                    return Result.fail("当前工序报工数不能大于前道工序已报工","当前工序报工数不能大于前道工序已报工");
//                }
//            }
//
                //工序限制
//            //获取当前工序已报工数量
//            BigDecimal n = sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008("D420",sfcte.getTE002(),sfcte.getTE003());
//            //当前工序已报工数量+报工数量
//            BigDecimal now = n.add(sfcte.getTE011().add(sfcte.getUDF51()).add(sfcte.getUDF52()).add(sfcte.getUDF53()));
//            //根据派工单获取对应工单的工序,员工，报告数集合
//             = sfcteMapper.getGXList("D420",sfcte.getTE002(),sfcte.getTE003());



//            if(findSFcte.getUDF08()!=null){
//                if (findSFcte.getUDF08().equals("Y")){
//                    return Result.fail("派工单报工结束,无需要重复报工","派工单报工结束,无需要重复报工");
//                }
//            }


//            List<SFCTE> sfcteList = this.sfcteMapper.gette001(findSFcte.getTE006(), findSFcte.getTE007(), findSFcte.getTE008());
//            if (!sfcteList.isEmpty()){
//                List<IHead> iHeads = this.iHeadMapper.queryTa001IHeadList(sfcteList.get(0).getTE001(), sfcteList.get(0).getTE002());
//                if (!iHeads.isEmpty()){
//                    if (iHeads.get(0).getResult().equals("N")){
//                        return Result.fail(iHeads.get(0).getTa001()+"-"+iHeads.get(0).getTa002()+"检验未合格,请勿报工!",iHeads.get(0).getTa001()+"-"+iHeads.get(0).getTa002()+"检验未合格,请勿报工!");
//                    }
//                }
//            }
                SFCTE saveSFCTE =  new SFCTE();
                SFCTD saveSFCTD =  new SFCTD();
                String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").
                        format(new Date()).replaceAll("-", "").
                        replaceAll(":", "").replaceAll(" ", "");
                //设置当前创建时间
                saveSFCTE.setCREATE_DATE(nowTime);

                String nowTimeNew = new SimpleDateFormat("yyyyMMddHHmmss").
                        format(new Date());
                String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                saveSFCTD.setCREATE_DATE(nowTimeNew);
                saveSFCTD.setCREATOR(sfcte.getCREATOR());
                // 设置报工单单别
                saveSFCTD.setTD001("D410");
                // 生成报工单单号
                String d410 = sfcteMapper.getNewTE002Year4("D410");
                saveSFCTD.setTD002(d410);
                saveSFCTD.setTD003(localDate);
                saveSFCTD.setTD005("Y");
                saveSFCTD.setTD004(" ");
                saveSFCTD.setUDF01("白班");
                saveSFCTD.setTD008(localDate);
                saveSFCTE.setCREATOR(sfcte.getCREATOR());
                sfctdMapper.save(saveSFCTD);


                // 设置报工单单别
                saveSFCTE.setTE001(saveSFCTD.getTD001());
                // 生成报工单单号
                saveSFCTE.setTE002(saveSFCTD.getTD002());
                // 设置序号
                saveSFCTE.setTE003(sfcte.getTE003());
                // 设置报工人员姓名
                saveSFCTE.setTE004(sfcte.getTE004());
                // 设置工单单别
                saveSFCTE.setTE006(findSFcte.getTE006());
                //设置工单单号
                saveSFCTE.setTE007(findSFcte.getTE007());
                // 设置工序
                saveSFCTE.setTE008(findSFcte.getTE008());
                // 设置工艺
                saveSFCTE.setTE009(findSFcte.getTE009());
                // 设置类型
                saveSFCTE.setTE010(findSFcte.getTE010());
                saveSFCTE.setTE011(sfcte.getTE011());
                //设置审核默认审核
                saveSFCTE.setTE014("Y");
                //设置备注
                saveSFCTE.setTE015(sfcte.getTE015());
                //设置   包装数量
                saveSFCTE.setTE016(findSFcte.getTE016());
                //设置产品品号
                saveSFCTE.setTE017(findSFcte.getTE017());
                //设置产品品名
                saveSFCTE.setTE018(findSFcte.getTE018());
                //设置产品规格
                saveSFCTE.setTE019(findSFcte.getTE019());
                //设置单位
                saveSFCTE.setTE020(findSFcte.getTE020());
                //设置包装单位
                saveSFCTE.setTE021(findSFcte.getTE021());
                //设置报工日期
                saveSFCTE.setTE023(nowTime.substring(0,8));
                //设置 预留字段
                saveSFCTE.setTE024(sfcte.getTE024());
                //设置 预留字段
                saveSFCTE.setTE025(sfcte.getTE025());
                //设置 预留字段
                saveSFCTE.setTE026(sfcte.getTE026());
                //设置 预留字段
                saveSFCTE.setTE027(sfcte.getTE027());
                //设置 计价单价
                saveSFCTE.setTE028(findSFcte.getTE028());
                //设置 工时工资率
                saveSFCTE.setTE029(findSFcte.getTE029());
                //设置 计件类型
                saveSFCTE.setTE030(findSFcte.getTE030());
                //设置j金额
                saveSFCTE.setTE031(sfcte.getTE031());
                //设置项目编号
                saveSFCTE.setTE032(findSFcte.getTE032());
                //设置工艺名称
                saveSFCTE.setUDF01(findSFcte.getUDF01());
                //设置UDF02不良原因
                saveSFCTE.setUDF02(sfcte.getUDF02());
                //设置UDF03工废原因
                saveSFCTE.setUDF03(sfcte.getUDF03());
                //设置UDF04废料原因
                saveSFCTE.setUDF04(sfcte.getUDF04());
                //设置UDF05
                saveSFCTE.setUDF05("");
                //设置工艺名称
                saveSFCTE.setUDF06(findSFcte.getUDF06());
                //设置工艺名称
                saveSFCTE.setUDF07(sfcte.getUDF07());
                //设置工艺名称
                saveSFCTE.setUDF08(findSFcte.getUDF08());
                //设置工艺名称
                saveSFCTE.setUDF09(findSFcte.getTE001());
                //设置工艺名称
                saveSFCTE.setUDF10(findSFcte.getTE002());
                //设置工艺名称
                saveSFCTE.setUDF11(findSFcte.getTE003());
                //设置工艺名称
                saveSFCTE.setUDF12(sfcte.getUDF12());
                //设置工废数量
                saveSFCTE.setUDF51(sfcte.getUDF51());
                //设置料废数量
                saveSFCTE.setUDF52(sfcte.getUDF52());
                //设置不良数量
                saveSFCTE.setUDF53(sfcte.getUDF53());
                //设置UDF54
                saveSFCTE.setUDF54(sfcte.getUDF53());
                //设置UDF55
                saveSFCTE.setUDF55(sfcte.getUDF53());
                //设置UDF56
                saveSFCTE.setUDF56(sfcte.getUDF53());
                //设置UDF57
                saveSFCTE.setUDF57(sfcte.getUDF57());
                //设置UDF58
                saveSFCTE.setUDF58(sfcte.getUDF58());
                //设置UDF59
                saveSFCTE.setUDF59(sfcte.getUDF59());
                //设置UDF60
                saveSFCTE.setUDF60(sfcte.getUDF60());
                //设置UDF61
                saveSFCTE.setUDF61(sfcte.getUDF61());
                //设置UDF62
                saveSFCTE.setUDF62(sfcte.getUDF62());
                //设置UDF05
                saveSFCTE.setUDF05(sfcte.getUDF05());
                saveSFCTE.setStatus("待检验");
                // 添加报工单身
                sfcteMapper.save(saveSFCTE);


//                BigDecimal te011bg = this.sfcteMapper.getTE011WithTE001WithTE006WithTE007WithTE008(sfcte.getTE001(), sfcte.getTE002(), sfcte.getTE003());
//                if (Double.parseDouble(String.valueOf(te011bg))>=Double.parseDouble(String.valueOf(findSFcte.getTE011()))){
//                    this.sfcteMapper.updateBg(sfcte.getTE001()+"-"+sfcte.getTE002()+"-"+sfcte.getTE003(),"Y");
//                }

                return Result.ok("报工成功");
            }
        }catch (Exception e){
            throw new YiFeiException(500,"报工错误发生异常:"+e.getMessage());
        }
    }


    /**
     * 查询报工单
     * @param queryBgDto
     * @return
     */
    public PageInfo<SfcteVo> queryGd(QueryBgDto queryBgDto) {
        PageHelper.startPage(queryBgDto.getPageNum(),queryBgDto.getPageSize());
        List<SfcteVo> sfcteList = this.sfcteMapper.queryGd(queryBgDto);
        PageInfo<SfcteVo> pageInfo = PageInfo.of(sfcteList);
        return pageInfo;
    }


    /**
     * 删除报工单
     * @param single
     * @param odd
     * @param xh
     * @return
     */
    @Transactional
    @Async
    public CompletableFuture<Result> deleteBg(String single, String odd, String xh) {
        try{
            synchronized (this) {
                //删除报工单sfcte
                sfcteMapper.deleteBg(single,odd,xh);
                //查询对应工单单身是否删除完毕
                if (sfcteMapper.selectBg(single,odd)==null){
                    //删除sfctd
                    sfctdMapper.deleteBg(single,odd);
                }

                return CompletableFuture.completedFuture(Result.ok("删除成功!"));
            }
        }catch (Exception e){
            throw new YiFeiException(500,"删除报工单错误发生异常:"+e.getMessage());
        }
    }

    @Override
    public Result sfcteadd(ReportDto reportDto) {
        SFCTE sfcte=sfcteMapper.selectOne(new QueryWrapper<SFCTE>()
                .eq("TE001",reportDto.getTe001())
                .eq("TE002",reportDto.getTe002())
                .eq("TE003",reportDto.getTe003()));
        if (sfcte==null){
            return Result.fail(reportDto.getTe001()+reportDto.getTe002()+reportDto.getTe003()+"报工失败,或不存在此派工单工序!");
        }else {
            SFCTD sfctd = new SFCTD();
            sfctd.setTD001("D410");
            Long currentTD002 = Long.parseLong(this.sfcteMapper.selectTd002("D410").trim())+1;
            sfctd.setTD002(String.valueOf(currentTD002));
            sfctd.setCREATE_DATE(DateUtils.getNow());
            sfctd.setTD003(DateUtils.dateTimeNumber());
//            sfctd.setUDF51(sfctdVo.getUdf51());
            int insert = this.sfctdMapper.insert(sfctd);
            if (insert>0){
                //报工单单别
                sfcte.setTE001(sfctd.getTD001());
                //报工单单号
                sfcte.setTE002(sfctd.getTD002());
                //报工单序号
                sfcte.setTE003(String.valueOf(1));
                //报工单存储派工单单别
                sfcte.setUDF09(reportDto.getTe001());
                //报工单存储派工单单号
                sfcte.setUDF10(reportDto.getTe002());
                //报工单存储派工单序号
                sfcte.setUDF11(reportDto.getTe003());
                //工艺编号
                sfcte.setTE009(reportDto.getTe009());
                //合格数量
                sfcte.setTE011(BigDecimal.valueOf(reportDto.getTe011()));
                //工废数量
                sfcte.setUDF51(BigDecimal.valueOf(reportDto.getUdf51()));
                //料废数量
                sfcte.setUDF52(BigDecimal.valueOf(reportDto.getUdf52()));
                //不良数量
                sfcte.setUDF53(BigDecimal.valueOf(reportDto.getUdf53()));
                //员工编号
                sfcte.setTE004(reportDto.getTe004());
                //员工名称
                sfcte.setUDF07(reportDto.getUdf07());
                //报工日期
                sfcte.setTE023(DateUtils.dateTimeNumber());
                //工序
//                sfcte.setTE008(sfctdVo.getTe008());
                //品号
//                sfcte.setTE017(sfctdVo.getTa006());
                //品名
//                sfcte.setTE018(sfctdVo.getTa034());
                //规格
//                sfcte.setTE019(sfctdVo.getTa035());
                this.sfcteMapper.insert(sfcte);
            }
            return Result.ok(reportDto.getTe001()+reportDto.getTe002()+reportDto.getTe003()+"报工成功!");
        }
    }

    @Override
    public List<BgNumVo> queryBgCount(QueryBgNumDto queryBgNumDto) {
        try{
            List<BgNumVo> bgNumVos = this.sfcteMapper.queryBgCount(queryBgNumDto);
            return bgNumVos;
        }catch (Exception e){
            throw new YiFeiException(500,"查询个人产量错误发生异常:"+e.getMessage());
        }
    }

    @Override
    public Result endPg(EndPgDto dto) {
        //结束派工单
        try {
            sfctaMapper.updateUdf09("D420",dto.getOdd(),dto.getXh());
        }catch (Exception e){
            throw new YiFeiException(500,"结束派工单错误发生异常:"+e.getMessage());
        }
        return Result.ok("结束派工成功!");
    }

    public String SerialNumber(String te006,String te007,String te008) {
        QueryWrapper<SFCTE> dispatchListQueryWrapper = new QueryWrapper<>();
        dispatchListQueryWrapper.orderByDesc("TE003");
        dispatchListQueryWrapper.eq("TE006",te006);
        dispatchListQueryWrapper.eq("TE007",te007);
        dispatchListQueryWrapper.eq("TE008",te008);
        //查询最新的流水号
        List<SFCTE> sCells = sfcteMapper.selectList(dispatchListQueryWrapper);
        //用于保存获取最新的流水号
        String temp="";
        //用于拼接需要的流水号
        String serialNumber="";
        //保存最新的流水号
        if(!sCells.isEmpty()){
            temp=sCells.get(0).getTE003();
        }
        //判断数据库是否有数据
        if (!StringUtils.isEmpty(temp)){
            Integer integer=Integer.valueOf(temp.trim())+1;
            if (integer>10){
                serialNumber="00"+String.valueOf(integer);
            }else {
                serialNumber="000"+String.valueOf(integer);
            }
        }else{
            serialNumber="0001";
        }
        return serialNumber;
    }

    @Override
    public List<SFCTE> qualityFrom(String page,String pageSize, String udf06, String start_time,String end_time) {
        return sfcteMapper.qualityFrom(page,pageSize,udf06,start_time,end_time);
    }

    @Override
    public List<SFCTE> querySfCteByTe004(QuerySfCteByTe004DTO dto) {
        return sfcteMapper.querySfCteByTe004(dto);
    }

    @Override
    public int qualityFromCount(String udf06, String start_time, String end_time) {
        return sfcteMapper.qualityFromCount(udf06,start_time,end_time);
    }

    @Override
    public Map<String, List<ReportVo>> qualityFromDown(String udf06, String start_time, String end_time,String type) {
        Map<String, List<ReportVo>> map = new HashMap<>();
        if (udf06!=null && !udf06.isEmpty()) {
            if ("外壳全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromDown("全检外壳", start_time, end_time, type));
            }else if ("内胆全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromDown("全检内胆", start_time, end_time, type));
            }else if ("镀铜全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromDown("全检镀铜内胆", start_time, end_time, type));
            }else if ("中底全检".equals(udf06)){
                List<ReportVo> dataList = new ArrayList<>();
                dataList.addAll(sfcteMapper.qualityFromDown("全检外底", start_time, end_time, type));
                dataList.addAll(sfcteMapper.qualityFromDown("全检激光", start_time, end_time, type));
                map.put("中底全检",dataList);
            }else if ("内底全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromDown("全检内底", start_time, end_time, type));
            }
            return map;
        }
        map.put("外壳全检", sfcteMapper.qualityFromDown("全检外壳", start_time, end_time, type));
        map.put("内胆全检", sfcteMapper.qualityFromDown("全检内胆", start_time, end_time, type));
        map.put("镀铜全检", sfcteMapper.qualityFromDown("全检镀铜内胆", start_time, end_time, type));
        List<ReportVo> dataList = new ArrayList<>();
        dataList.addAll(sfcteMapper.qualityFromDown("全检外底", start_time, end_time, type));
        dataList.addAll(sfcteMapper.qualityFromDown("全检激光", start_time, end_time, type));
        map.put("中底全检",dataList);
        map.put("内底全检", sfcteMapper.qualityFromDown("全检内底", start_time, end_time, type));
        return map;
    }

    @Override
    public Map<String, List<ReportVo>> qualityFromUp(String udf06, String start_time, String end_time,String type) {
        Map<String, List<ReportVo>> map = new HashMap<>();
        if (udf06!=null && !udf06.isEmpty()) {
            if ("外壳全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromUp("全检外壳", start_time, end_time, type));
            }else if ("内胆全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromUp("全检内胆", start_time, end_time, type));
            }else if ("镀铜全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromUp("全检镀铜内胆", start_time, end_time, type));
            }else if ("中底全检".equals(udf06)){
                List<ReportVo> dataList = new ArrayList<>();
                dataList.addAll(sfcteMapper.qualityFromUp("全检外底", start_time, end_time, type));
                dataList.addAll(sfcteMapper.qualityFromUp("全检激光", start_time, end_time, type));
                map.put("中底全检",dataList);
            }else if ("内底全检".equals(udf06)){
                map.put(udf06, sfcteMapper.qualityFromUp("全检内底", start_time, end_time, type));
            }
            return map;
        }
        map.put("外壳全检", sfcteMapper.qualityFromUp("全检外壳", start_time, end_time, type));
        map.put("内胆全检", sfcteMapper.qualityFromUp("全检内胆", start_time, end_time, type));
        map.put("镀铜全检", sfcteMapper.qualityFromUp("全检镀铜内胆", start_time, end_time, type));
        List<ReportVo> dataList = new ArrayList<>();
        dataList.addAll(sfcteMapper.qualityFromUp("全检外底", start_time, end_time, type));
        dataList.addAll(sfcteMapper.qualityFromUp("全检激光", start_time, end_time, type));
        map.put("中底全检",dataList);
        map.put("内底全检", sfcteMapper.qualityFromUp("全检内底", start_time, end_time, type));
        return map;
    }

    @Override
    public List<SFCTE> qualityFromTemp(String udf06,String page, String pageSize, String start_time, String end_time) {
        if ("一次测温".equals(udf06) || "三次测温".equals(udf06)) {
            return sfcteMapper.qualityFromTemp(udf06,page, pageSize, start_time, end_time);
        }else if("二次测温".equals(udf06)){
            return null;
        }
        return null;
    }

    @Override
    public int qualityFromTempCount(String udf06,String start_time, String end_time) {
        int count = 0;
        if ("一次测温".equals(udf06) || "三次测温".equals(udf06)) {
            count = sfcteMapper.qualityFromTempCount(udf06, start_time, end_time);
        }
        return count;
    }

    @Override
    public Map<String, List<ReportVo>> qualityFromTempUp(String udf06, String start_time, String end_time,String type) {
        Map<String, List<ReportVo>> map = new LinkedHashMap<>();
        if (udf06!=null && !udf06.isEmpty()) {
            if ("一次测温".equals(udf06)) {
                map.put(udf06,sfcteMapper.qualityFromTempUp(udf06, start_time, end_time,type));
            } else if ("二次测温".equals(udf06)) {
                map.put(udf06,sfcteMapper.qualityFromTempCountByWw(udf06, start_time, end_time,type));
            } else if ("三次测温".equals(udf06)) {
                map.put(udf06,sfcteMapper.qualityFromTempUp(udf06, start_time, end_time,type));
            }
            return map;
        }
        map.put("一次测温",sfcteMapper.qualityFromTempUp("一次测温", start_time, end_time,type));
        map.put("二次测温",sfcteMapper.qualityFromTempCountByWw("二次测温", start_time, end_time,type));
        map.put("三次测温",sfcteMapper.qualityFromTempUp("三次测温", start_time, end_time,type));
        return map;
    }

    @Override
    public Map<String, List<ReportVo>> qualityFromTempDown(String udf06, String start_time, String end_time, String type) {
        Map<String, List<ReportVo>> map = new LinkedHashMap<>();
        if (udf06!=null && !udf06.isEmpty()){
            if ("一次测温".equals(udf06) ) {
                map.put(udf06,sfcteMapper.qualityFromTempDown(udf06,start_time, end_time,type));
            }else if("二次测温".equals(udf06)){
                map.put(udf06,sfcteMapper.qualityFromTempCountByBf(udf06, start_time, end_time,type));
            }else if ("三次测温".equals(udf06)){
                map.put(udf06,sfcteMapper.qualityFromTempDown(udf06,start_time, end_time,type));
            }
            return map;
        }
        map.put("一次测温",sfcteMapper.qualityFromTempDown("一次测温", start_time, end_time,type));
        map.put("二次测温",sfcteMapper.qualityFromTempCountByBf("二次测温", start_time, end_time,type));
        map.put("三次测温",sfcteMapper.qualityFromTempDown("三次测温", start_time, end_time,type));
        return map;
    }

//    @Override
//    public Map<String, Integer> qualityFromTempSum(String udf06, Map<String,Map<String,Integer>> map) {
//        int count = 0;
//        Map<String,Integer> map1 = new HashMap<>();
//        if (udf06!=null && !udf06.isEmpty()){
//            count = map.get("upSum").get(udf06)+map.get("downSum").get(udf06);
//            map1.put(udf06,count);
//            return map1;
//        }
//        map1.put("一次测温",map.get("upSum").get("一次测温") + map.get("downSum").get("一次测温"));
//        map1.put("二次测温",map.get("upSum").get("二次测温") + map.get("downSum").get("二次测温"));
//        map1.put("三次测温",map.get("upSum").get("三次测温") + map.get("downSum").get("三次测温"));
//        return map1;


}
