package com.mgkj.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.constant.WmsConstants;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.*;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.session.*;
import com.mgkj.exception.BaseException;
import com.mgkj.exception.CustomException;
import com.mgkj.mapper.*;
import com.mgkj.service.MoService;
import com.mgkj.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mgkj.common.constant.WmsConstants.*;

@Service
public class MoServiceImpl implements MoService {

    @Autowired
    private MoMapper moMapper;
    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private MfgQcResultMapper   mfgQcResultMapper;

    @Autowired
    private MfgQcResultDMapper  mfgQcResultDMapper;

    @Autowired
    private MfgQcResultSdMapper mfgQcResultSdMapper;

    @Resource
    private InvBarcodeOperationMapper invBarcodeOperationMapper;


    @Override
    public MiddleReturnDto insertMoInStorageMiddleTable(List<MoInStorageSubmitDto> dtoList, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        String timestamp = MO_INSTORAGE_APPID + MO_INSTORAGE_APPMODULE + WMS_APPVERSION + UUID.randomUUID().toString();
        for (MoInStorageSubmitDto dto : dtoList) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);
            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            docNo.add(dto.getDocNo());
            String binCode = dto.getBinCode();
            String lotNumber= null;
            if(dto.getLotNumber() == null || dto.getLotNumber().trim().equals("")) {   // 否则去抓条码档里的
                lotNumber = moMapper.selectLotNumber(dto.getBarcode());
            }else{   // 如果前端传了，就用前端的
                lotNumber = dto.getLotNumber();
            }

            shjg = dto.getShjg();
            moMapper.insertMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
//                    .lsmd001(dto.getItemCode()+"#"+dto.getDocNo())      //条码编号
                    .lsmd001(dto.getBarcode())      //条码编号
                    .lsmd002(dto.getWarehouseCode())      //仓库
                    .lsmd003(dto.getBinCode())//库位
//                    .lsmd004(binCode)//批号
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(1)//异动类型
                    .lsmd007(dto.getDocNo())//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitNo())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(MO_INSTORAGE_APPID)//终端设备编号
                    .lsmd027(timestamp)//APP申请串号
                    .lsmd028(MO_INSTORAGE_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(binCode)
//                    .lsmd037(dto.getDocNo())
                    .lsmd037(isLotControlEnabled ? lotNumber : "" )
                    .lsmd039("002")
                    .lsmd042("NNN")
                    .lsmd045("0.0")
                    .lsmd046(dto.getUnitNo())
                    .lsmd047("0.0")
                    .lsmd048(dto.getLotDesc())//批号说明(炉号)
                    .lsmd053(dto.getScrapQty())
                    .lsmd054("0.0")
                    .lsmd055(dto.getRejectQty())
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                    .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                    .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                    .lsmdcrtid("1")//资料建立者
                    .lsmdent(WMS_ENTID)//企业代码
                    .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                    .lsmdmodid("1") //资料修改者
                    .lsmdowndp("qqq") //资料所属部门
                    .lsmdownid(createBy) //资料所有者
                    .lsmdsite(shjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()

            );
        }
        return new MiddleReturnDto(docNo.get(0),MO_INSTORAGE_APPID,timestamp,"",shjg);
    }


    //*****************************************************ABL装配生产整车入库*****************************************************************//
    @Override
    public MiddleReturnDto insertABlLjZpMoInStorageMiddleTable(List<MoLjzpInStorageSubmitDto> dtoList) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        String timestamp = MO_INSTORAGE_APPID+MO_INSTORAGE_APPMODULE+MO_INSTORAGE_APPVERSION+ UUID.randomUUID().toString();


        for (MoLjzpInStorageSubmitDto dto : dtoList) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);
            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());
            String binCode = dto.getBinCode();
            String lotNumber=moMapper.selectLotNumber(dto.getBarcode());
            shjg = dto.getShjg();

            moMapper.insertMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getWarehouseCode())      //仓库
                    .lsmd003(dto.getBinCode())//库位
//                    .lsmd004(binCode)//批号
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(1)//异动类型
                    .lsmd007(dto.getDocNo())//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(MO_INSTORAGE_APPID)//终端设备编号
                    .lsmd027(timestamp)//APP申请串号
                    .lsmd028(MO_INSTORAGE_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(binCode)
//                    .lsmd037(dto.getDocNo())
                    .lsmd037(isLotControlEnabled ? lotNumber : "" )
                    .lsmd039("002")
                    .lsmd042("NNN")
                    .lsmd045("0.0")
                    .lsmd046(dto.getUnitCode())
                    .lsmd047("0.0")
                    .lsmd048(isLotControlEnabled ? lotNumber : "")//批号说明(炉号)
//                    .lsmd048(dto.getLotDesc())//批号说明(炉号)
                    .lsmd053(dto.getScrapQty())
                    .lsmd054("0.0")
                    .lsmd055(dto.getRejectQty())
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                    .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                    .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                    .lsmdcrtid("1")//资料建立者
                    .lsmdent(WMS_ENTID)//企业代码
                    .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                    .lsmdmodid("1") //资料修改者
                    .lsmdowndp("qqq") //资料所属部门
                    .lsmdownid("dcms") //资料所有者
                    .lsmdsite(shjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()

            );
        }
        return new MiddleReturnDto(docNo.get(0),MO_INSTORAGE_APPID,timestamp,"",shjg);
    }


    @Override
    public MiddleReturnDto insertABlLjZpMiddleTable(List<MoLjZpIssueSubmitDto> list, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        String timestamp = MOISSUE_APPID + MOISSUE_APPMODULE + WMS_APPVERSION + UUID.randomUUID().toString();
        for (MoLjZpIssueSubmitDto dto : list) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled );



            // 处理批号和库位逻辑

            String binCode = dto.getBinCode();
            String barcode = dto.getBarcode();
            String lotNumber = moMapper.selectLotNumber(barcode);
