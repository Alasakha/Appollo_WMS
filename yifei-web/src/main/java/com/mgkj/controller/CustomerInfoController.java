package com.mgkj.controller;


import com.mgkj.common.result.Result;
import com.mgkj.dto.SelectDto;
import com.mgkj.service.CopmaService;
import com.mgkj.vo.CustomVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
@RestController
@RequestMapping("/api/Customer")
@Api(tags = "客户信息")
public class CustomerInfoController {

    @Resource
    private CopmaService copmaService;

    @GetMapping("/getDetailedCustomerInfo/{MA001}")
    @ApiOperation("获取客户详细信息")
    public Result getDetailedCustomerInfo(@PathVariable String MA001) {
        CustomVo customVo = copmaService.getDetailedCustomerInfo(MA001);
        return Result.ok(customVo);
    }

    @PostMapping("/getCustomerInfoByTerm")
    @ApiOperation("根据条件查询客户信息")
    public Result getCustomerInfoByTerm(@RequestBody SelectDto selectDto) {
        List<CustomVo> resultList = copmaService.getCustomerInfoByTerm(selectDto);
        return Result.ok(resultList);
    }
}

