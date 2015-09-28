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
package com.codescaping.automation.ui.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByCssSelector;

public class ByCssSelectorExtended extends ByCssSelector {

	private static final long serialVersionUID = 1L;

	private String ownSelector;
	private WebDriver driver = null;

	public ByCssSelectorExtended(WebDriver webDriver, String selector) {
		super(selector);
		ownSelector = selector;
		driver = webDriver;
	}

	public static By cssSelector(WebDriver webDriver, final String selector) {
		if (selector == null) {
			throw new IllegalArgumentException(
					"Cannot find elements when the selector is null");
		}
		return new ByCssSelectorExtended(webDriver, selector);
	}

	@Override
	public List<WebElement> findElements(SearchContext context) {
		try {
			if (context instanceof FindsByCssSelector) {
				return ((FindsByCssSelector) context)
						.findElementsByCssSelector(ownSelector);
			}

		} catch (InvalidSelectorException e) {
			return new SizzleSelector(driver).findElementsBySizzleCss(context,
					ownSelector);

		} catch (InvalidElementStateException e) {
			return new SizzleSelector(driver).findElementsBySizzleCss(context,
					ownSelector);

		} catch (WebDriverException e) {
			if (e.getMessage().contains(
					"An invalid or illegal string was specified")) {
				return new SizzleSelector(driver).findElementsBySizzleCss(
						context, ownSelector);
			}
			throw e;
		}
		throw new WebDriverException(
				"Driver does not support finding an element by selector: "
						+ ownSelector);
	}

	@Override
	public WebElement findElement(SearchContext context) {
		try {
			if (context instanceof FindsByCssSelector) {
				return ((FindsByCssSelector) context)
						.findElementByCssSelector(ownSelector);
			}
		} catch (InvalidSelectorException e) {
			return new SizzleSelector(driver).findElementBySizzleCss(context,
					ownSelector);

		} catch (InvalidElementStateException e) {
			return new SizzleSelector(driver).findElementBySizzleCss(context,
					ownSelector);

		} catch (WebDriverException e) {
			if (e.getMessage().toLowerCase()
					.contains("An invalid or illegal string was specified")) {
				return new SizzleSelector(driver).findElementBySizzleCss(
						context, ownSelector);
			}
			throw e;
		}
		throw new WebDriverException(
				"Driver does not support finding an element by selector: "
						+ ownSelector);
	}
}
