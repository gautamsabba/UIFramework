package com.ascendlearning.automation.ui.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.driver.DriverFactory;
import com.ascendlearning.automation.ui.exceptions.DriverException;

public class BaseTest {
	protected WebDriver driver = null;	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	static {
		try {
			PropertiesRepository.loadAllProperties();
		} catch (DriverException e) {
			LogManager.getLogger(BaseTest.class).error("Unable to load properties files", e);
		}
	}
	
	@BeforeMethod
	public void setup() {		
		driver = DriverFactory.getInstance().getDriver();
		manageDriver();
	}
			
	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().removeDriver();
	}
	
	/**
	 * Load the properties file from the classpath
	 * 
	 * e.g. loadProperties("jblearning.properties");
	 * @param propFile
	 * @throws DriverException 
	 */
	protected void loadProperties(String propFile) {
		try {
			PropertiesRepository.appendProperties(propFile);
		} catch (DriverException e) {
			logger.error("Unable to load properties file : " + propFile, e);
		}
	}
	
	protected void loadAllProperties() {
		try {
			PropertiesRepository.loadAllProperties();
		} catch (DriverException e) {
			e.printStackTrace();
			logger.error("Unable to load properties files", e);
		}
	}
	
	protected void manageDriver() {
		if (driver != null) {
			driver.manage().window().maximize();
		}
	}
}
