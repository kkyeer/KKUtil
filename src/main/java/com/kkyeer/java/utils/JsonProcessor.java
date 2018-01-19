package com.kkyeer.java.utils;

public class JsonProcessor {
	public static String toJSONString(String string) {
		return string.replaceAll("\"", "\\\\\"");
	}
	
}
