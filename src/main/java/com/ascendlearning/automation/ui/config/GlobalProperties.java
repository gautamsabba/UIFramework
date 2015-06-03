package com.ascendlearning.automation.ui.config;

public final class GlobalProperties {
	
	//Browser Constants	
	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	public static final String IE = "internet explorer";
	public static final String SAFARI = "safari";
	
	//Default wait - 10s
	public static final int EXPLICIT_WAIT=PropertiesRepository.getInt("global.driver.wait");
}
