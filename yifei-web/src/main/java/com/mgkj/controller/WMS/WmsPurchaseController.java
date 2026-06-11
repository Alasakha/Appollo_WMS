//package com.mgkj.controller.WMS;
//
//import com.mgkj.entity.PurchaseOrder;
//import com.mgkj.entity.PurchaseOrderD;
//import com.mgkj.result.Result;
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
// * @date 2024/1/26
// * @Description
// */
//@Slf4j
//@RestController
//@Api(tags = "智物流管理相关接口")
//@RequestMapping("/common")
//public class WmsPurchaseController {
//
//    @Autowired
//    private WmsPurchaseService wmspurchaseService;
//
//    @ApiOperation("erp采购单->wms采购单")
//    @GetMapping("/getErpPurchaseOrder")
//    @ResponseBody
//    public Result getErpPurchaseOrder(){
//        List<PurchaseOrder> list = wmspurchaseService.getErpPurchaseOrder();
//        System.out.println("-----------------");
//        System.out.println(list);
//        wmspurchaseService.setWmsPurchaseOrder(list);
//        List<PurchaseOrderD> list2 = wmspurchaseService.getErpPurchaseOrderD();
//        return Result.ok(list);
//    }
//
////    @ApiOperation("获取工厂信息")
////    @GetMapping("/listWarehouse")
////    @ResponseBody
////    public Result listWarehouse(){
////        List<WarehouseInfoVo> info = wmspurchaseService.listWarehouse();
////        return null;
////    }
//    @ApiOperation("通用-获取部门信息")
//    @GetMapping("/ListDepartmentInfo")
//    public Result ListDepartmentInfo(String department){
//        return wmspurchaseService.ListDepartmentInfo(department);
//    }
//
//    @ApiOperation("通用-获取员工信息")
//    @GetMapping("/ListEmployeeInfo")
//    public Result ListEmployeeInfo(String employee){
//        return wmspurchaseService.ListEmployeeInfo(employee);
//    }
//
//    @ApiOperation("通用-获取仓库信息")
//    @GetMapping("/ListWarehouseInfo")
//    public Result ListWarehouseInfo(String warehouse){return wmspurchaseService.ListWarehouseInfo(warehouse);}
//
//    @ApiOperation("通用-获取库位信息")
//    @GetMapping("/ListBinInfo")
//    public Result ListBinInfo(String warehouseId){return wmspurchaseService.ListBinInfo(warehouseId);}
//
//    @ApiOperation("是否启用批号")
//    @GetMapping("/isUseLotNo/{itemCode}")
//    public Result isUseLotNo(@PathVariable String itemCode){
//        return wmspurchaseService.isUseLotNo(itemCode);
//    }
//
//
//}
