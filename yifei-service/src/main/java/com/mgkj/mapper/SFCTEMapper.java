package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.*;
import com.mgkj.entity.SFCTE;
import com.mgkj.entity.SfcteBo;
import com.mgkj.vo.*;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/15/15:11
 * @Description:    派工/报工Mapper
 */
@Mapper
public interface SFCTEMapper extends BaseMapper<SFCTE> {

    SFCTE queryById(Pojo pojo);

    /**
     * 临时测试检验项目查询
     * @param pojo
     * @return
     */
    List<SFCTE> queryPageTest(Pojo pojo);

    void updateYourEntity(String code);

    void updateBg(String code,String val);

    void updateBgs(String code,String val);

    void updateYourEntityS(String code);

    /**
     * 查询已派工数量
     * @param TE006
     * @param TE007
     * @param TE008
     * @return
     */
    @DS("dscsys")
    @Select("select ISNULL(SUM(TE011),0) AS num from SFCTE where TE001 = 'D420' AND TE006 = #{TE006} and TE007 = #{TE007} and TE008 = #{TE008} and TE023 = #{TE023}")
    Integer querySfCtePgNum(String TE006, String TE007, String TE008, String TE023);

    /**
     * 查询所有未完成生产的工单
     * @return
     */
    @DS("dscsys")
    List<SfctaMoctaVo> querySfCtAMoCtaList(String TA001,String TA002,String TA006);

    @Select("SELECT ISNULL(MAX(TD002),FORMAT(GETDATE(),'yyMMdd')+'000') AS td002 FROM SFCTD WHERE TD001 = #{td001} ")
    String selectTd002(String td001);


    @Select("SELECT ISNULL(SUM(TA010),NULL)  FROM SFCTA WHERE TA001=#{TA001} AND TA002=#{TA002} AND TA003 = #{TA003}")
    Long queryta010(String TA001,String TA002,String TA003);


    @Delete("DELETE FROM SFCTD WHERE TD001 = #{TD001} AND TD002 = #{TD002} ")
    Integer deletesfctd(String TD001,String TD002);


    @DS("dscsys")
    List<SfctaMoctaVo> querySfCtaIPag(PojoDto pojoDto);

    @Select("select MO_ROUTING_D_ID from SFCTA WHERE TA001=#{TE006} AND TA002=#{TE007} AND TA003=#{TE008}")
    String queryMOROUTINGDID(String TE006,String TE007,String TE008);

    @DS("dscsys")
    String getMw002ByMw001(@Param("MW001") String MW001);

    /**
     * 查询报工单
     * @return
     */
    @DS("dscsys")
    @Select("SELECT  * FROM SFCTE WHERE TE001 = 'D410' AND TE006 = #{odd} and TE007 = #{Single} and TE008 = #{Gx}")
    List<SFCTE> querySfCte(String odd,String Single,String Gx);


    /**
     * 查询工单
     * @return
     */
    @DS("dscsys")
    @Select("SELECT TA015 FROM MOCTA WHERE TA001 = #{odd} and TA002 = #{Single}")
    Long querymocta(String odd,String Single);

    /**
     * 查询派工单
     * @return
     */
    @DS("dscsys")
    @Select("SELECT  * FROM SFCTE WHERE TE001 = 'D420' AND TE006 = #{odd} and TE007 = #{Single} and TE008 = #{Gx}")
    List<SFCTE> querySfCteS(String odd,String Single,String Gx);

    /**
     * 根据员工编号查询报工单
     * @return
     */
    @DS("dscsys")
    List<SFCTE> querySfCteByTe004(QuerySfCteByTe004DTO querySfCteByTe004DTO);


    /**
     * 根据派工共单单别 和 派工单单号 获取 派工详细信息列表
     * @param single        单别
     * @param odd           单号
     * @return
     */
    @DS("dscsys")
    @Select("select *, (SELECT MW002 FROM CMSMW WHERE MW001 = TE009) AS name from SFCTE where TE001 = #{single} and TE002 = #{odd} order by TE003  ")
    List<SFCTE> getListByTE001WithTE002(String single, String odd);


    @DS("dscsys")
    List<SFCTE> selectListStaff(String code,String dh,String ph);

    /**
     * 查询工序报工总数量
     * @param TE006
     * @param TE007
     * @param TE008
     * @return
     */
    @DS("dscsys")
    @Select("select ISNULL(SUM(TE011),0) AS num from SFCTE where TE001 = 'D410' AND TE006 = #{TE006} and TE007 = #{TE007} and TE008 = #{TE008} ")
    Integer querySfCteNum(String TE006, String TE007, String TE008);

    @DS("dscsys")
    @Select("select * from SFCTE where TE001 = 'D420' AND TE006 = #{TE006} and TE007 = #{TE007} and TE008 = #{TE008}  order by TE002")
    List<SFCTE> gette001(String TE006, String TE007, String TE008);


    /**
     * 根据 单别生成年位为4为的单号
     * @param TD001 单别
     * @return
     */
    @DS("dscsys")
    String getNewTE002Year4(@Param("TD001") String TD001);

