package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.entity.CMSMV;
import com.mgkj.entity.Employee;
import com.mgkj.entity.EmployPassword;
import com.mgkj.vo.CMSMVVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/12/18/11:15
 * @Description: 员工信息相关 Mapper
 */
@Mapper
public interface CMSMVMapper extends BaseMapper<CMSMV> {

    /**
     *  根据姓名和编号模糊查询和部门编号
     * @return
     */
    @DS("dscsys")
    List<CMSMV> getAll(String MV001,String MV002);

    CMSMVVo selectByUserId(String userId);

    @Update("UPDATE [USER] SET UDF026 = #{UDF01} WHERE LOGONNAME = #{MV001}")
    void updateByUserId(CMSMVVo cmsmv);

    List<Employee> getEmployee(String employeeName);

    @Update("UPDATE [USER] SET UDF026 = '123' WHERE LOGONNAME = #{userId}")
    void passwordInit(String userId);

    @Select("SELECT * FROM employPassword WHERE MV001 = #{userId}")
    EmployPassword selectByUserNo(String userId);

    @Insert("INSERT INTO employPassword (ID, MV001, MV002, UDF01)\n" +
            "        VALUES (#{ID}, #{MV001}, #{MV002}, #{UDF01})")
    void insertInto(EmployPassword e);
}
