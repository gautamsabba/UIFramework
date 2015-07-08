package com.ascendlearning.automation.ui.handlers;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.logging.LogHandler;
import com.google.common.collect.Iterables;

public class WindowHandler extends BaseHandler {

	private Logger logger = LogHandler.getLogger(this.getClass());

	WebDriver currentDriver = null;
	  
	public WindowHandler(WebDriver driver) {
		super(driver);
		currentDriver = driver;
	}
  
    public WebDriver switchToMainWindow(String...waitFor) {
		currentDriver = driver.switchTo().defaultContent();
        logger.info("Switching to main window");
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
        return driver;
    }

    public WebDriver switchToLatestWindow(String...waitFor) {
		currentDriver = driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
		logger.info("Switching to window : " + driver.getTitle());
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(driver, waitFor[0]);
		}
    	return driver;
    }
    
    public WebDriver switchToWindow(String name, String...waitFor) {
		currentDriver = driver.switchTo().window(name);
		logger.info("Switching to window : " + driver.getTitle());
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(driver, waitFor[0]);
		}
    	return driver;
    }
    
	public WebElement switchToModalDialog(String... waitFor) {
		WebElement activeElement = driver.switchTo().activeElement();
		logger.info("Switching to active element : " + activeElement.getText());
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(driver, waitFor[0]);
		}
		return activeElement;
	}

	public void cleanUp(WebDriver webDriver) {
		Iterator<String> handles = webDriver.getWindowHandles().iterator();
		while (handles.hasNext()) {
			String handle = handles.next();
			WebDriver local = switchToWindow(handle);
			local.close();
		}
    }

	public WebDriver getCurrentWindow() {
		return currentDriver;
	}
}
