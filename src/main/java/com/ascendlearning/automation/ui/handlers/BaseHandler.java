package com.ascendlearning.automation.ui.handlers;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.utils.ByCssSelectorExtended;

public class BaseHandler {
	protected WebDriver driver = null;

	public BaseHandler(WebDriver webDriver) {
		driver = webDriver;
	}

	protected void setDriverWait(String cssSelector) {
		WebDriverWait driverWait = new WebDriverWait(driver,
				GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(ByCssSelectorExtended.cssSelector(
						driver, cssSelector)));
	}

	protected void setDriverWait(WebDriver webDriver, String cssSelector) {
		WebDriverWait driverWait = new WebDriverWait(webDriver,
				GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(ByCssSelectorExtended.cssSelector(
						webDriver, cssSelector)));
	}

	protected void waitForPageToLoad(String cssSelector) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver,
				GlobalProperties.EXPLICIT_WAIT);
		wait.until(pageLoadCondition);
	}

	protected WebDriver waitForFrameToLoadAndSwitchToIt(String cssSelector) {
		WebDriverWait wait = new WebDriverWait(driver,
				GlobalProperties.EXPLICIT_WAIT);
		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(ByCssSelectorExtended
						.cssSelector(driver, cssSelector)));
		return driver;
	}

	protected WebElement findElement(String cssSelector) {
		return driver.findElement(ByCssSelectorExtended.cssSelector(driver,
				cssSelector));
	}

	protected List<WebElement> findElements(String cssSelector) {
		return driver.findElements(ByCssSelectorExtended.cssSelector(driver,
				cssSelector));
	}
}
