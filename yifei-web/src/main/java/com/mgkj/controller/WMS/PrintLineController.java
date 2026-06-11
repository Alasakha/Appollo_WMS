//package com.mgkj.controller.WMS;
//
//import com.mgkj.result.Result;
//import com.mgkj.service.PrintLineService;
//import com.mgkj.service.WmsPurchaseService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author yyyjcg
// * @date 2024/1/23
// * @Description
// */
//@Slf4j
//@RestController
//@Api(tags = "标签管理相关接口")
//public class PrintLineController {
//    @Autowired
//    private PrintLineService printLineService;
//    @Autowired
//    private WmsPurchaseService wmsPurchaseService;
//
//    @ApiOperation("获取编码要素")
//    @PostMapping("/listCodeElement/{pageNum}/{pageSize}")
//    public Result listCodeElement(@RequestBody CodeElement codeElement,
//                                  @PathVariable("pageNum")  Integer pageNum,
//                                  @PathVariable("pageSize") Integer pageSize){
//        if (pageSize == null){
//            pageSize = 10;
//        }
//        return printLineService.listCodeElement(codeElement,pageNum,pageSize);
//    }
//
//    @ApiOperation("新增编码要素")
//    @PostMapping("/insertCodeElement")
//    public Result insertCodeElement(@RequestBody CodeElement codeElement){
//        return printLineService.insertCodeElement(codeElement);
//    }
//
//    @ApiOperation("修改编码要素")
//    @PutMapping("/updateCodeElement")
//    public Result updateCodeElement(@RequestBody CodeElement codeElement){
//        return printLineService.updateCodeElement(codeElement);
//    }
//
//    @ApiOperation("删除编码要素")
//    @DeleteMapping("/deleteCodeElement")
//    public Result deleteCodeElement(String uuid){
//        return printLineService.deleteCodeElement(uuid);
//    }
//
//
//    @ApiOperation("获取编码分隔符")
//    @GetMapping("/listPrintSeparator")
//    public Result listPrintSeparator(){
//        return printLineService.listPrintSeparator();
//    }
//
//    @ApiOperation("修改编码分隔符")
//    @PutMapping("/updatePrintSeparator")
//    public Result updatePrintSeparator(@RequestBody PrintSeparator printSeparator){
//        return printLineService.updatePrintSeparator(printSeparator);
//    }
//
//
//    @ApiOperation("获取编码规则")
//    @PostMapping("/listCodeRule/{pageNum}/{pageSize}")
//    public Result listCodeRule(@RequestBody CodingRuleDto codingRule ,
//                               @PathVariable("pageNum") Integer pageNum,
//                               @PathVariable("pageSize") Integer pageSize){
//        return printLineService.listCodeRule(codingRule,pageNum,pageSize);
//    }
//
//    @ApiOperation("添加编码规则")
//    @PostMapping("/insertCodeRule")
//    public Result insertCodeRule(@RequestBody CodingRule codingRule){
//        return printLineService.insertCodeRule(codingRule);
//    }
//
//    @ApiOperation("删除编码规则")
//    @DeleteMapping("/deleteCodeRule")
//    public Result deleteCodeRule(String uuid){
//        return printLineService.deleteCodeRule(uuid);
//    }
//
//    @ApiOperation("修改编码规则")
//    @PutMapping("/updateCodeRule")
//    public Result updateCodeRule(@RequestBody CodingRule codingRule){
//        return printLineService.updateCodeRule(codingRule);
//    }
//
//    @ApiOperation("获取编码规则明细")
//    @GetMapping("/listCodeRuleDetail/{codeRuleUuid}")
//    public Result listCodeRuleDetail(@PathVariable("codeRuleUuid") String codeRuleUuid){
//        return printLineService.listCodeRuleDetail(codeRuleUuid);
//    }
//
//    @ApiOperation("新增编码规则明细")
//    @PostMapping("/insertCodeRuleDetail")
//    public Result insertCodeRuleDetail(@RequestBody List<CodeRuleDetail> list){
//        return printLineService.insertCodeRuleDetail(list);
//    }
//
//    @ApiOperation("删除编码规则明细")
//    @DeleteMapping("/deleteCodeRuleDetail")
//    public Result deleteCodeRuleDetail(String uuid){
//        return printLineService.deleteCodeRuleDetail(uuid);
//    }
//
//    @ApiOperation("修改编码规则明细")
//    @PutMapping("/updateCodeRuleDetail")
//    public Result updateCodeRuleDetail(@RequestBody CodeRuleDetail codeRuleDetail){
//        return printLineService.updateCodeRuleDetail(codeRuleDetail);
//    }
//
//    @ApiOperation("同步品号条码规则信息")
//    @GetMapping("/keepItemBarcode")
//    public Result keepItemBarcode(){
//        List<ItemInfo> itemInfo = wmsPurchaseService.getItemInfo();
//        return printLineService.keepItemBarcode(itemInfo);
//    }
//
//    @ApiOperation("维护品号条码规则信息-添加")
//    @PostMapping("/insertItemBarcode")
//    public Result insertItemBarcode(@RequestBody ItemInfo itemInfo){
//        return printLineService.insertItemBarcode(itemInfo);
//    }
//
//    @ApiOperation("维护品号条码规则信息-修改")
//    @PostMapping("/updateItemBarcode")
//    public Result updateItemBarcode(@RequestBody ItemInfo itemInfo){
//        return printLineService.updateItemBarcode(itemInfo);
//    }
//
//    @ApiOperation("维护品号条码规则信息-查询")
//    @PostMapping("/ListItemBarcode/{pageNum}/{pageSize}")
//    public Result ListItemBarcode(@RequestBody ItemInfo itemInfo,
//                                  @PathVariable("pageNum") int pageNum,
//                                  @PathVariable("pageSize") int pageSize){
//        return printLineService.ListItemBarcode(itemInfo,pageNum,pageSize);
//    }
//
//    @ApiOperation("插入条码信息")
//    @PostMapping("/insertBarcode")
//    public Result insertBarcode(@RequestBody List<BarcodeDto> list){
//        return printLineService.insertBarcode(list);
//    }
//
//    @ApiOperation("查询条码信息")
//    @PostMapping("/listBarcode/{pageNum}/{pageSize}")
//    public Result listBarcode(@RequestBody ListBarcodeDto dto,
//                              @PathVariable("pageNum") int pageNum,
//                              @PathVariable("pageSize") int pageSize){
//        return printLineService.listBarcode(dto,pageNum,pageSize);
//    }
//
//    @ApiOperation("条码信息弃用")
//    @GetMapping("/giveUpBarcode/{uuid}")
//    public Result giveUpBarcode(@PathVariable String uuid){
//        return printLineService.giveUpBarcode(uuid);
//    }
//
//    @ApiOperation("标签补打--查询")
//    @PostMapping("/ListBarcode")
//    public Result ListBarcode(@RequestBody ListBarcodeDto dto){
//        return printLineService.ListBarcode(dto);
//    }
//
//
//}
