package com.mgkj.service;


import com.mgkj.dto.StaffDetailDto;
import com.mgkj.dto.StaffDto;
import com.mgkj.vo.StaffDetailVo;
import com.mgkj.vo.StaffVo;
import com.mgkj.vo.StraffGroupVo;

import java.util.List;

/**
 * @author yyyjcg
 * @date 2023/12/27
 * @Description
 */
public interface StaffService {
    List<StaffDto> getAllStaffInfo(StaffVo staffVo);

    List<StaffDto> getGroupStaffInfo(StraffGroupVo straffGroupVo);

    List<StaffDetailDto> getStaffDetailInfo(StaffDetailVo staffDetailVo);
}
