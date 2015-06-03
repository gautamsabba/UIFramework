package com.ascendlearning.automation.ui.config;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.ascendlearning.automation.ui.exceptions.ExceptionConstants;
import com.ascendlearning.automation.ui.logging.LogHandler;

public final class PropertiesRepository {
	
	private static CombinedConfiguration propAggregator = new CombinedConfiguration();
	private static Logger log = LogHandler.getLogger(PropertiesRepository.class);
	
	/**
	 * Loading all the properties files in the classpath
	 */
	static {		
		try {
			String path = PropertiesRepository.class.getResource("").getPath();
			File[] propertiesFiles = new File(path).listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.endsWith(".properties");
			    }
			});
			for (File propFile:propertiesFiles) {
				PropertiesConfiguration properties = new PropertiesConfiguration(propFile);
				propAggregator.addConfiguration(properties);
			}
		} catch (ConfigurationException e) {
			log.error(ExceptionConstants.PROPERTIES_EXCEPTION, e);
		}	
	}
	
	/**
	 * Add additional properties
	 * @param properties
	 */
	public static void appendProperties(CombinedConfiguration properties) {
		propAggregator.addConfiguration(properties);
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
