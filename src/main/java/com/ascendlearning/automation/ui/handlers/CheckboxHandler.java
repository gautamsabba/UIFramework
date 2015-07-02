package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.exceptions.DriverException;

public class CheckboxHandler extends BaseHandler {
	public CheckboxHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getCheckbox(String selector) {
		return findElement(selector);
	}
	
	public boolean isCheckboxSelected(String selector) {
		WebElement checkbox = findElement(selector);
		return checkbox.isSelected();
	}
	
	public void selectCheckbox(WebElement checkbox, String...waitFor) throws DriverException {
		if (checkbox != null) {
			checkbox.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate checkbox element");
		}
	}
	
	public void selectCheckbox(String selector, String...waitFor) throws DriverException {
		WebElement checkbox = findElement(selector);
		if (checkbox != null) {
			checkbox.click();
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate checkbox element");
		}
	}
}
