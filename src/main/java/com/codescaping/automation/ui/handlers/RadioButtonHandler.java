package com.codescaping.automation.ui.handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codescaping.automation.ui.exceptions.DriverException;

public class RadioButtonHandler extends BaseHandler {

	public RadioButtonHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getRadioButton(String selector) {
		return findElement(selector);
	}
	
	public void selectRadioButton(WebElement radioButton, String...waitFor) throws DriverException {
		if (radioButton != null) {
			radioButton.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate radio button element");
		}
	}
	
	public void selectRadioButton(String selector, String...waitFor) throws DriverException {
		WebElement radioButton = findElement(selector);
		if (radioButton != null) {
			radioButton.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate radio button element");
		}
	}
}
