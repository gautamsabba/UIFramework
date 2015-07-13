package com.ascendlearning.automation.ui.driver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.config.PropertiesRepository;

public final class CapabilityGenerator {
	public static DesiredCapabilities getCapabilities(String browserType) {
		DesiredCapabilities cap = null;
		System.out.println("BROWSER : " + browserType);
		
		switch (browserType) {
			case GlobalProperties.FIREFOX:
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.download.dir",
					PropertiesRepository.getString("global.download.location"));
			firefoxProfile.setPreference("browser.download.alertOnEXEOpen", false);
			firefoxProfile.setPreference("browser.helperApps.neverAsksaveToDisk",
					"application/x-msexcel,application/excel,application/x-excel,"
							+ "application/excel,application/x-excel,application/excel,"
							+ "application/vnd.ms-excel,application/x-excel,application/x-msexcel");
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
			firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			firefoxProfile.setPreference("browser.download.manager.closeWhenDone", false);
			firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
			firefoxProfile.setPreference("browser.download.manager.useWindow", false);
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference(
					"services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("pdfjs.disabled", true);

			cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			cap.setBrowserName(
					PropertiesRepository.getString("global.browser.capability.browserName"));
			cap.setJavascriptEnabled(true);
			cap.setCapability("platform",
					PropertiesRepository.getString("global.browser.capability.platform"));
			cap.setCapability("takesScreenshot", PropertiesRepository
					.getBoolean("global.browser.capability.firefox.takesScreenshot"));
			cap.setCapability("handlesAlerts", PropertiesRepository
					.getBoolean("global.browser.capability.firefox.handlesAlerts"));
			cap.setCapability("cssSelectorsEnabled", PropertiesRepository
					.getBoolean("global.browser.capability.firefox.cssSelectorsEnabled"));
			break;
				
			case GlobalProperties.CHROME:
			System.setProperty("webdriver.chrome.driver",
					PropertiesRepository.getString("global.browser.chrome.driver.executable"));
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName(
					PropertiesRepository.getString("global.browser.capability.browserName"));
			cap.setJavascriptEnabled(true);
			cap.setCapability("platform",
					PropertiesRepository.getString("global.browser.capability.platform"));
			cap.setCapability("takesScreenshot", PropertiesRepository
					.getBoolean("global.browser.capability.chrome.takesScreenshot"));
			cap.setCapability("handlesAlerts", PropertiesRepository
					.getBoolean("global.browser.capability.chrome.handlesAlerts"));
			cap.setCapability("cssSelectorsEnabled", PropertiesRepository
					.getBoolean("global.browser.capability.chrome.cssSelectorsEnabled"));

			ChromeOptions options = new ChromeOptions();
			options.setBinary(
					PropertiesRepository.getString("global.browser.chrome.driver.executable"));
			String extensions = PropertiesRepository
					.getString("global.browser.capability.chrome.extensions");
			if (extensions != null && !extensions.trim().equals("")) {
				StringTokenizer tokens = new StringTokenizer(extensions, ",");
				String extensionPath = PropertiesRepository
						.getString("global.browser.capability.chrome.extensionpath");
				File[] extFiles = new File[tokens.countTokens()];
				int i = 0;
				while (tokens.hasMoreElements()) {
					extFiles[i] = new File(extensionPath + "/" + tokens.nextToken());
					i++;
				}
				options.addExtensions(extFiles);
			}

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.default_directory",
					PropertiesRepository.getString("global.download.location"));

			options.setExperimentalOption("prefs", prefs);

			cap.setCapability(ChromeOptions.CAPABILITY, options);
			break;

			case GlobalProperties.IE:				
			cap = DesiredCapabilities.internetExplorer();
			cap.setBrowserName(
					PropertiesRepository.getString("global.browser.capability.browserName"));
			cap.setJavascriptEnabled(true);
			cap.setCapability("platform",
					PropertiesRepository.getString("global.browser.capability.platform"));
			cap.setCapability("takesScreenshot", PropertiesRepository
					.getBoolean("global.browser.capability.ie.takesScreenshot"));
			cap.setCapability("handlesAlerts",
					PropertiesRepository.getBoolean("global.browser.capability.ie.handlesAlerts"));
			cap.setCapability("cssSelectorsEnabled", PropertiesRepository
					.getBoolean("global.browser.capability.ie.cssSelectorsEnabled"));
			cap.setCapability("requireWindowFocus", PropertiesRepository
					.getBoolean("global.browser.capability.ie.requireWindowFocus"));
			break;
				
			default:
			FirefoxProfile defaultProfile = new FirefoxProfile();
			defaultProfile.setPreference("browser.download.folderList", 2);
			defaultProfile.setPreference("browser.download.dir",
					PropertiesRepository.getString("global.download.location"));
			defaultProfile.setPreference("browser.download.alertOnEXEOpen", false);
			defaultProfile.setPreference("browser.helperApps.neverAsksaveToDisk",
					"application/x-msexcel,application/excel,application/x-excel,"
							+ "application/excel,application/x-excel,application/excel,"
							+ "application/vnd.ms-excel,application/x-excel,application/x-msexcel");
			defaultProfile.setPreference("browser.download.manager.showWhenStarting", false);
			defaultProfile.setPreference("browser.download.manager.focusWhenStarting", false);
			defaultProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			defaultProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			defaultProfile.setPreference("browser.download.manager.closeWhenDone", false);
			defaultProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
			defaultProfile.setPreference("browser.download.manager.useWindow", false);
			defaultProfile.setPreference("browser.download.manager.showWhenStarting", false);
			defaultProfile.setPreference(
					"services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
			defaultProfile.setPreference("pdfjs.disabled", true);

			cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxDriver.PROFILE, defaultProfile);
			cap.setBrowserName(
					PropertiesRepository.getString("global.browser.capability.browserName"));
			cap.setJavascriptEnabled(true);
			cap.setCapability("platform",
					PropertiesRepository.getString("global.browser.capability.platform"));
			cap.setCapability("takesScreenshot", PropertiesRepository
					.getBoolean("global.browser.capability.firefox.takesScreenshot"));
			cap.setCapability("handlesAlerts", PropertiesRepository
					.getBoolean("global.browser.capability.firefox.handlesAlerts"));
			cap.setCapability("cssSelectorsEnabled", PropertiesRepository
					.getBoolean("global.browser.capability.firefox.cssSelectorsEnabled"));
		}		
		return cap;
	}
}
