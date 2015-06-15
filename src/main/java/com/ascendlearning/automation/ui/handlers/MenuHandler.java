package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ascendlearning.automation.ui.exceptions.DriverException;

public class MenuHandler extends BaseHandler {

	public MenuHandler(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getMenuItem(String cssSelector) {
		WebElement we = driver.findElement(By.cssSelector(cssSelector));
		return we;
	}
	
	public void selectMenuItem(String selector, String...waitFor) throws DriverException {
		WebElement we = driver.findElement(By.cssSelector(selector));
		if(we != null) {
			we.click();
			if (waitFor != null && waitFor.length>0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate menu element");
		}
	}
	
	public void hoverOverMenuItem(String selector, String... waitFor) throws DriverException {
		WebElement we = driver.findElement(By.cssSelector(selector));
		Actions actions = new Actions(driver);
		if(we != null) {
			actions.moveToElement(we).build().perform();
			if (waitFor != null && waitFor.length>0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate menu element");
		}
	}
}
