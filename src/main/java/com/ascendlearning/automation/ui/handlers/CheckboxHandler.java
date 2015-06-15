package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.exceptions.DriverException;

public class CheckboxHandler extends BaseHandler {
	public CheckboxHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getCheckbox(String selector) {
		WebElement checkbox = driver.findElement(By.cssSelector(selector));
		return checkbox;
	}
	
	public boolean isCheckboxSelected(String selector) {
		WebElement checkbox = driver.findElement(By.cssSelector(selector));
		return checkbox.isSelected();
	}
	
	public void selectCheckbox(WebElement checkbox, String...waitFor) throws DriverException {
		if (checkbox != null) {
			checkbox.click();
			if (waitFor != null && waitFor.length>0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate checkbox element");
		}
	}
	
	public void selectCheckbox(String selector, String...waitFor) throws DriverException {
		WebElement checkbox = driver.findElement(By.cssSelector(selector));
		if (checkbox != null) {
			checkbox.click();
			if (waitFor != null && waitFor.length>0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate checkbox element");
		}
	}
}
