package com.mgkj.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mgkj.common.constant.WmsConstants;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.Item;
import com.mgkj.entity.Lsmdt;
import com.mgkj.entity.session.*;
import com.mgkj.exception.BaseException;
import com.mgkj.mapper.ItemMapper;
import com.mgkj.mapper.SaleMapper;
import com.mgkj.service.SaleService;
import com.mgkj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mgkj.common.constant.WmsConstants.*;

/**
 * @author yyyjcg
 * @date 2024/4/15
 * @Description
 */
@Service
public class SaleServiceImpl implements SaleService {
    @Resource
    private SaleMapper saleMapper;

    @Autowired
    private ItemMapper itemMapper;



    /**
     * 订单下架查询简洁的信息
     * @param dto
     * @return
     */
    @Override
    public Result ListSaleOrderOutStock(ListSaleOrderOutStockDto dto) {
        try {
            List<ListSaleOrderOutStockVo> list = saleMapper.ListSaleOrderOutStock(dto);
            if (!list.isEmpty()){
                return Result.ok(list).message("成功");
            }else {
                return Result.fail().message("暂无数据");
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("失败");
        }

    }

    /**
     * 订单下架根据单号查询详情
     * @param docNo
     * @return
     */
    @Override
    public Result ListSaleOrderOutStockDetail(String docNo) {
        try {
            List<ListSaleOrderOutStockDetailVo> list = saleMapper.ListSaleOrderOutStockDetail(docNo);
            if (!list.isEmpty()){
                return Result.ok(list).message("成功");
            }else {
                return Result.fail().message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("失败");
        }
    }

    /**
     * 通知下架--查询
     * @param dto
     * @return
     */
    @Override
    public Result ListNoticeOutStock(ListNoticeOutStockDto dto) {
        try {
            List<ListNoticeOutStockVo> list = saleMapper.ListNoticeOutStock(dto);
            if (!list.isEmpty()){
                return Result.ok(list).message("成功");
            }else {
                return Result.fail().message("暂无数据");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.fail().message("失败");
        }
    }

    /**
     * 通知下架--汇总查询
     * @param docNo
     * @return
     */
    @Override
    public Result ListNoticeOutStockDetail(String docNo) {
        try {
            List<ListNoticeOutStockDetailVo> list = saleMapper.ListNoticeOutStockDetail(docNo);
            if (!list.isEmpty()){
                return Result.ok(list).message("成功");
            }else {
                return Result.fail(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("失败");
        }
    }

//    /**
//     * 通知下架--同步中间表
//     * @param list
//     * @return
//     */
//    @Override
//    public MiddleReturnDto OtherOutStockInsertMiddleTable(List<NoticeOutStockSubmitDto> list) {
//        DateTime date = DateUtil.date();
//        List<String> docNo = new ArrayList<>();
//        for (NoticeOutStockSubmitDto dto : list) {
//            String barcode = dto.getBarcode();
//            docNo.add(dto.getDocNo());
//            saleMapper.insertMiddleTable(new Lsmdt(
//                    UUID.randomUUID().toString().toUpperCase(),                               //主键
//                    barcode,                             //条码编号
//                    dto.getWarehouseCode(),              //仓库
//                    dto.getBinCode(),                    //库位
//                    barcode.substring(barcode.indexOf("#")+1),                    //批号
//                    dto.getMatchQty(),                   //异动数量
//                    -1,                                   //异动类型
//                    dto.getDocNo(),                      //来源单号
////                "0",                                   //来源项次
////                null,                                  //目的单号
////                "0",                                   //目的项次
//                    "admin",                             //PDA操作人代号
//                    DateUtil.format(date,"yyyy-MM-dd"),              //扫描日期
//                    DateUtil.format(date,"HH:mm:ss.SSS"),               //时间
//                    "Y",                                 //扣账资料否
//                    dto.getUnitNo(),                     //异动数量单位
//                    1.0,                                 //异动与库存单位换算率
//                    NOTICEOUTSTOCK_APPID,                 //终端设备编号
//                    NOTICEOUTSTOCK_APPID+NOTICEOUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),  //APP申请串号
//                    NOTICEOUTSTOCK_APPMODULE,                   //APP申请所属模组
//                    "N",                                 //ERP异动码
//                    dto.getRemark(),                    //备注
//                    "",                                 //条码异动类型
//                    UUID.randomUUID().toString().trim().replace("-",""),//APP申请序列号
//                    "002",                              //039
//                    "YNY",                              //042
//                    dto.getUnitNo(),                   //046
//                    "0",                                //047
//                    0.00,
//                    0.00,
//                    "9998-12-31",                       //058
//                    dto.getItemCode(),                   //物料代码
//                    UUID.randomUUID().toString().trim().replace("-",""),//字符保留栏位
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd"),                 //过账日期
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd"),                 //日期保留栏位
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"), //资料创建日
//                    "1",                                //资料建立者
//                    WMS_ENTID,                          //企业代码
//                    DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"),//最近修改日
//                    "1",                                //资料修改者
//                    "qqq",                              //资料所属部门
//                    "dcms",                             //资料所有者
//                    WMS_COMPANYID,                      //营运据点
//                    "Y"                                //状态码
//            ));
//        }
//        return new MiddleReturnDto(docNo.get(0),NOTICEOUTSTOCK_APPID,NOTICEOUTSTOCK_APPID+NOTICEOUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
//    }

    /**
     * 通知下架提交
     * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject OtherOutStockSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,NOTICEOUTSTOCK_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),NOTICEOUTSTOCK_APPMODULE,WMS_APPVERSION,NOTICEOUTSTOCK_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        System.out.println(etenSession);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    @Override
    public Result ListSaleReturn(ListSaleReturnDto dto) {
        try {
            List<ListSaleReturnVo> list = saleMapper.ListSaleReturn(dto);
            if (!list.isEmpty()){
                return Result.ok(list).message("成功");
            }else {
                return Result.fail().message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail().message("失败");
        }
    }

    @Override
    public Result ListSaleReturnDetail(String docNo) {
        List<ListSaleReturnDetailVo> list = saleMapper.ListSaleReturnDetail(docNo);
        return Result.ok(list);
    }

    @Override
    public MiddleReturnDto SaleReturnInsertMiddleTable(List<SaleReturnSubmitDto> list) {
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        for (SaleReturnSubmitDto dto : list) {
            String barcode = dto.getBarcode();
            docNo.add(dto.getDocNo());
            saleMapper.insertMiddleTable2(Lsmdt.builder()
                            .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                            .lsmd001(barcode)      //条码编号
                            .lsmd002(dto.getWarehouseCode())      //仓库
                            .lsmd003(dto.getBinCode())//库位
                            .lsmd004(barcode.substring(barcode.indexOf("#")+1))//批号
//                    达威联调时wms与e10的批号管理不一致，先全改为* /""
//                            .lsmd004("")//批号
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
                            .lsmd026(SALERETURN_APPID)//终端设备编号
                            .lsmd027(SALERETURN_APPID+SALERETURN_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"))//APP申请串号
                            .lsmd028(SALERETURN_APPMODULE)//APP申请所属模组
                            .lsmd029("N")//ERP异动码
                            .lsmd030("")//备注
                            .lsmd031("1")//条码异动类型
                            .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
//                    .lsmd037(barcode.substring(barcode.indexOf("#")+1))
//                    达威联调时wms与e10的批号管理不一致，先全改为* /""
                            .lsmd034("2001")
                            .lsmd036("")
                            .lsmd037("")
                            .lsmd038("")
                            .lsmd039("002")
                            .lsmd042("YNY")
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
                            .build()
            );
        }
        return new MiddleReturnDto(docNo.get(0),SALERETURN_APPID,SALERETURN_APPID+SALERETURN_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
    }

    @Override
    public JSONObject SaleOutStockSubmit(MiddleReturnDto middleReturnDto, String createBy) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,createBy, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_T100,SALEOUTSTOCK_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                createBy, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),SALEOUTSTOCK_APPMODULE,WMS_APPVERSION,SALEOUTSTOCK_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
//        System.out.println(httpEntity);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    @Override
    public MiddleReturnDto SaleOrderOutStockInsertMiddleTable(List<SaleOrderOutDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        for (SaleOrderOutDto dto : list) {
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

            saleMapper.insertMiddleTable1(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getWarehouseCode())      //仓库
                    .lsmd003(dto.getBinCode())//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(-1)//异动类型
                    .lsmd007(dto.getDocNo())//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitNo())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(SALE_ORDER_OUTSTOCK_APPID)//终端设备编号
                    .lsmd027(SALE_ORDER_OUTSTOCK_APPID+SALE_ORDER_OUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(SALE_ORDER_OUTSTOCK_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd034("3002")
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("YNY")
                    .lsmd045("0.0")
                    .lsmd046(dto.getUnitNo())
                    .lsmd047("0.0")
                    .lsmd053(BigDecimal.ZERO)
                    .lsmd054("0.00")
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
                    .lsmdsite(WMS_COMPANYID) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
            System.out.println("---------------");
        }
        return new MiddleReturnDto(docNo.get(0),SALE_ORDER_OUTSTOCK_APPID,SALE_ORDER_OUTSTOCK_APPID+SALE_ORDER_OUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink");


    }

    @Override
    public JSONObject SaleOrderOutStockSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(),""),
                new SessionService(WMS_SERVICE_T100,SALE_ORDER_OUTSTOCK_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),SALE_ORDER_OUTSTOCK_APPMODULE,WMS_APPVERSION,SALE_ORDER_OUTSTOCK_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
//        System.out.println(httpEntity);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    @Override
    public JSONObject SaleReturnSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_T100,SALERETURN_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),SALERETURN_APPMODULE,WMS_APPVERSION,SALERETURN_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
//        System.out.println(httpEntity);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }

    @Override
    public MiddleReturnDto SaleOutStockInsertMiddleTable(List<SaleOutStockDto> list, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";

        for (SaleOutStockDto dto : list) {
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

            String binCode = dto.getBinCode();
            String lotNumber= saleMapper.selectLotNumber(dto.getBarcode());

            shjg = dto.getShjg();
            if (dto.getCurrentNum() == null ||
                    dto.getCurrentNum().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BaseException(500, "异动数量不能小于0！");
            }

            if (StrUtil.isBlank(dto.getRemark())) {
                dto.setRemark("WMS");
            }
            saleMapper.insertMiddleTable1(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getWarehouseCode())      //仓库
                    .lsmd003(dto.getBinCode())//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getCurrentNum())//异动数量
                    .lsmd006(-1)//异动类型
                    .lsmd007(dto.getDocNo())//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss:SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(SALEOUTSTOCK_APPID)//终端设备编号
                    .lsmd027(SALEOUTSTOCK_APPID+SALEOUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(SALEOUTSTOCK_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
//                    .lsmd034("2001")
                    .lsmd034("3002")
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("YNY")
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
            System.out.println("---------------");
        }
        return new MiddleReturnDto(docNo.get(0),SALEOUTSTOCK_APPID,SALEOUTSTOCK_APPID+SALEOUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink",shjg);

    }


}
