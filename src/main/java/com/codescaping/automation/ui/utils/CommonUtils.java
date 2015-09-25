package com.codescaping.automation.ui.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

public class CommonUtils {

	public static String replaceParameterizedString(String str, String[] replaceList) {
		String replacedString = StrSubstitutor.replace(str, getParameterMap(replaceList));
		return replacedString;
	}
	
	public static String replaceParameterizedString(String str, Map<String, String> replaceList) {
		String replacedString = StrSubstitutor.replace(str, replaceList);
		return replacedString;
	}
	
	private static Map<String, String> getParameterMap(String[] replaceList) {
		Map<String, String> map = new HashMap<String, String>();
		int i = 0;
		for (String temp : replaceList) {
			map.put("{" + i + "}", temp);
			i++;
		}
		return map;
	}
}
