package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextHandler {
	WebDriver driver = null;
	public TextHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getTextelement(String selector) {
		WebElement textElement = driver.findElement(By.cssSelector(selector));
		return textElement;
	}
	
	public void writeText(WebElement textElement, String text) {
		if (textElement != null) {
			textElement.click();
			textElement.clear();
			textElement.click();
			textElement.sendKeys(text);
		}
	}
	
	public void writeText(String selector, String text) {
		WebElement textElement = driver.findElement(By.cssSelector(selector));
		if (textElement != null) {
			textElement.click();
			textElement.clear();
			textElement.click();
			textElement.sendKeys(text);
		}
	}
	
	public String getText(String selector) {
		WebElement textElement = driver.findElement(By.cssSelector(selector));
		return (textElement != null) ? textElement.getText() : null;
	}
	
}
