package com.ascendlearning.automation.ui.config;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.logging.LogHandler;

public final class PropertiesRepository {
	
	private static CombinedConfiguration propAggregator = new CombinedConfiguration();
	
	/**
	 * Loading default properties
	 */
	static {		
		try {
			propAggregator.addConfiguration(new PropertiesConfiguration(GlobalProperties.GLOBAL_PROPS));
			propAggregator.addConfiguration(new PropertiesConfiguration(GlobalProperties.LOG_PROPS));
		} catch (ConfigurationException e) {
			LogHandler.getLogger(PropertiesRepository.class).error("Unable to load default properties", e);
		}				
	}
	
	/**
	 * Add additional properties
	 * @param properties
	 */
	public static void appendProperties(String propertiesFile) throws DriverException {
		
		PropertiesConfiguration properties;
		try {
			properties = new PropertiesConfiguration(propertiesFile);
		} catch (ConfigurationException ce) {
			throw new DriverException("Unable to load properties", ce);
		}
		
		if (properties != null) {
			propAggregator.addConfiguration(properties);
		}
	}
	
	public static void appendAllProperties(String relativePath) throws DriverException {
		File resourcesFolder = new File(relativePath);
		File[] listOfFiles = resourcesFolder.listFiles();
		if (listOfFiles != null && listOfFiles.length > 0) {
			for (File file: listOfFiles) {
				String fileName = file.getName();
				if(fileName.endsWith(".properties")) {
					appendProperties(fileName);
				}
			}
		}
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
