package com.codescaping.automation.ui.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@SuppressWarnings("unchecked")
public class SizzleSelector {
	private JavascriptExecutor driver;
	private final Logger logger = Logger.getLogger(this.getClass());

	public SizzleSelector(WebDriver webDriver) {
		driver = (JavascriptExecutor) webDriver;
	}

	/**
	 * Find element by sizzle css.
	 * 
	 * @param context
	 * 
	 * @param cssLocator
	 *            the cssLocator
	 * @return the web element
	 */
	public WebElement findElementBySizzleCss(SearchContext context,
			String cssLocator) {
		List<WebElement> elements = findElementsBySizzleCss(context, cssLocator);
		if (elements != null && elements.size() > 0) {
			return elements.get(0);
		}
		// if we get here, we cannot find the element via Sizzle.
		throw new NoSuchElementException("selector '" + cssLocator
				+ "' cannot be found in DOM");
	}

	/**
	 * Find elements by sizzle css.
	 * 
	 * @param cssLocator
	 *            the cssLocator
	 * @return the list of the web elements that match this locator
	 */
	public List<WebElement> findElementsBySizzleCss(SearchContext context,
			String cssLocator) {
		injectSizzleIfNeeded();
		String javascriptExpression = createSizzleSelectorExpression(cssLocator);
		List<WebElement> elements = executeRemoteScript(javascriptExpression);
		if (elements.size() > 0) {
			for (WebElement el : elements) {
				fixLocator(context, cssLocator, el);
			}
		}
		return elements;
	}

	private String createSizzleSelectorExpression(String using) {
		return "return Sizzle(\"" + using + "\")";
	}

	private void fixLocator(SearchContext context, String cssLocator,
			WebElement element) {

		if (element instanceof RemoteWebElement) {
			try {
				@SuppressWarnings("rawtypes")
				Class[] parameterTypes = new Class[] { SearchContext.class,
						String.class, String.class };
				Method m = element.getClass().getDeclaredMethod("setFoundBy",
						parameterTypes);
				m.setAccessible(true);
				Object[] parameters = new Object[] { context, "css selector",
						cssLocator };
				m.invoke(element, parameters);
			} catch (Exception fail) {
				// NOOP Would like to log here?
			}
		}
	}

	private final List<WebElement> executeRemoteScript(
			String javascriptExpression) {
		List<WebElement> list = null;
		JavascriptExecutor executor = driver;

		try {
			list = (List<WebElement>) executor
					.executeScript(javascriptExpression);
		} catch (WebDriverException wde) {
			if (wde.getMessage().contains("Sizzle is not defined")) {
				logger.error(
						"Attempt to execute the code '"
								+ javascriptExpression
								+ "' has failed - Sizzle was not detected. Trying once more",
						wde);
				// we wait for 1/2 sec
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				// try to inject sizzle once more.
				injectSizzleIfNeeded();
				// now, try again to execute
				list = (List<WebElement>) executor
						.executeScript(javascriptExpression);
			} else { // not a Sizzle case, just throw it
				throw wde;
			}
		} finally {
			if (list == null) {
				list = Collections.emptyList();
			}
		}
		return list;
	}

	/**
	 * Inject sizzle if needed.
	 */
	private void injectSizzleIfNeeded() {
		if (!sizzleLoaded()) {
			injectSizzle();
		} else {
			return; // sizzle is ready
		}

		for (int i = 0; i < 40; i++) {
			if (sizzleLoaded()) {
				return; // sizzle is loaded
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// FIX: nothing to print here
			}
			if (i % 10 == 0) {
				logger.warn("Attempting to re-load SizzleCSS from local director");
				injectSizzle();
			}
		}

		// Try on last time
		if (!sizzleLoaded()) {
			logger.error("After so many tries, sizzle does not appear in DOM");
		}
		// sizzle is not loaded yet
		throw new RuntimeException(
				"Sizzle loading from local director has failed - "
						+ "provide a better sizzle URL");
	}

	/**
	 * Check if the Sizzle library is loaded.
	 * 
	 * @return the true if Sizzle is loaded in the web page
	 */
	public Boolean sizzleLoaded() {
		Boolean loaded = true;
		try {
			loaded = (Boolean) driver
					.executeScript("return (window.Sizzle != null);");

		} catch (WebDriverException e) {
			logger.error(
					"While trying to verify Sizzle loading, WebDriver threw exception",
					e);
			loaded = false;
		}
		return loaded;
	}

	public void injectSizzle() {
		String scriptContent = null;
		try {
			URL sizzleFile = Resources.getResource("sizzle.min.js");
			scriptContent = Resources.toString(sizzleFile, Charsets.UTF_8);
		} catch (IOException e) {
			logger.error("Unable to read local sizzle file", e);
		}
		String execulatbleScript = " var headID = document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';";
		if (scriptContent != null) {
			execulatbleScript = execulatbleScript + "newScript.text = "
					+ scriptContent;
		} else {
			execulatbleScript = execulatbleScript
					+ "newScript.src = 'https://raw.githubusercontent.com/jquery/sizzle/master/src/sizzle.js';";
		}
		execulatbleScript = execulatbleScript
				+ "headID.appendChild(newScript);";
		driver.executeScript(execulatbleScript);
	}
}
