//package com.mgkj.service;
//
//
//
//import com.mgkj.common.result.Result;
//
//import java.util.List;
//
///**
// * @author yyyjcg
// * @date 2024/1/25
// * @Description
// */
//public interface PrintLineService {
//    //获取编码要素
//    Result listCodeElement(CodeElement codeElement, Integer pageNum, Integer pageSize);
//    //新增编码要素
//    Result insertCodeElement(CodeElement codeElement);
//    //删除编码要素
//    Result deleteCodeElement(String uuid);
//    //修改编码要素
//    Result updateCodeElement(CodeElement codeElement);
//
//
//    //获取编码分隔符
//    Result listPrintSeparator();
//    //修改编码分隔符
//    Result updatePrintSeparator(PrintSeparator printSeparator);
//
//
//    //获取编码规则
//    Result listCodeRule(CodingRuleDto codingRule , Integer pageNum, Integer pageSize);
//    //添加编码规则
//    Result insertCodeRule(CodingRule codingRule);
//    //删除编码规则
//    Result deleteCodeRule(String uuid);
//    //跟新编码规则
//    Result updateCodeRule(CodingRule codingRule);
//
//    //获取编码规则详情
//    Result listCodeRuleDetail(String codeRuleId);
//    //添加编码规则详情
//    Result insertCodeRuleDetail(List<CodeRuleDetail> list);
//    //删除编码规则详情
//    Result deleteCodeRuleDetail(String uuid);
//    //更新编码规则详情
//    Result updateCodeRuleDetail(CodeRuleDetail codeRuleDetail);
//
//    Result getBarcodeByDocNoItemCode(List<ListBarcodeDto> list);
//
//    Result keepItemBarcode(List<ItemInfo> itemInfo);
//
//    Result insertBarcode(List<BarcodeDto> list);
//
//    Result insertItemBarcode(ItemInfo itemInfo);
//
//    Result updateItemBarcode(ItemInfo itemInfo);
//
//    Result ListBarcode(ListBarcodeDto dto);
//
//    Result giveUpBarcode(String uuid);
//
//    Result ListItemBarcode(ItemInfo itemInfo, int pageNum, int pageSize);
//
//    Result listBarcode(ListBarcodeDto dto,int pageNum,int pageSize);
//
//    String getLotNoFromBarcode(String barcode,String itemCode);
//}
