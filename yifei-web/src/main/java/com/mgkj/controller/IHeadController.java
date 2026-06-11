package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.dto.IHeadDTO;
import com.mgkj.dto.IHeadPageDTO;
import com.mgkj.dto.Pojo;
import com.mgkj.entity.PageResult;
import com.mgkj.service.IHeadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/ihead")
@Api(tags = "检验结果相关接口")
public class IHeadController {

    @Resource
    private IHeadService iHeadService;

    @ApiOperation("新增检验")
    @PostMapping("/add")
    public Result add(@RequestBody IHeadDTO iHeadDTO){
        System.out.println(iHeadDTO.toString());
        iHeadService.add(iHeadDTO);
        return Result.ok("成功");
    }

    @ApiOperation("查看检验详情")
    @GetMapping ("/all")
    public Result<IHeadDTO> getAll(Integer uid){
        IHeadDTO iHeadDTO = iHeadService.getAll(uid);
        return Result.ok(iHeadDTO);
    }

    @ApiOperation("查看检验详情(UDF05为Y) 通过单号")
    @GetMapping ("/allId")
    public Result<IHeadDTO> allId(Integer uid){
        IHeadDTO iHeadDTO = iHeadService.allId(uid);
        return Result.ok(iHeadDTO);
    }

    @ApiOperation("查看检验详情(UDF05为Y) 通过品号")
    @PostMapping ("/allIds")
    public Result<List<IHeadDTO>> allIds(@RequestBody Pojo pojo){
        List<IHeadDTO> list = iHeadService.allIds(pojo);
        return Result.ok(list);
    }

    @ApiOperation("查看详情列表")
    @GetMapping("/alllist")
    public Result<List<IHeadDTO>> getAlllist(@RequestParam("ids") List<Integer> ids){
        List<IHeadDTO> list = iHeadService.getAlllList(ids);
        return Result.ok(list);
    }

    @ApiOperation("分页获取单头详细信息")
    @GetMapping("/page")
    public Result<PageResult> page(IHeadPageDTO iHeadPageDTO){
        PageResult pageResult  = iHeadService.page(iHeadPageDTO);
        return Result.ok(pageResult);
    }

}
