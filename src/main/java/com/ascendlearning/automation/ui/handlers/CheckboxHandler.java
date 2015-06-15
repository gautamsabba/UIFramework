package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxHandler {
	WebDriver driver = null;
	public CheckboxHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getCheckbox(String selector) {
		WebElement checkbox = driver.findElement(By.cssSelector(selector));
		return checkbox;
	}
	
	public boolean isCheckboxSelected(String selector) {
		WebElement checkbox = driver.findElement(By.cssSelector(selector));
		return checkbox.isSelected();
	}
	
	public void selectCheckbox(WebElement checkbox) {
		if (checkbox != null) {
			checkbox.click();
		}
	}
	
	public void clickButton(String selector) {
		WebElement checkbox = driver.findElement(By.cssSelector(selector));
		if (checkbox != null) {
			checkbox.click();
		}
	}
}
