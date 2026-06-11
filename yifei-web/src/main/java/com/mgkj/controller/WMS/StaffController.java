//package com.mgkj.controller.WMS;
//
//
//import com.mgkj.dto.StaffDetailDto;
//import com.mgkj.dto.StaffDto;
//import com.mgkj.result.Result;
//import com.mgkj.service.StaffService;
//import com.mgkj.vo.StaffDetailVo;
//import com.mgkj.vo.StaffVo;
//import com.mgkj.vo.StraffGroupVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @author yyyjcg
// * @date 2023/12/27
// * @Description
// */
//@Slf4j
//@RestController
//@Api(tags = "员工管理相关接口")
//public class StaffController {
//    @Autowired
//    private StaffService staffService;
//
//    @ApiOperation("获取人员信息")
//    @PostMapping("/getAllStaffInfo")
//    public Result getAllPurchaseInfo(@RequestBody StaffVo staffVo){
//        List<StaffDto> staffLists = staffService.getAllStaffInfo(staffVo);
//        return Result.ok(staffLists);
//    }
//    @ApiOperation("获取组别人员信息")
//    @PostMapping("/getGroupStaffInfo")
//    public Result getGroupStaffInfo(@RequestBody StraffGroupVo straffGroupVo){
//        List<StaffDto> staffLists = staffService.getGroupStaffInfo(straffGroupVo);
//        return Result.ok(staffLists);
//    }
//    @ApiOperation("获取人员详细信息")
//    @PostMapping("/getStaffDetailInfo")
//    public Result getStaffDetailInfo(@RequestBody StaffDetailVo staffDetailVo){
//        List<StaffDetailDto> staffLists = staffService.getStaffDetailInfo(staffDetailVo);
//        return Result.ok(staffLists);
//    }
//
//
//}
