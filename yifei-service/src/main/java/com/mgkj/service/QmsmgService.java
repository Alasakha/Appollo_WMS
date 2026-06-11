package com.mgkj.service;

import com.mgkj.common.result.Result;
import com.mgkj.dto.JiayanDto;
import com.mgkj.dto.PojoDto;
import com.mgkj.entity.Qmsmg;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface QmsmgService {

    Result<Map<String, Qmsmg>> queryPage(PojoDto pojoDto);

    Result insert(List<Qmsmg> qmsmgList);

    Result update(Qmsmg qmsmg);

    Result delete(String MG002,String MG003);

    Result deleteList(List<JiayanDto> list);

    Result<Qmsmg> queryXq(String MG002,String MG003);

//    Result getExcels(MultipartFile file, HttpServletResponse response) throws IOException;

    Result getExcels2(MultipartFile file, HttpServletResponse response) throws IOException;
}
