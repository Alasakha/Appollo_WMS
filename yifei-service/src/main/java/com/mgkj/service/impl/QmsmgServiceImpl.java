package com.mgkj.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.common.result.Result;
import com.mgkj.exception.YiFeiException;
import com.mgkj.dto.JiayanDto;
import com.mgkj.dto.PojoDto;
import com.mgkj.dto.QmsmgDto;
import com.mgkj.entity.Cmsmw;
import com.mgkj.entity.Invmb;
import com.mgkj.entity.Qmsmg;
import com.mgkj.mapper.CmsmwMapper;
import com.mgkj.mapper.INVMBMapper;
import com.mgkj.mapper.QmsmgMapper;
import com.mgkj.service.QmsmgService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class QmsmgServiceImpl extends ServiceImpl<QmsmgMapper, Qmsmg> implements QmsmgService {

    @Resource
    private QmsmgMapper qmsmgMapper;
    @Resource
    private CmsmwMapper cmsmwMapper;
    @Resource
    private INVMBMapper invmbMapper;

    @Override
    public Result queryPage(PojoDto pojoDto) {
      try {
          Map<String,Object> map =new HashMap<>();
          IPage<Qmsmg> iPage = new Page<>(pojoDto.getCurrent(),pojoDto.getSize());
          QueryWrapper<Qmsmg> qmsmgQueryWrapper = new QueryWrapper<>();
          if (StringUtils.isNotBlank(pojoDto.getPh())){
              qmsmgQueryWrapper.like("MG002",pojoDto.getPh());
          }
          if (StringUtils.isNotBlank(pojoDto.getFs())){
              qmsmgQueryWrapper.eq("MG020",pojoDto.getFs());
          }
          if (StringUtils.isNotBlank(pojoDto.getGy())){
              qmsmgQueryWrapper.eq("MG008",pojoDto.getGy());
          }
          IPage<Qmsmg> qmsmgIPage = this.qmsmgMapper.selectPage(iPage, qmsmgQueryWrapper);
          map.put("page",qmsmgIPage.getPages());
          map.put("data",qmsmgIPage.getRecords());
          map.put("total",qmsmgIPage.getTotal());
          return Result.ok(map);
      }catch (Exception e){
          throw new YiFeiException(500,"分页发生异常");
      }
    }

    @Override
    @Transactional
    public Result insert(List<Qmsmg> qmsmgList) {
        try{
            qmsmgList.stream().forEach(arr -> {
                arr.setMG001("1"+SerialNumber(arr.getMG002()));
                arr.setMG003(SerialNumber(arr.getMG002()));
                this.qmsmgMapper.insert(arr);
            });
            return Result.ok("新增成功");
        }catch (Exception e){
            throw new YiFeiException(500,e.getMessage());
        }
    }

    @Override
    public Result update(Qmsmg qmsmg) {
        try{
            int update = this.qmsmgMapper.update(qmsmg, new QueryWrapper<Qmsmg>().eq("MG002", qmsmg.getMG002()).
                    eq("MG003", qmsmg.getMG003()).eq("MG001",qmsmg.getMG001()));
            if (update>0){
                return Result.ok("修改成功");
            }
            return Result.fail("修改失败");
        }catch (Exception e){
            throw new YiFeiException(500,"修改失败:"+e.getMessage());
        }
    }

    @Override
    public Result delete(String MG002, String MG003) {
        try{
            int delete = this.qmsmgMapper.delete(new QueryWrapper<Qmsmg>().eq("MG002",MG002).
                    eq("MG003", MG003));
            if (delete>0){
                return Result.ok("删除成功");
            }
            return Result.fail("删除失败");
        }catch (Exception e){
            throw new YiFeiException(500,"删除失败:"+e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result deleteList(List<JiayanDto> list) {
        try{
            assert list != null;
            list.forEach(arr -> {
                int delete = this.qmsmgMapper.delete(new QueryWrapper<Qmsmg>().eq("MG002",arr.getMg002()).
                        eq("MG003", arr.getMg003()));
            });
            return Result.ok("删除成功");
        }catch (Exception e){
            throw new YiFeiException(500,"删除失败:"+e.getMessage());
        }
    }

    @Override
    public Result<Qmsmg> queryXq(String MG002, String MG003) {
        try{
            Qmsmg qmsmg = this.qmsmgMapper.selectOne(new QueryWrapper<Qmsmg>().eq("MG002", MG002).
                    eq("MG003", MG003));
            return Result.ok(qmsmg);
        }catch (Exception e){
            throw new YiFeiException(500,"查询失败:"+e.getMessage());
        }
    }

//    @Override
//    @Transactional
//    public Result getExcels(MultipartFile file, HttpServletResponse response) throws IOException {
//        try{
//            InputStream inputStream = file.getInputStream();
//            List<Qmsmg> lists = new ArrayList<>();
//            EasyExcel.read(inputStream, Qmsmg.class, new PageReadListener<Qmsmg>(lists::addAll)).sheet().doRead();
//            if (!lists.isEmpty()){
//                lists.forEach((arr) -> {
//                    if (arr.getMG002()==null){
//                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行"+"品号不能为空");
//                    }
//                    if (arr.getMG008()==null){
//                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行"+"工艺编号不能为空");
//                    }
//                    arr.setMG001("1"+Integer.valueOf(SerialNumber(arr.getMG002()))+Integer.valueOf((lists.indexOf(arr)+1)));
//                    arr.setMG003(String.valueOf(Integer.valueOf(SerialNumber(arr.getMG002()))+Integer.valueOf((lists.indexOf(arr)+1))));
//                    Invmb querys = this.invmbMapper.querys(arr.getMG002());
//                    if (querys!=null){
//                        if (querys.getMB002()!=null){
//                            arr.setUDF10(querys.getMB002());
//                        }
//                        if (querys.getMB003()!=null){
//                            arr.setUDF11(querys.getMB003());
//                        }
//                    }else {
//                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行,"+arr.getMG002()+"品号错误,无法批量新增!") ;
//                    }
//                    Cmsmw querys1 = this.cmsmwMapper.querys(arr.getMG008());
////                    System.out.println("测试："+querys1);
//                    if (querys1!=null){
//                        if (querys1.getMW002()!=null){
//                            arr.setUDF09(querys1.getMW002());
//                        }
//                        if (querys1.getMW003()!=null){
//                            arr.setUDF08(querys1.getMW003());
//                        }
//                    }else {
//                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行,"+arr.getMG008()+"工艺编号错误,无法批量新增!") ;
//                    }
//                    if(arr.getMG011()!=null){
//                        if (arr.getMG011().equals("文本型")){
//                            arr.setMG011("4");
//                        }else {
//                            arr.setMG011("3");
//                        }
//                    }
//                    if(arr.getMG009()!=null){
//                        if (arr.getMG009().equals("计数")){
//                            arr.setMG009("1");
//                        }else {
//                            arr.setMG009("2");
//                        }
//                    }
//                    if(arr.getMG015()!=null){
//                        if (arr.getMG015().equals("依抽查基础")){
//                            arr.setMG015("1");
//                        }else {
//                            arr.setMG015("2");
//                        }
//                    }
//                });
//            }
//            boolean b = this.saveBatch(lists);
//            if (b){
//                return Result.ok("导入成功");
//            }
//            return Result.fail(lists);
////            return Result.ok(lists);
//        }catch (Exception e){
//            throw new YiFeiException(500,"Excel表格导入发生异常"+e.getMessage());
//        }
//    }

    @Override
    @Transactional
    public Result getExcels2(MultipartFile file, HttpServletResponse response) throws IOException {
        try{
            InputStream inputStream = file.getInputStream();
            List<QmsmgDto> lists = new ArrayList<>();
            EasyExcel.read(inputStream, QmsmgDto.class, new PageReadListener<QmsmgDto>(lists::addAll)).sheet().doRead();
            if (!lists.isEmpty()){
                lists.forEach((arr) -> {
                    if (arr.getMG002()==null){
                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行"+"品号不能为空");
                    }
                    if (arr.getMG008()==null){
                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行"+"工艺编号不能为空");
                    }
                    arr.setMG001("1"+Integer.valueOf(SerialNumber(arr.getMG002()))+String.valueOf(lists.indexOf(arr)+1));
                    Invmb querys = this.invmbMapper.querys(arr.getMG002());
                    if (querys!=null){
                        if (querys.getMB002()!=null){
                            arr.setUDF10(querys.getMB002());
                        }
                        if (querys.getMB003()!=null){
                            arr.setUDF11(querys.getMB003());
                        }
                    }else {
                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行,"+arr.getMG002()+"品号错误,无法批量新增!") ;
                    }
                    System.out.println("123465");
                    Cmsmw querys1 = this.cmsmwMapper.querys(arr.getMG008());
//                    System.out.println("测试："+querys1);
                    System.out.println("12456");
                    if (querys1!=null){
                        if (querys1.getMW002()!=null){
                            arr.setUDF09(querys1.getMW002());
                        }
                        if (querys1.getMW003()!=null){
                            arr.setUDF08(querys1.getMW003());
                        }
                    }else {
//                        throw new YiFeiException(500,"第"+lists.indexOf(arr)+"行,"+arr.getMG008()+"工艺编号错误,无法批量新增!") ;
                        arr.setUDF09(arr.getMG008());
                    }
                    if(arr.getMG009()!=null){
                        if (arr.getMG009().equals("计数")){
                            arr.setMG009("1");
                        }else {
                            arr.setMG009("2");
                        }
                    }
                });
            }
            List<Qmsmg> qmsmgList = new ArrayList<>();
            if (!lists.isEmpty()){
               lists.forEach(arr -> {
                   Qmsmg qmsmg =new Qmsmg();
                   BeanUtils.copyProperties(arr, qmsmg);
                   qmsmgList.add(qmsmg);
               });
            }
            boolean b = this.saveBatch(qmsmgList);
            if (b){
                return Result.ok("导入成功");
            }
            return Result.fail(qmsmgList);
//            return Result.ok(lists);
        }catch (Exception e){
            throw new YiFeiException(500,"Excel表格导入发生异常"+e.getMessage());
        }
    }

    public String SerialNumber(String ph) {
        QueryWrapper<Qmsmg> qmsmgQueryWrapper = new QueryWrapper<>();
//        qmsmgQueryWrapper.orderByDesc("MG003");
        qmsmgQueryWrapper.eq("MG002",ph);
        //查询最新的流水号
        List<Qmsmg> sCells = qmsmgMapper.selectList(qmsmgQueryWrapper);
        //用于保存获取最新的流水号
        String temp="";
        //用于拼接需要的流水号
        String serialNumber="";
        //保存最新的流水号
        if(!sCells.isEmpty()){
            temp=sCells.get(0).getMG003();
        }
        //判断数据库是否有数据
        if (!StringUtils.isEmpty(temp)){
            Integer integer=Integer.valueOf(temp.trim())+1;
            serialNumber=String.valueOf(integer);
        }else{
            serialNumber="1";
        }
        return serialNumber;
    }
}
