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
package com.codescaping.automation.ui.config;

public final class GlobalProperties {
	
	//Browser Constants	
	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	public static final String IE = "internet explorer";
	public static final String SAFARI = "safari";
	
	//Default wait - 10s
	public static final int EXPLICIT_WAIT = PropertiesRepository.getInt("global.driver.wait");
	
	//Default Properties Files
	public static final String PROPS_LIST = "prop-files.properties";
	public static final String GLOBAL_PROPS = "global.properties";
	public static final String LOG_PROPS = "log4j.properties";

	// Selector Types
	public static final String CSS_SELECTOR = "CSS";
	public static final String SIZZLE_SELECTOR = "SIZZLE";
}
