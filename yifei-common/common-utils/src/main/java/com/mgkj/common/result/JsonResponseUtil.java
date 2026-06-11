package com.mgkj.common.result;

import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 *
 * @Author LBB
 * @Create: 2025-03-20 12:00
 * @Version 1.0
 */


public class JsonResponseUtil {
    public static Result handleJsonResponse(JSONObject jsonObject) {
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
