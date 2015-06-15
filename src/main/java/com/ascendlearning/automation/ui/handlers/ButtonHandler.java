package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.exceptions.DriverException;

public class ButtonHandler extends BaseHandler {
	public ButtonHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getButton(String selector) {
		WebElement button = driver.findElement(By.cssSelector(selector));
		return button;
	}
	
	public void clickButton(WebElement button, String...waitFor) throws DriverException {
		if (button != null) {
			button.click();
			if (waitFor != null && waitFor.length>0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate button element");
		}
	}
	
	public void clickButton(String selector, String...waitFor) {
		WebElement button = driver.findElement(By.cssSelector(selector));
		if (button != null) {
			button.click();
			if (waitFor != null && waitFor.length>0) {
				setDriverWait(waitFor[0]);
			}
		}
	}
}
