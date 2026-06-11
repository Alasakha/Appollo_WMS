package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.entity.BadInfoTable;
import com.mgkj.service.BadInfoTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2024/01/02/15:20
 * @Description:    单据不良信息 相关接口
 */
@Slf4j
@RestController
@RequestMapping("/api/badinfo")
@Api(tags = "单据不良信息相关接口")
public class BadInfoTableController {

    @Resource
    private BadInfoTableService badInfoTableService;

    @ApiOperation("单据不良信息添加")
    @PostMapping("/add")
    public Result add(@RequestBody List<BadInfoTable> badInfoTables){
        // System.out.println(sfcte.toString());
        log.info(badInfoTables.toString());
        return badInfoTableService.add(badInfoTables);
    }

    @ApiOperation("单据不良信息删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return badInfoTableService.delete(id);
    }

    @ApiOperation("单据不良信息修改")
    @PostMapping("/update")
    public Result update(@RequestBody BadInfoTable badInfoTable)
    {
        log.info(badInfoTable.toString());
        return badInfoTableService.update(badInfoTable);
    }

    @ApiOperation("单据不良信息查看全部")
    @GetMapping("selectAll")
    public Result<List<BadInfoTable>> selectAll(){
        return badInfoTableService.selectAll();
    }

    @ApiOperation("单据不良信息查看")
    @GetMapping("select")
    public Result select( BadInfoTable badInfoTable){
        log.info(badInfoTable.toString());
        return badInfoTableService.select(badInfoTable);
    }

}
