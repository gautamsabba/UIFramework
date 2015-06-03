package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextFieldHandler {
	WebDriver driver = null;
	public TextFieldHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getTextField(String selector) {
		WebElement textField = driver.findElement(By.cssSelector(selector));
		return textField;
	}
	
	public void writeText(WebElement textField, String text) {
		if (textField != null) {
			textField.clear();
			textField.sendKeys(text);
		}
	}
	
	public void writeText(String selector, String text) {
		WebElement textField = driver.findElement(By.cssSelector(selector));
		if (textField != null) {
			textField.clear();
			textField.sendKeys(text);
		}
	}
}
