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
package com.codescaping.automation.ui.listeners;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.codescaping.automation.ui.test.BaseTest;

public class ScreenshotListener extends TestListenerAdapter {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
    public void onTestFailure(ITestResult result) {
		 Object currentClass = result.getInstance();
	     WebDriver webDriver = ((BaseTest) currentClass).getWebDriver();
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.info("Capturing screenshot for failed method: " + result.getName());
			File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			FileOutputStream out = null;
			try {
				new File("target/screenshots/").mkdirs(); // Insure directory is there
				String fileName = new SimpleDateFormat("yyyy-MM-dd_hhmm").format(new Date());
				fileName = "target/screenshots/" + result.getName() + "_" + fileName + ".png";
				logger.info("Creating screenshot file: " + fileName);
				out = new FileOutputStream(fileName);
				FileUtils.copyFile(scrFile, out);
			} catch (IOException e) {
				logger.error("Unable to save screenshot", e);
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					logger.error("Unable to close stream to screenshot output file", e);
				}
			}
		}
    }
}
