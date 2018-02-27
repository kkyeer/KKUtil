package com.kkyeer.java.utils;

/**
 * @author kkyeer@gamil.com
 * @date 2018/1/18 14:26
 */
public final class StringUtil {

    /**
     * 防止实例化
     */
    private StringUtil(){

    }

    /**
     * @Description: 检测字符串是否为空或者null
     * @Author: kkyeer
     * @Date: 2018/1/20 0:48
     * @param inputString 待检测的字符串
     * @return 是否为空或者Null，为空或null 返回true，反之返回false
     */
    public static boolean nullOrEmpty(String inputString) {
        return inputString == null || "".equals(inputString.trim());
    }
}
