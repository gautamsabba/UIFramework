package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ascendlearning.automation.ui.exceptions.DriverException;
import com.ascendlearning.automation.ui.utils.SizzleSelector;

public class TextHandler extends BaseHandler {

	public TextHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getTextElement(String selector) {
		WebElement textElement = driver.findElement(By.cssSelector(selector));
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
		WebElement textElement = driver.findElement(By.cssSelector(selector));
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
	
	public String getText(String selector, boolean... useSizzle) {
		if (useSizzle != null && useSizzle.length > 0 && useSizzle[0]) {
			SizzleSelector sizzle = new SizzleSelector(driver);
			WebElement textElement = sizzle.findElementBySizzleCss(selector);
			return textElement.getText();
		} else {
			WebElement textElement = driver.findElement(By.cssSelector(selector));
			return (textElement != null) ? textElement.getText() : null;
		}
	}
}
