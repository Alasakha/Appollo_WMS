package com.mgkj.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
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
import com.mgkj.mapper.PassiveMapper;
import com.mgkj.service.PassiveService;
import com.mgkj.vo.PassiveEmployeeInfoVo;
import com.mgkj.vo.TransferBarcodeVo;
import com.mgkj.vo.UnfinishedProjectsVo;
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
 * @date 2023/12/26
 * @Description
 */
@Service
public class PassiveServiceImp implements PassiveService {

    @Autowired
    private  PassiveMapper passiveMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public MiddleReturnDto PassiveToStorageInsertMiddleTable(List<PassiveToStorageDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        for (PassiveToStorageDto dto : list) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;
//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            String binCode = dto.getBinCode();
            shjg = dto.getShjg();


            String lotNumber = passiveMapper.selectLotNumber(dto.getBarcode());
            //无源入库没有单号
            docNo.add("");
            passiveMapper.insertMiddleTable( Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase())
                    .lsmd001(barcode)
                    .lsmd002(dto.getWarehouseCode())
                    .lsmd003(binCode)
                    .lsmd004(lotNumber)
                    .lsmd005(dto.getMatchQty())
                    .lsmd006(1)
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")
                    .lsmd014(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmd015(DateUtil.format(date, "HH:mm:ss:SSS"))
                    .lsmd017(dto.getInToType())
                    .lsmd018(dto.getEmployeeCode())
                    .lsmd019(dto.getIsDeduct())
                    .lsmd020(dto.getDepartmentCode())
                    .lsmd024(dto.getUnitCode())
                    .lsmd025(BigDecimal.ONE)
                    .lsmd026(PASSIVE_INSTORAGE_APPID)
                    .lsmd027(PASSIVE_INSTORAGE_APPID + PASSIVE_INSTORAGE_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))
                    .lsmd028(PASSIVE_INSTORAGE_APPMODULE)
                    .lsmd029("N")
                    .lsmd030("")
                    .lsmd031("1")
                    .lsmd033(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd039("004")
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
                    .lsmd084(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd085(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd086(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdcrtid("1")
                    .lsmdent(WMS_ENTID)
                    .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdmodid("1")
                    .lsmdowndp("qqq")
                    .lsmdownid("dcms")
                    .lsmdsite(shjg)
                    .lsmdstus("Y")
                    .build()
                    );
        }

        return new MiddleReturnDto(docNo.get(0),PASSIVE_INSTORAGE_APPID,PASSIVE_INSTORAGE_APPID+PASSIVE_INSTORAGE_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddhhmmss"),"",shjg);
    }
    @Override
    public JSONObject PassiveToStorageSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,PASSIVE_INSTORAGE_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),PASSIVE_INSTORAGE_APPMODULE,WMS_APPVERSION,PASSIVE_INSTORAGE_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
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
    public JSONObject ABLPassiveToStorageSubmit(MiddleReturnDto middleReturnDto,String typeCode) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,PASSIVE_INSTORAGE_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                typeCode, middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),PASSIVE_INSTORAGE_APPMODULE,WMS_APPVERSION,PASSIVE_INSTORAGE_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
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
    public MiddleReturnDto PassiveOutStorageInsertMiddleTable(List<PassiveOutStorageDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";

        for (PassiveOutStorageDto dto : list) {

            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 解析条码与库位
            String barcode = dto.getBarcode();
            String binCode = dto.getBinCode();
            shjg = dto.getShjg();
            String lotNumber = passiveMapper.selectLotNumber(barcode);

            // 无源入库没有单号
            docNo.add("");

            if (dto.getMatchQty() == null ||
                    dto.getMatchQty().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BaseException(500, "异动数量不能小于0！");
            }

            // 构建并插入中间表数据
            Lsmdt lsmdt = Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase())
                    .lsmd001(barcode)
                    .lsmd002(dto.getWarehouseCode())
                    .lsmd003(binCode)
                    .lsmd004(lotNumber) // 保留空批号
                    .lsmd005(dto.getMatchQty())
                    .lsmd006(-1)
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")
                    .lsmd014(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))
                    .lsmd017(dto.getInToType())
                    .lsmd018(dto.getEmployeeCode())
                    .lsmd019(dto.getIsDeduct())
                    .lsmd020(dto.getDepartmentCode())
                    .lsmd024(dto.getUnitCode())
                    .lsmd025(BigDecimal.ONE)
                    .lsmd026(PASSIVE_OUT_APPID)
                    .lsmd027(PASSIVE_OUT_APPID + PASSIVE_OUT_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddhhmmss"))
                    .lsmd028(PASSIVE_OUT_APPMODULE)
                    .lsmd029("N")
                    .lsmd030("")
                    .lsmd031("1")
                    .lsmd033(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd039("004")
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
                    .lsmd084(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd085(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd086(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdcrtid("1")
                    .lsmdent(WMS_ENTID)
                    .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdmodid("1")
                    .lsmdowndp("qqq")
                    .lsmdownid("dcms")
                    .lsmdsite(shjg)
                    .lsmdstus("Y")
                    .build();

            passiveMapper.insertMiddleTable(lsmdt);
        }

        return new MiddleReturnDto(docNo.get(0),PASSIVE_OUT_APPID,PASSIVE_OUT_APPID+PASSIVE_OUT_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"",shjg);
    }

    @Override
    public JSONObject ABLPassiveOutStorageSubmit(MiddleReturnDto middleReturnDto,String typeCode, String createBy) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,createBy, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,PASSIVE_OUT_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                typeCode, middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                createBy, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),PASSIVE_OUT_APPMODULE,WMS_APPVERSION,PASSIVE_OUT_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
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
    @Override
    public JSONObject PassiveOutStorageSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(MO_INSTORAGE_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getErpRemark()),
                new SessionService(WMS_SERVICE_PROD,PASSIVE_OUT_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        "","","","","","","","","","","","","","",
                        new ScanToStorageParameterData(
                                "", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),PASSIVE_OUT_APPMODULE,WMS_APPVERSION,PASSIVE_OUT_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
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

    @Override
    public MiddleReturnDto PassiveTransferInsertMiddleTable(List<PassiveTransferDocSubmitDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String md084 = UUID.randomUUID().toString().trim().replace("-","");
        for (PassiveTransferDocSubmitDto dto : list) {

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
//            String lotNumber = "";
            String barcode = dto.getBarcode();
//                        if (barcode.contains("#")) {
//                  lotNumber = barcode.split("#")[1];
//            }
            String lotNumber = passiveMapper.selectLotNumber(dto.getBarcode());

            WmsConstants.WMS_COMPANYID = dto.getShjg();

            passiveMapper.insertTransferMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getToWarehouseCode())      //仓库
                    .lsmd003(dto.getToBinCode())//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(1)//异动类型
                    .lsmd007("")//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(PASSIVE_TRANSFER_APPID)//终端设备编号
                    .lsmd027(PASSIVE_TRANSFER_APPID+PASSIVE_TRANSFER_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(PASSIVE_TRANSFER_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getToBinCode())
                    .lsmd037(isLotControlEnabled? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("004")
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
                    .lsmdsite(WMS_COMPANYID) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
            passiveMapper.insertTransferMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getFromWarehouseCode())      //仓库
                    .lsmd003(dto.getFromBinCode())//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(-1)//异动类型
                    .lsmd007("")//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(PASSIVE_TRANSFER_APPID)//终端设备编号
                    .lsmd027(PASSIVE_TRANSFER_APPID+PASSIVE_TRANSFER_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(PASSIVE_TRANSFER_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getFromBinCode())
                    .lsmd037(isLotControlEnabled? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("004")
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
                    .lsmdsite(WMS_COMPANYID) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
        }

        return new MiddleReturnDto("",PASSIVE_TRANSFER_APPID,PASSIVE_TRANSFER_APPID+PASSIVE_TRANSFER_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink");
    }



    @Override
    public Result<List<TransferBarcodeVo>> getTransferBarcodeInfo(PassiveTransferBarcodeDto dto) {
        String barcode = dto.getBarcode();
        List<TransferBarcodeVo> list =  passiveMapper.getTransferBarcodeInfo(barcode);
        return Result.ok(list);
    }

    @Override
    public Result<List<PassiveEmployeeInfoVo>> getEmployeeInfo(PassiveEmployeeInfoDto dto) {
        String employeeName = dto.getEmployeeName();
        List<PassiveEmployeeInfoVo> list =  passiveMapper.getEmployeeInfo(employeeName);
        return Result.ok(list);
    }


    //    ========================================ABL无源入库=====================================================
    @Override
    public MiddleReturnDto ABLPassiveToStorageInsertMiddleTable(List<PassiveToStorageDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        String timestamp = PASSIVE_INSTORAGE_APPID + PASSIVE_INSTORAGE_APPMODULE + WMS_APPVERSION + UUID.randomUUID().toString();
        for (PassiveToStorageDto dto : list) {
            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 查询是否启用了库位管理
//            boolean isBinControlEnabled = itemMapper.selectBinControl(dto.getItemCode()) == 1;
//            System.out.println("商品：" + dto.getItemCode() + " 批号管理：" + isLotControlEnabled);

            // 解析条码
            String barcode = dto.getBarcode();
            String binCode = dto.getBinCode();
            shjg = dto.getShjg();
            String lotNumber = passiveMapper.selectLotNumber(dto.getBarcode());
//            String lotNumber = "20251017001";
            //无源入库没有单号
            docNo.add("");
            passiveMapper.insertMiddleTable( Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase())
                    .lsmd001(barcode)
                    .lsmd002(dto.getWarehouseCode())
                    .lsmd003(binCode)
                    .lsmd004(isLotControlEnabled ? lotNumber : "")
                    .lsmd005(dto.getMatchQty())
                    .lsmd006(1)
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")
                    .lsmd014(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmd015(DateUtil.format(date, "HH:mm:ss:SSS"))
                    .lsmd017(dto.getInToType())
                    .lsmd018(dto.getEmployeeCode())
                    .lsmd019(dto.getIsDeduct())
                    .lsmd020(dto.getDepartmentCode())
                    .lsmd024(dto.getUnitCode())
                    .lsmd025(BigDecimal.ONE)
                    .lsmd026(PASSIVE_INSTORAGE_APPID)
                    .lsmd027(timestamp)
                    .lsmd028(PASSIVE_INSTORAGE_APPMODULE)
                    .lsmd029("N")
                    .lsmd030("")
                    .lsmd031("1")
                    .lsmd033(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
//                    .lsmd037(lotNumber)
                    .lsmd039("004")
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
                    .lsmd084(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd085(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd086(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdcrtid("1")
                    .lsmdent(WMS_ENTID)
                    .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdmodid("1")
                    .lsmdowndp("qqq")
                    .lsmdownid("dcms")
                    .lsmdsite(shjg)
                    .lsmdstus("Y")
                    .build()
            );
        }

        return new MiddleReturnDto(docNo.get(0),PASSIVE_INSTORAGE_APPID,timestamp,"",shjg);
    }


    @Override
    public MiddleReturnDto ABLPassiveOutStorageInsertMiddleTable(List<PassiveOutStorageDto> list, String createBy) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String shjg = "";
        for (PassiveOutStorageDto dto : list) {

            // 查询是否启用了批号管理
            boolean isLotControlEnabled = itemMapper.selectCount(
                    new LambdaQueryWrapper<Item>()
                            .eq(Item::getItemCode, dto.getItemCode())
                            .ne(Item::getLotControl, "N")
            ) > 0;

            // 解析条码与库位
            String barcode = dto.getBarcode();
            String binCode = dto.getBinCode();
            shjg = dto.getShjg();
            String lotNumber = passiveMapper.selectLotNumber(barcode);

            // 无源入库没有单号
            docNo.add("");

            // 构建并插入中间表数据
            Lsmdt lsmdt = Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase())
                    .lsmd001(barcode)
                    .lsmd002(dto.getWarehouseCode())
                    .lsmd003(binCode)
                    .lsmd004(lotNumber) // 保留空批号
                    .lsmd005(dto.getMatchQty())
                    .lsmd006(-1)
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")
                    .lsmd014(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmd015(DateUtil.format(date, "HH:mm:ss.SSS"))
                    .lsmd017(dto.getInToType())
                    .lsmd018(dto.getEmployeeCode())
                    .lsmd019(dto.getIsDeduct())
                    .lsmd020(dto.getDepartmentCode())
                    .lsmd024(dto.getUnitCode())
                    .lsmd025(BigDecimal.ONE)
                    .lsmd026(PASSIVE_OUT_APPID)
                    .lsmd027(PASSIVE_OUT_APPID + PASSIVE_OUT_APPMODULE + WMS_APPVERSION + DateUtil.format(date, "yyMMddHHmmss"))
                    .lsmd028(PASSIVE_OUT_APPMODULE)
                    .lsmd029("N")
                    .lsmd030("")
                    .lsmd031("1")
                    .lsmd033(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd036(binCode)
                    .lsmd037(isLotControlEnabled ? lotNumber : "")
                    .lsmd039("004")
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
                    .lsmd084(UUID.randomUUID().toString().replace("-", ""))
                    .lsmd085(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd086(DateUtil.format(date, "yyyy-MM-dd"))
                    .lsmd906("0")
                    .lsmd907("0")
                    .lsmdcrtdt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdcrtid("1")
                    .lsmdent(WMS_ENTID)
                    .lsmdmoddt(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"))
                    .lsmdmodid("1")
                    .lsmdowndp("qqq")
                    .lsmdownid(createBy)
                    .lsmdsite(shjg)
                    .lsmdstus("Y")
                    .build();

            passiveMapper.insertMiddleTable(lsmdt);
        }

        return new MiddleReturnDto(docNo.get(0),PASSIVE_OUT_APPID,PASSIVE_OUT_APPID+PASSIVE_OUT_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"",shjg);
    }

    @Override
    public MiddleReturnDto ABLPassiveTransferInsertMiddleTable(List<PassiveTransferDocSubmitDto> list) {
        System.out.println("同步中间表");
        DateTime date = DateUtil.date();
        List<String> docNo = new ArrayList<>();
        String md084 = UUID.randomUUID().toString().trim().replace("-","");
        String shjg = "";
        for (PassiveTransferDocSubmitDto dto : list) {

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

            String barcode = dto.getBarcode();
            String lotNumber = passiveMapper.selectLotNumber(dto.getBarcode());
            shjg = dto.getShjg();
            passiveMapper.insertTransferMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getToWarehouseCode())      //仓库
                    .lsmd003(dto.getToBinCode())//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(1)//异动类型
                    .lsmd007("")//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(PASSIVE_TRANSFER_APPID)//终端设备编号
                    .lsmd027(PASSIVE_TRANSFER_APPID+PASSIVE_TRANSFER_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(PASSIVE_TRANSFER_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("1")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getToBinCode())
                    .lsmd037(isLotControlEnabled? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("004")
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
                    .lsmdsite("1") //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
            passiveMapper.insertTransferMiddleTable(Lsmdt.builder()
                    .lsmdTId(UUID.randomUUID().toString().toUpperCase()) //主键
                    .lsmd001(barcode)      //条码编号
                    .lsmd002(dto.getFromWarehouseCode())      //仓库
                    .lsmd003(dto.getFromBinCode())//库位
                    .lsmd004(lotNumber)//批号
                    .lsmd005(dto.getMatchQty())//异动数量
                    .lsmd006(-1)//异动类型
                    .lsmd007("")//来源单号
                    .lsmd008("0")
                    .lsmd010("0")
                    .lsmd013("admin")//PDA操作人代号
                    .lsmd014(DateUtil.format(date,"yyyy-MM-dd"))//扫描日期
                    .lsmd015(DateUtil.format(date,"HH:mm:ss.SSS"))//时间
                    .lsmd019("Y")//扣账资料否
                    .lsmd024(dto.getUnitCode())//异动数量单位
                    .lsmd025(BigDecimal.ONE)//异动与库存单位换算率
                    .lsmd026(PASSIVE_TRANSFER_APPID)//终端设备编号
                    .lsmd027(PASSIVE_TRANSFER_APPID+PASSIVE_TRANSFER_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"))//APP申请串号
                    .lsmd028(PASSIVE_TRANSFER_APPMODULE)//APP申请所属模组
                    .lsmd029("N")//ERP异动码
                    .lsmd030(dto.getRemark())//备注
                    .lsmd031("")//条码异动类型
                    .lsmd033(UUID.randomUUID().toString().trim().replace("-",""))//APP申请序列号
                    .lsmd036(dto.getFromBinCode())
                    .lsmd037(isLotControlEnabled? lotNumber : "")
                    .lsmd038("")
                    .lsmd039("004")
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
                    .lsmdsite(shjg) //营运据点
                    .lsmdstus("Y") //状态码
                    .build()
            );
        }
        //提交时填写调出的收货机构，als设置；填写调入的收货机构，单身资料
        return new MiddleReturnDto("",PASSIVE_TRANSFER_APPID,PASSIVE_TRANSFER_APPID+PASSIVE_TRANSFER_APPMODULE+WMS_APPVERSION+DateUtil.format(date,"yyMMddHHmmss"),"zhilink",shjg);
    }

    @Override
    public Result<List<UnfinishedProjectsVo>> getUnfinishedProjects(UnfinishedProjectsDto dto) {
        String ProjectName = dto.getProjectName();
        List<UnfinishedProjectsVo> list =  passiveMapper.getUnfinishedProjects(ProjectName);
        return Result.ok(list);
    }

    @Override
    public JSONObject PassiveTransferSubmit(MiddleReturnDto middleReturnDto) {
        System.out.println("提交");
        EtenSession etenSession = new EtenSession(WMS_SUBMIT_KEY,WMS_TYPE,
                new SessionHost(WMS_HOST_PROD,WMS_HOST_IP,WMS_HOST_LANG,WMS_HOST_ACCT, middleReturnDto.getTimestamp(), ""),
                new SessionService(WMS_SERVICE_PROD,PASSIVE_TRANSFER_NAME,WMS_SERVICE_IP,WMS_SERVICE_ID),
                new SessionDatakey(WMS_ENTID,middleReturnDto.getWmsCompanyid()),
                new SessionPayload(new StdData(new ScanToStorageParameter(
                        //几种组合：1621*1621、1600*1621单身资料；1600*1600、1621*1600 als单据
                        "","","","","","","","","","","","","","1600",
                        new ScanToStorageParameterData(
                                "1621", middleReturnDto.getDocNo(), middleReturnDto.getDocNo(), "","","","","","","","","","","","",""
                        ),
                        new ScanToStorageParameterHost(
                                WMS_HOST_ACCT, middleReturnDto.getTimestamp(), middleReturnDto.getAppid(),PASSIVE_TRANSFER_APPMODULE,WMS_APPVERSION,PASSIVE_TRANSFER_NAME,WMS_ENTID,middleReturnDto.getWmsCompanyid(),middleReturnDto.getErpRemark(),"0",""
                        )
                ))));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
        System.out.println(httpEntity.getBody());
        JSONObject result = JSON.parseObject(response);
        return result;
    }

}

