package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgkj.dto.MoQtyInfo;
import com.mgkj.entity.MOCTA;
import com.mgkj.vo.WorkOrderOvertimeVo;
import com.mgkj.vo.WorkOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/16:05
 * @Description: 工单单头 mapper
 */
@Mapper
public interface MOCTAMapper extends BaseMapper<MOCTA> {

    /**
     * 根据 工单单别 单号 获取工单单头详情
     */
    @DS("demo")
    @Select("select  * from MOCTA where TA001 = #{TA001} and TA002 = #{TA002}")
    MOCTA getByTA001WithTA002(String TA001, String TA002);

    @DS("demo")
    @Select("select  * from MOCTA where RTRIM(TA001)+'-'+RTRIM(TA002) = #{TA001TA002}")
    MOCTA getByTA001TA002(@Param("TA001TA002") String TA001TA002);

    @DS("demo")
    @Select("SELECT COUNT(*) FROM " +
            "MOCTA LEFT JOIN CMSMD " +
            "ON TA021=MD001 LEFT JOIN PURMA " +
            "ON MA001=TA032 " +
            "WHERE TA011<>'Y' " +
            "AND TA011<>'y' " +
            "AND TA013='Y' " +
            "AND TA010<CONVERT(CHAR(8),GETDATE(),112)")
    Integer getOverdueNum();

    @DS("demo")
    @Select("SELECT ISNULL(ISNULL(MD002,MA002),'') workCenter,TA003 beginDate,TA001 workSeparate,TA002 workNumber,RTRIM(TA006) productArticle,TA034 productName,TA035 productSpecification,CAST(TA015 AS REAL) expectedOutput,CAST(TA017 AS REAL) storedItems,TA009 expectedStart,TA010 expectedEnd " +
            "FROM MOCTA LEFT JOIN CMSMD " +
            "ON TA021=MD001 LEFT JOIN PURMA " +
            "ON MA001=TA032 " +
            "WHERE TA011<>'Y' " +
            "AND TA011<>'y' " +
            "AND TA013='Y' " +
            "AND TA010<CONVERT(CHAR(8),GETDATE(),112) " +
            "ORDER BY ISNULL(ISNULL(MD002,MA002),'') desc")
    List<WorkOrderVo> getOverdueDetails();

    @DS("demo")
    @Select("SELECT ISNULL(ISNULL(MD002,MA002),'') workCenter,COUNT(*) 'number'" +
            "FROM MOCTA LEFT JOIN CMSMD " +
            "ON TA021=MD001 LEFT JOIN PURMA " +
            "ON MA001=TA032 " +
            "WHERE TA011<>'Y' " +
            "AND TA011<>'y' " +
            "AND TA013='Y' " +
            "AND TA010<CONVERT(CHAR(8),GETDATE(),112) " +
            "GROUP BY ISNULL(ISNULL(MD002,MA002),'') " +
            "ORDER BY ISNULL(ISNULL(MD002,MA002),'') desc")
    List<WorkOrderVo> getOverdueInfo();

    @DS("demo")
    @Select("SELECT ISNULL(ISNULL(m.MD002, ma.MA002),'') workCenter,TA001 single,TA002 singleNumber,TA003 beginDate, TA001 workSeparate, TA002 workNumber, RTRIM(TA006) productArticle, TA034 productName, TA035 productSpecification, CAST(TA015 AS REAL) expectedOutput, CAST(TA017 AS REAL) storedItems, TA009 expectedStart, TA010 expectedEnd " +
            "FROM MOCTA t " +
            "LEFT JOIN CMSMD m ON t.TA021 = m.MD001 " +
            "LEFT JOIN PURMA ma ON ma.MA001 = t.TA032 " +
            "WHERE t.TA011<>'Y' " +
            "AND t.TA011<>'y' " +
            "AND t.TA013='Y' " +
            "AND t.TA010<CONVERT(CHAR(8),GETDATE(),112) " +
            "AND ISNULL(ISNULL(m.MD002, ma.MA002),'') = #{workCenter} " +
            "ORDER BY t.TA009")
    IPage<WorkOrderVo> getOverdueDetailsByWorkCenter(Page<WorkOrderVo> page, @Param("workCenter") String workCenter);

    @DS("demo")
    @Select("SELECT COUNT(*) 'number'" +
            "FROM MOCTA LEFT JOIN CMSMD " +
            "ON TA021=MD001 LEFT JOIN PURMA " +
            "ON MA001=TA032 " +
            "WHERE TA011<>'Y' " +
            "AND TA011<>'y' " +
            "AND TA013='Y' " +
            "AND TA010<CONVERT(CHAR(8),GETDATE(),112) " +
            "GROUP BY ISNULL(ISNULL(MD002,MA002),'') " +
            "HAVING ISNULL(ISNULL(MD002,MA002),'') = #{workCenter} " +
            "ORDER BY ISNULL(ISNULL(MD002,MA002),'') desc " )
    String getOverdueCount(String workCenter);

    @DS("demo")
    List<WorkOrderOvertimeVo> getOverdueDetailsInfo(String workCenter,String single, String singleNumber);

    IPage<MOCTA> selectAll(Page<MOCTA> page);

    BigDecimal getTb005ByTb001Tb002AndProductId(@Param("product") String ljProduct, @Param("docNo") String docNo);

    @Select("SELECT PLAN_QTY planQty,COMPLETED_QTY completedQty FROM MO WHERE DOC_NO = #{docNo}")
    MoQtyInfo getMoQtyByDocNo(@Param("docNo") String docNo);
}
