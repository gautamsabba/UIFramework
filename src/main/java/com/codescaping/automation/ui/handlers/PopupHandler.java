/*******************************************************************************
 * Copyright (c) 2015 Gautam Sabba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.codescaping.automation.ui.handlers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.codescaping.automation.ui.logging.LogHandler;

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
