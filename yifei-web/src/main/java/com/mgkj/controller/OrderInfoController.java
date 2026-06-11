package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.dto.SelectDto;
import com.mgkj.entity.Invmb;
import com.mgkj.entity.Invmc;
import com.mgkj.service.InvmcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : ssy
 * @date: 2024-01-08
 * @Description:
 */
@RestController
@Api(tags = "商品信息")
@RequestMapping("/api/order")
public class OrderInfoController {

    @Resource
    private InvmcService invmcService;

    @PostMapping("/getOrderInfoByTrim")
    @ApiOperation("根据条件查询客户信息")
    public Result getAllOrderInfo(@RequestBody SelectDto selectDto){
        List<Invmb> invmbs = invmcService.getOrderInfoByTrim(selectDto);
        return Result.ok(invmbs);
    }


}