    /**
     * 保存报工单
     * @param saveSFCTE
     */
    @DS("dscsys")
    @Insert("insert into SFCTE " +
            "(COMPANY, CREATOR, USR_GROUP, CREATE_DATE, MODIFIER, MODI_DATE, FLAG, " +
            "TE001, TE002, TE003, TE004, TE005, TE006, TE007, TE008, TE009, TE010, " +
            "TE011, TE012, TE013, TE014, TE015, TE016, TE017, TE018, TE019, TE020, " +
            "TE021, TE022, TE023, TE024, TE025, TE026, TE027, TE028, TE029, TE030, " +
            "TE031, TE032, UDF01, UDF02, UDF03, UDF04, UDF05, UDF06, UDF51, UDF52, " +
            "UDF53, UDF54, UDF55, UDF56, UDF07, UDF08, UDF09, UDF10, UDF11, UDF12, " +
            "UDF57, UDF58, UDF59, UDF60, UDF61, UDF62) " +
            "values (#{COMPANY},#{CREATOR},#{USR_GROUP},#{CREATE_DATE},#{MODIFIER},#{MODI_DATE},#{FLAG}," +
            "#{TE001},#{TE002},#{TE003},#{TE004},#{TE005},#{TE006},#{TE007},#{TE008},#{TE009},#{TE010}," +
            "#{TE011},#{TE012},#{TE013},#{TE014},#{TE015},#{TE016},#{TE017},#{TE018},#{TE019},#{TE020}," +
            "#{TE021},#{TE022},#{TE023},#{TE024},#{TE025},#{TE026},#{TE027},#{TE028},#{TE029},#{TE030}," +
            "#{TE031},#{TE032},#{UDF01},#{UDF02},#{UDF03},#{UDF04},#{UDF05},#{UDF06},#{UDF51},#{UDF52}," +
            "#{UDF53},#{UDF54},#{UDF55},#{UDF56},#{UDF07},#{UDF08},#{UDF09},#{UDF10},#{UDF11},#{UDF12}," +
            "#{UDF57},#{UDF58},#{UDF59},#{UDF60},#{UDF61},#{UDF62})")
    void save(SFCTE saveSFCTE);

    /**
     * 根据 单别 单号 和序号 查询派工单详细信息
     * @param TE001
     * @param TE002
     * @param TE003
     * @return
     */
    @DS("dscsys")
    @Select("select * from SFCTE where TE001 = #{TE001} and TE002 = #{TE002} and TE003 = #{TE003}")
    SFCTE getByTE001WithTE002WithTE003(@Param("TE001") String TE001,@Param("TE002")String TE002,@Param("TE003") String TE003);

    @DS("dscsys")
    @Select("select ISNULL(SUM(UDF51)+SUM(UDF52)+SUM(UDF53)+SUM(TE011),0) from SFCTE where UDF09 = #{TE001} and UDF10 = #{TE002} and UDF11 = #{TE003}")
    BigDecimal getTE011WithTE001WithTE006WithTE007WithTE008(String TE001, String TE002, String TE003);

    @DS("dscsys")
    List<SFCTE> qualityFrom(@Param("page") String page,@Param("pageSize")String pageSize, @Param("UDF06") String udf06,@Param("start_time") String start_time, @Param("end_time") String end_time);

    @DS("dscsys")
    int qualityFromCount(@Param("UDF06")String udf06, @Param("start_time")String start_time, @Param("end_time")String end_time);

    @DS("dscsys")
    List<ReportVo> qualityFromDown(@Param("UDF06")String udf06, @Param("start_time")String start_time, @Param("end_time")String end_time,@Param("type") String type);

    @DS("dscsys")
    List<ReportVo> qualityFromUp(@Param("UDF06")String udf06, @Param("start_time")String start_time, @Param("end_time")String end_time,@Param("type") String type);

    @DS("dscsys")
    List<SFCTE> qualityFromTemp(@Param("UDF06") String udf06,@Param("page") String page,@Param("pageSize")String pageSize,@Param("start_time") String start_time, @Param("end_time") String end_time);

    @DS("dscsys")
    int qualityFromTempCount(@Param("UDF06") String udf06,@Param("start_time") String start_time, @Param("end_time") String end_time);

    @DS("dscsys")
    List<ReportVo> qualityFromTempUp(@Param("UDF06") String udf06, @Param("start_time") String start_time, @Param("end_time") String end_time,@Param("type")String type);

    @DS("dscsys")
    List<ReportVo> qualityFromTempDown(@Param("UDF06") String udf06,@Param("start_time") String start_time, @Param("end_time") String end_time,@Param("type")String type);

    @DS("dscsys")
    List<ReportVo> qualityFromTempCountByWw(@Param("UDF06") String udf06,@Param("start_time") String start_time, @Param("end_time") String end_time,@Param("type")String type);

    @DS("dscsys")
    List<ReportVo> qualityFromTempCountByBf(@Param("UDF06") String udf06,@Param("start_time") String start_time, @Param("end_time") String end_time,@Param("type")String type);

    @DS("dscsys")
    List<SfcteBo> getSonList(@Param("id") String id);

    Integer getTe003ByTe001Te002(@Param("TE001") String te001, @Param("TE002") String te002);

    @DS("dscsys")
    List<SfctaMoctaTwoVo> querySfCtaIPag2(PojoDto2 pojoDto);

    @DS("dscsys")
    List<SfcteVo> queryGd(QueryBgDto queryBgDto);

    @DS("dscsys")
    @Delete("DELETE FROM SFCTE WHERE TE001 = #{single} AND TE002 = #{odd}  AND TE003 = #{xh}")
    void deleteBg(@Param("single") String single,@Param("odd") String odd,@Param("xh") String xh);


    List<BgNumVo> queryBgCount(QueryBgNumDto queryBgNumDto);

    @Select("SELECT * FROM SFCTE WHERE TE001 = #{single} AND TE002 = #{odd}")
    SFCTE selectBg(@Param("single") String single,@Param("odd") String odd);
}
