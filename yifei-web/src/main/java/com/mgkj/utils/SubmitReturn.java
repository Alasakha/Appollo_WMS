package com.mgkj.utils;

import com.alibaba.fastjson.JSONObject;
import com.mgkj.common.result.Result;
import com.mgkj.mapper.BmBarcodeDetailMapper;
import com.mgkj.mapper.MoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmitReturn {
    @Autowired
    private BmBarcodeDetailMapper bmBarcodeDetailMapper;
    public Result handleJsonResponse(JSONObject jsonObject) {
        try {
            // 获取执行结果的 code
            String executionCode = jsonObject
                    .getJSONObject("payload")
                    .getJSONObject("std_data")
                    .getJSONObject("execution")
                    .getString("code");

            if ("-1".equals(executionCode)) {
                return Result.fail(jsonObject).message("添加失败");
            } else if ("0".equals(executionCode)) {
                return Result.ok(jsonObject).message("添加成功");
            }
            return Result.fail().message("未知错误，请检查返回结果：" + jsonObject.toString());
        } catch (Exception e) {
            return Result.fail().message("JSON 解析失败，异常信息：" + e.getMessage());
        }
    }
}
