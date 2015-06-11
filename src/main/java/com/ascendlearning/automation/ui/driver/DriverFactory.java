package com.ascendlearning.automation.ui.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.config.PropertiesRepository;
import com.ascendlearning.automation.ui.exceptions.ExceptionConstants;
import com.ascendlearning.automation.ui.logging.LogHandler;

public class DriverFactory {
	
	private Logger logger = LogHandler.getLogger(DriverFactory.class);

	private DriverFactory() {
	}
	
	private static DriverFactory instance = new DriverFactory();
 
	public static DriverFactory getInstance() {
		return instance;
	}
 
	ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {// thread local threadDriver object for webdriver
		@Override
		protected WebDriver initialValue() {
			
			String browserType = PropertiesRepository.getString("global.browser.name");
			System.out.println("BROWSER : " + browserType);
			logger.info("Browser Type : " + browserType);
			DesiredCapabilities dc = CapabilityGenerator.getCapabilities(browserType);
			logger.info("Desired Capabilities : " + dc);
			
			if(PropertiesRepository.getBoolean("global.grid.mode")) {
				try {
					URL hubURL = new URL(PropertiesRepository.getString("global.grid.hub"));					
					logger.info("Hub URL : " + hubURL);					
					return new RemoteWebDriver(hubURL, dc);
				} catch (MalformedURLException e) {
					logger.error(ExceptionConstants.PROPERTIES_EXCEPTION, e);
					return null;
				}
			} else {
				switch (browserType){			
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
 
	public WebDriver getDriver() {// call this method to get the threadDriver object and launch the browser
		return threadDriver.get();
	}
 
	public void removeDriver() {// Quits the threadDriver and closes the browser
		threadDriver.get().quit();
		threadDriver.remove();
	}
}
