package com.ascendlearning.automation.ui.handlers;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.logging.LogHandler;
import com.google.common.collect.Iterables;

public class WindowHandler {

	protected WebDriver driver = null;
	private Logger logger = LogHandler.getLogger(this.getClass());
	  
	public WindowHandler(WebDriver webDriver) {
		driver = webDriver;
	}
  
    public void switchToMainWindow() {
        driver.switchTo().defaultContent();
        logger.info("Switching to main window");
    }

    public WebDriver switchToLatestWindow() {
    	driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
    	logger.info("Switching to window : " + driver.getWindowHandle());
    	return driver;
    }
    
    public WebDriver switchToWindow(String name) {
    	driver.switchTo().window(name);
    	logger.info("Switching to window : " + driver.getWindowHandle());
    	return driver;
    }
    
    public Iterator<String> getWindowNames() {
    	return driver.getWindowHandles().iterator();
    }
}
