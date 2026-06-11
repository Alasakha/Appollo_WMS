package com.mgkj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.SFCTA;
import com.mgkj.entity.SFCTE;
import com.mgkj.vo.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/15/15:12
 * @Description:    派工/报工 service 接口
 */

public interface SFCTEService extends IService<SFCTE> {

    Result quxiao(String code);

    Result queryById(Pojo pojo);

    Result test(String code);

    Result<Map<String, MocVo>> queryPageList(Pojo pojo);

    Result<SFCTE> detailPB(String TE001,String TE002);

    Result deleteDb(String TE001,String TE002, String T003);

    Result<Map<String,SFCTE>> queryRejects(SelectDto selectDto);

    Result<List<SfctaMoctaTwoVo>> querySfCtaIPag(PojoDto2 pojoDto);

    /**
     * 查询工单工艺进行报工
     * @param TA001
     * @param TA002
     * @return
     */
    List<SFCTA> querySfCta(String TA001, String TA002);

    /**
     * 新增派工单
     * @param sfcte
     * @return
     */
    Result<List<String>> insertSfcte(List<SFCTE> sfcte);

    /**
     * 查询工单信息进度
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<Map<String, SfctaMoctaVo>> querySfCtAMoCtaList(Integer pageNum, Integer pageSize,String TA001,String TA002,String TA006);

    /**
     * 通过工单中的单头单身工艺顺序查询报工单数据
     * @param odd
     * @param Single
     * @param Gx
     * @return
     */
    List<SFCTE> querySfCte(String odd,String Single,String Gx);

    /**
     * 通过工单中的单头单身工艺顺序查询派工单数据
     * @param odd
     * @param Single
     * @param Gx
     * @return
     */
    List<SFCTE> querySfCteS(String odd,String Single,String Gx);

    /**
     * 分页条件查询报工单明细信息
     * @param selectDto
     * @return
     */
    Result<Map<String,SFCTE>> queryIPage(SelectDto selectDto);


    /**
     * 分页条件查询报工单明细信息
     * @param selectDto
     * @return
     */
    Result<Map<String,SFCTE>> queryIPageS(SelectDto selectDto);


    /**
     * 根据派工共单单别 和 派工单单号 获取 派工详细信息列表
     * @param single    单别
     * @param odd       单号
     * @return
     */
    List<SFCTE> selectListByDbDh(String single, String odd);

    List<SFCTE> selectListStaff(String code,String dh,String ph);

    /**
     * 新增 报工单
     *
     * @param sfcte
     */
    Result add(SFCTE sfcte);

    /**
     * 新增 报工单
     * @param udf06 报工类型
     */
    List<SFCTE> qualityFrom(String page,String pageSize, String udf06, String start_time,String end_time);

    /**
     * 根据员工编号查询报工单
     */
    List<SFCTE> querySfCteByTe004(QuerySfCteByTe004DTO querySfCteByTe004DTO);

    int qualityFromCount(String udf06, String start_time, String end_time);

    Map<String, List<ReportVo>> qualityFromDown(String udf06, String start_time, String end_time,String type);

    Map<String, List<ReportVo>> qualityFromUp(String udf06, String start_time, String end_time,String type);

    List<SFCTE> qualityFromTemp(String udf06,String page, String pageSize, String start_time, String end_time);

    int qualityFromTempCount(String udf06,String start_time, String end_time);

    Map<String, List<ReportVo>> qualityFromTempUp(String udf06, String start_time, String end_time,String type);

    Map<String, List<ReportVo>> qualityFromTempDown(String udf06,String start_time, String end_time,String type);

    /**
     * 查询工单报工
     * @param queryBgDto
     * @return
     */
    PageInfo<SfcteVo> queryGd(QueryBgDto queryBgDto);

    /**
     * 根据工单 单别 单号 序号 删除报工单
     * @param odd
     * @param single
     * @param xh
     * @return
     */
    CompletableFuture<Result> deleteBg(String single, String odd, String xh);

    Result sfcteadd(ReportDto reportDto);

    List<BgNumVo> queryBgCount(QueryBgNumDto queryBgNumDto);

    Result endPg(EndPgDto dto);

//    Map<String, Integer> qualityFromTempSum(String udf06, Map<String,Map<String,Integer>> map);
}
