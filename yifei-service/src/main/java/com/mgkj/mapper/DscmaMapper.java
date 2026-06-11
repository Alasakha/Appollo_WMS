package com.mgkj.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgkj.dto.DscmaListDto;
import com.mgkj.dto.EmployeeListDto;
import com.mgkj.dto.GetLoginUserByDeptNoDto;
import com.mgkj.entity.Dscma;
import com.mgkj.vo.GetLoginUserByDeptNoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiangPandeng
 * @since 2024-03-04
 */
@DS("DSCSYS")
@Mapper
public interface DscmaMapper extends BaseMapper<Dscma> {

    List<GetLoginUserByDeptNoVo> getLoginUserByDeptNo(GetLoginUserByDeptNoDto dto);

    List<Dscma> dscmaList(DscmaListDto dto);

    List<Dscma> queryList(DscmaListDto dto);

    List<Dscma> selUserList();

    List<Dscma> queryList2(DscmaListDto dto);

    List<Dscma> employeeList(EmployeeListDto dto);

    List<Dscma> employeeListToday(String date);

}
