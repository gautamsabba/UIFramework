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
package com.codescaping.automation.ui.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.codescaping.automation.ui.config.GlobalProperties;
import com.codescaping.automation.ui.config.PropertiesRepository;
import com.codescaping.automation.ui.exceptions.ExceptionConstants;
import com.codescaping.automation.ui.logging.LogHandler;

public final class DriverFactory {
	
	private Logger logger = LogHandler.getLogger(DriverFactory.class);

	private DriverFactory() {
	}
	
	private static DriverFactory instance = new DriverFactory();
 
	public static DriverFactory getInstance() {
		return instance;
	}
 
	ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() { // thread local
																		 // threadDriver object for
																		 // webdriver
		@Override
		protected WebDriver initialValue() {
			
			String browserType = PropertiesRepository.getString("global.browser.name");
			DesiredCapabilities dc = CapabilityGenerator.getCapabilities(browserType);
			logger.info("Desired Capabilities : " + dc);
			
			if (PropertiesRepository.getBoolean("global.grid.mode")) {
				try {
					URL hubURL = new URL(PropertiesRepository.getString("global.grid.hub"));					
					logger.info("Hub URL : " + hubURL);					
					return new RemoteWebDriver(hubURL, dc);
				} catch (MalformedURLException e) {
					logger.error(ExceptionConstants.PROPERTIES_EXCEPTION, e);
					return null;
				}
			} else {
				switch (browserType) {
					case GlobalProperties.FIREFOX:
						return new FirefoxDriver(dc);
					case GlobalProperties.IE:
						return new InternetExplorerDriver(dc);
					case GlobalProperties.CHROME:
						return new ChromeDriver(dc);
					default:
						return new FirefoxDriver(dc);
				}
			}
		}
	};
 
	public WebDriver getDriver() { // call this method to get the threadDriver object and launch the
								   // browser
		return threadDriver.get();
	}
 
	public void removeDriver() { // Quits the threadDriver and closes the browser
		WebDriver driver = threadDriver.get();
		driver.close();
		driver.quit();
		threadDriver.remove();
	}
}