//            String lotNumber = "120250819";
            shjg = dto.getShjg();

            docNo.add(dto.getDocNo());

            if (dto.getCurrentNum() == null || dto.getCurrentNum().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BaseException(500, "异动数量不能小于0！");
            }

            moMapper.insertMiddleTable2(
                    Lsmdt.builder()
                            .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                            .lsmd001(barcode)      //条码编号
                            .lsmd002(dto.getWarehouseCode())      //仓库
                            .lsmd003(dto.getBinCode())//库位
                            .lsmd004(lotNumber) // 批号
//                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
                            .lsmd005(dto.getCurrentNum())//异动数量
                            .lsmd006(-1)//异动类型
                            .lsmd007(dto.getDocNo())//来源单号
                            .lsmd008("0")
                            .lsmd010("0")
                            .lsmd013("admin")//PDA操作人代号
                            .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                            .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                            .lsmd019("Y")//扣账资料否
                            .lsmd024(dto.getUnitCode())//异动数量单位
                            .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                            .lsmd026(MOISSUE_APPID)//终端设备编号
                            .lsmd027(timestamp)//APP申请串号
                            .lsmd028(MOISSUE_APPMODULE)//APP申请所属模组
                            .lsmd029("N")//ERP异动码
                            .lsmd030("")//备注
                            .lsmd031("1")//条码异动类型
                            .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                            .lsmd036(binCode) // 如果开启库位管理，赋值库位，否则为空
//                            .lsmd036(isBinControlEnabled ? binCode : "") // 如果开启库位管理，赋值库位，否则为空
                            .lsmd037(isLotControlEnabled ? lotNumber : "") // 如果开启批号管理，赋值批号，否则为空
                            .lsmd038("")
                            .lsmd039("002")
                            .lsmd042("NNN")
                            .lsmd045("0.0")
                            .lsmd046(dto.getUnitCode())
                            .lsmd047("0.0")
                            .lsmd053(BigDecimal.ZERO)
                            .lsmd054("0.00")
                            .lsmd055(BigDecimal.ZERO)
                            .lsmd058("9998-12-31")
                            .lsmd081("0.0")
                            .lsmd082("0.0")
                            .lsmd083(dto.getItemCode())
                            .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                            .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                            .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                            .lsmd906("0")
                            .lsmd907("0")
                            .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                            .lsmdcrtid("1")//资料建立者
                            .lsmdent(WMS_ENTID)//企业代码
                            .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                            .lsmdmodid("1") //资料修改者
                            .lsmdowndp("qqq") //资料所属部门
                            .lsmdownid(createBy) //资料所有者
                            .lsmdsite(shjg) //营运据点
                            .lsmdstus("Y") //状态码
                            .build()
            );
        }
        return new MiddleReturnDto(docNo.get(0),MOISSUE_APPID,timestamp,"",shjg);
