package com.ascendlearning.automation.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.driver.DriverFactory;

public class BaseTest {
	
	WebDriver driver = null;
	
	@BeforeMethod
	public void baseSetup() {
		driver = DriverFactory.getInstance().getDriver();
	}
		

	
	@AfterMethod
	public void baseTearDown() {
		DriverFactory.getInstance().removeDriver();
	}
	
	protected void setDriverWait(String selector) {
		WebDriverWait driverWait = new WebDriverWait(driver, GlobalProperties.EXPLICIT_WAIT);
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
	}

}
