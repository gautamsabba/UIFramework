package com.ascendlearning.automation.ui.handlers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.ascendlearning.automation.ui.logging.LogHandler;

public class PopupHandler extends BaseHandler {
	  
	public PopupHandler(WebDriver driver) {
		super(driver);
	}
    public void acceptAlert(String...waitFor) {
    	driver.switchTo().defaultContent();
    	Alert alert = driver.switchTo().alert();
    	alert.accept();
    	if (waitFor != null && waitFor.length>0) {
			setDriverWait(waitFor[0]);
		}
    }
    
    public void dismissAlert(String...waitFor) {
    	driver.switchTo().defaultContent();
    	Alert alert = driver.switchTo().alert();
    	alert.dismiss();
    	if (waitFor != null && waitFor.length>0) {
			setDriverWait(waitFor[0]);
		}
    }
    
    public String getAlertText() {
    	driver.switchTo().defaultContent();
    	Alert alert = driver.switchTo().alert();
    	return alert.getText();
    }
	
}
