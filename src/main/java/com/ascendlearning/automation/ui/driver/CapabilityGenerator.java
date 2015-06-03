package com.ascendlearning.automation.ui.driver;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.config.PropertiesRepository;

public final class CapabilityGenerator {
	public static DesiredCapabilities getCapabilities() {
		DesiredCapabilities cap = null;
		String browserType = PropertiesRepository.getString("global.browser.name");
		
		switch (browserType){			
			case GlobalProperties.FIREFOX:
				cap = DesiredCapabilities.firefox();
				cap.setCapability("browserName", PropertiesRepository.getString("global.browser.capability.firefox.browserName"));
				cap.setCapability("platform", PropertiesRepository.getString("global.browser.capability.firefox.platform"));
				cap.setCapability("takesScreenshot", PropertiesRepository.getBoolean("global.browser.capability.firefox.takesScreenshot"));
				cap.setCapability("handlesAlerts", PropertiesRepository.getBoolean("global.browser.capability.firefox.handlesAlerts"));
				cap.setCapability("cssSelectorsEnabled", PropertiesRepository.getBoolean("global.browser.capability.firefox.cssSelectorsEnabled"));
				break;
				
			default:
				cap = DesiredCapabilities.firefox();
				cap.setCapability("browserName", PropertiesRepository.getString("global.browser.capability.firefox.browserName"));
				cap.setCapability("platform", PropertiesRepository.getString("global.browser.capability.firefox.platform"));
				cap.setCapability("takesScreenshot", PropertiesRepository.getBoolean("global.browser.capability.firefox.takesScreenshot"));
				cap.setCapability("handlesAlerts", PropertiesRepository.getBoolean("global.browser.capability.firefox.handlesAlerts"));
				cap.setCapability("cssSelectorsEnabled", PropertiesRepository.getBoolean("global.browser.capability.firefox.cssSelectorsEnabled"));		
		}		
		
		return cap;
	}

}
