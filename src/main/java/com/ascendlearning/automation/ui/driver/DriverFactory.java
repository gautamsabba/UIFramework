package com.ascendlearning.automation.ui.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.ExceptionConstants;
import com.ascendlearning.automation.ui.logging.LogHandler;

public class DriverFactory {
	
	private Logger log = LogHandler.getLogger(DriverFactory.class);

	private DriverFactory() {
      //Do-nothing..Do not allow to initialize this class from outside
	}
	private static DriverFactory instance = new DriverFactory();
 
	public static DriverFactory getInstance()
	{
		return instance;
	}
 
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {// thread local driver object for webdriver
		@Override
		protected WebDriver initialValue() {
			if(PropertiesRepository.getBoolean("global.grid.mode")) {
				try {
					return new RemoteWebDriver(new URL(PropertiesRepository.getString("global.grid.hub")), CapabilityGenerator.getCapabilities());
				} catch (MalformedURLException e) {
					log.error(ExceptionConstants.PROPERTIES_EXCEPTION, e);
					return null;
				}
			} else {
				return new RemoteWebDriver(CapabilityGenerator.getCapabilities());
			}
		}
	};
 
	public WebDriver getDriver() {// call this method to get the driver object and launch the browser
		return driver.get();
	}
 
	public void removeDriver() {// Quits the driver and closes the browser
		driver.get().quit();
		driver.remove();
	}
}
