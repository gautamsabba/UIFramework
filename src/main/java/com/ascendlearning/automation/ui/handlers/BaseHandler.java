package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.utils.SizzleSelector;

public class BaseHandler {
	protected WebDriver driver = null;
	protected String selectorType = GlobalProperties.CSS_SELECTOR;
	public BaseHandler(WebDriver webDriver) {
		driver = webDriver;		
	}
	
	public void setCSSSelector() {
		selectorType = GlobalProperties.CSS_SELECTOR;
	}

	public void setSizzleSelector() {
		selectorType = GlobalProperties.SIZZLE_SELECTOR;
	}

	protected void setDriverWait(String selector) {
		WebDriverWait driverWait = new WebDriverWait(driver, GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
	}

	protected WebElement findElement(String cssSelector) {
		WebElement we = null;
		if (selectorType.equals(GlobalProperties.SIZZLE_SELECTOR)) {
			SizzleSelector sizzle = new SizzleSelector(driver);
			we = sizzle.findElementBySizzleCss(cssSelector);
		} else {
			we = driver.findElement(By.cssSelector(cssSelector));
		}
		return we;
	}
}
