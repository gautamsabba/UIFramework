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
package com.codescaping.automation.ui.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.codescaping.automation.ui.config.PropertiesRepository;
import com.codescaping.automation.ui.driver.DriverFactory;
import com.codescaping.automation.ui.exceptions.DriverException;

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
	public void tearDown(ITestResult result) {		
		DriverFactory.getInstance().removeDriver();
	}
	
	/**
	 * Return the driver instance for use in listeners
	 * 
	 * @return WebDriver
	 */
	public WebDriver getWebDriver() {
		return driver;
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
