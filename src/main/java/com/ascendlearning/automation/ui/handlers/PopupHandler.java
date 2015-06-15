package com.ascendlearning.automation.ui.handlers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.logging.LogHandler;

public class PopupHandler {
	
	protected WebDriver driver = null;
	private Logger logger = LogHandler.getLogger(this.getClass());
	  
	public PopupHandler(WebDriver webDriver) {
		driver = webDriver;
	}
    public void acceptAlert() {
    	driver.switchTo().defaultContent();
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	logger.info("Accepting alert : " + alert.getText());
    }
    
    public void dismissAlert() {
    	driver.switchTo().defaultContent();
    	Alert alert = driver.switchTo().alert();
    	alert.dismiss();
    	logger.info("Dismissing alert : " + alert.getText());
    }
    
    public String getAlertText() {
    	driver.switchTo().defaultContent();
    	Alert alert = driver.switchTo().alert();
    	return alert.getText();
    }
	
}
