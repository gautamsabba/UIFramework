package com.automation.ui.handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.ui.exceptions.DriverException;

public class TextHandler extends BaseHandler {

	public TextHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getTextElement(String selector) {
		WebElement textElement = findElement(selector);
		return textElement;
	}
	
	public void writeText(WebElement textElement, String text, String...waitFor) throws DriverException {
		if (textElement != null) {
			textElement.click();
			textElement.clear();
			textElement.click();
			textElement.sendKeys(text);
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate text element");
		}
	}
	
	public void writeText(String selector, String text, String...waitFor) throws DriverException {
		WebElement textElement = findElement(selector);
		if (textElement != null) {
			textElement.click();
			textElement.clear();
			textElement.click();
			textElement.sendKeys(text);
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate text element");
		}
	}
	
	public String getText(String selector) throws DriverException {
		WebElement textElement = findElement(selector);
		if (textElement != null) {
			return textElement.getText();
		} else {
			throw new DriverException("Unable to locate text element");
		}
	}
	
	public String getTextFromValueAttr(String selector) throws DriverException {
		WebElement textElement = findElement(selector);
		if (textElement != null) {
			return textElement.getAttribute("value");
		} else {
			throw new DriverException("Unable to locate text element");
		}
	}
}
