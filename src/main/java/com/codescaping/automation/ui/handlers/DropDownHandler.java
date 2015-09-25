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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.codescaping.automation.ui.exceptions.DriverException;

public class DropDownHandler extends BaseHandler {
	
	public DropDownHandler(WebDriver driver) {
		super(driver);
	}
	
	public Select getDropDown(String selector) throws DriverException {
		Select dropDown = null;
		WebElement we = findElement(selector);
		if (we != null) {
			dropDown = new Select(we);
		} else {
			throw new DriverException("Unable to locate dropdown element");
		}
		return dropDown;
	}
	
	public void selectByVisibleText(Select dropDown, String visibleTest, String...waitFor) throws DriverException {
		if (dropDown != null) {
			dropDown.selectByVisibleText(visibleTest);
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate dropdown element");
		}
	}
	
	public void selectByIndex(Select dropDown, int index, String...waitFor) throws DriverException {
		if (dropDown != null) {
			dropDown.selectByIndex(index);
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate dropdown element");
		}
	}
	
	public void selectByValue(Select dropDown, String value, String...waitFor) throws DriverException {
		if (dropDown != null) {
			dropDown.selectByValue(value);
			if (waitFor != null && waitFor.length > 0) {
				setDriverWait(waitFor[0]);
			}
		} else {
			throw new DriverException("Unable to locate dropdown element");
		}
	}
}
