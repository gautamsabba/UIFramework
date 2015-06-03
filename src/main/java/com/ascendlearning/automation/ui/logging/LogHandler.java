package com.ascendlearning.automation.ui.logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogHandler {
	
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clazz) {
		return LogManager.getLogger(clazz);
	}
	
	
}
