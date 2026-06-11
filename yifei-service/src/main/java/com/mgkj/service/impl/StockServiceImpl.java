package com.mgkj.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.Item;
import com.mgkj.entity.Lsmdt;
import com.mgkj.entity.session.*;
import com.mgkj.mapper.ItemMapper;

import com.mgkj.mapper.StockMapper;
import com.mgkj.service.StockService;
import com.mgkj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mgkj.common.constant.WmsConstants.*;


/**
 * @author yyyjcg
 * @date 2024/4/7
 * @Description
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;
   @Autowired
    private ItemMapper itemMapper;

    @Override
    public Result ListStock(ListStockDto dto) {
        try {
            List<ListStockVo> list = stockMapper.ListStock(dto);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    /**
     *    其他入库查看简洁
     */
    @Override
    public Result ListOtherInStock(OtherInStockDto dto) {
        try {
            List<OtherInStockVo> list = stockMapper.ListOtherInStock(dto);
            if (!list.isEmpty()){
                return Result.ok(list);
            }else {
                return Result.fail().message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    /**
     *    其他入库查看详情
     */
    @Override
    public Result<List<OtherInStockDetailVo>> OtherInStockDetail(String docNo) {
        try {
            List<OtherInStockDetailVo> list = stockMapper.OtherInStockDetail(docNo);
            if (!list.isEmpty()){
                return Result.ok(list);
            }else {
                return Result.fail(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    /**
     *    其他入库提交中间表
     */
//    @Override
//    public MiddleReturnDto insertMiddleTable(List<OtherInstockSubmitDto> list) {
//        System.out.println("同步中间表");
//        DateTime date = DateUtil.date();
//        List<String> docNo = new ArrayList<>();
//        for (OtherInstockSubmitDto dto : list) {
//            LambdaQueryWrapper<Item> queryWrapper  = new LambdaQueryWrapper<Item>().eq(Item::getItemCode, dto.getItemCode()).ne(Item::getLotControl, "N");
//           int i =  itemMapper.selectBinControl(dto.getItemCode());
//            System.out.println("i=" + i);
//            //开启批号或开启库位管理
//            if (itemMapper.selectCount(queryWrapper) > 0 || i ==1) {
//                //入库单都是账随物动
////                String ph = stockMapper.selectPh(dto.getDocNo(),dto.getItemCode());
//                String barcode = dto.getBarcode();
//                docNo.add(dto.getDocNo());
//                stockMapper.insertMiddleTable2(Lsmdt.builder()
//                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
//                    .lsmd001(barcode)      //条码编号
//                    .lsmd002(dto.getWarehouseCode())      //仓库
//                    .lsmd003(dto.getBinCode())//库位
//                    .lsmd004(barcode.substring(barcode.indexOf("#")+1))//批号
////                    .lsmd004(ph)//批号
//                            .lsmd004("")//批号
//                    .lsmd005(dto.getMatchQty())//异动数量
//                    .lsmd006(1)//异动类型
//                    .lsmd007(dto.getDocNo())//来源单号
//                    .lsmd008("0")
//                    .lsmd010("0")
//                    .lsmd013("admin")//PDA操作人代号
//                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
//                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
//                    .lsmd019("Y")//扣账资料否
//                    .lsmd024(dto.getUnitCode())//异动数量单位
//                    .lsmd025(1.0)//异动与库存单位换算率
//                    .lsmd026(OTHERINSTOCK_APPID)//终端设备编号
//                    .lsmd027(OTHERINSTOCK_APPID+OTHERINSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"))//APP申请串号
//                    .lsmd028(OTHERINSTOCK_APPMODULE)//APP申请所属模组
//                    .lsmd029("N")//ERP异动码
//                    .lsmd030("")//备注
//                    .lsmd031("1")//条码异动类型
//                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
//                    .lsmd036(dto.getBinCode())
////                    .lsmd036("")
//                    .lsmd037("")
////                    .lsmd037(barcode.substring(barcode.indexOf("#")+1))
////                    .lsmd037("********************")
//                    .lsmd038("")
//                    .lsmd039("001")
//                    .lsmd042("NNN")
//                    .lsmd045("0.0")
//                    .lsmd046(dto.getUnitCode())
//                    .lsmd047("0.0")
//                    .lsmd053(0.00)
//                    .lsmd054("0.00")
//                    .lsmd055(0.00)
//                    .lsmd058("9998-12-31")
//                    .lsmd081("0.0")
//                    .lsmd082("0.0")
//                    .lsmd083(dto.getItemCode())
//                    .lsmd084(UUID.randomUUID().toString().trim().replace("-",""))//字符保留栏位
//                    .lsmd085(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//过账日期
//                    .lsmd086(DateUtil.format(DateUtil.date(),"yyyy-MM-dd"))//日期保留栏位
//                    .lsmd906("0")
//                    .lsmd907("0")
//                    .lsmdcrtdt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//资料创建日
//                    .lsmdcrtid("1")//资料建立者
//                    .lsmdent(WMS_ENTID)//企业代码
//                    .lsmdmoddt(DateUtil.format(DateUtil.date(),"yyyy-MM-dd HH:mm:ss"))//最近修改日
//                    .lsmdmodid("1") //资料修改者
//                    .lsmdowndp("qqq") //资料所属部门
//                    .lsmdownid("dcms") //资料所有者
//                    .lsmdsite(WMS_COMPANYID) //营运据点
//                    .lsmdstus("Y") //状态码
//                    .build()
//                );
//                } else {
//                String barcode = dto.getBarcode();
//                docNo.add(dto.getDocNo());
//                stockMapper.insertMiddleTable2(Lsmdt.builder()
//                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
//                                .lsmd001(barcode)      //条码编号
//                                .lsmd002(dto.getWarehouseCode())      //仓库
//                                .lsmd003(dto.getBinCode())//库位
//                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
////                            .lsmd004("")//批号
//                                .lsmd005(dto.getMatchQty())//异动数量
//                                .lsmd006(1)//异动类型
//                                .lsmd007(dto.getDocNo())//来源单号
//                                .lsmd008("0")
//                                .lsmd010("0")
//                                .lsmd013("admin")//PDA操作人代号
//                                .lsmd014(DateUtil.format(date, "yyyy-MM-dd"))//扫描日期
//                                .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))//时间
//                                .lsmd019("Y")//扣账资料否
//                                .lsmd024(dto.getUnitCode())//异动数量单位
//                                .lsmd025(1.0)//异动与库存单位换算率
//                                .lsmd026(OTHERINSTOCK_APPID)//终端设备编号
//                                .lsmd027(OTHERINSTOCK_APPID + OTHERINSTOCK_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))//APP申请串号
//                                .lsmd028(OTHERINSTOCK_APPMODULE)//APP申请所属模组
//                                .lsmd029("N")//ERP异动码
//                                .lsmd030("")//备注
//                                .lsmd031("1")//条码异动类型
//                                .lsmd033(UUID.randomUUID().toString().trim().replace("-", ""))//APP申请序列号
//                                .lsmd036("")
////                            .lsmd037(barcode.substring(barcode.indexOf("#")+1))
////                    达威联调时wms与e10的批号管理不一致，先全改为* /""
//                                .lsmd037("")
//                                .lsmd038("")
//                                .lsmd039("001")
//                                .lsmd042("NNN")
//                                .lsmd045("0.0")
//                                .lsmd046(dto.getUnitCode())
//                                .lsmd047("0.0")
//                                .lsmd053(0.00)
//                                .lsmd054("0.00")
//                                .lsmd055(0.00)
//                                .lsmd058("9998-12-31")
//                                .lsmd081("0.0")
//                                .lsmd082("0.0")
//                                .lsmd083(dto.getItemCode())
//                                .lsmd084(UUID.randomUUID().toString().trim().replace("-", ""))//字符保留栏位
//                                .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//过账日期
//                                .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd"))//日期保留栏位
//                                .lsmd906("0")
//                                .lsmd907("0")
//                                .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//资料创建日
//                                .lsmdcrtid("1")//资料建立者
//                                .lsmdent(WMS_ENTID)//企业代码
//                                .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))//最近修改日
//                                .lsmdmodid("1") //资料修改者
//                                .lsmdowndp("qqq") //资料所属部门
//                                .lsmdownid("dcms") //资料所有者
//                                .lsmdsite(WMS_COMPANYID) //营运据点
//                                .lsmdstus("Y") //状态码
//                                .build()
//                );
//            }
//        }
//        return new MiddleReturnDto(docNo.get(0),OTHERINSTOCK_APPID,OTHERINSTOCK_APPID+OTHERINSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
//    }


    @Override
    public MiddleReturnDto insertMiddleTable(List<OtherInstockSubmitDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();

        for (OtherInstockSubmitDto dto : list) {
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

            if (isLotControlEnabled) {
                // 如果启用了批号管理，从条码中获取批号
                if (barcode.contains("#")) {
                    lotNumber = barcode.substring(barcode.indexOf("#") + 1);
                }
            }

            stockMapper.insertMiddleTable2(
                    Lsmdt.builder()
                            .lsmdTId(UUID.randomUUID().toString().toUpperCase()) // 主键
                            .lsmd001(barcode) // 条码编号
                            .lsmd002(dto.getWarehouseCode()) // 仓库
                            .lsmd003(binCode) // 库位
                            .lsmd004(lotNumber) // 批号
                            .lsmd005(dto.getMatchQty()) // 异动数量
                            .lsmd006(1) // 异动类型
                            .lsmd007(dto.getDocNo()) // 来源单号
                            .lsmd008("0")
                            .lsmd010("0")
                            .lsmd013("admin") // PDA操作人代号
                            .lsmd014(DateUtil.format(date, "yyyy-MM-dd")) // 扫描日期
                            .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS")) // 时间
                            .lsmd019("Y") // 扣账资料否
                            .lsmd024(dto.getUnitCode()) // 异动数量单位
                            .lsmd025(BigDecimal.ONE) // 异动与库存单位换算率
                            .lsmd026(OTHERINSTOCK_APPID) // 终端设备编号
                            .lsmd027(OTHERINSTOCK_APPID + OTHERINSTOCK_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss")) // APP申请串号
                            .lsmd028(OTHERINSTOCK_APPMODULE) // APP申请所属模组
                            .lsmd029("N") // ERP异动码
                            .lsmd030("") // 备注
                            .lsmd031("1") // 条码异动类型
                            .lsmd033(UUID.randomUUID().toString().trim().replace("-", "")) // APP申请序列号
                            .lsmd036(binCode) // 如果开启库位管理，赋值库位，否则为空
//                            .lsmd036(isBinControlEnabled ? binCode : "") // 如果开启库位管理，赋值库位，否则为空
                            .lsmd037(isLotControlEnabled ? lotNumber : "") // 如果开启批号管理，赋值批号，否则为空
                            .lsmd038("")
                            .lsmd039("001")
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
                            .lsmd084(UUID.randomUUID().toString().trim().replace("-", "")) // 字符保留栏位
                            .lsmd085(DateUtil.format(DateUtil.date(), "yyyy-MM-dd")) // 过账日期
                            .lsmd086(DateUtil.format(DateUtil.date(), "yyyy-MM-dd")) // 日期保留栏位
                            .lsmd906("0")
                            .lsmd907("0")
                            .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss")) // 资料创建日
                            .lsmdcrtid("1") // 资料建立者
                            .lsmdent(WMS_ENTID) // 企业代码
                            .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss")) // 最近修改日
                            .lsmdmodid("1") // 资料修改者
                            .lsmdowndp("qqq") // 资料所属部门
                            .lsmdownid("dcms") // 资料所有者
                            .lsmdsite(WMS_COMPANYID) // 营运据点
                            .lsmdstus("Y") // 状态码
                            .build()
            );
        }

        return new MiddleReturnDto(
                docNo.get(0),
                OTHERINSTOCK_APPID,
                OTHERINSTOCK_APPID + OTHERINSTOCK_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"),
                ""
        );
    }


    /**
     *    其他入库调用e10提交接口
     */
    @Override
    public JSONObject OtherInStockSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,OTHERINSTOCK_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),OTHERINSTOCK_APPMODULE,WMS_APPVERSION,OTHEROUTSTOCK_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        System.out.println(response);
        System.out.println(httpEntity.getBody());
        return result;
    }

    /**
     *    其他出库简单查询
     * @param （单号，条码，人员，部门，日期）
     */
    @Override
    public Result ListOtherOutStock(OtherOutStockDto dto) {

        try {
            List<OtherOutStockVo> list = stockMapper.ListOtherOutStock(dto);
            if (!list.isEmpty()){
                return Result.ok(list);
            }else {
                return Result.fail().message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    /**
     *   其他出库详情
     * @param （单号）
     */
    @Override
    public Result<List<OtherOutStockDetailVo>> OtherOutStockDetail(String docNo) {
        try {
            List<OtherOutStockDetailVo> list = stockMapper.OtherOutStockDetail(docNo);
            if (!list.isEmpty()){
                return Result.ok(list);
            }else {
                return Result.fail(list).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    /**
     *    其他出库同步中间表
     */
    @Override
    public MiddleReturnDto OtherOutStockInsertMiddleTable(List<OtherOutStockSubmitDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        for (OtherOutStockSubmitDto dto : list) {
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
                stockMapper.insertMiddleTable2(Lsmdt.builder()
                                .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                                .lsmd001(barcode)      //条码编号
                                .lsmd002(dto.getWarehouseCode())      //仓库
                                .lsmd003(binCode)//库位
                                .lsmd004(lotNumber)//批号
//                                .lsmd004(barcode.substring(barcode.indexOf("#") + 1))//批号
//                            .lsmd004("")//批号
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
                                .lsmd026(OTHEROUTSTOCK_APPID)//终端设备编号
                                .lsmd027(OTHEROUTSTOCK_APPID + OTHEROUTSTOCK_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))//APP申请串号
                                .lsmd028(OTHEROUTSTOCK_APPMODULE)//APP申请所属模组
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
                                .lsmdownid("dcms") //资料所有者
                                .lsmdsite(WMS_COMPANYID) //营运据点
                                .lsmdstus("Y") //状态码
                                .build()
                );
        }

        return new MiddleReturnDto(docNo.get(0),OTHEROUTSTOCK_APPID,OTHEROUTSTOCK_APPID+OTHEROUTSTOCK_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"");
    }

    /**
     *    其他出库提交
     * @param middleReturnDto
     * @return
     */
    @Override
    public JSONObject OtherOutStockSubmit(MiddleReturnDto middleReturnDto) {
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,OTHEROUTSTOCK_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),OTHEROUTSTOCK_APPMODULE,WMS_APPVERSION,OTHEROUTSTOCK_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
                        )
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
    public Result ListTransferDoc(ListTransferDocDto dto) {
        try {
            List<ListTransferDocVo> list = stockMapper.ListTransferDoc(dto);
            if (!list.isEmpty()){
                return Result.ok(list);
            }else {
                return Result.fail(null).message("暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(null).message("查询失败,联系管理员");
        }

    }

    @Override
    public Result ListTransferDocDetail(String docNo) {
        try {
            List<ListTransferDocDetailVo> list = stockMapper.ListTransferDocDetailVo(docNo);
            return Result.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @Override
    public MiddleReturnDto insertTransferDocMiddleTable(List<TransferDocSubmitDto> list) {
        DateTime date = DateUtil.date();
        String md084 = UUID.randomUUID().toString().trim().replace("-", "");
        List<String> docNo = new ArrayList<>();
        String fromShjg = "";
        for (TransferDocSubmitDto dto : list) {
            String toShjg = dto.getToShjg();
            fromShjg = dto.getFromShjg();

            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;
            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            String barcode = dto.getBarcode();
            String lotNumber = stockMapper.selectLotNumber(dto.getBarcode());
            String toinCode = dto.getToBinCode();
            docNo.add(dto.getDocNo());
            stockMapper.insertMiddleTable2(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getToWarehouseCode())      //仓库
                    .lsmd003(dto.getToBinCode())//库位
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
                    .lsmd026(TRANSFERDOC_APPID)//终端设备编号
                    .lsmd027(TRANSFERDOC_APPID+TRANSFERDOC_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(TRANSFERDOC_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getToBinCode())
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("NNN")
                    .lsmd045("0.0")
                    .lsmd046("")
                    .lsmd047("0.0")
                    .lsmd053(BigDecimal.ZERO)
                    .lsmd054("0.0")
                    .lsmd055(BigDecimal.ZERO)
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(md084)//字符保留栏位
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
                    .lsmdsite(toShjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
            stockMapper.insertMiddleTable2(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getFromWarehouseCode())      //仓库
                    .lsmd003(dto.getFromBinCode())//库位
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
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(TRANSFERDOC_APPID)//终端设备编号
                    .lsmd027(TRANSFERDOC_APPID+TRANSFERDOC_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(TRANSFERDOC_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getFromBinCode())
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("NNN")
                    .lsmd045("0.0")
                    .lsmd046("")
                    .lsmd047("0.0")
                    .lsmd053(BigDecimal.ZERO)
                    .lsmd054("0.0")
                    .lsmd055(BigDecimal.ZERO)
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(md084)//字符保留栏位
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
                    .lsmdsite(fromShjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
        }

        return new MiddleReturnDto(docNo.get(0),TRANSFERDOC_APPID,TRANSFERDOC_APPID+TRANSFERDOC_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink",fromShjg);
    }

    @Override
    public JSONObject ListTransferDocSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_T100,TRANSFERDOC_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),TRANSFERDOC_APPMODULE,MO_INSTORAGE_APPVERSION,TRANSFERDOC_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        System.out.println(JSONUtil.toJsonStr(etenSession));
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        return result;
    }

//    @Override
//    public JSONObject ListTransferDocSubmit(MiddleReturnDto middleReturnDto) {
//        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
//                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
//                new SessionService(WMS_SERVICE_PROD,TRANSFERDOC_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
//                new SessionDatakey(WMS_ENTID,WMS_COMPANYID),
//                new SessionPayload(new StdData(new ScanToStorageParameter(
//                        "","","","","","","","","","","","","","",
//                        new ScanToStorageParameterData(
//                                "","" , "", "","","","","","","","","","","","",""
//                        ),
//                        new ScanToStorageParameterHost(
//                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),TRANSFERDOC_APPMODULE,WMS_APPVERSION,TRANSFERDOC_NAME,WMS_ENTID,WMS_COMPANYID,middleReturnDto.getErpRemark(),"0",""
//                        )
//                ))));
//
//        //链式构建请求
//        String result2 = HttpRequest.post(SERVICE_IP_PORT+"/Restful/invokeSrv")
//                .header(Header.CONTENT_TYPE, "text/plain; charset=utf-8")//头信息，多个头信息多次调用此方法即可
//                .form(JSONUtil.toJsonStr(etenSession))//表单内容
//                .timeout(4000)//超时，毫秒
//                .execute().body();
//        System.out.println(result2);
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
////        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
////        RestTemplate restTemplate = new RestTemplate();
////        System.out.println("sdd");
////        System.out.println(JSONUtil.toJsonStr(etenSession));
//        // http://4gbmggao.dongtaiyuming.net/Restful/invokeSrv
//        // http://4gbmggao.dongtaiyuming.net/Restful/invokeSrv
////        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
////        System.out.println("fd");
////        JSONObject result = JSON.parseObject(response);
//        return null;
//    }



    @Override
    public MiddleReturnDto insertTransferDocOutMiddleTable(List<TransferDocOutSubmitDto> list) {
        DateTime date = DateUtil.date();
        String md084 = UUID.randomUUID().toString().trim().replace("-", "");
        List<String> docNo = new ArrayList<>();
        String fromShjg = "";
        for (TransferDocOutSubmitDto dto : list) {
            String toShjg = dto.getToShjg();
            fromShjg = dto.getFromShjg();

            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;
            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            String barcode = dto.getBarcode();
            String lotNumber = stockMapper.selectLotNumber(dto.getBarcode());
            String toinCode = dto.getToBinCode();
            docNo.add(dto.getDocNo());
            stockMapper.insertMiddleTable2(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getFromWarehouseCode())      //仓库
                    .lsmd003(dto.getFromBinCode())//库位
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
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(TRANSFERDOC_OUT_APPID)//终端设备编号
                    .lsmd027(TRANSFERDOC_OUT_APPID+TRANSFERDOC_OUT_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(TRANSFERDOC_OUT_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getFromBinCode())
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("002")
                    .lsmd042("NNN")
                    .lsmd045("0.0")
                    .lsmd046("")
                    .lsmd047("0.0")
                    .lsmd053(BigDecimal.ZERO)
                    .lsmd054("0.0")
                    .lsmd055(BigDecimal.ZERO)
                    .lsmd058("9998-12-31")
                    .lsmd081("0.0")
                    .lsmd082("0.0")
                    .lsmd083(dto.getItemCode())
                    .lsmd084(md084)//字符保留栏位
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
                    .lsmdsite(fromShjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
        }

        return new MiddleReturnDto(docNo.get(0),TRANSFERDOC_OUT_APPID,TRANSFERDOC_OUT_APPID+TRANSFERDOC_OUT_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink",fromShjg);
    }

    @Override
    public JSONObject ListTransferDocOutSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_T100,TRANSFERDOC_OUT_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),TRANSFERDOC_OUT_APPMODULE,WMS_APPVERSION,TRANSFERDOC_OUT_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        System.out.println(JSONUtil.toJsonStr(etenSession));
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        JSONObject result = JSON.parseObject(response);
        return result;
    }








}
