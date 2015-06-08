package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MenuHandler {
	
	WebDriver driver = null;
	public MenuHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getMenuItem(String cssSelector) {
		WebElement we = driver.findElement(By.cssSelector(cssSelector));
		return we;
	}
	
	public void selectMenuItem(String selector) {
		WebElement we = driver.findElement(By.cssSelector(selector));
		we.click();
	}
	
	public void hoverOverMenuItem(String selector) {
		WebElement we = driver.findElement(By.cssSelector(selector));
		Actions actions = new Actions(driver);
		actions.moveToElement(we).build().perform();
	}
	
	public void selectByIndex(Select dropDown, int index) {
		if (dropDown != null) {
			dropDown.selectByIndex(index);
		}
	}
	
	public void selectByValue(Select dropDown, String value) {
		if (dropDown != null) {
			dropDown.selectByValue(value);
		}
	}
}
