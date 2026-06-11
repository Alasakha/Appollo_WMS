package com.mgkj.controller;

import com.mgkj.common.result.Result;
import com.mgkj.dto.IHeadDTO;
import com.mgkj.dto.JiayanDto;
import com.mgkj.dto.Pojo;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Cmsmw;
import com.mgkj.entity.Invmb;
import com.mgkj.entity.Qmsmg;
import com.mgkj.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin // 解决跨域
@RestController
@RequestMapping("/api/Qmsmg")
@Api(tags = "检验前端控制器")
public class QmsmgController {

    @Resource
    private IHeadService iHeadService;
    @Resource
    private SFCTEService sfcteService;
    @Resource
    private QmsmgService qmsmgService;
    @Resource
    private InvmbService invmbService;
    @Resource
    private CmsmwService cmsmwService;

    @PostMapping("/update")
    @ApiOperation("修改品号检验项目")
    public Result update(@RequestBody Qmsmg qmsmg){
        return this.qmsmgService.update(qmsmg);
    }

    @GetMapping("/delete")
    @ApiOperation("通过品号和检验编号删除品号检验项目")
    public Result delete(String mg002,String mg003){
        return this.qmsmgService.delete(mg002,mg003);
    }

    @PostMapping("/deleteList")
    @ApiOperation("通过品号和检验编号批量删除品号检验项目")
    public Result deleteList(@RequestBody List<JiayanDto> list){
        return this.qmsmgService.deleteList(list);
    }

    @GetMapping("/queryXq")
    @ApiOperation("通过品号和检验编号查询详细信息,便于修改")
    public Result<Qmsmg> queryXq(String mg002,String mg003){
        return this.qmsmgService.queryXq(mg002,mg003);
    }

    @PostMapping("queryInvmb")
    @ApiOperation("分页查询品号信息")
    public Result<Map<String,Invmb>> queryInvmb(@RequestBody PojoDto pojoDto){
        return invmbService.query(pojoDto);
    }

    @PostMapping("queryCmsmw")
    @ApiOperation("分页查询工艺信息")
    public Result<Map<String,Cmsmw>> queryCmsmw(@RequestBody PojoDto pojoDto){
        return cmsmwService.query(pojoDto);
    }

    @PostMapping("addqmsmg")
    @ApiOperation("新增检验项目")
    public Result addqmsmg(@RequestBody List<Qmsmg> qmsmgList){
       return this.qmsmgService.insert(qmsmgList);
    }

    @PostMapping("queryIPage")
    @ApiOperation("分页查询品号检验项目")
    public Result<Map<String,Qmsmg>> queryIPage(@RequestBody PojoDto pojo){
        return this.qmsmgService.queryPage(pojo);
    }

    @PostMapping("test")
    @ApiOperation("测试修改")
    public Result test(String code){
        return this.sfcteService.test(code);
    }

    @ApiOperation("检验单通过品号进行查询")
    @PostMapping("/queryList")
    public Result query(@RequestBody Pojo pojo){
        return this.sfcteService.queryById(pojo);
    }

    @ApiOperation("新增检验结果")
    @PostMapping("/add")
    public Result add(@RequestBody IHeadDTO iHeadDTO){
        System.out.println(iHeadDTO.toString());
        iHeadService.add(iHeadDTO);
        return Result.ok();
    }

    @ApiOperation("Excel表格数据导入接口")
    @PostMapping("/getExcels")
    public Result getExcels(@RequestParam(value = "files") MultipartFile file,HttpServletResponse response) throws Exception {
       return qmsmgService.getExcels2(file,response);
    }

    @ApiOperation("检验单详情删除")
    @GetMapping("/del")
    public Result del(Integer id){
        return iHeadService.del(id);
    }
}
