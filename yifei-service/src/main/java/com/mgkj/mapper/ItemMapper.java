package com.mgkj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author 逆水寒
* @description 针对表【ITEM(通用品号信息/CHT/通用品號資料/ENU/General Item Data)】的数据库操作Mapper
* @createDate 2025-02-25 14:06:22
* @Entity com.mgkj.entity.Item
*/
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    @Select("SELECT ISNULL(MAX(CAST(W.BIN_CONTROL AS INT)), 0) FROM ITEM I " +
            "LEFT JOIN ITEM_PLANT IP ON IP.ITEM_ID = I.ITEM_BUSINESS_ID " +
            "LEFT JOIN WAREHOUSE W ON W.WAREHOUSE_ID = IP.INBOUND_WAREHOUSE_ID " +
            "WHERE I.ITEM_CODE = #{itemCode}")
    int selectBinControl(String itemCode);
}




