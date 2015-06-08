package com.ascendlearning.automation.ui.assertions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class VerificationHandler {
	
	private static Logger logger = LogManager.getLogger(VerificationHandler.class);
	
	public static void verifyTrue(boolean flag) {
		try {
			Assert.assertTrue(flag);
		} catch (AssertionError e) {
			logger.error("False returned", e);
		}
	}
	
	public static void verifyFalse(boolean flag) {
		try {
			Assert.assertFalse(flag);
		} catch (AssertionError e) {
			logger.error("True returned", e);
		}
	}
	
	public static void verifyEquals(String actual, String control) {
		try {
			Assert.assertEquals(actual,control);
		} catch (AssertionError e) {
			logger.error("Value mismatch", e);
		}
	}
	
	public static void verifyNotEquals(String actual, String control) {
		try {
			Assert.assertNotEquals(actual,control);
		} catch (AssertionError e) {
			logger.error("Values match", e);
		}
	}
}
