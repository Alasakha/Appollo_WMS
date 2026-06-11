package com.mgkj.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.SFCTA;
import com.mgkj.entity.SFCTE;
import com.mgkj.service.SFCTEService;
import com.mgkj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/15/15:09
 * @Description:
 */
@Slf4j
@Api(tags = "派工-报工相关接口")
@RestController
@RequestMapping("/api/sfcte")
public class SFCTEController {

    @Resource
    private SFCTEService sfcteService;

    @ApiOperation("报工单条件-员工编号查询")
    @PostMapping("/querySfCteByTe004")
    public Result<List<SFCTE>> querySfCteByTe004(@RequestBody QuerySfCteByTe004DTO querySfCteByTe004DTO) {
        List<SFCTE> sfctes = sfcteService.querySfCteByTe004(querySfCteByTe004DTO);
        return Result.ok(sfctes);
    }

    @ApiOperation("结束派工单")
    @PostMapping("/endPg")
    public Result endPg(@RequestBody EndPgDto dto) {
        return sfcteService.endPg(dto);
    }

    @ApiOperation("添加报工单")
    @PostMapping("/add")
    public Result add(@RequestBody SFCTE sfcte){
        return sfcteService.add(sfcte);
    }

    @ApiOperation("查询工单报工")
    @Transactional
    @PostMapping("/queryGd")
    public Result<PageInfo<SfcteVo>> queryGd(@RequestBody QueryBgDto queryBgDto){
        PageInfo<SfcteVo> sfcteList = this.sfcteService.queryGd(queryBgDto);
        return Result.ok(sfcteList);
    }

