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

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.tree.OverrideCombiner;
import org.apache.log4j.LogManager;

import com.codescaping.automation.ui.exceptions.DriverException;

public final class PropertiesRepository {
	
	private static CombinedConfiguration propAggregator 
							= new CombinedConfiguration(new OverrideCombiner());
	
	/**
	 * Add additional properties
	 * 
	 * @param propertiesFile
	 * @throws DriverException
	 */
	public static void appendProperties(String propertiesFile) throws DriverException {
		
		PropertiesConfiguration properties = null;
		try {
			LogManager.getLogger(PropertiesRepository.class).info("Loading property file : " + propertiesFile);
			properties = new PropertiesConfiguration(propertiesFile);
			properties.setDelimiterParsingDisabled(true);
		} catch (ConfigurationException ce) {
			throw new DriverException("Unable to load properties", ce);
		}		
		if (properties != null) {
			propAggregator.addConfiguration(properties);
		}
	}
	
	public static void loadAllProperties() throws DriverException {
		Properties propFilesList = new Properties();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();		
		try {
			URL url = loader.getResource(GlobalProperties.PROPS_LIST);
			propFilesList.load(new FileReader(url.getPath()));
		} catch (IOException e) {
			throw new DriverException("Unable to load props-files.txt", e);
		}
		String filesList = propFilesList.getProperty("propFiles");
		LogManager.getLogger(PropertiesRepository.class).info("List of files to load : " + filesList);
		StringTokenizer tokens = new StringTokenizer(filesList, ",");
		while (tokens.hasMoreElements()) {
			String fileName = tokens.nextToken();			
			appendProperties(fileName);
		}
		//Load default properties last 
		appendProperties(GlobalProperties.GLOBAL_PROPS);
		appendProperties(GlobalProperties.LOG_PROPS);
	}
	
	public static String getString(String key) {
		return propAggregator.getString(key);
	}
	
	public static int getInt(String key) {
		return propAggregator.getInt(key);
	}
	
	public static boolean getBoolean(String key) {
		return propAggregator.getBoolean(key);
	}
}