//        return new MiddleReturnDto(docNo.get(0),MOISSUE_APPID,"unknown02:00:00:00:00:00ab1cbf8632fb41ceA0054.223240307153009","");
    }


    //*****************************************************ABL装配生产整车入库*****************************************************************//


    @Override
    public JSONObject MoInStorageSubmit(MiddleReturnDto middleReturnDto, String createBy) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,createBy, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,MO_INSTORAGE_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                createBy, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),MO_INSTORAGE_APPMODULE,MO_INSTORAGE_APPVERSION,MO_INSTORAGE_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(httpEntity.getBody());
        System.out.println(result);
        return result;
    }

    //**************************工单发料**************************

    //获取工单简洁信息
    @Override
    public Result ListMoSimpleInfo(MoSimpleDto moSimpleDto) {
        try {
            List<MoSimpleVo> list = moMapper.ListMoSimpleInfo(moSimpleDto);
            if (list != null && !list.isEmpty()){
                return Result.ok(list).message("获取成功");
            }else {
                return Result.ok().message("暂无数据");
            }
        }catch (Exception e){
            return Result.fail().message("获取失败");
        }

    }
    //获取工单汇总信息
    @Override
    public Result ListMoCollectInfo(String docNo) {
        try {
            MoCollectVo moCollectVo = moMapper.getMoSimpleInfoByDocNo(docNo);
            List<MoCollectBodyVo> list = moMapper.ListMoCollectInfo(docNo);
            moCollectVo.setData(list);
            return Result.ok(moCollectVo).message("获取成功");
        }catch (Exception e){
            return Result.fail().message("获取失败");
        }

    }
    //获取工单详细信息
    @Override
    public Result<List<MoDetailVo>> getMoDetailInfo(MoDetailDto moDetailDto) {
        List<MoDetailVo> list = new ArrayList<>();
        try {
            list = moMapper.getMoDetailInfo(moDetailDto);
            if (!list.isEmpty()){
                return Result.ok(list).message("获取成功");
            }else {
                return Result.fail(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(list).message("查询失败");
        }



    }
    //工单发料提交中间表
    @Override
    public MiddleReturnDto insertMiddleTable(List<MoIssueSubmitDto> list, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();

        for (MoIssueSubmitDto dto : list) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());

            // 处理批号和库位逻辑
            String lotNumber = "";
            String binCode = dto.getBinCode();
            if (barcode.contains("#")) {
//                lotNumber = "25-03-17";
                lotNumber = "250423002";
            }

                if (dto.getMatchQty() == null ||
                        dto.getMatchQty().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new BaseException(500, "异动数量不能小于0！");
                }
                moMapper.insertMiddleTable2(
                        Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(dto.getBinCode())//库位
                                .lsmd004(lotNumber) // 批号
//                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
                                .lsmd005(dto.getMatchQty())//异动数量
                                .lsmd006(-1)//异动类型
                                .lsmd007(dto.getDocNo())//来源单号
                                .lsmd008("0")
                                .lsmd010("0")
                                .lsmd013("admin")//PDA操作人代号
                                .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                                .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                                .lsmd019("Y")//扣账资料否
                                .lsmd024(dto.getUnitCode())//异动数量单位
                                .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                                .lsmd026(MOISSUE_APPID)//终端设备编号
                                .lsmd027(MOISSUE_APPID + MOISSUE_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))//APP申请串号
                                .lsmd028(MOISSUE_APPMODULE)//APP申请所属模组
                                .lsmd029("N")//ERP异动码
                                .lsmd030("")//备注
                                .lsmd031("1")//条码异动类型
                                .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                                .lsmd036(binCode) // 如果开启库位管理，赋值库位，否则为空
//                            .lsmd036(isBinControlEnabled ? binCode : "") // 如果开启库位管理，赋值库位，否则为空
                                .lsmd037(isLotControlEnabled ? lotNumber : "") // 如果开启批号管理，赋值批号，否则为空
                                .lsmd038("")
                                .lsmd039("002")
                                .lsmd042("NNN")
                                .lsmd045("0.0")
                                .lsmd046(dto.getUnitCode())
                                .lsmd047("0.0")
                                .lsmd053(BigDecimal.ZERO)
                                .lsmd054("0.00")
                                .lsmd055(BigDecimal.ZERO)
                                .lsmd058("9998-12-31")
                                .lsmd081("0.0")
                                .lsmd082("0.0")
                                .lsmd083(dto.getItemCode())
                                .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                                .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                                .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                                .lsmd906("0")
                                .lsmd907("0")
                                .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                                .lsmdcrtid("1")//资料建立者
                                .lsmdent(WMS_ENTID)//企业代码
                                .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                                .lsmdmodid("1") //资料修改者
                                .lsmdowndp("qqq") //资料所属部门
                                .lsmdownid(createBy) //资料所有者
                                .lsmdsite(WMS_COMPANYID) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );
        }
        return new MiddleReturnDto(docNo.get(0),MOISSUE_APPID,MOISSUE_APPID+MOISSUE_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
//        return new MiddleReturnDto(docNo.get(0),MOISSUE_APPID,"unknown02:00:00:00:00:00ab1cbf8632fb41ceA0054.223240307153009","");
    }

    @Override
    public JSONObject MoIssueSubmit(MiddleReturnDto middleReturnDto, String createBy) {
        EtenSession etenSession = new EtenSession(MOISSUE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,createBy, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,MOISSUE_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                createBy, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),MOISSUE_APPMODULE,MOISSUE_APPVERSION,MOISSUE_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }


    //**************************领料下架**************************
    //领料下架-简单信息
    @Override
    public Result ListIssueReceiptSimpleInfo(IssueReceiptSimpleDto issueReceiptSimpleDto) {
        List<IssueReceiptSimpleVo> list = moMapper.ListIssueReceiptSimpleInfo(issueReceiptSimpleDto);
        return Result.ok(list).message("获取成功");
    }
    //领料下架-详细信息
    @Override
    public Result<List<IssueReceiptDetailVo>> ListIssueReceiptDetailInfo(IssueReceiptDeatailDto issueReceiptDeatailDto) {
        List<IssueReceiptDetailVo> list = new ArrayList<>();
        try {
            list = moMapper.ListIssueReceiptDetailInfo(issueReceiptDeatailDto);
            if (!list.isEmpty()){
                return Result.ok(list).message("获取成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(list).message("查询失败");
        }


    }

    //领料下架-汇总信息
    @Override
    public Result<IssueReceiptCollectVo> ListIssueReceiptCollectInfo(String docNo) {
        IssueReceiptCollectVo vo = moMapper.ListIssueReceiptSimpleInfoByDocNo(docNo);
        List<IssueReceiptCollectBodyVo> list = moMapper.ListIssueReceiptCollectBodyInfo(docNo);
        vo.setData(list);
        return Result.ok(vo).message("获取成功");
    }

    /**
     * 领料下架-提交中间表
     * @param list
     * @return
     */
    @Override
    public MiddleReturnDto insertIssueReceiptMiddleTable(List<String> list, String docNo, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
//        List<String> docNo = new ArrayList<>();
        String shjg = "";

        for (String id : list) {
            //===============
            InvBarcodeOperation dto = invBarcodeOperationMapper.selectByid(id);
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemNo())
                            .ne(Item::getLotControl, "N")
            ) > 0;
            //=======================
            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);
            System.out.println("商品：" + dto.getItemNo() + " 批号管理：" + isLotControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
//            docNo.add(dto.getDocNo());

            // 处理批号和库位逻辑
            String binCode = dto.getCellNo();
            if (StrUtil.isEmpty(binCode)) {
                binCode = moMapper.queryBinCodeByItemCode(dto.getItemNo());
            }
            String lotNumber = moMapper.selectLotNumber(dto.getBarcode());

            shjg = dto.getOrgNo();
            if (dto.getQty() == null ||
                    dto.getQty().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BaseException(500, "异动数量不能小于0！");
            }
            moMapper.insertMiddleTable2(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getWarehouseNo())      //仓库
                    .lsmd003(binCode)//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getQty())//异动数量
                    .lsmd006(-1)//异动类型
                    .lsmd007(docNo)//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitNo())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(MOISSUE_RECEIPT_APPID)//终端设备编号
                    .lsmd027(MOISSUE_RECEIPT_APPID + MOISSUE_RECEIPT_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddHHmmss"))//APP申请串号
                    .lsmd028(MOISSUE_RECEIPT_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030("")//备注
                    .lsmd031("")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("YNY")
                    .lsmd045("0.0")
                    .lsmd046("")
                    .lsmd047("0.0")
//                    .lsmd048("APLC-9471-984M")-

                    .lsmd053(BigDecimal.ZERO)
                    .lsmd054("0.00")
                    .lsmd055(BigDecimal.ZERO)
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemNo())
                    .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                    .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                    .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                    .lsmdcrtid("1")//资料建立者
                    .lsmdent(WMS_ENTID)//企业代码
                    .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                    .lsmdmodid("1") //资料修改者
                    .lsmdowndp("qqq") //资料所属部门
                    .lsmdownid(createBy) //资料所有者
                    .lsmdsite(shjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
        }
        return new MiddleReturnDto(docNo,MOISSUE_RECEIPT_APPID,MOISSUE_RECEIPT_APPID+MOISSUE_RECEIPT_APPMODULE+MOISSUE_RECEIPT_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink",shjg);


    }




    /**
     * 领料下架-提交E10
      * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject IssueReceiptSubmit(MiddleReturnDto middleReturnDto, String createBy) {
        EtenSession etenSession = new EtenSession(MOISSUE_RECEIPT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,createBy, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_PROD,MOISSUE_RECEIPT_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                createBy, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),MOISSUE_RECEIPT_APPMODULE,MOISSUE_RECEIPT_APPVERSION,MOISSUE_RECEIPT_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }


    //**************************入库上架**************************

    /**
     * 入库上架-获取简洁信息
     * @param
     * @return
     */
    @Override
    public Result ListMoShelvesSimpleInfo(MoShelvesSimpleDto moShelvesSimpleDto) {
        List<MoShelvesSimpleVo> list = moMapper.ListMoShelvesSimpleInfo(moShelvesSimpleDto);
        return Result.ok(list).message("获取成功");
    }


    /**
     * 入库上架提交中间表
     * @param dtoList
     * @return
     */
    @Override
    public MiddleReturnDto insertMoInStorageShelvesMiddleTable(List<MoInStorageShelvesDto> dtoList) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        for (MoInStorageShelvesDto dto : dtoList) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());
            // 处理批号和库位逻辑
            String lotNumber = "";
            String binCode = dto.getBinCode();
            if (barcode.contains("#")) {
                lotNumber = barcode.substring(barcode.indexOf("#") + 1);
            }

            moMapper.insertMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(dto.getItemCode()+"#"+dto.getDocNo())      //条码编号
                    .lsmd002(dto.getWarehouseCode())      //仓库
                    .lsmd003(dto.getBinCode())//库位
                    .lsmd004(binCode)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(1)//异动类型
                    .lsmd007(dto.getDocNo())//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitNo())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(MO_SHELVES_APPID)//终端设备编号
                    .lsmd027(MO_SHELVES_APPID+MO_SHELVES_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(MO_SHELVES_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd039("002")
                    .lsmd042("YNY")
                    .lsmd045("0.0")
                    .lsmd046("")
                    .lsmd047("0.0")
                    .lsmd048(dto.getLotDesc())//批号说明(炉号)
                    .lsmd053(dto.getScrapQty())
                    .lsmd054("0.0")
                    .lsmd055(dto.getRejectQty())
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                    .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                    .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                    .lsmdcrtid("1")//资料建立者
                    .lsmdent(WMS_ENTID)//企业代码
                    .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                    .lsmdmodid("1") //资料修改者
                    .lsmdowndp("qqq") //资料所属部门
                    .lsmdownid("dcms") //资料所有者
                    .lsmdsite(WMS_COMPANYID) //营运据点
                    .lsmdstus("Y") //状态码
                    .lsmd036(dto.getBinCode())
                    .build()

            );
        }
        System.out.println(date);
        System.out.println(DateUtil.format(date,"yyMMddmmHHss"));
        return new MiddleReturnDto(docNo.get(0),MO_SHELVES_APPID,MO_SHELVES_APPID+MO_SHELVES_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink");
    }


    /**
     * 入库上架E10提交
     * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject MoInStorageShelvesSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_PROD,MO_SHELVES_NAME_LIST,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),MO_SHELVES_APPMODULE,WMS_APPVERSION,MO_SHELVES_NAME_LIST,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(httpEntity.getBody());
        System.out.println(result);
        return result;
    }




    //**************************工单退料**************************
    //工单退料-获取简易信息

    @Override
    public Result ListMoReturnSimpleInfo(MoReturnSimpleDto moReturnSimpleDto) {

        List<MoReturnSimpleVo> list = moMapper.ListMoReturnSimpleInfo(moReturnSimpleDto);
        if (list != null && !list.isEmpty()){
            return Result.ok(list).message("查询成功");
        }else {
            return Result.fail().message("暂无数据");
        }

    }

    @Override
    public Result GetMoReturnDocByFrameNo(MoReturnSimpleDto moReturnSimpleDto) {
        List<MoReturnFrameLookupVo> list = moMapper.ListMoReturnDocByFrameNo(moReturnSimpleDto);
        if (list != null && !list.isEmpty()) {
            return Result.ok(list).message("查询成功");
        }
        return Result.fail().message("未找到对应工单");
    }

    /**
     * 工单退料-查询
     * @param moReturnSimpleDto
     * @return
     */
    @Override
    public Result ListMoReturnCollectInfo(MoReturnSimpleDto moReturnSimpleDto) {
        try {
            List<MoReturnCollectVo> vo = moMapper.ListMoReturnCollectInfoByDoc(moReturnSimpleDto);
            if (vo!=null && !vo.isEmpty()){
                return Result.ok(vo).message("获取成功");
            }else {
                return Result.ok(vo).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("查询失败,联系管理员");
        }

    }

    //工单退料-同步中间表
    @Override
    public MiddleReturnDto insertMoReturnMiddleTable(List<MoReturnSubmitDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();

        for (MoReturnSubmitDto dto : list) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());

            // 处理批号和库位逻辑
            String lotNumber = "";
            String binCode = dto.getBinCode();
            if (StrUtil.isNotBlank(dto.getLotNo())) {
                lotNumber = dto.getLotNo();
            } else if (barcode.contains("#")) {
                lotNumber = barcode.substring(barcode.indexOf("#") + 1);
            }


            moMapper.insertMiddleTable2(Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(binCode)//库位
                                .lsmd004(lotNumber)//批号
                                .lsmd005(dto.getMatchQty())//异动数量
                                .lsmd006(1)//异动类型
                                .lsmd007(dto.getDocNo())//来源单号
                                .lsmd008("0")
                                .lsmd010("0")
                                .lsmd013("admin")//PDA操作人代号
                                .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                                .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                                .lsmd019("Y")//扣账资料否
                                .lsmd024(dto.getUnitCode())//异动数量单位
                                .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                                .lsmd026(MORETURN_APPID)//终端设备编号
                                .lsmd027(MORETURN_APPID + MORETURN_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))//APP申请串号
                                .lsmd028(MORETURN_APPMODULE)//APP申请所属模组
                                .lsmd029("N")//ERP异动码
                                .lsmd030("")//备注
                                .lsmd031("1")//条码异动类型
                                .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                                .lsmd036(binCode)
                                .lsmd037(isLotControlEnabled ? lotNumber : "")
                                .lsmd038("")
                                .lsmd039("002")
                                .lsmd042("NNN")
                                .lsmd045("0.0")
                                .lsmd046("")
                                .lsmd047("0.0")
                                .lsmd053(BigDecimal.ZERO)
                                .lsmd054("0.00")
                                .lsmd055(BigDecimal.ZERO)
                                .lsmd058("9998-12-31")
                                .lsmd081("0.0")
                                .lsmd082("0.0")
                                .lsmd083(dto.getItemCode())
                                .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                                .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                                .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                                .lsmd906("0")
                                .lsmd907("0")
                                .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                                .lsmdcrtid("1")//资料建立者
                                .lsmdent(WMS_ENTID)//企业代码
                                .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                                .lsmdmodid("1") //资料修改者
                                .lsmdowndp("qqq") //资料所属部门
                                .lsmdownid("dcms") //资料所有者
                                .lsmdsite(WMS_COMPANYID) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );

        }
        return new MiddleReturnDto(docNo.get(0),MORETURN_APPID,MORETURN_APPID+MORETURN_APPMODULE+MORETURN_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
    }


    //工单退料-提交
    @Override
    public JSONObject MoReturnSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(MORETURN_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_T100,MORETURN_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),MORETURN_APPMODULE,MORETURN_APPVERSION,MORETURN_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }




    //**************************退料上架**************************
    //退料上架-获取简洁信息
    @Override
    public Result<List<IssueReceiptReturnSimpleVo>> ListIssueReceiptReturnSimpleInfo(IssueReceiptReturnSimpleDto dto) {
        List<IssueReceiptReturnSimpleVo> list = moMapper.ListIssueReceiptReturnSimpleInfo(dto);
        if (list != null && !list.isEmpty()){
            return Result.ok(list).message("查询成功");
        }else {
            return Result.fail(list).message("暂无数据");
        }
    }

    @Override
    public Result<List<IssueReceiptReturnVo>> ListIssueReceiptReturnCollectInfo
            (String docNo) {
        List<IssueReceiptReturnVo> list = new ArrayList<>();
        try {
            list = moMapper.ListIssueReceiptReturnCollectBodyInfo(docNo);
            if (!list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.ok(list).message("获取成功");
        }


    }


    //**************************工单入库**************************
    @Override
    public JSONObject MoInStorageDto(MoInStorageDto dto) {
        System.out.println("开始查询");
        DateTime date = DateUtil.date();
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, MO_INSTORAGE_APPID+MO_INSTORAGE_APPMODULE+MO_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"), ""),
                new SessionService(WMS_SERVICE_PROD,MO_INSTORAGE_NAME_LIST,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new MoInStorageParameter(
                        new MoInStorageParameterData(
                                dto.getDocNo(),
                                dto.getItemNo(),
                                dto.getDepartmentNo(),
                                "10",
                                dto.getDateBegin(),
                                dto.getDateEnd()
                        ),
                        new MoInStorageParameterHost(
                                WMS_HOST_ACCT,
                                MO_INSTORAGE_APPID+MO_INSTORAGE_APPMODULE+MO_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),
                                MO_INSTORAGE_APPID,
                                MO_INSTORAGE_APPMODULE,
                                MO_INSTORAGE_APPVERSION,
                                MO_INSTORAGE_NAME_LIST,
                                WMS_ENTID,
                                WMS_COMPANYID
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(httpEntity.getBody());
        System.out.println("获取结果");
        return result;
    }

    @Override
    public JSONObject getMoInStorageDetail(String docNo) {
        DateTime date = DateUtil.date();
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, MO_INSTORAGE_APPID+MO_INSTORAGE_APPMODULE+MO_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"), ""),
                new SessionService(WMS_SERVICE_PROD,MO_INSTORAGE_NAME_LIST,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new MoInStorageDeatilParameter(
                        new MoInStorageDetailParameterData(docNo),
                        new MoInStorageDetailParameterHost(
                                WMS_HOST_ACCT,
                                MO_INSTORAGE_APPID+MO_INSTORAGE_APPMODULE+MO_INSTORAGE_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),
                                MO_INSTORAGE_APPID,
                                MO_INSTORAGE_APPMODULE,
                                MO_INSTORAGE_APPVERSION,
                                MO_INSTORAGE_NAME_DETAIL,
                                WMS_ENTID,
                                WMS_COMPANYID
                        ),docNo
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        return result;
    }


    @Override
    public int updateMoInStorageCode(List<MoInStorageSubmitDto> dtoList) {
        int totalUpdatedRows = 0;
        for (MoInStorageSubmitDto dto : dtoList) {
            List<String> warehouseCodes = moMapper.selectWarehouseCode(dto.getDocNo());
            boolean needUpdate = true;
            for (String code : warehouseCodes) {
                if (dto.getWarehouseCode().equals(code)) {
                    needUpdate = false;
                    break;
                }
            }
            if (needUpdate) {
                int updatedRows = moMapper.updateMoInStorageCode(dto.getDocNo(), dto.getWarehouseCode());
                totalUpdatedRows += updatedRows;
            }
        }
        return totalUpdatedRows;
    }

    @Override
    public Result<List<MoShelvesDetailVo>> ListMoShelvesDetailInfo(String docNo) {
            List<MoShelvesDetailVo> list = moMapper.ListMoShelvesDetailInfo(docNo);
            if (list!=null && !list.isEmpty()){
                return Result.ok(list).message("查询成功");
            }else {
                return Result.ok(list).message("暂无数据");
            }

        }

    /**
     * 退料上架-提交-同步中间表
     * @param list
     * @return
     */
    @Override
    public MiddleReturnDto insertIssueReceiptReturnMiddleTable(List<MoIssueReceiptReturnSubmitDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();

        for (MoIssueReceiptReturnSubmitDto dto : list) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());

            // 处理批号和库位逻辑
            String lotNumber = "";
            String binCode = dto.getBinCode();
            if (barcode.contains("#")) {
//                lotNumber = "25-03-17";
                lotNumber = barcode.substring(barcode.indexOf("#") + 1);
            }


            moMapper.insertMiddleTable2(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getWarehouseCode())      //仓库
                    .lsmd003(binCode)//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(1)//异动类型
                    .lsmd007(dto.getDocNo())//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(MOISSUE_RETURN_APPID)//终端设备编号
                    .lsmd027(MOISSUE_RETURN_APPID + MOISSUE_RETURN_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddHHmmss"))//APP申请串号
                    .lsmd028(MOISSUE_RETURN_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030("")//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("YNY")
                    .lsmd045("0.0")
                    .lsmd046("")
                    .lsmd047("0.0")
                    .lsmd053(BigDecimal.ZERO)
                    .lsmd054("0.00")
                    .lsmd055(BigDecimal.ZERO)
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                    .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                    .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                    .lsmdcrtid("1")//资料建立者
                    .lsmdent(WMS_ENTID)//企业代码
                    .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                    .lsmdmodid("1") //资料修改者
                    .lsmdowndp("qqq") //资料所属部门
                    .lsmdownid("dcms") //资料所有者
                    .lsmdsite(WMS_COMPANYID) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );

        }
        return new MiddleReturnDto(docNo.get(0),MOISSUE_RETURN_APPID,MOISSUE_RETURN_APPID+MOISSUE_RETURN_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"");

    }

    /**
     * 退料上架-提交-E10
     * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject IssueReceiptReturnSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_PROD,MOISSUE_RETURN_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),MOISSUE_RETURN_APPMODULE,WMS_APPVERSION,MOISSUE_RETURN_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(httpEntity.getBody());
        System.out.println(result);
        return result;

    }

    @Override
    public Result<PageInfo<MoCheckHeader>> ListMoStorageCheck(MoStorageCheckDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());

        List<MoCheckHeader>  moCheckHeaders = moMapper.ListMoStorageCheck(dto.getApplyDocNo());
        return Result.ok(new PageInfo<>(moCheckHeaders));
    }

    @Override
    public Result<List<MoCheck>> ListMoStorageCheckAll(String checkDocNo) {
        List<MoCheck> checkList = new ArrayList<>();

        MoCheckHeader moCheckHeader = moMapper.ListMoStorageCheckOne(checkDocNo);

        MoCheck check = new MoCheck();
        check.setHeader(moCheckHeader);

        List<MoCheckLine> lineList = moMapper.ListMoCheckLine(checkDocNo);
        check.setLines(lineList);
        checkList.add(check);
        return Result.ok(checkList);
    }

    @DSTransactional
    @Override
    public Result UpdateMoStorageCheck(MoCheck dto) {

        return Result.fail("停用");

//        MoCheckHeader header = dto.getHeader();
//        String By = header.getEmployeeUUID();
//        String checkDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
//        System.out.println("checkDate:"+checkDate);
//
//        boolean anyLineNotQualified = false; // 1为false，0为true，初始为全部合格
//        for (MoCheckLine line : dto.getLines()) {
//            System.out.println("更新生产入库检验单单身信息");
//            moMapper.UpdateMoStorageCheckLine(line);
//            // 如果存在一条单身的 decision 为 true，则标记整单为不合格
//            if (line.getDecision() == null || !line.getDecision()) {
//                anyLineNotQualified = true;
//            }
//
//        }
//        String decision = anyLineNotQualified ? "2" : "1";
//        header.setDecision(decision);
//
//        System.out.println("更新生产入库检验单头");
//        moMapper.UpdateMoStorageCheckHeader(header);
//
//        System.out.println("更新生产入库申请单信息");
//        moMapper.UpdateMoApplyStorage(dto.getHeader());
//
//        System.out.println("插入工单质检单头");
//
//        MfgQcResult mfgQcResult = new MfgQcResult();
//        mfgQcResult.setMFG_QC_RESULT_ID(UUID.randomUUID().toString());
//        mfgQcResult.setApproveStatus("Y");
//        mfgQcResult.setApproveDate(checkDate);
//        mfgQcResult.setApproveBy(By);
//        mfgQcResult.setCreateDate(checkDate);
//        mfgQcResult.setLastModifiedDate(checkDate);
//        mfgQcResult.setModifiedDate(checkDate);
//        mfgQcResult.setCreateBy(By);
//        mfgQcResult.setLastModifiedBy(By);
//        mfgQcResult.setModifiedBy(By);
//        mfgQcResult.setOwner_Org_RTK("PLANT");
//        mfgQcResult.setOwner_Org_ROid("8E9AE49D-0DEB-452D-019E-192E7CBBF1E5");
//        mfgQcResult.setSOURCE_ID_RTK("PURCHASE_ARRIVAL.PURCHASE_ARRIVAL_D");
//        mfgQcResult.setSOURCE_ID_ROid("5E11769E-6784-451A-B6CE-264012174580");//到货检验单头UUID
//        mfgQcResultMapper.insert(mfgQcResult);
//
//        System.out.println("插入工单质检单身1");
//        MfgQcResultD mfgQcResultTD = new MfgQcResultD();
//        mfgQcResultTD.setMFG_QC_RESULT_D_ID(UUID.randomUUID().toString());
//        mfgQcResultTD.setQC_DATE(checkDate);
//        mfgQcResultTD.setQUALIFIED_BUSINESS_QTY(header.getAcceptableQty());
//        mfgQcResultTD.setUNQUALIFIED_BUSINESS_QTY(header.getUnqualifiedQty());
//        mfgQcResultTD.setIN_DESTROYED_BUSINESS_QTY(header.getDestroyedQty());
//        mfgQcResultTD.setREMARK(header.getRemark());
//
////        poqcreTD.setPO_INSPECTION_CODE(header.getCheckDocNo());
////        poqcreTD.setDETERMINE_QTY(BigDecimal.ZERO);//待判定数量
////        poqcreTD.setRESULT_STATUS("3");
////        //到货检验单单头uuid
////        poqcreTD.setPO_INSPECTION_ID(header.getInspectionUuid());
//
//        mfgQcResultTD.setQC_NO(header.getCheckDocNo());
//        mfgQcResultTD.setTO_DECIDE_QTY(header.getInspectionQty());//待判定数量=送检数量
//
//        mfgQcResultTD.setCreateDate(checkDate);
//        mfgQcResultTD.setLastModifiedDate(checkDate);
//        mfgQcResultTD.setModifiedDate(checkDate);
//        mfgQcResultTD.setCreateBy(By);
//        mfgQcResultTD.setLastModifiedBy(By);
//        mfgQcResultTD.setModifiedBy(By);
//        //生产质检单头UUID
//        mfgQcResultTD.setMFG_QC_RESULT_ID(mfgQcResult.getMFG_QC_RESULT_ID());
//
//        mfgQcResultDMapper.insert(mfgQcResultTD);
//
//        System.out.println("插入工单质检单身2");
//        MfgQcResultSd mfgQcResultSD = new MfgQcResultSd();
//        mfgQcResultSD.setSequenceNumber(1);
//        mfgQcResultSD.setACCEPTED_BUSINESS_QTY(header.getAcceptableQty());//允收业务数量
//        mfgQcResultSD.setSCRAP_BUSINESS_QTY(header.getDestroyedQty());//报废业务数量
//        mfgQcResultSD.setRETURN_BUSINESS_QTY(header.getUnqualifiedQty());//拒收业务数量
//        mfgQcResultSD.setDECIDE_DATE(checkDate);//判定日期
//        mfgQcResultSD.setREMARK(header.getRemark());
//        mfgQcResultSD.setMFG_QC_RESULT_SD_ID(UUID.randomUUID().toString());
//        mfgQcResultSD.setApproveStatus("Y");
//        mfgQcResultSD.setApproveDate(checkDate);
//        mfgQcResultSD.setApproveBy(header.getEmployeeUUID());
//        mfgQcResultSD.setCreateDate(checkDate);
//        mfgQcResultSD.setLastModifiedDate(checkDate);
//        mfgQcResultSD.setModifiedDate(checkDate);
//        mfgQcResultSD.setCreateBy(header.getEmployeeUUID());
//        mfgQcResultSD.setLastModifiedBy(header.getEmployeeUUID());
//        mfgQcResultSD.setModifiedBy(header.getEmployeeUUID());
//        mfgQcResultSD.setMFG_QC_RESULT_D_ID(mfgQcResultTD.getMFG_QC_RESULT_ID());//采购质检检验单单身UUID
//
//        mfgQcResultSdMapper.insert(mfgQcResultSD);
//        return Result.ok("审核成功");
    }

    @Override
    public List<MoLjzpInStorageSubmitDto> selectLjMo(String barcode) {
        List<MoLjzpInStorageSubmitDto> moLjzplist = moMapper.selectLjMo(barcode);
        return moLjzplist;
    }

    @Override
    public MiddleReturnDto ABlLjZpMiddleTable(List<InvBarcodeOperation> operations, String docNo) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        String shjg = "";
        String timestamp = MOISSUE_APPID + MOISSUE_APPMODULE + WMS_APPVERSION + UUID.randomUUID().toString();
        for (InvBarcodeOperation dto : operations) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemNo())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

            System.out.println("商品：" + dto.getItemNo() + " 批号管理：" + isLotControlEnabled );



            // 处理批号和库位逻辑

            String binCode = dto.getCellNo();
            String barcode = dto.getBarcode();
            String lotNumber = moMapper.selectLotNumber(barcode);
//            String lotNumber = "120250819";
            shjg = dto.getOrgNo();


            moMapper.insertMiddleTable2(
                    Lsmdt.builder()
                            .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                            .lsmd001(barcode)      //条码编号
                            .lsmd002(dto.getWarehouseNo())      //仓库
                            .lsmd003(binCode)//库位
                            .lsmd004(lotNumber) // 批号
//                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
                            .lsmd005(dto.getQty())//异动数量
                            .lsmd006(-1)//异动类型
                            .lsmd007(dto.getDocNo())//来源单号
                            .lsmd008("0")
                            .lsmd010("0")
                            .lsmd013("admin")//PDA操作人代号
                            .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
                            .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
                            .lsmd019("Y")//扣账资料否
                            .lsmd024(dto.getUnitNo())//异动数量单位
                            .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                            .lsmd026(MOISSUE_APPID)//终端设备编号
                            .lsmd027(timestamp)//APP申请串号
                            .lsmd028(MOISSUE_APPMODULE)//APP申请所属模组
                            .lsmd029("N")//ERP异动码
                            .lsmd030("")//备注
                            .lsmd031("1")//条码异动类型
                            .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
                            .lsmd036(binCode) // 如果开启库位管理，赋值库位，否则为空
//                            .lsmd036(isBinControlEnabled ? binCode : "") // 如果开启库位管理，赋值库位，否则为空
                            .lsmd037(isLotControlEnabled ? lotNumber : "") // 如果开启批号管理，赋值批号，否则为空
                            .lsmd038("")
                            .lsmd039("002")
                            .lsmd042("NNN")
                            .lsmd045("0.0")
                            .lsmd046(dto.getDocNo())
                            .lsmd047("0.0")
                            .lsmd053(BigDecimal.ZERO)
                            .lsmd054("0.00")
                            .lsmd055(BigDecimal.ZERO)
                            .lsmd058("9998-12-31")
                            .lsmd081("0.0")
                            .lsmd082("0.0")
                            .lsmd083(dto.getDocNo())
                            .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
                            .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
                            .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
                            .lsmd906("0")
                            .lsmd907("0")
                            .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
                            .lsmdcrtid("1")//资料建立者
                            .lsmdent(WMS_ENTID)//企业代码
                            .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
                            .lsmdmodid("1") //资料修改者
                            .lsmdowndp("qqq") //资料所属部门
                            .lsmdownid("dcms") //资料所有者
                            .lsmdsite(shjg) //营运据点
                            .lsmdstus("Y") //状态码
                            .build()
            );
        }
        return new MiddleReturnDto(docNo,MOISSUE_APPID,timestamp,"",shjg);
    }

    @Override
    public MiddleReturnDto insertMoInStorageMiddleTableNew(List<FinishedGoodsInboundDetail> dtoList) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        String timestamp = MO_INSTORAGE_APPID + MO_INSTORAGE_APPMODULE + WMS_APPVERSION + UUID.randomUUID().toString();
        for (FinishedGoodsInboundDetail dto : dtoList) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;

//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled + " 库位管理：" + isBinControlEnabled);
            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            docNo.add(dto.getDocNo());
            String binCode = dto.getBinCode();
            String lotNumber= null;
            if(dto.getLotNo() == null || dto.getLotNo().trim().equals("")) {   // 否则去抓条码档里的
                lotNumber = moMapper.selectLotNumber(dto.getBarcode());
            }else{   // 如果前端传了，就用前端的
                lotNumber = dto.getLotNo();
            }

            shjg = String.valueOf(dto.getOrgNo());
            moMapper.insertMiddleTable(Lsmdt.builder()
                            .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
//                    .lsmd001(dto.getItemCode()+"#"+dto.getDocNo())      //条码编号
                            .lsmd001(dto.getBarcode())      //条码编号
                            .lsmd002(dto.getWarehouseCode())      //仓库
                            .lsmd003(dto.getBinCode())//库位
//                    .lsmd004(binCode)//批号
                            .lsmd004(lotNumber)//批号
                            .lsmd005(dto.getQty())//异动数量
                            .lsmd006(1)//异动类型
                            .lsmd007(dto.getDocNo())//来源单号
                            .lsmd008("0")
                            .lsmd010("0")
                            .lsmd013("admin")//PDA操作人代号
                            .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                            .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                            .lsmd019("Y")//扣账资料否
                            .lsmd024(dto.getUnitCode())//异动数量单位
                            .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                            .lsmd026(MO_INSTORAGE_APPID)//终端设备编号
                            .lsmd027(timestamp)//APP申请串号
                            .lsmd028(MO_INSTORAGE_APPMODULE)//APP申请所属模组
                            .lsmd029("N")//ERP异动码
                            .lsmd030(dto.getRemark())//备注
                            .lsmd031("1")//条码异动类型
                            .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                            .lsmd036(binCode)
//                    .lsmd037(dto.getDocNo())
                            .lsmd037(isLotControlEnabled ? lotNumber : "" )
                            .lsmd039("002")
                            .lsmd042("NNN")
                            .lsmd045("0.0")
                            .lsmd046(dto.getUnitCode())
                            .lsmd047("0.0")
                            .lsmd048("")//批号说明(炉号)
                            .lsmd053(BigDecimal.ZERO)
                            .lsmd054("0.0")
                            .lsmd055(BigDecimal.ZERO)
                            .lsmd058("9998-12-31")
                            .lsmd081("0.0")
                            .lsmd082("0.0")
                            .lsmd083(dto.getItemCode())
                            .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
                            .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
                            .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
                            .lsmd906("0")
                            .lsmd907("0")
                            .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
                            .lsmdcrtid("1")//资料建立者
                            .lsmdent(WMS_ENTID)//企业代码
                            .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
                            .lsmdmodid("1") //资料修改者
                            .lsmdowndp("qqq") //资料所属部门
                            .lsmdownid("dcms") //资料所有者
                            .lsmdsite(shjg) //营运据点
                            .lsmdstus("Y") //状态码
                            .build()

            );
        }
        return new MiddleReturnDto(docNo.get(0),MO_INSTORAGE_APPID,timestamp,"",shjg);
    }

    /**
     * 入库申请-获取简洁信息-调用E10
     * @param moStorageApplySimpleDto
     * @return
     */
    @Override
    public JSONObject ListMoStorageApplySimpleInfo(MoStorageApplySimpleDto moStorageApplySimpleDto) {
        System.out.println("开始查询");
        DateTime date = DateUtil.date();
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, MO_STORAGEAPPLY_APPID+MO_STORAGEAPPLY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss")),
                new SessionService(WMS_SERVICE_PROD,MO_STORAGEAPPLY_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new MoStorageApplySimpleParameter(
                        new MoStorageApplyParameterData(
                                moStorageApplySimpleDto.getDocNo(),
                                moStorageApplySimpleDto.getItemCode(),
                                moStorageApplySimpleDto.getDepartmentCode(),
                                "10",
                                moStorageApplySimpleDto.getDateBegin(),
                                moStorageApplySimpleDto.getDateEnd()
                        ),
                        new MoStorageApplyParameterHost(
                                WMS_HOST_ACCT,
                                MO_STORAGEAPPLY_APPID+MO_STORAGEAPPLY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),
                                MO_STORAGEAPPLY_APPID,
                                MO_STORAGEAPPLY_APPMODULE,
                                WMS_APPVERSION,
                                MO_STORAGEAPPLY_NAME,
                                WMS_ENTID,
                                WMS_COMPANYID
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(httpEntity.getBody());
        return result;

    }

    /**
     *
     * 入库申请-获取详细信息-调用E10
     * @param moStorageApplyDetailDto
     * @return
     */
    @Override
    public JSONObject ListMoStorageApplyDetailInfo(MoStorageApplyDetailDto moStorageApplyDetailDto) {
        System.out.println("开始查询");
        DateTime date = DateUtil.date();
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, MO_STORAGEAPPLY_APPID+MO_STORAGEAPPLY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss")),
                new SessionService(WMS_SERVICE_PROD,MO_STORAGEAPPLY_DETAIL_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new MoStorageApplyDetailParameter(
                        new MoStorageApplyParameterData(
                                "('" + moStorageApplyDetailDto.getDocNo() + "')",
                                moStorageApplyDetailDto.getAllowMultiOut()
                        ),
                        new MoStorageApplyParameterHost(
                                WMS_HOST_ACCT,
                                MO_STORAGEAPPLY_APPID+MO_STORAGEAPPLY_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),
                                MO_STORAGEAPPLY_APPID,
                                MO_STORAGEAPPLY_APPMODULE,
                                WMS_APPVERSION,
                                MO_STORAGEAPPLY_DETAIL_NAME,
                                WMS_ENTID,
                                WMS_COMPANYID
                        ),
                        "('" + moStorageApplyDetailDto.getDocNo() + "')",
                        moStorageApplyDetailDto.getAllowMultiOut()
                )
                )));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(httpEntity.getBody());
        return result;
    }











}
