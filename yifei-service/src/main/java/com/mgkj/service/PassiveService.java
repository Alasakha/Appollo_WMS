package com.mgkj.service;

import com.alibaba.fastjson.JSONObject;
import com.mgkj.common.result.Result;
import com.mgkj.dto.*;
import com.mgkj.vo.PassiveEmployeeInfoVo;
import com.mgkj.vo.TransferBarcodeVo;
import com.mgkj.vo.UnfinishedProjectsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-08 17:44
 * @Version 1.0
 */


public interface PassiveService {
    MiddleReturnDto PassiveToStorageInsertMiddleTable(List<PassiveToStorageDto> list);

    JSONObject PassiveToStorageSubmit(MiddleReturnDto middleReturnDto);
    JSONObject ABLPassiveToStorageSubmit(MiddleReturnDto middleReturnDto,String typeCode);

    MiddleReturnDto PassiveOutStorageInsertMiddleTable(List<PassiveOutStorageDto> list);

    JSONObject ABLPassiveOutStorageSubmit(MiddleReturnDto middleReturnDto,String typeCode, String createBy);
    JSONObject PassiveOutStorageSubmit(MiddleReturnDto middleReturnDto);

    MiddleReturnDto PassiveTransferInsertMiddleTable(List<PassiveTransferDocSubmitDto> list);

    JSONObject PassiveTransferSubmit(MiddleReturnDto middleReturnDto);

    Result<List<TransferBarcodeVo>> getTransferBarcodeInfo(PassiveTransferBarcodeDto dto);

    Result<List<PassiveEmployeeInfoVo>> getEmployeeInfo(PassiveEmployeeInfoDto dto);




//    ========================================ABL无源=====================================================
    MiddleReturnDto ABLPassiveToStorageInsertMiddleTable(List<PassiveToStorageDto> list);

    MiddleReturnDto ABLPassiveOutStorageInsertMiddleTable(List<PassiveOutStorageDto> list, String createBy);

    MiddleReturnDto ABLPassiveTransferInsertMiddleTable(List<PassiveTransferDocSubmitDto> list);

    Result<List<UnfinishedProjectsVo>> getUnfinishedProjects(UnfinishedProjectsDto dto);
}
