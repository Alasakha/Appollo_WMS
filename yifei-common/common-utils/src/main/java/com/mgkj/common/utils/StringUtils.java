package com.mgkj.common.utils;

public class StringUtils {

    public static boolean isEmpty(String str) {
        if(null == str || str.trim().equals("")) return true;
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
