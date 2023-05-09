package com.qraccess.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qraccess.controllers.PublicController;

public class Log {
	public static void error(String text) {
		System.err.print(text);
	}
	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);
	
	public static void info(String text) {
		logger.info("INFO: {}", text);
		//System.out.println("INFO: " + text);
	}
}