    @ApiOperation("删除报工单")
    @DeleteMapping("/deleteBg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "single", value = "报工单别", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "odd", value = "报工单号", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "xh", value = "报工序号", dataType = "String", dataTypeClass = String.class)
    })
    public Result deleteBg(@RequestParam(value = "single",required = true) String single,
                                              @RequestParam(value = "odd",required = true) String odd,
                                              @RequestParam(value = "xh",required = true) String xh) throws ExecutionException, InterruptedException {
        return this.sfcteService.deleteBg(single,odd,xh).get();
    }


    @ApiOperation("按日期区间查询个人产量")
    @PostMapping("/queryBgCount")
    public Result<List<BgNumVo>> queryBgCount(@RequestBody QueryBgNumDto queryBgNumDto){
        List<BgNumVo> sfcteList = sfcteService.queryBgCount(queryBgNumDto);
        return Result.ok(sfcteList);
    }

    @ApiOperation("报工单分页条件查询需要检验的项目")
    @PostMapping("/queryIpAge")
    public Result<Map<String, MocVo>> queryIpAge(@RequestBody Pojo pojo) {
        return this.sfcteService.queryPageList(pojo);
    }

    @GetMapping("/quxiao")
    @ApiOperation("取消检验")
    public Result quxiao(String code){
        return this.sfcteService.quxiao(code);
    }

    @ApiOperation("派/报工查询中的详情接口")
    @GetMapping("/detailPB")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TE001", value = "派/报工单单别", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "TE002", value = "派/报工单单号", dataType = "String", dataTypeClass = String.class)
    })
    public Result<SFCTE> detailB(String TE001,String TE002){
        return this.sfcteService.detailPB(TE001,TE002);
    }

    @ApiOperation("派/报工单删除接口")
    @PostMapping("/deleteDb")
    public Result deleteDb(@RequestBody PojoDto pojoDto){
        return this.sfcteService.deleteDb(pojoDto.getSingle(),pojoDto.getOdd(), pojoDto.getXh());
    }

    @ApiOperation("分页查询有不良品的报工单")
    @PostMapping("/queryRejects")
    public  Result<Map<String,SFCTE>> queryRejects(@RequestBody SelectDto selectDto){
        return sfcteService.queryRejects(selectDto);
    }

    @ApiOperation("分页查询工单工艺所有信息")
    @PostMapping("/querySfCtaIPag")
    public Result<List<SfctaMoctaTwoVo>> querySfCtaIPag(@RequestBody PojoDto2 pojoDto){
        Result<List<SfctaMoctaTwoVo>> listResult = this.sfcteService.querySfCtaIPag(pojoDto);
        return listResult;
    }

    @ApiOperation("通过单别单号查找工单工艺(详情接口)")
    @GetMapping("/querySfCta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "single", value = "单别", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "odd", value = "单号", dataType = "String", dataTypeClass = String.class)
    })
    public Result<List<SFCTA>> querySfCta(@RequestParam(value = "single",required = true) String single,
                                          @RequestParam(value = "odd",required = true) String odd){
        List<SFCTA> sfctas = sfcteService.querySfCta(single, odd);
        return Result.ok(sfctas);
    }

    @ApiOperation("新增派工单(单个或多个)")
    @PostMapping("/insertSfcte")
    public Result<List<String>> insertSfcte(@RequestBody List<SFCTE> sfcte){
        return this.sfcteService.insertSfcte(sfcte);
    }

    @ApiOperation("查找派工单详情 H5页面报工查找派工单进行报工用")
    @GetMapping("/selectListByDbDh")
    public Result<List<SFCTE>> selectListByDbDh(@RequestParam(value = "single",required = true) String single,
                                   @RequestParam(value = "odd",required = true) String odd){
        List<SFCTE> sfcteList = sfcteService.selectListByDbDh(single,odd);
        return Result.ok(sfcteList);
    }

    @ApiOperation("通过员工编号查询当前需要报工的派工单")
    @GetMapping("/selectListStaff/{code}")
    public Result selectListStaff(@PathVariable String code,String dh,String ph){
        List<SFCTE> sfcteList = sfcteService.selectListStaff(code,dh,ph);
        return Result.ok(sfcteList);
    }

    @ApiOperation("新阳光添加报工单")
    @Transactional
    @PostMapping("/sfcteadd")
    public Result sfcteadd(@RequestBody ReportDto reportDto){
        return sfcteService.sfcteadd(reportDto);
    }


    @ApiOperation("查询工单报工进度信息")
    @GetMapping("/queyrOdd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TA001", value = "工单单别", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "TA002", value = "工单单号", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "TA006", value = "工单品号", dataType = "String", dataTypeClass = String.class)
    })
    public Result<Map<String,SfctaMoctaVo>> queryOdd(@RequestParam(value = "pageNum", defaultValue = "1" ,required = true) Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "10",required = true) Integer pageSize,
                                                     String TA001,String TA002,String TA006){
        Result<Map<String, SfctaMoctaVo>> mapResult = this.sfcteService.querySfCtAMoCtaList(pageNum, pageSize,TA001,TA002,TA006);
        return mapResult;
    }

    @ApiOperation("查询当前工单工艺的报工单数据")
    @GetMapping("/querySfCte")
    public Result<List<SFCTE>> querySfCte(@RequestParam(value = "single",required = true) String single,
                                          @RequestParam(value = "odd",required = true) String odd,
                                          @RequestParam(value = "gx",required = true) String gx){
        List<SFCTE> sfcteList = this.sfcteService.querySfCte(odd, single, gx);
        return Result.ok(sfcteList);
    }
    @ApiOperation("查询当前工单工艺的派工单数据")
    @GetMapping("/querySfCteS")
    public Result<List<SFCTE>> querySfCteS(@RequestParam(value = "single",required = true) String single,
                                           @RequestParam(value = "odd",required = true) String odd,
                                           @RequestParam(value = "gx",required = true) String gx){
        List<SFCTE> sfcteList = this.sfcteService.querySfCteS(odd, single, gx);
        return Result.ok(sfcteList);
    }

    @ApiOperation("Excel表格数据下载")
    @GetMapping("/getExcel")
    public Result getExcel(@RequestParam(value = "single",required = true) String single,
                           @RequestParam(value = "odd",required = true) String odd,
                           @RequestParam(value = "gx",required = true) String gx, HttpServletResponse response) throws IOException {
        //配置文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String datetime = sdf.format(new Date());
        String fileName = URLEncoder.encode("报工数据", "UTF-8") + datetime;
        //响应内容格式
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        List<SFCTE> sfcteList = this.sfcteService.querySfCte(odd,single,gx);
        if (sfcteList.isEmpty()){
            return Result.fail("暂无数据,无法下载Excel表格");
        }
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(response.getOutputStream(), SFCTE.class).sheet("下载excel").doWrite(sfcteList);
        return Result.ok();
    }

    @ApiOperation("查询所有报工单明细信息")
    @PostMapping("/queryIPage")
    public Result<Map<String,SFCTE>> queryIPage(@RequestBody SelectDto selectDto){
        return this.sfcteService.queryIPage(selectDto);
    }

    @ApiOperation("查询所有派工单明细信息")
    @PostMapping("/queryIPageS")
    public Result<Map<String,SFCTE>> queryIPageS(@RequestBody SelectDto selectDto){
        return this.sfcteService.queryIPageS(selectDto);
    }

    @ApiOperation("报工Excel表格数据下载")
    @PostMapping("/getExcelBg")
    public Result getExcelBg(@RequestBody List<SFCTE> sfcteList, HttpServletResponse response) throws IOException {
        //配置文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String datetime = sdf.format(new Date());
        String fileName = URLEncoder.encode("报工明细数据", "UTF-8") + datetime;
        //响应内容格式
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(response.getOutputStream(), SFCTE.class).sheet("下载excel").doWrite(sfcteList);
        return Result.ok();
    }

}
