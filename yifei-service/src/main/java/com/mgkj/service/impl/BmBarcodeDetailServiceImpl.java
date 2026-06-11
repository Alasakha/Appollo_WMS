package com.mgkj.service.impl;



import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.ListPrintBarcodeDto;
import com.mgkj.dto.PrintBarcodeDocCjmDto;
import com.mgkj.dto.PrintBarcodeDocDto;
import com.mgkj.dto.PrintBarcodeDto;
import com.mgkj.entity.BmBarcodeDetail;
import com.mgkj.entity.Mo;
import com.mgkj.entity.MoD;
import com.mgkj.mapper.BmBarcodeDetailMapper;
import com.mgkj.mapper.MoMapper;
import com.mgkj.service.BmBarcodeDetailService;
import com.mgkj.vo.ListPrintBarcodeVO;
import com.mgkj.vo.WarehouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @description 针对表【bm_barcode_detail(条码管理表)】的数据库操作Service实现
 * @createDate 2024-11-14 10:19:56
 */
@Service
public class BmBarcodeDetailServiceImpl extends ServiceImpl<BmBarcodeDetailMapper, BmBarcodeDetail>
        implements BmBarcodeDetailService {
    @Autowired
    private BmBarcodeDetailMapper bmBarcodeDetailMapper;
    @Autowired
    private MoMapper moMapper;




    @Override
    @DSTransactional
    public Result insertPrintBarcodeByJobNo(List<PrintBarcodeDocDto> list) {

        DateTime date = DateUtil.date();
        // 用于存储每一个物料的dto的信息
        HashMap<String, Object> itemMap = new HashMap<>();
        List<String> itemNoList = new ArrayList<>();
        for (PrintBarcodeDocDto dto : list) {
            //关闭对应工单的工艺管理
            bmBarcodeDetailMapper.updateByJobNo(dto.getJobNo());

            BmBarcodeDetail bmBarcodeDetail = new BmBarcodeDetail();
            bmBarcodeDetail.setId(UUID.randomUUID().toString());
            bmBarcodeDetail.setOrgNo("1");
            bmBarcodeDetail.setItemNo(dto.getItemNo());
            bmBarcodeDetail.setLotDate(date);
            bmBarcodeDetail.setBarType(2);
            bmBarcodeDetail.setLotDesc(dto.getCaterIa1HeatNo()); //批号说明(炉号)
            bmBarcodeDetail.setSourceNo(dto.getJobNo());
            bmBarcodeDetail.setBarcodeWeight(dto.getBlankWeight());//条码重量(净重)
            bmBarcodeDetail.setQty(dto.getQty());//数量
            bmBarcodeDetail.setSnBarcodeStatus(10);//SN码流转状态
            bmBarcodeDetail.setCombinationLotNo(dto.getLotNo());//批号
            bmBarcodeDetail.setLotAtt03(dto.getHeatTreatmentNumber());//热处理品号
            bmBarcodeDetail.setLotAtt04(dto.getLotNumberOfTheMaterial());//材料批号
            bmBarcodeDetail.setLotAtt05(dto.getContainerNumber());//箱号
            bmBarcodeDetail.setLotAtt06(dto.getItemDesc());//规格
            bmBarcodeDetail.setStandardCol11(dto.getBlankUnitWeight());//单位净重
            bmBarcodeDetail.setStandardCol12(dto.getBoxWeight());//容器重量
            bmBarcodeDetail.setCreateFlag(0);
            bmBarcodeDetail.setCreateBy(dto.getCreateBy());
            bmBarcodeDetail.setCreateDate(DateTime.now());
            bmBarcodeDetail.setUpdateDate(DateTime.now());
            bmBarcodeDetail.setItemName(dto.getItemName());
            bmBarcodeDetail.setVersion(0L);
            bmBarcodeDetail.setTenantsId(1L);
            bmBarcodeDetail.setUpdateBy("");

            // 查询同一品号在同一天的最大标签序号
            Integer maxLabelSeqObj = bmBarcodeDetailMapper.getMaxLabelSeq(dto.getItemNo(), date);
            int maxLabelSeq;
            if (maxLabelSeqObj == null) {
                maxLabelSeq = 0;
            } else {
                maxLabelSeq = maxLabelSeqObj;
            }

            //标签号
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDateTime = localDateTime.format(formatter);

//        根据是否当天已生成过标签来确定起始序号
            int startSeq;
            if (maxLabelSeq == 0) {
                startSeq = 0;
            } else {
                startSeq = maxLabelSeq;
            }
            // 标签序号这里应该
            int newLabelSeq = startSeq + 1;

            if (dto.getBarCode() == null && dto.getBarCode().isEmpty()) {
                bmBarcodeDetail.setBarcode(dto.getItemNo() + "#" + formattedDateTime + "#" + String.format("%04d", newLabelSeq));
            } else {
                bmBarcodeDetail.setBarcode(dto.getBarCode());
            }
            System.out.println(bmBarcodeDetail.getBarcode());
            bmBarcodeDetailMapper.insert(bmBarcodeDetail);
            System.out.println(bmBarcodeDetail.getWarehouseCode() + "," + bmBarcodeDetail.getBinCode() + "," + bmBarcodeDetail.getWarehouseName());

            String labelNo = dto.getItemNo() + "#" + formattedDateTime + "#" + String.format("%04d", newLabelSeq);
            itemNoList.add(labelNo);
        }
        itemMap.put("labelNo",itemNoList);
        return Result.ok(itemMap).message("插入标签成功");

    }

    @Override
    @DSTransactional
    public Result insertPrintBarcodeByCjm(List<PrintBarcodeDocCjmDto> list) {
        DateTime date = DateUtil.date();
        // 用于存储每一个物料的dto的信息
        HashMap<String, Object> itemMap = new HashMap<>();
        List<String> itemNoList = new ArrayList<>();
        for (PrintBarcodeDocCjmDto dto : list) {
            //关闭对应工单的工艺管理
            bmBarcodeDetailMapper.updateByJobNo(dto.getJobNo());

            BmBarcodeDetail bmBarcodeDetail = new BmBarcodeDetail();
            bmBarcodeDetail.setId(UUID.randomUUID().toString());
            bmBarcodeDetail.setOrgNo("1");
            bmBarcodeDetail.setUnitNo("111");

            String s = bmBarcodeDetailMapper.selectTA021(dto.getJobNo());
            if (s.startsWith("1")) {
                bmBarcodeDetail.setSnWarehouseNo("1015");
            } else if (s.startsWith("2")) {
                bmBarcodeDetail.setSnWarehouseNo("2016");
            }
            bmBarcodeDetail.setItemNo(dto.getItemNo());
            bmBarcodeDetail.setLotDate(date);
            bmBarcodeDetail.setBarType(2);
            bmBarcodeDetail.setLotDate(date);
            bmBarcodeDetail.setLotDesc(dto.getCaterIa1HeatNo()); //批号说明(炉号)
            bmBarcodeDetail.setSourceNo(dto.getJobNo());
            bmBarcodeDetail.setBarcodeWeight(dto.getBlankWeight());//条码重量(净重)
            bmBarcodeDetail.setQty(dto.getQty());//数量
            bmBarcodeDetail.setSnBarcodeStatus(10);//SN码流转状态
            bmBarcodeDetail.setCombinationLotNo(dto.getLotNo());//批号
            bmBarcodeDetail.setLotAtt03(dto.getHeatTreatmentNumber());//热处理品号
            bmBarcodeDetail.setLotAtt04(dto.getLotNumberOfTheMaterial());//材料批号
            bmBarcodeDetail.setLotAtt05(dto.getContainerNumber());//箱号
            bmBarcodeDetail.setLotAtt06(dto.getItemDesc());//规格
            bmBarcodeDetail.setStandardCol11(dto.getBlankUnitWeight());//单位净重
            bmBarcodeDetail.setStandardCol12(dto.getBoxWeight());//容器重量
            bmBarcodeDetail.setCreateFlag(0);
            bmBarcodeDetail.setCreateBy(dto.getCreateBy());
            bmBarcodeDetail.setCreateDate(DateTime.now());
            bmBarcodeDetail.setUpdateDate(DateTime.now());
            bmBarcodeDetail.setItemName(dto.getItemName());
            bmBarcodeDetail.setVersion(0L);
            bmBarcodeDetail.setTenantsId(1L);
            bmBarcodeDetail.setUpdateBy("");

//            // 查询同一品号在同一天的最大标签序号
//            Integer maxLabelSeqObj = bmBarcodeDetailMapper.getMaxLabelSeq(dto.getItemNo(), date);
//            int maxLabelSeq;
//            if (maxLabelSeqObj == null) {
//                maxLabelSeq = 0;
//            } else {
//                maxLabelSeq = maxLabelSeqObj;
//            }
//
            //标签号
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDateTime = localDateTime.format(formatter);
//
////        根据是否当天已生成过标签来确定起始序号
//            int startSeq;
//            if (maxLabelSeq == 0) {
//                startSeq = 0;
//            } else {
//                startSeq = maxLabelSeq;
//            }
//            // 标签序号这里应该
//            int newLabelSeq = startSeq + 1;
//
            bmBarcodeDetail.setBarcode(dto.getBarcode());

            System.out.println(bmBarcodeDetail.getBarcode());
            bmBarcodeDetailMapper.insert(bmBarcodeDetail);
            System.out.println(bmBarcodeDetail.getWarehouseCode() + "," + bmBarcodeDetail.getBinCode() + "," + bmBarcodeDetail.getWarehouseName());

            String labelNo = dto.getItemNo() + "#" + formattedDateTime;
            itemNoList.add(labelNo);
        }
        itemMap.put("labelNo",itemNoList);
        return Result.ok().message("插入标签成功");
    }

    @Override
    public Result listBarcodeInfo(ListPrintBarcodeDto dto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ListPrintBarcodeVO> resultList = bmBarcodeDetailMapper.listBarcodeInfo(dto);
        PageInfo<ListPrintBarcodeVO> pageInfo = new PageInfo<>(resultList);
        return Result.ok(pageInfo).message("查询成功");
    }



}





