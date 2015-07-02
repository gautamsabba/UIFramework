package com.ascendlearning.automation.ui.handlers;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.logging.LogHandler;
import com.google.common.collect.Iterables;

public class WindowHandler extends BaseHandler {

	private Logger logger = LogHandler.getLogger(this.getClass());
	  
	public WindowHandler(WebDriver driver) {
		super(driver);
	}
  
    public WebDriver switchToMainWindow(String...waitFor) {
        driver.switchTo().defaultContent();
        logger.info("Switching to main window");
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
        return driver;
    }

    public WebDriver switchToLatestWindow(String...waitFor) {
    	driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
    	logger.info("Switching to window : " + driver.getWindowHandle());
    	return driver;
    }
    
    public WebDriver switchToWindow(String name, String...waitFor) {
    	driver.switchTo().window(name);
    	logger.info("Switching to window : " + driver.getWindowHandle());
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
    	return driver;
    }
    
	public WebElement switchToModalDialog(String... waitFor) {
		WebElement activeElement = driver.switchTo().activeElement();
		logger.info("Switching to active element : " + activeElement.getText());
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
		return activeElement;
	}

    public Iterator<String> getWindowNames() {
    	return driver.getWindowHandles().iterator();
    }
}
