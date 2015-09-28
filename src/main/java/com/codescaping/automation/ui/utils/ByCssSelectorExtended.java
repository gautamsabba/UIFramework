/**
 * Copyright (c) 2013, Persado Intellectual Property Limited. All rights
 * reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * * Neither the name of the Persado Intellectual Property Limited nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * 
 */
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
