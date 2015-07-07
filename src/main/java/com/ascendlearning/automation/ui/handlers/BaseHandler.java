package com.ascendlearning.automation.ui.handlers;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.utils.ByCssSelectorExtended;

public class BaseHandler {
	protected WebDriver driver = null;
	public BaseHandler(WebDriver webDriver) {
		driver = webDriver;		
	}
	
	protected void setDriverWait(String selector) {
		WebDriverWait driverWait = new WebDriverWait(driver, GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(ByCssSelectorExtended.cssSelector(selector)));
	}

	protected WebElement findElement(String cssSelector) {
		return driver.findElement(ByCssSelectorExtended.cssSelector(driver, cssSelector));
	}

	protected List<WebElement> findElements(String cssSelector) {
		return driver.findElements(ByCssSelectorExtended.cssSelector(driver, cssSelector));
	}
}
