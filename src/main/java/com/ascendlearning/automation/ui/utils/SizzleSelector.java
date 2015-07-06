package com.ascendlearning.automation.ui.utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@SuppressWarnings("unchecked")
public class SizzleSelector {
	private JavascriptExecutor driver;
	private final Logger logger = Logger.getLogger(this.getClass());

	public SizzleSelector(WebDriver webDriver) {
		driver = (JavascriptExecutor) webDriver;
	}

	public WebElement findElementBySizzleCss(String using) {
		injectSizzleIfNeeded();
		String javascriptExpression = createSizzleSelectorExpression(using);
		List<WebElement> elements = (List<WebElement>) driver.executeScript(javascriptExpression);
		if (elements.size() > 0)
			return elements.get(0);
		return null;
	}

	public List<WebElement> findElementsBySizzleCss(String using) {
		injectSizzleIfNeeded();
		String javascriptExpression = createSizzleSelectorExpression(using);
		return (List<WebElement>) driver.executeScript(javascriptExpression);
	}

	private String createSizzleSelectorExpression(String using) {
		return "return Sizzle(\"" + using + "\")";
	}

	private void injectSizzleIfNeeded() {
		if (!sizzleLoaded())
			injectSizzle();
	}

	public Boolean sizzleLoaded() {
		Boolean loaded;
		try {
			loaded = (Boolean) driver.executeScript("return Sizzle()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}
		return loaded;
	}

	public void injectSizzle() {
		String scriptContent = null;
		try {
			URL sizzleFile = Resources.getResource("sizzle.min.js");
			scriptContent = Resources.toString(sizzleFile,
					Charsets.UTF_8);
		} catch (IOException e) {
			logger.error("Unable to read local sizzle file", e);
		}
		String execulatbleScript = " var headID = document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';";
		if (scriptContent != null) {
			execulatbleScript = execulatbleScript + "newScript.text = " + scriptContent;
		} else {
			execulatbleScript = execulatbleScript
					+ "newScript.src = 'https://raw.githubusercontent.com/jquery/sizzle/master/src/sizzle.js';";
		}
		execulatbleScript = execulatbleScript + "headID.appendChild(newScript);";
		driver.executeScript(execulatbleScript);
	}
}
