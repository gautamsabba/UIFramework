package com.codescaping.automation.ui.handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.codescaping.automation.ui.exceptions.DriverException;

public class MenuHandler extends BaseHandler {

	public MenuHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getMenuItem(String cssSelector) throws DriverException {
		return findElement(cssSelector);
	}
	
	public void selectMenuItem(String cssSelector, String... waitFor) throws DriverException {
		WebElement we = findElement(cssSelector);
		if (we != null) {
			we.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate menu element");
		}
	}
	
	public void hoverOverMenuItem(String cssSelector, String... waitFor) throws DriverException {
		WebElement we = findElement(cssSelector);
		Actions actions = new Actions(driver);
		if (we != null) {
			actions.moveToElement(we).build().perform();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate menu element");
		}
	}
}
