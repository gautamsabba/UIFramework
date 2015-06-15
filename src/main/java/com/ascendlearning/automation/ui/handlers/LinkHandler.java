package com.ascendlearning.automation.ui.handlers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.logging.LogHandler;

public class LinkHandler {
	
	protected WebDriver driver = null;
	private Logger logger = LogHandler.getLogger(this.getClass());
	
	public LinkHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectLink(String selector) {
		WebElement link = driver.findElement(By.cssSelector(selector));
		link.click();
	}
	
	public void selectLinkByText(String text, boolean exactMatch) {
		WebElement link = (exactMatch == true) ? 
				driver.findElement(By.linkText(text)) :
					driver.findElement(By.partialLinkText(text));
		link.click();
	}
}
