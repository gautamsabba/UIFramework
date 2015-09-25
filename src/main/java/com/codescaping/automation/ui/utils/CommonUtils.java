/*******************************************************************************
 * Copyright (c) 2015 Gautam Sabba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
