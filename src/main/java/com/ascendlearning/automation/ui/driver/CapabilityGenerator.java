package com.ascendlearning.automation.ui.driver;

import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.config.PropertiesRepository;

public final class CapabilityGenerator {
	public static DesiredCapabilities getCapabilities(String browserType) {
		DesiredCapabilities cap = null;
		System.out.println("BROWSER : " + browserType);
		
		switch (browserType){			
			case GlobalProperties.FIREFOX:
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName(PropertiesRepository.getString("global.browser.capability.browserName"));
				cap.setCapability("platform", PropertiesRepository.getString("global.browser.capability.platform"));
				cap.setCapability("takesScreenshot", PropertiesRepository.getBoolean("global.browser.capability.firefox.takesScreenshot"));
				cap.setCapability("handlesAlerts", PropertiesRepository.getBoolean("global.browser.capability.firefox.handlesAlerts"));
				cap.setCapability("cssSelectorsEnabled", PropertiesRepository.getBoolean("global.browser.capability.firefox.cssSelectorsEnabled"));
				break;
				
			case GlobalProperties.CHROME:
				System.setProperty("webdriver.chrome.driver", PropertiesRepository.getString("global.browser.chrome.driver.executable"));
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName(PropertiesRepository.getString("global.browser.capability.browserName"));
				cap.setCapability("platform", PropertiesRepository.getString("global.browser.capability.platform"));
				cap.setCapability("takesScreenshot", PropertiesRepository.getBoolean("global.browser.capability.chrome.takesScreenshot"));
				cap.setCapability("handlesAlerts", PropertiesRepository.getBoolean("global.browser.capability.chrome.handlesAlerts"));
				cap.setCapability("cssSelectorsEnabled", PropertiesRepository.getBoolean("global.browser.capability.chrome.cssSelectorsEnabled"));
				ChromeOptions options = new ChromeOptions();
				String extensions = PropertiesRepository.getString("global.browser.capability.chrome.extensions");
				if(extensions != null && !extensions.trim().equals("")) {
					StringTokenizer tokens = new StringTokenizer(extensions, ",");
					String extensionPath = PropertiesRepository.getString("global.browser.capability.chrome.extensionpath");
					while(tokens.hasMoreElements()) {
						File extFile = new File(extensionPath+"/"+tokens.nextToken());
						options.addExtensions(new File(PropertiesRepository.getString("global.browser.capability.chrome.extensionpath")));
					}					
				}				
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				break;
				
			case GlobalProperties.IE:				
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName(PropertiesRepository.getString("global.browser.capability.browserName"));
				cap.setCapability("platform", PropertiesRepository.getString("global.browser.capability.platform"));
				cap.setCapability("takesScreenshot", PropertiesRepository.getBoolean("global.browser.capability.ie.takesScreenshot"));
				cap.setCapability("handlesAlerts", PropertiesRepository.getBoolean("global.browser.capability.ie.handlesAlerts"));
				cap.setCapability("cssSelectorsEnabled", PropertiesRepository.getBoolean("global.browser.capability.ie.cssSelectorsEnabled"));
				cap.setCapability("requireWindowFocus", PropertiesRepository.getBoolean("global.browser.capability.ie.requireWindowFocus"));
				break;
				
			default:
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName(PropertiesRepository.getString("global.browser.capability.browserName"));
				cap.setCapability("platform", PropertiesRepository.getString("global.browser.capability.platform"));
				cap.setCapability("takesScreenshot", PropertiesRepository.getBoolean("global.browser.capability.firefox.takesScreenshot"));
				cap.setCapability("handlesAlerts", PropertiesRepository.getBoolean("global.browser.capability.firefox.handlesAlerts"));
				cap.setCapability("cssSelectorsEnabled", PropertiesRepository.getBoolean("global.browser.capability.firefox.cssSelectorsEnabled"));		
		}		
		return cap;
	}
}
