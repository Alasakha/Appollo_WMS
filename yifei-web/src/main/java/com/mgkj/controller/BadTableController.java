package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.entity.BadInfoTable;
import com.mgkj.entity.BadTable;
import com.mgkj.service.BadTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:20
 * @Description: 不良信息 相关接口
 */
@Slf4j
@RestController
@RequestMapping("/api/bad")
@Api(tags = "不良信息相关接口")
public class BadTableController {
    @Resource
    private BadTableService badTableService;

    @ApiOperation("不良信息添加")
    @PostMapping("/add")
    public Result add(@RequestBody BadTable badTable){
        // System.out.println(sfcte.toString());
        log.info(badTable.toString());
        return badTableService.add(badTable);
    }

    @ApiOperation("不良信息删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return badTableService.delete(id);
    }

    @ApiOperation("不良信息修改")
    @PostMapping("/update")
    public Result update(@RequestBody BadTable badTable)
    {
        log.info(badTable.toString());
        return badTableService.update(badTable);
    }

    @ApiOperation("不良信息查看全部")
    @GetMapping("selectAll")
    public Result<List<BadTable>> selectAll(){
        return badTableService.selectAll();
    }

    @ApiOperation("不良信息查看")
    @GetMapping("select")
    public Result select( BadTable badTable){
        log.info(badTable.toString());
        return badTableService.select(badTable);
    }

}
