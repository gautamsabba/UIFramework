package com.ascendlearning.automation.ui.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHandler {
	
	WebDriver driver = null;
	public DropDownHandler(WebDriver driver) {
		this.driver = driver;
	}
	
	public Select getDropDown(String selector) {
		Select dropDown = null;
		WebElement we = driver.findElement(By.cssSelector(selector));
		if (we != null) {
			dropDown = new Select(we);
		}
		return dropDown;
	}
	
	public void selectByVisibleText(Select dropDown, String visibleTest) {
		if (dropDown != null) {
			dropDown.selectByVisibleText(visibleTest);
		}
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
