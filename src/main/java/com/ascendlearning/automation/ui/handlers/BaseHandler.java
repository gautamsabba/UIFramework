package com.ascendlearning.automation.ui.handlers;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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

	public void setDriverWait(String cssSelector) {
		WebDriverWait driverWait = new WebDriverWait(driver,
				GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(ByCssSelectorExtended.cssSelector(
						driver, cssSelector)));
	}

	public void setDriverWait(WebDriver webDriver, String cssSelector) {
		WebDriverWait driverWait = new WebDriverWait(webDriver,
				GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(ByCssSelectorExtended.cssSelector(
						webDriver, cssSelector)));
	}

	public void waitForPageToLoad(String cssSelector) {
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

	public WebDriver waitForFrameToLoadAndSwitchToIt(String cssSelector) {
		WebDriverWait wait = new WebDriverWait(driver,
				GlobalProperties.EXPLICIT_WAIT);
		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(ByCssSelectorExtended
						.cssSelector(driver, cssSelector)));
		return driver;
	}

	public WebElement findElement(String cssSelector) {
		return driver.findElement(ByCssSelectorExtended.cssSelector(driver,
				cssSelector));
	}

	public List<WebElement> findElements(String cssSelector) {
		return driver.findElements(ByCssSelectorExtended.cssSelector(driver,
				cssSelector));
	}

	public boolean isDisplayed(WebElement we) {
		if (we == null) {
			throw new WebDriverException("WebElement is null");
		}
		return we.isDisplayed();
	}

	public boolean isEnabled(WebElement we) {
		if (we == null) {
			throw new WebDriverException("WebElement is null");
		}
		return we.isEnabled();
	}

	public boolean isSelected(WebElement we) {
		if (we == null) {
			throw new WebDriverException("WebElement is null");
		}
		return we.isSelected();
	}
}
