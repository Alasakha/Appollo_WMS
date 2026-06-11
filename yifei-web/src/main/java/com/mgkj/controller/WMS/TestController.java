//package com.mgkj.controller.WMS;
//
//import cn.hutool.json.JSONUtil;
//import com.mgkj.entity.session.EtenSession;
//import com.mgkj.entity.session.ParameterDataList;
//import com.mgkj.result.Result;
//import com.mgkj.service.TestService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @author yyyjcg
// * @date 2024/1/11
// * @Description
// */
//@RestController
//@RequestMapping("/test")
//@Api("测试相关接口")
//public class TestController {
//
//    @Autowired
//    private TestService testService;
//
//    @ApiOperation("测试e10触发器传品号参数-get")
//    @GetMapping("/e10trigger")
//    public void TriggerParameter(@RequestParam("ITEM_BUSINESS_ID") String itemBusinessId, @RequestParam("ITEM_CODE") String itemCode) {
//        System.out.println("接收到的参数值为: " + itemCode + "," + itemBusinessId);
//
//    }
//
//
////    @ApiOperation("测试e10触发器传品号参数")
////    @PostMapping("/e10trigger")
////    public void TriggerParameter(@RequestParam String jsonData) {
////
////            System.out.println(jsonData);
////        }
//
//
////    @ApiOperation("测试e10触发器传品号参数")
////    @PostMapping("/e10trigger")
////    public void TriggerParameter(@RequestParam JSONObject jsonObject){
////
////        // 获取jsonobject参数，并toJSONString
////        String s = JSONArray.toJSONString(jsonObject);
////        String s1 = JSON.toJSONString(jsonObject);
////        System.out.println(s);
////        System.out.println(s1);
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
////        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
////        System.out.println(JSONUtil.toJsonStr(etenSession));
////        RestTemplate restTemplate = new RestTemplate();
////        String response = restTemplate.postForObject(SERVICE_IP_PORT+"/Restful/invokeSrv", httpEntity, String.class);
////        JSONObject result = JSON.parseObject(response);
////        System.out.println(result);
//
////    }
//
//    @ApiOperation("测试调wms的接口")
////    @PostMapping("/e10trigger")
//    public void WmsInterfaceTest(){
//
//        //1.设置请求头
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
//        //2.设置请求体
//        WmsDto wmsDto = new WmsDto();
////        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
////        System.out.println(JSONUtil.toJsonStr(etenSession));
////        RestTemplate restTemplate = new RestTemplate();
////        String response = restTemplate.postForObject("http://175.6.98.248:8015"+"/api/YiWuDW/AddProduct", httpEntity, String.class);
////        JSONObject result = JSON.parseObject(response);
////        System.out.println(result);
//
//    }
//
//
//
//
//
//    @GetMapping("/hello")
////    @PreAuthorize("hasAnyAuthority('admin')")
//    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
//    private Result testMethod(){
//        return Result.ok().message("hello");
//    }
//
//
//    @GetMapping("/sessionTest")
//    private Result sessionTest(){
////        ParameterDataList list = new ParameterDataList();
////        EtenSession etenSession = new EtenSession().setSessionPayload(new SessionPayload().setStd_data(new StdData().setParameter(new Parameter().setData(new ArrayList<>().add(new)););););
//        ParameterDataList list = new ParameterDataList();
//        EtenSession etenSession = new EtenSession();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
//        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSession), httpHeaders);
//        System.out.println(JSONUtil.toJsonStr(etenSession));
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.postForObject("http://wt4sqcpk.dongtaiyuming.net/Restful/invokeSrv", httpEntity, String.class);
//        return Result.ok(response).message("hello");
//    }
//
//
//
////    @GetMapping("/TestSubmitDto")
////    private Result TestSubmitDto(@RequestBody EtenSubmitDto etenSubmitDto){
////        EtenSubmitSession etenSubmitSession = new EtenSubmitSession(etenSubmitDto.getKey(), etenSubmitDto.getType(),
////                new SessionHost("APP","","zh_CN",etenSubmitDto.getAcct(), etenSubmitDto.getTimestamp(), etenSubmitDto.getRemark()),
////                new SessionService("T100","als.a001.upload","","topprd"),
////                new SessionDatakey(etenSubmitDto.getEntId(), etenSubmitDto.getCompanyId()),
////                new SessionSubmitPayload(
////                        new SubmitStdData(new SubmitParameter(etenSubmitDto.getWo_no(), etenSubmitDto.getUnit_no(), etenSubmitDto.getItem_no(), etenSubmitDto.getSubop_no(), etenSubmitDto.getOp_seq(), etenSubmitDto.getUndefect_qty(), etenSubmitDto.getDefect_qty(), etenSubmitDto.getReport_min(),
////                                etenSubmitDto.getClassNo(), etenSubmitDto.getMachineNo(), etenSubmitDto.getGroupNo(), etenSubmitDto.getGroupNo(), etenSubmitDto.getNoTime(), etenSubmitDto.getDocTypeNo()
////                                ,new SubmitParameterData(etenSubmitDto.getDocTypeNo(), etenSubmitDto.getDocNo(), etenSubmitDto.getDocNo(), etenSubmitDto.getSeq(), etenSubmitDto.getOrderSeq(), etenSubmitDto.getMeasureValue(), etenSubmitDto.getString(), etenSubmitDto.getDecision(), etenSubmitDto.getStatu(), etenSubmitDto.getDefectReason(), etenSubmitDto.getDefectReasonName(), etenSubmitDto.getDefectReasonQty(), etenSubmitDto.getQty(), etenSubmitDto.getQcNo(), etenSubmitDto.getRemark(), etenSubmitDto.getInspectionItem(), etenSubmitDto.getWarehouseNo(), etenSubmitDto.getItemNo()),
////                                new SubmitParameterHost(etenSubmitDto.getAcct(), etenSubmitDto.getTimestamp(), etenSubmitDto.getAppid(), etenSubmitDto.getAppmodule(), etenSubmitDto.getAppversion(), etenSubmitDto.getName(), etenSubmitDto.getEntId(), etenSubmitDto.getCompanyId(), etenSubmitDto.getRemark(), etenSubmitDto.getGiftFlag())))));
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("Content-Type","text/plain; charset=utf-8");
////        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.toJsonStr(etenSubmitSession), httpHeaders);
////        RestTemplate restTemplate = new RestTemplate();
////        String response = restTemplate.postForObject("http://wt4sqcpk.dongtaiyuming.net/Restful/invokeSrv", httpEntity, String.class);
////        return Result.ok(response);
////    }
//
//}
//
