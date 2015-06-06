package com.ascendlearning.automation.ui.config;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.exceptions.ExceptionConstants;
import com.ascendlearning.automation.ui.logging.LogHandler;

public final class PropertiesRepository {
	
	private static CombinedConfiguration propAggregator = new CombinedConfiguration();
	private static Logger log = LogHandler.getLogger(PropertiesRepository.class);
	
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
	public static void appendProperties(String propertiesFile) throws DriverException{
		
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
