package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.exceptions.DriverException;

public class LinkHandler extends BaseHandler {

	public LinkHandler(WebDriver driver) {
		super(driver);
	}
	
	public void selectLink(String selector, String...waitFor) throws DriverException {
		WebElement link = findElement(selector);
		if (link != null) {
			link.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate link element");
		}
	}
	
	public WindowHandler selectLinkToLaunchNewWindow(String selector, String... waitFor)
			throws DriverException {
		WebElement link = findElement(selector);
		WindowHandler windowHandler = null;
		if (link != null) {
			link.click();
			windowHandler = new WindowHandler(driver);
			windowHandler.switchToLatestWindow(waitFor);
		} else {
			throw new DriverException("Unable to locate link element");
		}
		return windowHandler;
	}

	public void selectLinkByText(String text, boolean exactMatch, String...waitFor) throws DriverException {
		WebElement link = (exactMatch == true) ? driver.findElement(By.linkText(text))
				: driver.findElement(By.partialLinkText(text));
		if (link != null) {
			link.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate link element");
		}
	}
}
