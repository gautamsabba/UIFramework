package com.ascendlearning.automation.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.ascendlearning.automation.ui.config.GlobalProperties;
import com.ascendlearning.automation.ui.driver.DriverFactory;

public class BaseTest {
	
	protected WebDriver driver = null;
	
	@BeforeMethod
	public void setup() {
		driver = DriverFactory.getInstance().getDriver();
	}
			
	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().removeDriver();
	}

}
