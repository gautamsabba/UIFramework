package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButtonHandler {
	WebDriver driver = null;
	public RadioButtonHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getRadioButton(String selector) {
		WebElement radioButton = driver.findElement(By.cssSelector(selector));
		return radioButton;
	}
	
	public void selectRadioButton(WebElement radioButton) {
		if (radioButton != null) {
			radioButton.click();
		}
	}
	
	public void selectRadioButton(String selector) {
		WebElement radioButton = driver.findElement(By.cssSelector(selector));
		if (radioButton != null) {
			radioButton.click();
		}
	}
}
