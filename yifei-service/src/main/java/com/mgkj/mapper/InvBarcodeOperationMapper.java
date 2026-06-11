package com.mgkj.mapper;

import com.mgkj.entity.InvBarcodeOperation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 逆水寒
* @description 针对表【inv_barcode_operation】的数据库操作Mapper
* @createDate 2025-04-17 15:35:48
* @Entity com.mgkj.entity.InvBarcodeOperation
*/
@Mapper
public interface InvBarcodeOperationMapper extends BaseMapper<InvBarcodeOperation> {


    InvBarcodeOperation selectByid(@Param("id")String id);

    @Update("UPDATE inv_barcode_operation SET status_code = '1' WHERE id = #{id}")
    boolean updateByid(@Param("id") String id);

    List<InvBarcodeOperation> queryNotSubimtByTypeAndCreateBy(@Param("chagType") int chagType, @Param("createBy") String createBy);

    List<InvBarcodeOperation> invBarcodeOperationListByIds(@Param("idList") List<String> idList);

    List<InvBarcodeOperation> selectByIdList(@Param("ids") List<String> ids);
}




