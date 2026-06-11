package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.entity.CMSME;
import com.mgkj.service.CmsMeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cmsme")
@Api(tags = "部门信息相关接口")
public class CmsMeController {

    @Resource
    private CmsMeService cmsMeService;

    @GetMapping("/getAll")
    @ApiOperation("查询部门信息")
    public Result<List<CMSME>> getAll(){
        return this.cmsMeService.getAll();
    }
}
