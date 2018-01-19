package com.kkyeer.java.utils;

/**
 * @author kkyeer@gamil.com
 * @date 2018/1/18 14:26
 */
public class StringUtil {
    public static boolean nullOrEmpty(String inputString) {
        return inputString == null || "".equals(inputString.trim());
    }
}
