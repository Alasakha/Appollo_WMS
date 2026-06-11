package com.mgkj.mapper;

import com.mgkj.common.result.Result;
import com.mgkj.dto.PassiveTransferDocSubmitDto;
import com.mgkj.dto.UnfinishedProjectsDto;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.Lsmdt;

import com.mgkj.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2024-12-08 18:08
 * @Version 1.0
 */

@Mapper
public interface PassiveMapper {

    void insertMiddleTable(Lsmdt lsmdt);

    void insertTransferMiddleTable(Lsmdt build);
    @Select("SELECT ISNULL(combination_lot_no,'') FROM bm_barcode_detail WHERE barcode = #{barcode}")
    String selectLotNumber(@Param("barcode") String barcode);

    List<TransferBarcodeVo> getTransferBarcodeInfo(@Param("barcode") String barcode);


    void UpdateBarcodeQty(@Param("barcode") String barcode, @Param("transferQty") Double transferQty);

    void batchUpdateBarcodeQty(@Param("qtyList")List<PassiveTransferDocSubmitDto> qtyList);

    List<PassiveEmployeeInfoVo> getEmployeeInfo(@Param("employeeName") String employeeName);

    BarCodeDetailVo getBarCodeDetailByBarCode(@Param("barcode")  String barcode);

    BigDecimal getQtySum(@Param("barcode") String barcode, @Param("chatType") Integer chatType);

    List<InvBarcodeOperationVo> queryInvBarcodeOperationVo(@Param("createBy")  String createBy);


    void deleteInvBarcodeOperationByCreateBy(@Param("createBy") String createBy,@Param("chagType") Integer chagType);

    List<InvBarcodeOperation> queryTransNotSubimtByTypeAndCreateBy(@Param("chagType") int chagType, @Param("createBy") String createBy,@Param("barcode")  String barcode);
    List<InvBarcodeOperation> queryNotSubimtByTypeAndCreateBy(@Param("chagType") int chagType, @Param("createBy") String createBy);

    @Update("UPDATE inv_barcode_operation SET status_code = '1' WHERE id = #{id}")
    boolean updateByid(@Param("id") String id);

    int updateBmBarcodeDetailOfQty(@Param("barcode") String barcode,@Param("qty") double qty);

    List<InvBarcodeOperationVo> queryToOrOutInvBarcodeOperationVo(@Param("createBy")  String createBy,@Param("chagType")  Integer chagType);

    List<PassiveOutStorageTypeDocVo> passiveOutStorageTypeDoc(@Param("shjg")String shjg);

    List<PassiveOutStorageTypeDocVo> PassiveToStorageTypeDoc(@Param("shjg")String shjg);

    List<String> selectByCreateBy(String createBy);

    List<UnfinishedProjectsVo> getUnfinishedProjects(String projectName);

    String selectItemIdByItemCode(@Param("itemCode") String itemCode);

    List<TransactionDocDVo> selectTransactionDocDByDocNo(@Param("docNo") String docNo);

    int updateTransactionDocD(@Param("transactionDocDId")String transactionDocDId, @Param("carType")String carType, @Param("projectId")String projectId);

    void updateRemark(@Param("docNo") String docNo, @Param("remark") String remark);
}
