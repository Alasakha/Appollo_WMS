package com.mgkj.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.entity.FinishedGoodsInboundDetail;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2024/3/8
 * @Description
 */
public interface MoService {
    MiddleReturnDto insertMoInStorageMiddleTable(List<MoInStorageSubmitDto> dtoList, String createBy);

    JSONObject MoInStorageSubmit(MiddleReturnDto middleReturnDto, String createBy);

    //                  *************工单发料************
    //工单发料-获取工单简洁信息

    Result ListMoSimpleInfo(MoSimpleDto moSimpleDto);
    //工单发料-获取工单汇总信息
    Result ListMoCollectInfo(String docNo);
    //工单发料-获取工单详细信息
    Result<List<MoDetailVo>> getMoDetailInfo(MoDetailDto moDetailDto);
    //工单发料-提交中间表
    MiddleReturnDto insertMiddleTable(List<MoIssueSubmitDto> moIssueSubmitDto, String createBy);
    //工单发料-提交
    JSONObject MoIssueSubmit(MiddleReturnDto middleReturnDto, String createBy);


    //                  *************领料下架************
    //领料下架-简洁信息
    Result ListIssueReceiptSimpleInfo(IssueReceiptSimpleDto issueReceiptSimpleDto);

    //领料下架-汇总信息
    Result<IssueReceiptCollectVo> ListIssueReceiptCollectInfo(String docNo);
    //领料下架-同步中间表
    MiddleReturnDto insertIssueReceiptMiddleTable(List<String> list, String docNo, String createBy);


    //*****************************************************ABL装配生产整车入库*****************************************************************//
    MiddleReturnDto insertABlLjZpMoInStorageMiddleTable(List<MoLjzpInStorageSubmitDto> dtoList);

    MiddleReturnDto insertABlLjZpMiddleTable(List<MoLjZpIssueSubmitDto> moIssueSubmitDto, String createBy);



    //*****************************************************ABL装配生产整车入库*****************************************************************//






    //领料下架-提交
    JSONObject IssueReceiptSubmit(MiddleReturnDto middleReturnDto, String createBy);

    //                  *************入库上架************
    //入库上架-简洁信息
    Result ListMoShelvesSimpleInfo(MoShelvesSimpleDto moShelvesSimpleDto);
    MiddleReturnDto insertMoInStorageShelvesMiddleTable(List<MoInStorageShelvesDto> dtoList);
    JSONObject MoInStorageShelvesSubmit(MiddleReturnDto middleReturnDto);


    //                *************工单退料************
    //工单退料-简洁信息
    Result ListMoReturnSimpleInfo(MoReturnSimpleDto moReturnSimpleDto);

    Result GetMoReturnDocByFrameNo(MoReturnSimpleDto moReturnSimpleDto);

    //工单退料-汇总信息
    Result ListMoReturnCollectInfo(MoReturnSimpleDto moReturnSimpleDto);


    //                *************退料上架************
    Result<List<IssueReceiptReturnSimpleVo>> ListIssueReceiptReturnSimpleInfo(IssueReceiptReturnSimpleDto dto);

    Result<List<IssueReceiptReturnVo>> ListIssueReceiptReturnCollectInfo(String docNo);

    Result<List<IssueReceiptDetailVo>> ListIssueReceiptDetailInfo(IssueReceiptDeatailDto issueReceiptDeatailDto);

    MiddleReturnDto insertMoReturnMiddleTable(List<MoReturnSubmitDto> list);

    JSONObject MoReturnSubmit(MiddleReturnDto middleReturnDto);

    JSONObject MoInStorageDto(MoInStorageDto dto);

    JSONObject getMoInStorageDetail(String docNo);


    int updateMoInStorageCode(List<MoInStorageSubmitDto> dtoList);


    Result<List<MoShelvesDetailVo>> ListMoShelvesDetailInfo(String docNo);

    MiddleReturnDto insertIssueReceiptReturnMiddleTable(List<MoIssueReceiptReturnSubmitDto> list);

    JSONObject IssueReceiptReturnSubmit(MiddleReturnDto middleReturnDto);

    Result<PageInfo<MoCheckHeader>> ListMoStorageCheck(MoStorageCheckDto dto);

    Result<List<MoCheck>> ListMoStorageCheckAll(String checkDocNo);

    Result UpdateMoStorageCheck(MoCheck dto);


    JSONObject ListMoStorageApplySimpleInfo(MoStorageApplySimpleDto moStorageApplySimpleDto);

    JSONObject ListMoStorageApplyDetailInfo(MoStorageApplyDetailDto moStorageApplyDetailDto);

    List<MoLjzpInStorageSubmitDto> selectLjMo(String barcode);

    MiddleReturnDto ABlLjZpMiddleTable(List<InvBarcodeOperation> operations, String docNo);

    MiddleReturnDto insertMoInStorageMiddleTableNew(List<FinishedGoodsInboundDetail> currentList);
}
