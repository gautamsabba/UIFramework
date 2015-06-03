package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonHandler {
	WebDriver driver = null;
	public ButtonHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getButton(String selector) {
		WebElement button = driver.findElement(By.cssSelector(selector));
		return button;
	}
	
	public void clickButton(WebElement button) {
		if (button != null) {
			button.click();
		}
	}
	
	public void clickButton(String selector) {
		WebElement button = driver.findElement(By.cssSelector(selector));
		if (button != null) {
			button.click();
		}
	}
}
