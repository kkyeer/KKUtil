package com.kkyeer.java.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kk
 */
public class AddressUtil {
	/**
	 * @Author: kkyeer 
	 * @Date: 2018/1/19 17:33
	 * @param null
	 * @Description:
	 *
	 */
	public static Map<String, String> getDevidedEle(String address) {
		String provNameString = "";
		String cityNameString = "";
		String distNameString = "";
		Map<String, String> retMap = new HashMap<>();
		
		// 定义省级的Regex
		String provRegex = ".*自治区|.*省|.[^市 ]*市{1}";
		Pattern pattern = Pattern.compile(provRegex);
		Matcher matcher = pattern.matcher(address);
		if (matcher.find()) {
			provNameString = matcher.group();
			String wholeAfter = address.substring(provNameString.length());

			String cityRegex = ".*盟|.*旗|.*自治州|.*地区|.[^市 ]*市|.*县|.*区";
			Pattern patternCity = Pattern.compile(cityRegex);
			Matcher matcherCity = patternCity.matcher(wholeAfter);
			if (matcherCity.find()) {
				cityNameString = matcherCity.group();
				wholeAfter = wholeAfter.substring(cityNameString.length());

				String distRegex = ".*县|.*区|.*市|.*旗";
				Pattern patternDist = Pattern.compile(distRegex);
				Matcher matcherDist = patternDist.matcher(wholeAfter);
				if (matcherDist.find()) {
					distNameString = matcherDist.group();
				}
			}
		}		
		retMap.put("prov", provNameString);
		retMap.put("city", cityNameString);
		retMap.put("dist", distNameString);
		return retMap;
	}

}
