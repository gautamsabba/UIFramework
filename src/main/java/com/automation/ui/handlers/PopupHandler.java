package com.automation.ui.handlers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.automation.ui.logging.LogHandler;

public class PopupHandler extends BaseHandler {

	private Logger logger = LogHandler.getLogger(this.getClass());

	public PopupHandler(WebDriver driver) {
		super(driver);
	}

	public void acceptAlert(String... waitFor) {
		driver.switchTo().defaultContent();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
	}

	public void dismissAlert(String... waitFor) {
		driver.switchTo().defaultContent();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
	}

	public Alert switchToAlert(String... waitFor) {
		driver.switchTo().defaultContent();
		Alert alert = driver.switchTo().alert();
		logger.info("Switching to alert");
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
		return alert;
	}

	public String getAlertText(String... waitFor) {
		driver.switchTo().defaultContent();
		Alert alert = switchToAlert(waitFor);
		logger.info("Switching to alert");
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
		return alert.getText();
	}

	public void loginWithoutPopup(String urlWithoutHTTP, String userName,
			String password, String... waitFor) {
		String newURL = "http://" + userName + ":" + password + "@"
				+ urlWithoutHTTP;
		driver.get(newURL);
		if (waitFor != null && waitFor.length > 0) {
			setDriverWait(waitFor[0]);
		}
	}

}
